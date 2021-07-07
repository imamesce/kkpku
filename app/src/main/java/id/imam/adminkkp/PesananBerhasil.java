package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.adapter.AdapterBerhasil;
import id.imam.adminkkp.model.ModelBerhasil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class PesananBerhasil extends AppCompatActivity {
  private   FirebaseFirestore firebaseFirestore;
    AdapterBerhasil adapter;
    RecyclerView recyclerViewF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_berhasil);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewF = findViewById(R.id.pesanberhasil);


        Query query2 = firebaseFirestore.collection("produk").whereEqualTo("keterangan","tersedia");

        FirestoreRecyclerOptions<ModelBerhasil> options = new FirestoreRecyclerOptions.Builder<ModelBerhasil>()
                .setQuery(query2,ModelBerhasil.class)
                .build();
        query2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                adapter.notifyDataSetChanged();

            }
        });
        adapter = new AdapterBerhasil(options);
        recyclerViewF.setHasFixedSize(true);
        recyclerViewF.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewF.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterBerhasil.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                Toast.makeText(PesananBerhasil.this, "id" + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PesananBerhasil.this,LihatData.class);
                intent.putExtra("id_wisata",id);
                startActivity(intent);


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