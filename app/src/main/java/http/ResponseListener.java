package http;

import com.example.Updater;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ResponseListener implements IListener {
    @Override
    public void onResponse(InputStream pInputStream) {
        InputStreamReader reader = new InputStreamReader(pInputStream);
        Updater updater = new GsonBuilder().create().fromJson(reader, Updater.class);

    }
}
