package com.example.coffeebakery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.coffeebakery.LoginActivity.gmail;

public class ProfileActivity extends AppCompatActivity {

    EditText edthoten, edtsdt, edtsonhaduong, edtphuongxa, edtquanhuyen, edttinhthanhpho;
    TextView txtusername;
    Button btndongy;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Anhxa();

        txtusername.setText(gmail);
        mData = FirebaseDatabase.getInstance().getReference();


        btndongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile p = new Profile();
                p.hoten = edthoten.getText().toString().trim();
                p.sdt = edtsdt.getText().toString().trim();
                p.sonha = edtsonhaduong.getText().toString().trim();
                p.phuong = edtphuongxa.getText().toString().trim();
                p.quan = edtquanhuyen.getText().toString().trim();
                p.thanhpho = edttinhthanhpho.getText().toString().trim();
                p.gmail = gmail;

                mData.child("KHACHHANG").child(p.getGmail()).setValue(p);

                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Anhxa() {
        edthoten = findViewById(R.id.edt_hoten);
        edtsdt = findViewById(R.id.edt_sdt);
        edtsonhaduong = findViewById(R.id.edt_sonhaduong);
        edtphuongxa = findViewById(R.id.edt_phuongxa);
        edtquanhuyen = findViewById(R.id.edt_quanhuyen);
        edttinhthanhpho = findViewById(R.id.edt_tinhthanhpho);
        txtusername = (TextView) findViewById(R.id.txt_username);
        btndongy = (Button) findViewById(R.id.btn_Dongy);
    }
}