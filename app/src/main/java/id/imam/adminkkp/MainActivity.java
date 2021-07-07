package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CardView tambahDatawisata,tambahGambarpromo,lihatPesananberhasil,statuspesananpelanggan,gagalbayar,konfirmasipembatalan,datasemuauser,datasemuaadmin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    LinearLayout linearLayout;
    Button keluaraplikasi;
   // FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambahDatawisata = findViewById(R.id.tambahdatawisata);
        lihatPesananberhasil = findViewById(R.id.pesananberhasil);
        statuspesananpelanggan =  findViewById(R.id.pesananpelanggan);
        gagalbayar = findViewById(R.id.gagalbayar);
        konfirmasipembatalan = findViewById(R.id.konfirmasipembatalan);
        datasemuauser = findViewById(R.id.datasemuauser);
        firebaseAuth = firebaseAuth.getInstance();
        datasemuaadmin =findViewById(R.id.datasemuaadmin);
        linearLayout = findViewById(R.id.layoutfalse);
        firebaseFirestore = FirebaseFirestore.getInstance();
        linearLayout.setVisibility(View.GONE);
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        keluaraplikasi = findViewById(R.id.buttontinggalkanaplkasi);
        String emaill = firebaseAuth.getCurrentUser().getEmail();


        keluaraplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });



       // Query query2 = firebaseFirestore.collection("admin").whereEqualTo("status","true");

        firebaseFirestore.collection("hapusadmin").document(emaill).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){

                        linearLayout.setVisibility(View.VISIBLE);

                    }
                }

            }
        });










        datasemuaadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zr2= new Intent(MainActivity.this,SemuaDataAdmin.class);
                startActivity(zr2);
            }
        });
        datasemuauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zzz12= new Intent(MainActivity.this,DataSemuaUser.class);
                startActivity(zzz12);
            }
        });

        konfirmasipembatalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zzz1= new Intent(MainActivity.this,KonfirmasiPembatalan.class);
                startActivity(zzz1);
            }
        });

        gagalbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();

               Intent zzz= new Intent(MainActivity.this,LoginActivity.class);
               startActivity(zzz);
               finish();
            }
        });

        lihatPesananberhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zz = new Intent(MainActivity.this,PesananBerhasil.class);
                startActivity(zz);
            }
        });

        statuspesananpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bb = new Intent(MainActivity.this,StatusPesananPelanggan.class);
                startActivity(bb);
            }
        });


        tambahDatawisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,TambahDataWisata.class);
                startActivity(a);
            }
        });



    }
}