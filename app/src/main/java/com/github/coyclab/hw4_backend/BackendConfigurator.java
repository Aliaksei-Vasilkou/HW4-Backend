package com.github.coyclab.hw4_backend;

import com.example.Updater;
import com.example.UpdaterApi;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

import http.HttpClient;
import http.IListener;

public class BackendConfigurator {

    private Updater mVersion;

    BackendConfigurator(){
        final String url = new UpdaterApi("http://10.0.2.2:8080").getUrl();
        final ConfigurationResponseListener listener = new ConfigurationResponseListener();
        new HttpClient().onRequest(url, listener);
        mVersion =  listener.getVersion();
    }

    Boolean isActualVersion(){
        return mVersion.getActualVersion() <= BuildConfig.VERSION_CODE;
    }

    Boolean isNeedForceUpdate(){
        return mVersion.getNeedForceUpdate();
    }



    private static class ConfigurationResponseListener implements IListener {

        private Updater mVersion;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                mVersion = new GsonBuilder()
                        .setLenient()
                        .create()
                        .fromJson(inputStreamReader, Updater.class);
            } catch (Exception pE){
                pE.printStackTrace();
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {}
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        Updater getVersion() {
            return mVersion;
        }
    }

}
