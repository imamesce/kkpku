package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.model.ModelStatusPelanggan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

public class StatusPesananPelanggan extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerViewpesanan;
    AdapterStatusPelanggan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pesanan_pelanggan);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewpesanan = findViewById(R.id.recyclerviewpesanananda);
        recyclerViewpesanan.setHasFixedSize(true);
        recyclerViewpesanan.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewpesanan.setAdapter(adapter);

        Query query2 = firebaseFirestore.collection("order").whereEqualTo("keterangan","sedang di proses");
        FirestoreRecyclerOptions<ModelStatusPelanggan> options = new FirestoreRecyclerOptions.Builder<ModelStatusPelanggan>()
                .setQuery(query2,ModelStatusPelanggan.class)
                .build();
        adapter = new AdapterStatusPelanggan(options);
        recyclerViewpesanan.setHasFixedSize(true);
        recyclerViewpesanan.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewpesanan.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterStatusPelanggan.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
               //ini integer Long harga = documentSnapshot.getLong("harga_wisata");
                String harga = documentSnapshot.getString("harga_wisata");
                String namawisata = documentSnapshot.getString("nama_wisata");
                String keterangan_wisata = documentSnapshot.getString("keterangan_wisata");
                String keterangan =  documentSnapshot.getString("keterangan");
                String alamatemail =  documentSnapshot.getString("alamat_email");
                String gambarwisata = documentSnapshot.getString("Image");
                String tempatwisata = documentSnapshot.getString("tempat_wisata");
                Date time = documentSnapshot.getDate("dibuat");


                Toast.makeText(StatusPesananPelanggan.this, "id" + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(StatusPesananPelanggan.this, UpdatePesananPelanggan.class);
                //get id
              //intent integer..  intent.putExtra("harga",""+harga);
                intent.putExtra("harga_wisata",harga);
                intent.putExtra("id_order",id);
                intent.putExtra("nama_wisata",namawisata);
                intent.putExtra("keterangan_wisata",keterangan_wisata);
                intent.putExtra("keterangan",keterangan);
                intent.putExtra("alamat_email",alamatemail);
                intent.putExtra("dibuat",time);
                intent.putExtra("Image",gambarwisata);
                intent.putExtra("tempat_wisata",tempatwisata);
                startActivity(intent);

            }
        });


    }
    private class productViewholder extends RecyclerView.ViewHolder {
        TextView pesanan;
        public productViewholder(@NonNull View itemView) {
            super(itemView);
            pesanan = itemView.findViewById(R.id.textpesannama);

        }
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