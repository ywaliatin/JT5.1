package com.example.jt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<NewsArticle> newsArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize data
        List<NewsArticle> threeColumnArticles = new ArrayList<>();
        threeColumnArticles.add(new NewsArticle("Singapore", "Singapore , officially the Republic of Singapore, is an island country and city-state in maritime Southeast Asia. It is located about one degree of latitude (137 kilometres or 85 miles) north of the equator, off the southern tip of the Malay Peninsula, bordering the Strait of Malacca to the west, the Singapore Strait to the ", R.drawable.singapore));
        threeColumnArticles.add(new NewsArticle("Malaysia", "Malaysia is a country in Southeast Asia. The federal constitutional monarchy consists of thirteen states and three federal territories, separated by the South China Sea into two regions: Peninsular Malaysia and Borneo's East Malaysia. Peninsular Malaysia shares a land and maritime border with Thailand and", R.drawable.malaysia));
        threeColumnArticles.add(new NewsArticle("Thailand", "Thailand , historically known as Siam (/saɪˈæm, ˈsaɪæm/), officially the Kingdom of Thailand, is a country in Southeast Asia, on the Indochinese Peninsula, spanning 513,120 square kilometres (198,120 sq mi), with a population of almost 70 million, bordered to the north by Myanmar and Laos, to the east by Laos", R.drawable.singapore));


        List<NewsArticle> twoColumnArticles = new ArrayList<>();
        twoColumnArticles.add(new NewsArticle("Indonesia", "Indonesia, officially the Republic of Indonesia, is a country in Southeast Asia and Oceania between the Indian and Pacific oceans. It consists of over 17,000 islands, including Sumatra, Java, Sulawesi, and parts of Borneo and New Guinea. Indonesia is the world's largest archipelagic state and the 14th-largest country ", R.drawable.indonesia));
        twoColumnArticles.add(new NewsArticle("Thailand", "This is the eighth article", R.drawable.thailand));
        twoColumnArticles.add(new NewsArticle("Indonesia", "This is the seventh article", R.drawable.indonesia));
        twoColumnArticles.add(new NewsArticle("Thailand", "This is the eighth article", R.drawable.thailand));
        twoColumnArticles.add(new NewsArticle("Indonesia", "This is the seventh article", R.drawable.indonesia));
        twoColumnArticles.add(new NewsArticle("Thailand", "This is the eighth article", R.drawable.thailand));


        // Initialize RecyclerView for 3-column articles
        RecyclerView recyclerView3 = findViewById(R.id.mainRecyclerView1);
        recyclerView3.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView3.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView3.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        RecyclerViewAdapter adapter3 = new RecyclerViewAdapter(threeColumnArticles, MainActivity.this);
        recyclerView3.setAdapter(adapter3);

        // Initialize RecyclerView for 2-column articles
        RecyclerView recyclerView2 = findViewById(R.id.mainRecyclerView2);
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(twoColumnArticles, MainActivity.this);
        recyclerView2.setAdapter(adapter2);
    }


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
            holder.titleTextView.setText(newsArticle.getTitle());
            holder.descriptionTextView.setText(newsArticle.getDescription());
            Picasso.get().load(newsArticle.getImageResourceId()).into(holder.imageView);

            // Add top padding to first item
            if (position == 0) {
                holder.itemView.setPadding(holder.itemView.getPaddingLeft(), 0, holder.itemView.getPaddingRight(), holder.itemView.getPaddingBottom());
            } else {
                holder.itemView.setPadding(holder.itemView.getPaddingLeft(), 16, holder.itemView.getPaddingRight(), holder.itemView.getPaddingBottom());
            }

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        NewsArticle selectedArticle = newsArticles.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putString("title", selectedArticle.getTitle());
                        bundle.putString("description", selectedArticle.getDescription());
                        bundle.putInt("imageResourceId", selectedArticle.getImageResourceId());

                        NewsFragment newsFragment = new NewsFragment();
                        newsFragment.setArguments(bundle);

                        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, newsFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return newsArticles.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView titleTextView;
            public TextView descriptionTextView;

            public ViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.imageView);
                titleTextView = view.findViewById(R.id.titleTextView);
                descriptionTextView = view.findViewById(R.id.descriptionTextView);
            }
        }
    }
}
