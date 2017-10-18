package com.github.coyclab.hw4_backend;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coyclab.backend.productApi.ProductApi;
import com.example.coyclab.backend.productApi.model.Product;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static final String NO_DATA = "No data available";
    public static final String URL_LOCAL = "http://10.0.2.2:8080/_ah/api/";
    public static final String URL_REMOTE = "https://my-test-project-33133.appspot.com//_ah/api/";
    private static ProductApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            ProductApi.Builder builder = new ProductApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(URL_REMOTE)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        try {
            List<Product> products = myApiService.list().execute().getItems();
            if (products == null || products.isEmpty()) {
                return NO_DATA;
            }

            return products.get(products.size() - 1).toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(final String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}