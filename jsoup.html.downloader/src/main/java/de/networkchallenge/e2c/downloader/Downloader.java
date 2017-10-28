package de.networkchallenge.e2c.downloader;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Downloader {
	private String url;

	public Downloader(String url)
	{
		this(url, false);
	}
	
	public Downloader(String url, boolean sslTrustAll) {
		this.url = url;
		if (sslTrustAll)
			try {
				setTrustAllCerts();
			} catch (Exception e) {
				System.out.println("enabling sslTrustAll went wrong, trying without...");
			}
	}
	
	public void save() throws IOException
	{
		final Response response = Jsoup.connect(url).execute();
        final Document doc = response.parse();

        final File f = new File("index.html");
        FileUtils.writeStringToFile(f, doc.outerHtml(), "UTF-8");
	}
	
	private void setTrustAllCerts() throws Exception
	{
	    TrustManager[] trustAllCerts = new TrustManager[]{
	        new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType ) { }
	            public void checkServerTrusted( java.security.cert.X509Certificate[] certs, String authType ) { }
	        }
	    };

	    // Install the all-trusting trust manager
	    try {
	        SSLContext sc = SSLContext.getInstance( "SSL" );
	        sc.init( null, trustAllCerts, new java.security.SecureRandom() );
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HttpsURLConnection.setDefaultHostnameVerifier( 
	            new HostnameVerifier() {
	                public boolean verify(String urlHostName, SSLSession session) {
	                    return true;
	                }
	            });
	    }
	    catch ( Exception e ) {
	        //We can not recover from this exception.
	        e.printStackTrace();
	    }
	}
}
