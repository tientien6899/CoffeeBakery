package com.example.coffeebakery.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coffeebakery.DanhMuc.CoffeeActivity;
import com.example.coffeebakery.DanhMuc.FoodActivity;
import com.example.coffeebakery.DanhMuc.FreezeeActivity;
import com.example.coffeebakery.DanhMuc.JuiceActivity;
import com.example.coffeebakery.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    RecyclerView poster, newsp, tapchi;
    PosterAdapter posterAdapter;
    TapChiAdapter tapChiAdapter;
    NewProdectAdapter newProdectAdapter;
    ArrayList<Poster> posterArrayList;
    ArrayList<NewProduct> newProductArrayList;
    ArrayList<TapChi> tapChiArrayList;
    private DatabaseReference NewData;
    ImageView caphe, freezee, juice, food;

    public HomeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_home, container, false);
        NewData = FirebaseDatabase.getInstance().getReference();

        //danh sach poster
        poster = (RecyclerView) v.findViewById(R.id.rcv_Poster);
        poster.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        poster.setItemAnimator(new DefaultItemAnimator());
        posterArrayList = new ArrayList<>();
        NewData.child("Poster").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Poster pt = snap.getValue(Poster.class);
                    posterArrayList.add(pt);
                }
                posterAdapter = new PosterAdapter(v.getContext(), posterArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL, false);
                poster.setAdapter(posterAdapter);
                poster.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //danh sach tap chi
        tapchi = (RecyclerView) v.findViewById(R.id.rcv_Tapchi);
        tapchi.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        tapchi.setItemAnimator(new DefaultItemAnimator());
        tapChiArrayList = new ArrayList<TapChi>();
        NewData.child("Post").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    TapChi tc = snap.getValue(TapChi.class);
                    tapChiArrayList.add(tc);
                }
                tapChiAdapter = new TapChiAdapter(tapChiArrayList,v.getContext());
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL, false);
                tapchi.setAdapter(tapChiAdapter);
                tapchi.setLayoutManager(linearLayoutManager1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        caphe = v.findViewById(R.id.danhmuc_caphe);
        caphe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CoffeeActivity.class);
                startActivity(intent);
            }
        });

        freezee = v.findViewById(R.id.danhmuc_freezee);
        freezee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FreezeeActivity.class);
                startActivity(intent);
            }
        });

        juice = v.findViewById(R.id.danhmuc_juice);
        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), JuiceActivity.class);
                startActivity(intent);
            }
        });

        food = v.findViewById(R.id.danhmuc_food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FoodActivity.class);
                startActivity(intent);
            }
        });



        return v;
    }
}