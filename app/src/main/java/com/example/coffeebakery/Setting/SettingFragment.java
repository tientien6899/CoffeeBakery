package com.example.coffeebakery.Setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.coffeebakery.Profile;
import com.example.coffeebakery.ProfileActivity;
import com.example.coffeebakery.R;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    LinearLayout chinhsachchung, baomat, thanhtoan, vanchuyen, thongtincanhan, thongtinlienhe;

    public SettingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        chinhsachchung = v.findViewById(R.id.setting_setting1);
        chinhsachchung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ChungActivity.class);
                context.startActivity(intent);
            }
        });

        baomat = v.findViewById(R.id.setting_setting2);
        baomat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, BaoMatActivity.class);
                context.startActivity(intent);
            }
        });

        thanhtoan = v.findViewById(R.id.setting_setting3);
        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ThanhToanActivity.class);
                context.startActivity(intent);
            }
        });

        vanchuyen = v.findViewById(R.id.setting_setting4);
        vanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, VanChuyenActivity.class);
                context.startActivity(intent);
            }
        });


        thongtinlienhe = v.findViewById(R.id.setting_setting6);
        thongtinlienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, LienHeActivity.class);
                context.startActivity(intent);
            }
        });


        thongtincanhan = v.findViewById(R.id.setting_setting7);
        thongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ProfileActivity.class);
                context.startActivity(intent);
            }
        });

        return v;
    }
}