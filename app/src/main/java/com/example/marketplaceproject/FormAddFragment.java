package com.example.marketplaceproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormAddFragment extends Fragment {

    EditText ProductTitle_input, ProductDescription_input, ProductPrice_input, ProductSellerInfo_input;
    Button button_addForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_add, container, false);

        ProductTitle_input = view.findViewById(R.id.ProductTitle_input);
        ProductDescription_input = view.findViewById(R.id.ProductDescription_input);
        ProductPrice_input = view.findViewById(R.id.ProductPrice_input);
        ProductSellerInfo_input = view.findViewById(R.id.ProductSellerInfo_input);
        button_addForm = view.findViewById(R.id.button_addForm);
        button_addForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database myDB = new Database(getActivity()); // Use getActivity() to get the context
                myDB.addproduct(ProductTitle_input.getText().toString().trim(),
                        ProductDescription_input.getText().toString().trim(),
                ProductPrice_input.getText().toString().trim(),
                        ProductSellerInfo_input.getText().toString().trim());

            }
        });


        return view; // Add this return statement


}
    }
