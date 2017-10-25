package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private HttpURLConnection urlConnection;
    private InputStream urlConnectionInputStream;

    @Override
    public void onRequest(final String pUrl, final IListener pListener) {
        try {
            urlConnection = (HttpURLConnection) new URL(pUrl).openConnection();
            urlConnectionInputStream = urlConnection.getInputStream();
            pListener.onResponse(urlConnectionInputStream);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (urlConnectionInputStream != null) {
                    urlConnectionInputStream.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
    }
}
