package com.example.marketplaceproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    private ProgressBar progressBar;
    private ImageView imageView;

    private ImageView imageViewnotif;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = rootView.findViewById(R.id.progressBar);
        imageView = rootView.findViewById(R.id.imageView);
        imageViewnotif = rootView.findViewById(R.id.imageViewnotif);

        // Initially, hide the ProgressBar and imageView
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.INVISIBLE);
        imageViewnotif.setVisibility(View.INVISIBLE);

        // Show the ProgressBar for 5 seconds
        progressBar.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE); // Show the content
                imageViewnotif.setVisibility(View.VISIBLE);
            }
        }, 2000); // 5000 milliseconds = 5 seconds

        return rootView;
    }
}
