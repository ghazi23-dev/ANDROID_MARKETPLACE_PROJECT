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


public class ListFragment extends Fragment {

    RecyclerView recyclerView;
CustomAdapter adapter;
    Database myDB;
    CustomAdapter customAdapter;
    ArrayList<String> product_id , product_title , product_description , product_price , product_sellerinfo;
    FloatingActionButton addBtnForm;


    public ListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //RECYCLER VIEW PARAM // ARRAYS
        // PASS DB CONTEXT AS REQUIRED CALL FN
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

        // Find the RecyclerView in the inflated layout
        recyclerView = rootView.findViewById(R.id.RecyclerView);

        adapter = new CustomAdapter(requireContext(), product_id, product_title, product_description, product_price, product_sellerinfo);


        recyclerView.setAdapter(adapter);

        // Use requireContext() to provide the correct Context to LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        StoreDatainArrays();

        return rootView;
    }


    void StoreDatainArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_title.add(cursor.getString(1));
                product_description.add(cursor.getString(2));
                product_price.add(cursor.getString(0));
                product_sellerinfo.add(cursor.getString(0));
            }
        }
    }


}