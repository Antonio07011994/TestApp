package com.example.antonio.testapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int listPosition;
    private String POSITION = "listPosition";
    private String LIST = "items";
    private String URL = "https://agency5-mobile-school.firebaseio.com";
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            listPosition = 0;
        }
        else {
            items = savedInstanceState.getParcelableArrayList(LIST);
            listPosition = savedInstanceState.getInt(POSITION);
        }
        setContentView(R.layout.activity_main);
        if (items == null) {
            getData();
        } else {
            showData();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(LIST, (ArrayList<? extends Parcelable>) items);
        outState.putInt(POSITION, ((ListView)findViewById(R.id.dataList)).getFirstVisiblePosition());
        super.onSaveInstanceState(outState);
    }

    public void getData() {
        final ProgressBar loading_data = (ProgressBar)findViewById(R.id.loading_data);
        loading_data.setVisibility(ProgressBar.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetJSON getJSON = retrofit.create(GetJSON.class);
        Call<JSON> call = getJSON.getData();
        call.enqueue(new Callback<JSON>() {
            @Override
            public void onResponse(Call<JSON> call, Response<JSON> response) {
                JSON data = response.body();
                items = data.getItems();
                showData();
            }

            @Override
            public void onFailure(Call<JSON> call, Throwable t) {
                loading_data.setVisibility(ProgressBar.INVISIBLE);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Error")
                        .setMessage("Failed to load data. Check your internet connection.")
                        .setCancelable(true)
                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getData();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                System.out.println("I can't do that!!!");
            }
        });
    }

    public void showData() {
        final ListView dataList = (ListView)findViewById(R.id.dataList);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createIntent(position);
            }
        });
        Image_Text_Adapter adapter = new Image_Text_Adapter(this, items);
        (findViewById(R.id.loading_data)).setVisibility(ProgressBar.INVISIBLE);
        dataList.setAdapter(adapter);
        dataList.post(new Runnable() {
            @Override
            public void run() {
                dataList.setSelection(listPosition);
            }
        });
    }

    public void createIntent(int position) {
        Intent intent = new Intent(MainActivity.this, FullInfoActivity.class);
        intent.putExtra("id", items.get(position).getId().toString());
        intent.putExtra("firstname", items.get(position).getFirstName());
        intent.putExtra("lastname", items.get(position).getLastName());
        intent.putExtra("photo", items.get(position).getPhoto());
        intent.putExtra("phone", items.get(position).getPhone());
        intent.putExtra("email", items.get(position).getEmail());
        intent.putExtra("address", items.get(position).getAddress());
        intent.putExtra("company", items.get(position).getCompany());
        intent.putExtra("about", items.get(position).getAbout());
        startActivity(intent);
    }
}
