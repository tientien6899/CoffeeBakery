package com.example.coffeebakery.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    NewProdectAdapter adapter;
    PosterAdapter posterAdapter;
    TapChiAdapter tapChiAdapter;
    NewProdectAdapter newProdectAdapter;
    ArrayList<Poster> posterArrayList;
    ArrayList<NewProduct> newProductArrayList;
    ArrayList<TapChi> tapChiArrayList;
    private DatabaseReference NewData;

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

        //danh sach san pham moi
        NewData = FirebaseDatabase.getInstance().getReference();
        newProductArrayList = new ArrayList<NewProduct>();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        String ngay = dateformat.format(calendar.getTime());
        String[] arrngay = ngay.split("-");
        newsp = (RecyclerView) v.findViewById(R.id.rcv_Sanphammoi);
        newsp.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        newsp.setItemAnimator(new DefaultItemAnimator());
        NewData.child("SanPham").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    String ngaydang = data.child("ngaydang").getValue().toString();
                    String[] arrngaydang = ngaydang.split("-");
                    if(Integer.parseInt(arrngay[2]) <= Integer.parseInt(arrngaydang[2])){
                        if(Integer.parseInt(arrngaydang[1]) == Integer.parseInt(arrngay[1])){
                            String ten = data.child("tensp").getValue().toString();
                            String ma = data.child("masp").getValue().toString();
                            String gias = data.child("giaS").getValue().toString();
                            String link = data.child("link").getValue().toString();
                            newProductArrayList.add(new NewProduct(ma,ten,gias,link,ngaydang));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        newProdectAdapter = new NewProdectAdapter(v.getContext(),newProductArrayList);
        newsp.setAdapter(newProdectAdapter);

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


        return v;
    }
}