package com.example.marketplaceproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                String title = ProductTitle_input.getText().toString().trim();
                String description = ProductDescription_input.getText().toString().trim();
                String price = ProductPrice_input.getText().toString().trim();
                String sellerInfo = ProductSellerInfo_input.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty() || price.isEmpty() || sellerInfo.isEmpty()) {
                    // Display a message indicating that fields are empty
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Database myDB = new Database(getActivity()); // Use getActivity() to get the context
                    myDB.addproduct(title, description, price, sellerInfo);
// Clear Fields **
                    ProductTitle_input.setText("");
                    ProductDescription_input.setText("");
                    ProductPrice_input.setText("");
                    ProductSellerInfo_input.setText("");
                }
            }
        });

        return view;
    }
}
