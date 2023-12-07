package com.example.marketplaceproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListFragment extends Fragment implements CustomAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    Database myDB;
    CustomAdapter customAdapter;
    ArrayList<String> product_id, product_title, product_description, product_price, product_sellerinfo;
    FloatingActionButton addBtnForm;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new Database(requireContext());
        product_id = new ArrayList<>();
        product_title = new ArrayList<>();
        product_description = new ArrayList<>();
        product_price = new ArrayList<>();
        product_sellerinfo = new ArrayList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = rootView.findViewById(R.id.RecyclerView);
        customAdapter = new CustomAdapter(requireContext(), product_id, product_title, product_description, product_price, product_sellerinfo);
        customAdapter.setOnItemClickListener(this); // Set the click listener here

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        storeDataInArrays();

        return rootView;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                product_id.add(cursor.getString(0));
                product_title.add(cursor.getString(1));
                product_description.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
                product_sellerinfo.add(cursor.getString(4));
            }
        }
    }

    @Override
    public void onDeleteClick(int position) {
        // Handle delete action here
        int productIdToDelete = Integer.parseInt(product_id.get(position));
        myDB.deleteProduct(productIdToDelete);

        // Remove the item from the ArrayLists
        product_id.remove(position);
        product_title.remove(position);
        product_description.remove(position);
        product_price.remove(position);
        product_sellerinfo.remove(position);

        // Notify the adapter that the data set has changed
        customAdapter.notifyDataSetChanged();
    }
}
