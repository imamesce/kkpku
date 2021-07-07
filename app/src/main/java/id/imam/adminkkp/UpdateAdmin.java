package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateAdmin extends AppCompatActivity {
TextView namaAdmin,Email,idAdmin;
Button hapusadmin;
FirebaseFirestore firebaseFirestore;
//FirebaseAuth mauth;
//FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);
        hapusadmin = findViewById(R.id.buttonhapus);
        firebaseFirestore = FirebaseFirestore.getInstance();
        idAdmin = findViewById(R.id.textView15);
        namaAdmin = findViewById(R.id.textView16);
        Email = findViewById(R.id.textView17);
      //  mauth = FirebaseAuth.getInstance();
      //  firebaseUser = mauth.getCurrentUser();
        //FirebaseUser a = mauth.getCurrentUser();


        Intent intentambildata = getIntent();
        if (intentambildata.hasExtra("username")) {
            String namaadmin = intentambildata.getStringExtra("username");
            String email = intentambildata.getStringExtra("email");
            String idadmin = intentambildata.getStringExtra("id_admin");
            namaAdmin.setText(namaadmin);
            Email.setText(email);
            idAdmin.setText(idadmin);
            idAdmin.getText().toString();

            hapusadmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, Object> user = new HashMap<>();
                   user.put("status","false");
                  ////  user.put("")
                  //  Map<String, Object> userr = new HashMap<>();
                  // // user.put("status","false");
                    //user.put("id_admin",intentambildata.getStringExtra("id_admin"));
                    String id = getIntent().getStringExtra("id");
                    String emailll = getIntent().getStringExtra("email");
                    firebaseFirestore.collection("admin").document(id).set(user,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                     //   onBackPressed();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                             Map<String, Object> userr = new HashMap<>();
                             user.put("status","keluar");
                             user.put("email",emailll);

                            firebaseFirestore.collection("hapusadmin").document(emailll).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            onBackPressed();
                            }
                        });
                        }
                    });

                }
            });





        }
    }


}