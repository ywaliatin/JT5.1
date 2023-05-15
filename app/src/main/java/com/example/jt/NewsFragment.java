package com.example.jt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class NewsFragment extends Fragment {

    private TextView titleTextView;
    private ImageView imageView;
    private TextView descriptionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);


        // Retrieve the views
        titleTextView = view.findViewById(R.id.titleTextView1);
        descriptionTextView = view.findViewById(R.id.descriptionTextView1);
        imageView = view.findViewById(R.id.imageView1);



        // Get the arguments passed in from the MainActivity
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            String description = bundle.getString("description");
            int imageResourceId = bundle.getInt("imageResourceId");

            // Update the views with the selected news article
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            imageView.setImageResource(imageResourceId);
        }

        return view;
    }
}
