package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class newsDetail extends AppCompatActivity {
    ImageView imageView;
    TextView textDEscription;
    TextView showmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        imageView = findViewById(R.id.NewsDetailImage);
        textDEscription = findViewById(R.id.NewsDetailtext);
        showmore = findViewById(R.id.showMore);

        Intent intent= getIntent();
        String image = intent.getStringExtra("image_url");
        String detail = intent.getStringExtra("detail");
        final String urlweb = intent.getStringExtra("url");


        textDEscription.setText(detail);
        Glide.with(this).load(image).into(imageView);

        showmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url = new Intent(Intent.ACTION_VIEW);
                url.setData(Uri.parse(urlweb));
                startActivity(url);

            }
        });



    }
}
