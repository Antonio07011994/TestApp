package com.example.antonio.testapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FullInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_info);
        ImageView photo = (ImageView) findViewById(R.id.photo);
        final ProgressBar loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(ProgressBar.VISIBLE);
        Picasso.with(this)
                .load(getIntent().getExtras().getString("photo"))
                .fit()
                .error(R.drawable.failed_to_load_image)
                .into(photo, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading.setVisibility(ProgressBar.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        loading.setVisibility(ProgressBar.INVISIBLE);
                    }
                });
        showData();
    }

    public void showData() {
        ((TextView) findViewById(R.id.textFirstname)).setText(getIntent().getExtras().getString("firstname"));
        ((TextView) findViewById(R.id.textLastname)).setText(getIntent().getExtras().getString("lastname"));
        ((TextView) findViewById(R.id.phone_text)).setText(getIntent().getExtras().getString("phone"));
        ((TextView) findViewById(R.id.email_text)).setText(getIntent().getExtras().getString("email"));
        ((TextView) findViewById(R.id.address_text)).setText(getIntent().getExtras().getString("address"));
        ((TextView) findViewById(R.id.company_text)).setText(getIntent().getExtras().getString("company"));
        ((TextView) findViewById(R.id.about_text)).setText(getIntent().getExtras().getString("about"));
        ((TextView) findViewById(R.id.id_text)).setText(getIntent().getExtras().getString("id"));
        findViewById(R.id.phone_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ((TextView) v).getText()));
                startActivity(intent);
            }
        });
        findViewById(R.id.email_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] {((TextView)v).getText().toString()});
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Выбрать почтовое приложение"));
            }
        });
    }
}
