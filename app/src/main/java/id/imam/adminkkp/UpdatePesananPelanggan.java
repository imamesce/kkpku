package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdatePesananPelanggan extends AppCompatActivity {
ImageView gambarpesanan;
TextView namawisata,hargawisata,keteranganwisata,keterangan,dibuat,alamatemail,tempatwisata;
Button konfirmasi,gagalkonfirmasi;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pesanan_pelanggan);
        tempatwisata =findViewById(R.id.tempatwisata);
        namawisata = findViewById(R.id.namawisata);
        hargawisata = findViewById(R.id.hargawisata);
        keterangan = findViewById(R.id.keterangan);
        keteranganwisata = findViewById(R.id.keteranganwisata);
        dibuat = findViewById(R.id.dibuat);
        alamatemail = findViewById(R.id.email);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        gambarpesanan =findViewById(R.id.imageView);
        konfirmasi =findViewById(R.id.buttonkonfirmasi);
        gagalkonfirmasi = findViewById(R.id.buttongagal);
        String adminn =firebaseAuth.getCurrentUser().getUid();





        Intent intentambildata = getIntent();
        if (intentambildata.hasExtra("id_order")) {
           // String id_orderr = intentambildata.getStringExtra("id_order");
            String namaWisata = intentambildata.getStringExtra("nama_wisata");
            String Keterangan = intentambildata.getStringExtra("keterangan");
            String Keteranganwisata = intentambildata.getStringExtra("keterangan_wisata");
            String Alamatemail = intentambildata.getStringExtra("alamat_email");
            String Tempat = intentambildata.getStringExtra("tempat_wisata");
            String hargaa = intentambildata.getStringExtra("harga_wisata");

            // statuspesan.setText(Integer.parseInt(String.valueOf(harga)));
            hargawisata.setText(hargaa);
            namawisata.setText(namaWisata);
            keterangan.setText(Keterangan);
            keteranganwisata.setText(Keteranganwisata);
            alamatemail.setText(Alamatemail);
            tempatwisata.setText(Tempat);
            String gambar=getIntent().getExtras().getString("Image");


            Glide.with(this)
                    .load(gambar)
                    .placeholder(R.drawable.ic_baseline_add_circle_outline_24)
                    .into(gambarpesanan);
        }

        gagalkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idkoder = getIntent().getStringExtra("id_order");

                String emailll=getIntent().getStringExtra("alamat_email");



                Map<String, String> ass= new HashMap<>();
                ass.put("status","gagalbayar");
                ass.put("keterangan","dibatalkan admin");
                ass.put("kode_transaksi",idkoder);
                ass.put("tempat_wisata",tempatwisata.getText().toString());
                ass.put("nama_wisata",namawisata.getText().toString());

                ass.put("keterangan_wisata",keteranganwisata.getText().toString());

                ass.put("alamat_email",alamatemail.getText().toString());

                ass.put("harga_wisata",hargawisata.getText().toString());
                String idsana = getIntent().getStringExtra("id_order");
                firebaseFirestore.collection("gagal_bayarr").document(emailll).set(ass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    gagalbayar();
                    }
                });
            }
        });
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // CollectionReference documentSnapshot = firebaseFirestore.collection("admin");
                firebaseFirestore.collection("admin").document(adminn).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot1 = task.getResult();
                        if (documentSnapshot1.exists()) {
                            String paterrn = "EEEE,dd MMMM yyyy";
                            DateFormat dateFormat = new SimpleDateFormat(paterrn);


                            Date date = Calendar.getInstance().getTime();
                            String todayy = dateFormat.format(date);
                            String hariini = ""+todayy;

                            String namaadmin = documentSnapshot1.getString("username");
                            String kode =getIntent().getStringExtra("id_order");

                            Map <String,String> us = new HashMap<>();
                            us.put("status","true");
                            us.put("keterangan","berhasil");
                            us.put("kode_transaksi",kode);
                            us.put("nama_admin",namaadmin);
                            us.put("tanggal_berhasil",hariini);
                            String id =getIntent().getStringExtra("id_order");
                            firebaseFirestore.collection("order").document(id).set(us, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                     onBackPressed();
                                }
                            });

                        }
                    }
                });





            }
        });
    }

    private void gagalbayar() {
        String kodee=getIntent().getStringExtra("id_order");

        Map <String ,Object> b = new HashMap<>();
        b.put("nama_wisata",namawisata.getText().toString());
        b.put("kode_transaksi",kodee);
        b.put("tempat_wisata",tempatwisata.getText().toString());
        b.put("keterangan_wisata",keteranganwisata.getText().toString());
        b.put("alamat_email",alamatemail.getText().toString());
        b.put("harga",hargawisata.getText().toString());
        DocumentReference obb = firebaseFirestore.collection("order").document(kodee);
            obb.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
               onBackPressed();
                }
            });
    }

}