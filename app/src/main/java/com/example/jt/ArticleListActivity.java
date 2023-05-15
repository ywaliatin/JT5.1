package com.example.jt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends AppCompatActivity {

    private List<NewsArticle> newsArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        // Initialize data
        newsArticles = new ArrayList<>();
        newsArticles.add(new NewsArticle("Singapore", "This is the first article", R.drawable.singapore));
        newsArticles.add(new NewsArticle("Malaysia", "This is the second article", R.drawable.malaysia));
        newsArticles.add(new NewsArticle("Australia", "This is the third article", R.drawable.australia));
        newsArticles.add(new NewsArticle("France", "This is the fourth article", R.drawable.france));
        newsArticles.add(new NewsArticle("Indonesia", "This is the fifth article", R.drawable.indonesia));
        newsArticles.add(new NewsArticle("Vietnam", "This is the sixth article", R.drawable.vietmam));

        Log.d("ArticleListActivity", "newsArticles size: " + newsArticles.size());

        // Pass the list of articles back to the main activity as a result
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("newsArticles", new ArrayList<>(newsArticles));
        setResult(RESULT_OK, intent);
        finish();
    }
}
