package com.example.marketplaceproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> product_id, product_title, product_price, product_description, product_seller_info;
    private OnItemClickListener mListener;

    public CustomAdapter(Context context, ArrayList<String> product_id, ArrayList<String> product_title, ArrayList<String> product_description, ArrayList<String> product_price, ArrayList<String> product_seller_info) {
        this.context = context;
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_seller_info = product_seller_info;
    }

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_title_txt.setText(String.valueOf(product_title.get(position)));
        holder.product_description_txt.setText(String.valueOf(product_description.get(position)));
        holder.product_price_txt.setText(String.valueOf(product_price.get(position)));
        holder.product_sellerinfo_txt.setText(String.valueOf(product_seller_info.get(position)));

        holder.buttonDelete.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id_txt, product_title_txt, product_price_txt, product_description_txt, product_sellerinfo_txt;
    //    Button buttonDelete;
ImageView buttonDelete;
//constructor View Recy
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_title_txt = itemView.findViewById(R.id.product_title_txt);
            product_description_txt = itemView.findViewById(R.id.product_description_txt);
            product_price_txt = itemView.findViewById(R.id.product_price_txt);
            product_sellerinfo_txt = itemView.findViewById(R.id.product_sellerinfo_txt);
            buttonDelete = itemView.findViewById(R.id.buttondelete);
        }
    }
}