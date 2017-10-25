package http;

import java.io.InputStream;

public interface IListener {

    void onResponse(InputStream pInputStream);
}
