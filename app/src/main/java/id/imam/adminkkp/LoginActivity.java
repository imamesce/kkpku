package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    boolean valid = true;
    FirebaseFirestore firebaseFirestore;
    EditText editlogin,editpassword;
Button bn_login;
    private boolean valid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editlogin =  findViewById(R.id.editTextLogin);
        editpassword = findViewById(R.id.editTextPassword);
        bn_login = findViewById(R.id.buttonlogin);
        mAuth =FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();









        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (mAuth.getCurrentUser() != null){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
            }
        };

        bn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editlogin.getText().toString().trim();
                String password = editpassword.getText().toString().trim();
                if (email.isEmpty()){
                    editlogin.setError("Masukan email anda..");
                    editpassword.requestFocus();
                    return;
                }
                loginuser(email,password);
            }
        });

    }

    private void loginuser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "YEAY,,ANDA BERHASIL MASUK", Toast.LENGTH_LONG).show();




                        } else {


                            Toast.makeText(LoginActivity.this, "EROR", Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                 Toast.makeText(LoginActivity.this,"Terjadi kesalahan,,saat login",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthlistener != null){
            mAuth.removeAuthStateListener(mAuthlistener);
    }




    }
}