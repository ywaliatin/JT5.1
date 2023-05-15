package com.example.jt;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<NewsArticle> newsArticles;
    private Context context;

    public RecyclerViewAdapter(List<NewsArticle> newsArticles, Context context) {
        this.newsArticles = newsArticles;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsArticle newsArticle = newsArticles.get(position);
        holder.bind(newsArticle);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.i("Checking ", "buttonClick");
                if (position != RecyclerView.NO_POSITION) {
                    NewsArticle selectedArticle = newsArticles.get(position);

                    Log.d("RecyclerViewAdapter", "Clicked on article: " + selectedArticle.getTitle() + " - " + selectedArticle.getDescription());


                    Intent intent = new Intent(context, NewsArticleDetailActivity.class);
                    intent.putExtra(NewsArticleDetailActivity.EXTRA_NEWS_ARTICLE, selectedArticle);
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            titleTextView = view.findViewById(R.id.titleTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);

            // Set the width of the ImageView to be one-third of the width of the RecyclerView
            //imageView.getLayoutParams().width = view.getContext().getResources().getDisplayMetrics().widthPixels / 3;
        }

        public void bind(NewsArticle newsArticle) {
            titleTextView.setText(newsArticle.getTitle());
            descriptionTextView.setText(newsArticle.getDescription());
            Picasso.get().load(newsArticle.getImageResourceId()).into(imageView);
            Log.d("RecyclerViewAdapter", "Set description: " + newsArticle.getDescription() + " for news article: " + newsArticle.getTitle());
        }
    }
}
