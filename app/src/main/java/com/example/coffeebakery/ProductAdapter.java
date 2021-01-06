package com.example.coffeebakery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product,ProductAdapter.Holder> {
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductAdapter.Holder holder, int position, @NonNull Product model) {

        holder.tensp.setText(model.getTensp());
        holder.gias.setText(model.getGiaS() + " đ");
        holder.mota.setText(model.getMota());
        Glide.with(holder.hinhanh.getContext()).load(model.getLink()).into(holder.hinhanh);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("DANHMUC",model.danhmuc);
                intent.putExtra("TENSP",model.tensp);
                intent.putExtra("MASP",model.masp);
                intent.putExtra("GIAS",model.giaS);
                intent.putExtra("GIAM",model.giaM);
                intent.putExtra("GIAL",model.giaL);
                intent.putExtra("GIAKM",model.giaKM);
                intent.putExtra("LINK",model.link);
                intent.putExtra("MOTA",model.mota);
                context.startActivity(intent);

//                Button xoa = (Button) d.findViewById(R.id.huy);
//                xoa.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
//                        mData.child("SanPham").child("Coffee").child(model.getMasp()).removeValue();
//                    }
//                });
//                d.show();

            }
        });

    }


    @NonNull
    @Override
    public ProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_product, parent, false);

        return new Holder(v);

    }
    class Holder extends RecyclerView.ViewHolder{
        ImageView hinhanh;
        TextView tensp, gias, mota;
        public Holder(@NonNull final View itemView) {
            super(itemView);
            hinhanh = (ImageView)itemView.findViewById(R.id.img_Hinhsp);
            tensp = (TextView)itemView.findViewById(R.id.txt_Tensp);
            gias = (TextView)itemView.findViewById(R.id.txt_GiaspS);
            mota = (TextView)itemView.findViewById(R.id.txt_Mota);
        }
    }
}
