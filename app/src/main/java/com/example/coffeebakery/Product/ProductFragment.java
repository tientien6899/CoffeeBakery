package com.example.coffeebakery.Product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeebakery.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    static ProductAdapter adapter;
    private DatabaseReference danhsachRef, mData;

    public ProductFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_product, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rcv_dssanpham);
        mData = FirebaseDatabase.getInstance().getReference();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        danhsachRef = mData.child("SanPham");
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(danhsachRef, new SnapshotParser<Product>() {
                            @NonNull
                            @Override
                            public Product parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Product(snapshot.child("tensp").getValue().toString(),
                                        snapshot.child("danhmuc").getValue().toString(),
                                        snapshot.child("link").getValue().toString(),
                                        snapshot.child("masp").getValue().toString(),
                                        snapshot.child("giaS").getValue().toString(),
                                        snapshot.child("giaM").getValue().toString(),
                                        snapshot.child("giaL").getValue().toString(),
                                        snapshot.child("giaKM").getValue().toString(),
                                        snapshot.child("mota").getValue().toString(),
                                        snapshot.child("ngaydang").getValue().toString());
                            }
                        })
                        .build();
        adapter = new ProductAdapter(options);
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}