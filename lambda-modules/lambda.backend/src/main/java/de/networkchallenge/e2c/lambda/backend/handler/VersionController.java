package de.networkchallenge.e2c.lambda.backend.handler;

import de.networkchallenge.e2c.lambda.backend.model.VersionResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * REST endpoint for showing version information
 */
@Path("/version")
public class VersionController {
    private static final String FILE_PATH = "build.properties";
    private static final Properties PROPERTIES = loadPropertiesFromPackage();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VersionResponse getVersion() {
        return new VersionResponse(PROPERTIES.getProperty("timestamp", "0"), PROPERTIES.getProperty("revision"));
    }

    private static Properties loadPropertiesFromPackage() {
        InputStream in = null;
        Properties prop = new Properties();
        try {
            in = VersionController.class.getClassLoader().getResourceAsStream(FILE_PATH);
            prop.load(in);
            System.out.println(prop.getProperty("name"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return prop;
    }
}
