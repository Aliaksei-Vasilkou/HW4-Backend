package com.example;

public class UpdaterApi {

    private static final String VERSION_PATH = "version.json";

    private String mBasePath;

    public UpdaterApi(final String pBasePath) {
        if (pBasePath.charAt(pBasePath.length() - 1) == '/'){
            mBasePath = pBasePath;
        } else {
            mBasePath = pBasePath + "/";
        }
    }

    public String getUrl(){
        return mBasePath + VERSION_PATH;
    }
}
