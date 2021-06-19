package com.example.coffeebakery.Setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeebakery.HomeActivity;
import com.example.coffeebakery.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.coffeebakery.HomeActivity.mData;

public class ChungActivity extends AppCompatActivity {

    ImageView back;
    TextView noidung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chung);

        AnhXa();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        mData.child("ChinhSach").child("Chính sách chung").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str_noidung = snapshot.child("noidung").getValue().toString();
                noidung.setText(str_noidung);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void AnhXa() {
        back = findViewById(R.id.img_Backchung);
        noidung = findViewById(R.id.chung_chinhsach);
    }
}