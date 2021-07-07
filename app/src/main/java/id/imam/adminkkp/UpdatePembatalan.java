package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Document;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdatePembatalan extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    private Button konfirmasisetuju;
    TextView masukantotaluang;
    TextView textView20;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    //FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pembatalan);
       // firebaseFirestore = FirebaseFirestore.getInstance();
        konfirmasisetuju = findViewById(R.id.buttonsetuju);
        masukantotaluang =findViewById(R.id.masukanuang);
        textView20 = findViewById(R.id.textView20);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        textView20.setText(getIntent().getStringExtra("harga_wisata"));
        String hargaa = getIntent().getStringExtra("harga_wisata");
        String adminnn =firebaseAuth.getCurrentUser().getUid();
        long totalll = Long.parseLong(hargaa);
        long diskon = ((totalll*30)/100);
        long jumlah = totalll-diskon;
        long diskon2 = jumlah;
           totalll-=diskon2;
           String harge = ""+totalll;


        masukantotaluang.getText().toString();
        masukantotaluang.setText(harge);


                /**
        long hargaa = Long.parseLong(hargawisata);
        totalll+=hargaa;
        String harge = ""+totalll;
        totalpendapatan.getText().toString();
        totalpendapatan.setText(harge);
       */




        konfirmasisetuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masukinuang = masukantotaluang.getText().toString().trim();
                if (TextUtils.isEmpty(masukinuang)){
                    masukantotaluang.setError("Total transfer harus di isi");
                    masukantotaluang.requestFocus();
                    return;
                }
              //ini buat get semua  CollectionReference documentSnapshot = firebaseFirestore.collection("admin");
                firebaseFirestore.collection("admin").document(adminnn).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot documentSnapshot1 = task.getResult();
                            if (documentSnapshot1.exists()){
                                String  namaadmin = documentSnapshot1.getString("username");
                                String paterrn = "EEEE,dd MMMM yyyy";
                                DateFormat dateFormat = new SimpleDateFormat(paterrn);
                                Date date = Calendar.getInstance().getTime();
                                String todayy = dateFormat.format(date);
                                String hariini = ""+todayy;





                                Map<String, Object> user = new HashMap<>();
                                user.put("status_pembatalan","berhasil di proses");
                                user.put("keterangan_transfer","silahkan cek rekening anda");
                                user.put("status_transfer","true");
                                user.put("keterangan_pembatalan","true");
                                user.put("statuspembatalan","0");
                                user.put("total_transfer",masukinuang);
                                // user.put("waktu_batal",dateFormat);
                                user.put("waktu_batal",hariini);
                                user.put("nama_admin",namaadmin);

                                firebaseFirestore.collection("pembatalan_order").document(getIntent().getStringExtra("id_order")).set(user,SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Map<String, Object> user1 = new HashMap<>();

                                        firebaseFirestore.collection("order").document(getIntent().getStringExtra("id_order")).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                onBackPressed();
                                            }
                                        });
                                    }
                                });

                            }
                        }


                    }
                });
               // String hari = "EEEE-dd MMMM yyyy"+;









            }
        });



    }
}