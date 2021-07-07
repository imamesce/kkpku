package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class TambahAdmin extends AppCompatActivity {
FirebaseAuth firebaseAuth;
FirebaseFirestore firebaseFirestore;
EditText emailadmin,namaadmin,passwordadmin;
Button tambahadmin;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_admin);
        firebaseAuth= firebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        emailadmin = findViewById(R.id.editemail);
        namaadmin = findViewById(R.id.editnamaadmin);
        passwordadmin = findViewById(R.id.editpasswordadmin);
        tambahadmin = findViewById(R.id.tambahadmin);

        tambahadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = namaadmin.getText().toString().trim();
                String email = emailadmin.getText().toString();
                String password= passwordadmin.getText().toString().trim();
                if (TextUtils.isEmpty(nama)){
                    namaadmin.setError("nama harus di isi");
                    namaadmin.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailadmin.setError("Email yang benar");
                    emailadmin.requestFocus();
                    return;
                }if (password.length()<6){
                    passwordadmin.setError("password minimal 6");
                    passwordadmin.requestFocus();
                    return;
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(TambahAdmin.this,"user dibuat",Toast.LENGTH_LONG).show();
                                userID= firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("admin").document(userID);

                                Map<String, Object> user = new HashMap<>();
                                user.put("username",nama);
                                user.put("password",password);
                                user.put("email",email);

                                user.put("id_admin",userID);
                                user.put("status","true");
                                documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                               onBackPressed();
                            }
                        }
                    });


                }
            }
        });

    }
}