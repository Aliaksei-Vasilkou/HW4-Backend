package http;

public interface IHttpClient {

    void onRequest(String pUrl, IListener pListener);
}
