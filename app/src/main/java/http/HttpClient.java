package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private HttpURLConnection urlConnection;
    private InputStream urlConnectionInputStream;

    @Override
    public void onRequest(String pUrl, IListener pListener) {
        try {
            urlConnection = (HttpURLConnection) new URL(pUrl).openConnection();
            urlConnectionInputStream = urlConnection.getInputStream();
            pListener.onResponse(urlConnectionInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnectionInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
    }
}
