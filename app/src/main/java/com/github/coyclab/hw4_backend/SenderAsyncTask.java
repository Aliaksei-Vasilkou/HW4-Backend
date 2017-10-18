package com.github.coyclab.hw4_backend;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.coyclab.backend.productApi.ProductApi;
import com.example.coyclab.backend.productApi.model.Product;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

public class SenderAsyncTask extends AsyncTask<Pair<Context, Product>, Void, String> {

    private static final String NO_DATA = "No data available";
    private static ProductApi myApiService = null;
    private Context context;
    private Product productObject;

    @Override
    protected String doInBackground(Pair<Context, Product>... params) {
        if(myApiService == null) {  // Only do this once
            ProductApi.Builder builder = new ProductApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        context = params[0].first;
        productObject = params[0].second;
        try {
            List<Product> products = myApiService.list().execute().getItems();
            myApiService.insert(productObject.getDiscount(), productObject.getId(), productObject.getName(), productObject.getPrice()).execute();
            if (products == null || products.isEmpty()){
                return NO_DATA;
            }
            return products.get(products.size()-1).toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(final String result) {
        Toast.makeText(context, result + "\nADDED", Toast.LENGTH_LONG).show();
    }
}