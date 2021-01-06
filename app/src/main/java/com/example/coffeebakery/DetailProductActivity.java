package com.example.coffeebakery;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.coffeebakery.CartFragment.tongSL;
import static com.example.coffeebakery.LoginActivity.gmail;

public class DetailProductActivity extends AppCompatActivity {

    TextView txt_detailtensp, txt_detailgiasp, txt_detailmotasp, txt_soluongsp, tongsl, tongtien, tensp;
    Button btn_tang, btn_giam, btn_datmon;
    RadioButton rb_nho, rb_vua, rb_lon;
    EditText edt_ghichu;
    ImageView img_detailhinhsp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myData = database.getReference();
    public static int slmon = 1;
    public static int STT = 1;
    static int temp = 0;
    String kichthuoc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        AnhXa();
        Intent intent = getIntent();
        Glide.with(DetailProductActivity.this).load(intent.getStringExtra("LINK")).into(img_detailhinhsp);
        String link = intent.getStringExtra("LINK");
        txt_detailtensp.setText(intent.getStringExtra("TENSP"));
        txt_detailgiasp.setText(intent.getStringExtra("GIAS"));
        txt_detailmotasp.setText(intent.getStringExtra("MOTA"));
        tongtien.setText(txt_detailgiasp.getText().toString().trim());
        tensp.setText(intent.getStringExtra("TENSP"));
        String masp = intent.getStringExtra("MASP");

        //Thiết lập tổng tiền
        final int s = Integer.parseInt(intent.getStringExtra("GIAS"));
        final int m = Integer.parseInt(intent.getStringExtra("GIAM"));
        final int l = Integer.parseInt(intent.getStringExtra("GIAL"));


        //Thiết lập chọn size
        rb_nho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int sl = Integer.parseInt(txt_soluongsp.getText().toString().trim());
                    tongtien.setText(String.valueOf(s*sl) + " đ");
                    txt_detailgiasp.setText(s + "");
                    kichthuoc = "Nhỏ (S)";
                }
            }
        });

        rb_vua.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int sl = Integer.parseInt(txt_soluongsp.getText().toString().trim());
                    tongtien.setText(String.valueOf(m * sl) + " đ");
                    txt_detailgiasp.setText(m + "");
                    kichthuoc = "Vừa (M)";
                }
            }
        });

        rb_lon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int sl = Integer.parseInt(txt_soluongsp.getText().toString().trim());
                    tongtien.setText(String.valueOf(l * sl) + " đ");
                    txt_detailgiasp.setText(l + "");
                    kichthuoc = "Lớn (L)";
                }
            }
        });
        int sluong = Integer.parseInt(txt_soluongsp.getText().toString().trim());
        final int[] a = {sluong};
         //Nút giảm số lượng
        btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a[0] -= 1;
                if (a[0] == 1) {
                    btn_giam.setVisibility(View.INVISIBLE);
                }
                tongSL = tongSL - Integer.parseInt(txt_soluongsp.getText().toString().trim()) + a[0];
                txt_soluongsp.setText(String.valueOf(a[0]));
                tongsl.setText(String.valueOf(a[0]));
            }
        });

        //Nút tăng số lượng
        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a[0] > 0){
                    btn_giam.setVisibility(View.VISIBLE);
                }
                a[0] += 1;
                tongSL = tongSL - Integer.parseInt(txt_soluongsp.getText().toString().trim()) + a[0];
                txt_soluongsp.setText(String.valueOf(a[0]));
                tongsl.setText(String.valueOf(a[0]));
            }
        });

        txt_soluongsp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                int ab = Integer.parseInt(String.valueOf(cs));
                if(rb_nho.isChecked())
                    temp = Integer.parseInt(String.valueOf(s)) * ab;
                if(rb_vua.isChecked())
                    temp = Integer.parseInt(String.valueOf(m)) * ab;
                if(rb_lon.isChecked())
                    temp = Integer.parseInt(String.valueOf(l)) * ab;
                tongtien.setText(String.valueOf(temp) + " đ");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Thiết lập ĐẶT MÓN
        btn_datmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart(String.valueOf(slmon),
                        "Cart" + STT,
                        masp,
                        tensp.getText().toString().trim(),
                        txt_detailgiasp.getText().toString().trim(),
                        txt_soluongsp.getText().toString().trim(),
                        link,
                        tongtien.getText().toString().trim(),
                        kichthuoc,
                        edt_ghichu.getText().toString().trim());
                myData.child("Taikhoan").child(gmail).child("Giohang").child("Cart" + STT).child(cart.getSttgiohang()).setValue(cart);
                tongSL += Integer.parseInt(cart.getSoluong());
                slmon++;
                Toast.makeText(DetailProductActivity.this, "Thêm sản phẩm vào giỏ hàng thành công !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AnhXa() {
        txt_detailtensp = (TextView) findViewById(R.id.txt_DetailTenSP);
        txt_detailgiasp = (TextView) findViewById(R.id.txt_DetailGiaSP);
        txt_detailmotasp = (TextView) findViewById(R.id.txt_DetailMotaSP);
        txt_soluongsp = (TextView) findViewById(R.id.txt_Soluong);
        tongtien = (TextView) findViewById(R.id.Tongtien);
        tongsl = (TextView) findViewById(R.id.TongSL);
        tensp = (TextView) findViewById(R.id.Tensp);
        btn_tang = (Button) findViewById(R.id.btn_TangSL);
        btn_giam = (Button) findViewById(R.id.btn_GiamSL);
        btn_datmon = (Button) findViewById(R.id.btn_Datmon);
        rb_nho = (RadioButton) findViewById(R.id.rb_Nho);
        rb_vua = (RadioButton) findViewById(R.id.rb_Vua);
        rb_lon = (RadioButton) findViewById(R.id.rb_Lon);
        edt_ghichu = (EditText) findViewById(R.id.edt_Ghichu);
        img_detailhinhsp = (ImageView) findViewById(R.id.img_DetailHinhSP);
    }
}