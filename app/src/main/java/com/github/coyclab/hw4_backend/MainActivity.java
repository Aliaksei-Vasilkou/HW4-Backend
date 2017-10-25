package com.github.coyclab.hw4_backend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coyclab.backend.productApi.model.Product;

public class MainActivity extends AppCompatActivity {

    private Button mSendButton;
    private EditText mIdEditText;
    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mDiscountEditText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {

            @Override

            public void run() {
                final BackendConfigurator configurator = new BackendConfigurator();
                if (!configurator.isActualVersion()) {
                    if (configurator.isNeedForceUpdate()) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        MainActivity.this);
                                alertDialogBuilder
                                        .setTitle("Update")
                                        .setMessage("You should update your application here: " +
                                                BuildConfig.VERSION_URL)
                                        .setCancelable(false)
                                        .create()
                                        .show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        MainActivity.this);

                                alertDialogBuilder
                                        .setTitle("Update")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                            public void onClick(final DialogInterface dialog, final int id) {
                                                MainActivity.this.finish();
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setCancelable(false)
                                        .create()
                                        .show();
                            }
                        });
                    }
                }
            }
        }).start();

        initView();

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

        mSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Long id = Long.valueOf(mIdEditText.getText().toString());
                String name = mNameEditText.getText().toString();
                Double price = Double.valueOf(mPriceEditText.getText().toString());
                int discount = Integer.valueOf(mDiscountEditText.getText().toString());
                Product productObject = new Product();
                productObject.setName(name).setId(id).setDiscount(discount).setPrice(price);
                new SenderAsyncTask().execute(new Pair<Context, Product>(MainActivity.this, productObject));
            }
        });
    }

    private void initView() {
        mSendButton = (Button) findViewById(R.id.send_button);
        mIdEditText = (EditText) findViewById(R.id.id_edit_text);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mPriceEditText = (EditText) findViewById(R.id.price_edit_text);
        mDiscountEditText = (EditText) findViewById(R.id.discount_edit_text);
    }
}
