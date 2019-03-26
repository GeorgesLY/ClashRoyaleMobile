package com.example.clashroyale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    //private Context context;
    //private List<Items> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView name = findViewById(R.id.firstLine);
        TextView description = findViewById(R.id.secondLine);
        //ImageView image = findViewById(R.id.icon);


        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));

        //image.setImageBitmap(intent.getStringExtra("image"));



    }
}

