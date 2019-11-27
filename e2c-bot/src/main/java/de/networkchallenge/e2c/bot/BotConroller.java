package de.networkchallenge.e2c.bot;

import com.google.gson.Gson;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import de.networkchallenge.e2c.lambda.backend.handler.ResponseBuilder;
import de.networkchallenge.e2c.lambda.backend.model.ResponseEvent;
import de.networkchallenge.e2c.lambda.backend.model.ResponseObject;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.MalformedURLException;
import java.net.URL;

@Path("/bot")
public class BotConroller {
    TelegramBot bot;

    public BotConroller()
    {
        if (System.getenv("BOT_TOKEN") != null &&  System.getenv("ENDPOINT_URL") != null)
        {
            bot = new TelegramBot(System.getenv("BOT_TOKEN"));
        //gson = new Gson();
        SetWebhook request = new SetWebhook()
                .url(System.getenv("ENDPOINT_URL"));
        BaseResponse response = bot.execute(request);
        boolean ok = response.isOk();
        }
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String webhook(Update update) {
        System.out.println("MessageID: " + update.getMessage().getMessage_id() + ", ChatID: " + update.getMessage().getChat().getId() + ", Text: " + update.getMessage().getText());
        ResponseObject eventRespone;
        try {
            eventRespone = new ResponseBuilder(ParserRegistry.getInstance(), new URL(update.getMessage().getText())).build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new SendMessage(update.getMessage().getChat().getId(), "sorry, unparsable URL").toWebhookResponse();
        }
        if (eventRespone.getEvents().isEmpty())
            return new SendMessage(update.getMessage().getChat().getId(), eventRespone.getStatus().toString()).toWebhookResponse();
        else
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder  = stringBuilder.append("Folgende Ereignisse habe ich gefunden:\n");
            for(ResponseEvent e : eventRespone.getEvents())
            {
                stringBuilder.append(e.getEventTitle() + " am " + e.getEventBegin() + "\n");
            }
            String text = stringBuilder.toString();
            String response = new SendMessage(update.getMessage().getChat().getId(), text).toWebhookResponse();
            System.out.println(response);
            if (bot != null)
                bot.execute(new SendMessage(update.getMessage().getChat().getId(), text));
            return response;
        }
        /*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder  = stringBuilder.append("Folgende Ereignisse habe ich gefunden:\n");
        return new SendMessage(update.getMessage().getChat().id(), stringBuilder.toString()).toWebhookResponse();*/
    }
}
