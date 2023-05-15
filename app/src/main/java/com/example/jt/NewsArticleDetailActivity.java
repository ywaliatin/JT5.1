package com.example.jt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsArticleDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NEWS_ARTICLE = "com.example.jt.EXTRA_NEWS_ARTICLE";

    private TextView titleTextView;
    private ImageView imageView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article_detail);

        titleTextView = findViewById(R.id.titleTextView);
        imageView = findViewById(R.id.imageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        // Retrieve the NewsArticle object passed from the RecyclerViewAdapter
        Intent intent = getIntent();
        NewsArticle newsArticle = intent.getParcelableExtra(EXTRA_NEWS_ARTICLE);

        Log.d("NewsArticleDetailActivity", "Received news article: " + newsArticle.getTitle() + " - " + newsArticle.getDescription());


        // Set the text and image views in the news_article_detail.xml layout file
        titleTextView.setText(newsArticle.getTitle());
        descriptionTextView.setText(newsArticle.getDescription());
        Picasso.get().load(newsArticle.getImageResourceId()).into(imageView);

    }
}

