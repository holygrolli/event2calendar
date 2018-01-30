package de.networkchallenge.e2c.lambda.backend;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import de.networkchallenge.e2c.lambda.backend.model.ResponseObject;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.HttpMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple MainHandler.
 */
public class MainHandlerTest {

	@Test
	public void testHandle() throws ContainerInitializationException {

		final MainHandler mainHandler = new MainHandler();
		Context contextMock = Mockito.mock(Context.class);
		Mockito.when(contextMock.getLogger()).thenReturn(Mockito.mock(LambdaLogger.class));
		String url = new String(Base64.getUrlEncoder().encode("Hello world!".getBytes()));
		final AwsProxyResponse response = mainHandler.handleRequest(new AwsProxyRequestBuilder().path("/url/" + url).method(HttpMethod.GET).build(), new MockLambdaContext());
		assertNotNull("Repsonse must not be null", response);
		assertTrue(response.getBody().contains(ResponseObject.Status.URL_INVALID.toString()));
	}

	@Ignore
	public void testHandle2() throws UnsupportedEncodingException, ContainerInitializationException {

		final MainHandler mainHandler = new MainHandler();
		String url = URLEncoder.encode(new String(Base64.getEncoder().encode("https://films2017.dok-leipzig.de/de/film/?ID=16412".getBytes())), "UTF-8");
		final AwsProxyResponse response = mainHandler.handleRequest(new AwsProxyRequestBuilder().path("/url/" + url).method(HttpMethod.GET).build(), new MockLambdaContext());
		assertNotNull("Repsonse must not be null", response);
		assertTrue(response.getBody().contains(ResponseObject.Status.OK.toString()));
	}
	@Ignore
	public void testHandle3() throws UnsupportedEncodingException, ContainerInitializationException {

		final MainHandler mainHandler = new MainHandler();
		String url = URLEncoder.encode(new String(Base64.getEncoder().encode("https://films2017.dok-leipzig.de/de/film/?ID=18371&title=Betrug".getBytes())), "UTF-8");
		final AwsProxyResponse response = mainHandler.handleRequest(new AwsProxyRequestBuilder().path("/url/" + url).method(HttpMethod.GET).build(), new MockLambdaContext());
		assertNotNull("Repsonse must not be null", response);
		assertTrue(response.getBody().contains(ResponseObject.Status.OK.toString()));
	}
	@Ignore
	public void testHandleRequestMapping() throws UnsupportedEncodingException, ContainerInitializationException {

		final MainHandler mainHandler = new MainHandler();
		String url = URLEncoder.encode(new String(Base64.getEncoder().encode("https://films2017.dok-leipzig.de/de/film/?ID=16412".getBytes())), "UTF-8");
		final AwsProxyResponse response = mainHandler.handleRequest(new AwsProxyRequestBuilder().path("/parse").method(HttpMethod.GET).queryString("url",url).build(), new MockLambdaContext());
		assertNotNull("Repsonse must not be null", response);
		assertTrue(response.getBody().contains(ResponseObject.Status.OK.toString()));
	}
}
