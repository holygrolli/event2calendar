package de.networkchallenge.e2c.lambda.backend;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import de.networkchallenge.e2c.lambda.backend.handler.UrlController;
import spark.Spark;

import static spark.Spark.before;

/**
 * Hello world!
 *
 */
public class MainHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse>
{

    private SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler =
            SparkLambdaContainerHandler.getAwsProxyHandler();
    private boolean initialized = false;

    public MainHandler() throws ContainerInitializationException {
    }

    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        if (!initialized) {
            initialized = true;
            defineRoutes();
            Spark.awaitInitialization();
        }
        return handler.proxy(awsProxyRequest, context);
    }

    public static void main(String... args){
        defineRoutes();
        Spark.awaitInitialization();
    }
    private static void defineRoutes() {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
        new UrlController();
    }
}
