package id.imam.adminkkp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.adapter.AdapterKonfirmasiPembatalan;
import id.imam.adminkkp.adapter.AdapterSemuaAdmin;
import id.imam.adminkkp.model.ModelKonfirmasiPembatalan;
import id.imam.adminkkp.model.ModelSemuaAdmin;
import id.imam.adminkkp.model.ModelSemuaUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SemuaDataAdmin extends AppCompatActivity {
    CardView updateadmin;
    FirebaseFirestore firebaseFirestore;
    RecyclerView semuaadmin;
    AdapterSemuaAdmin adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semua_data_admin);
       updateadmin = findViewById(R.id.updateadmin);
       firebaseFirestore = FirebaseFirestore.getInstance();
       semuaadmin = findViewById(R.id.recycsemuaadmin);

        Query query2 = firebaseFirestore.collection("admin").whereEqualTo("status","true");
        FirestoreRecyclerOptions<ModelSemuaAdmin> options = new FirestoreRecyclerOptions.Builder<ModelSemuaAdmin>()
                .setQuery(query2, ModelSemuaAdmin.class)
                .build();
        adapter = new AdapterSemuaAdmin(options);
        semuaadmin.setHasFixedSize(true);
        semuaadmin.setLayoutManager(new LinearLayoutManager(this));
        semuaadmin.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterSemuaAdmin.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                String namaadmin = documentSnapshot.getString("username");
                String email = documentSnapshot.getString("email");
                String id_admin = documentSnapshot.getString("id_admin");

                Toast.makeText(SemuaDataAdmin.this, "id" + id, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SemuaDataAdmin.this, UpdateAdmin.class);
                intent.putExtra("id_admin","b"+id_admin);
                intent.putExtra("id",id);
                intent.putExtra("username",namaadmin);
                intent.putExtra("email",email);

                startActivity(intent);



            }
        });

        updateadmin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent aaaa = new Intent(SemuaDataAdmin.this,TambahAdmin.class);
               startActivity(aaaa);
           }
       });



    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}