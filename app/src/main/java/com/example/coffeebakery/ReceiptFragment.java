package com.example.coffeebakery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import static com.example.coffeebakery.DetailProductActivity.STT;
import static com.example.coffeebakery.HomeActivity.mData;
import static com.example.coffeebakery.LoginActivity.gmail;

public class ReceiptFragment extends Fragment {
    RecyclerView recyclerView;
    ReceiptAdapter adapter;
    private DatabaseReference myData;
    public ReceiptFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_receipt, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcv_dsdonhang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myData =  mData.child("Taikhoan").child(gmail).child("HoaDon");
        FirebaseRecyclerOptions<Receipt> options =
                new FirebaseRecyclerOptions.Builder<Receipt>()
                        .setQuery(myData, new SnapshotParser<Receipt>() {
                            @NonNull
                            @Override
                            public Receipt parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Receipt(snapshot.child("madon").getValue().toString(),
                                        snapshot.child("ngaydat").getValue().toString(),
                                        snapshot.child("magh").getValue().toString(),
                                        snapshot.child("trangthai").getValue().toString(),
                                        snapshot.child("tongtien").getValue().toString());
                            }
                        })
                        .build();
        adapter = new ReceiptAdapter(options);
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