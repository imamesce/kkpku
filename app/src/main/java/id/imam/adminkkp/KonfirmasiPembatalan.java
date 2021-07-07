package id.imam.adminkkp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.adapter.AdapterKonfirmasiPembatalan;
import id.imam.adminkkp.model.ModelKonfirmasiPembatalan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class KonfirmasiPembatalan extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    AdapterKonfirmasiPembatalan adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembatalan);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyckonfirmasipembatalan);




        Query query2 = firebaseFirestore.collection("pembatalan_order").whereEqualTo("keterangan_pembatalan","false");
        FirestoreRecyclerOptions<ModelKonfirmasiPembatalan> options = new FirestoreRecyclerOptions.Builder<ModelKonfirmasiPembatalan>()
                .setQuery(query2,ModelKonfirmasiPembatalan.class)
                .build();

        adapter = new AdapterKonfirmasiPembatalan(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterKonfirmasiPembatalan.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                String harga = documentSnapshot.getString("harga_wisata");

                Toast.makeText(KonfirmasiPembatalan.this, "id" + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(KonfirmasiPembatalan.this, UpdatePembatalan.class);
                intent.putExtra("id_order",id);
                intent.putExtra("harga_wisata",harga);

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