package com.example.coffeebakery.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeebakery.R;

import java.util.List;

public class NewProdectAdapter extends RecyclerView.Adapter<NewProdectAdapter.Holder> {

    Context context;
    private List<NewProduct> productList;

    public NewProdectAdapter(Context context, List<NewProduct> products){
        this.context = context;
        this.productList = products;
    }

    @NonNull
    @Override
    public NewProdectAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_new_product, parent, false);
        return new NewProdectAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProdectAdapter.Holder holder, int position) {
        NewProduct np = productList.get(position);
        holder.ten.setText(np.getTensp());
        holder.gia.setText(np.getGiasp());
        Glide.with(holder.hinh.getContext()).load(np.getLink()).into(holder.hinh);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView ten, gia;
        ImageView hinh;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ten = (TextView) itemView.findViewById(R.id.txt_NewTensp);
            gia = (TextView) itemView.findViewById(R.id.txt_NewGiaspS);
            hinh = (ImageView) itemView.findViewById(R.id.img_NewHinhsp);
        }
    }
}


