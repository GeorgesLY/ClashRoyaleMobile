package com.example.clashroyale;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.clashroyale.model.Items;
import com.example.clashroyale.model.RestClashResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vincent ETIENNE on 12/02/2019.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/GeorgesLY/Programmation-Mobile/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ClashRestApi clashRestApi = retrofit.create(ClashRestApi.class);


        Call<RestClashResponse> call = clashRestApi.getListClashAPI();
        call.enqueue(new Callback<RestClashResponse>() {
            @Override
            public void onResponse(Call<RestClashResponse> call, Response<RestClashResponse> response) {
                RestClashResponse restClashResponse = response.body();
                List<Items> listClash = restClashResponse.getItems();
                showList(listClash);
            }

            @Override
            public void onFailure(Call<RestClashResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    private void showList(List<Items> list) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new OnClick(getApplicationContext(), recyclerView, new OnClick.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Items items = list.get(position);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("description", items.getMaxLevel());
                intent.putExtra("name", items.getName());

                //intent.putExtra("image",items.getUrl());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
