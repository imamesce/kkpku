package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class TambahDataWisata extends AppCompatActivity {
    private EditText nama_wisata,dekripsi_wisata,harga_wisata,keterangan_wisata;
    private Button btn_tambahwisata;
    TextView spinner;
  private   ImageView gambar_wisata;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Uri filePath;
    Display mDisplay;
    public static final int READ_PHONE = 110;
    Uri url;
    Spinner tempat_wisata;
    private String TAG;
    private  DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_wisata);
        nama_wisata = findViewById(R.id.edtnamawisata);
        firebaseFirestore = FirebaseFirestore.getInstance();

        storageReference =FirebaseStorage.getInstance().getReference();
        spinner = findViewById(R.id.spinner);
        dekripsi_wisata = findViewById(R.id.edtdeskripsiwisata);
        tempat_wisata =  findViewById(R.id.edttempatwisata);
        harga_wisata = findViewById(R.id.edthargawisata);
        keterangan_wisata = findViewById(R.id.edtketeranganwisata);
        btn_tambahwisata = findViewById(R.id.buttontambahdata);
        gambar_wisata =  findViewById(R.id.gambarwisata);
        firebaseStorage = FirebaseStorage.getInstance();
        Calendar calendar = Calendar.getInstance();
        final int tahun = calendar.get(Calendar.YEAR);
        final int bulan = calendar.get(Calendar.MONTH);
        final int hari = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayAdapter <CharSequence> adapterSn = ArrayAdapter.createFromResource(this,R.array.tempatwisata,android.R.layout.simple_spinner_item);
        adapterSn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tempat_wisata.setAdapter(adapterSn);

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal=spinner.getText().toString().trim();
                if (tanggal.isEmpty()) {
                    spinner.setError("Masukan Tanggal anda..");
                    spinner.requestFocus();
                    return;
                }
                else {


                    DatePickerDialog datePickerDialog = new DatePickerDialog(TambahDataWisata.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListener, tahun, bulan, hari);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                }

            }
        });
        setListener =  new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int tahun, int bulan, int DayOfMonth) {
                bulan = bulan + 1;





               String date = hari+"-"+bulan+"-"+tahun;

                spinner.setText(date);

            }
        };


        tempat_wisata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();



        if(Build.VERSION.SDK_INT >= 23){
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
            }else{
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PHONE);
            }
        }


            gambar_wisata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("gambar/*");
                    startActivityForResult(intent,222);

                }
            });




        btn_tambahwisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting data from edittext fields.
                String Namawisata=nama_wisata.getText().toString().trim();
                String Deskripsiwisata = dekripsi_wisata.getText().toString().trim();
                String Keteranganwisata = keterangan_wisata.getText().toString();
                String Harga = harga_wisata.getText().toString();
               final String Tempatwisata = tempat_wisata.getSelectedItem().toString();


                String Pilih = "Pilih Tanggal";



                if (Namawisata.isEmpty()){
                    nama_wisata.setError("masukin nama wisata");
                    nama_wisata.requestFocus();
                    return;
                }


                if (Deskripsiwisata.isEmpty()){
                    dekripsi_wisata.setError("masukin deskripsi wisata");
                    dekripsi_wisata.requestFocus();
                    return;
                }
                if (Keteranganwisata.isEmpty()){
                    keterangan_wisata.setError("masukin keterangan wisata");
                    keterangan_wisata.requestFocus();
                    return;
                }
                if (Harga.isEmpty()){
                    harga_wisata.setError("masukin harga wisata");
                    harga_wisata.requestFocus();
                    return;
                }



                    if(filePath != null ){
                        uploadfilekefirestore(filePath);
                    }else {
                        Toast.makeText(TambahDataWisata.this,"silahkan pilih gambar",Toast.LENGTH_LONG).show();

                    }

                    // calling method to add data to Firebase Firestore.


            }
        });

    }

    private void uploadfilekefirestore(Uri uri) {
        final StorageReference filereff = storageReference.child("gambar_wisata/"+System.currentTimeMillis()+"."+getFileExtension(uri));
        filereff.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                filereff.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        url = uri;



                        Map<String, Object> user = new HashMap<>();
                        user.put("gambar_wisata", url.toString());
                        user.put("nama_wisata",nama_wisata.getText().toString());
                        user.put("dekripsi_wisata",dekripsi_wisata.getText().toString());
                        user.put("keterangan_wisata",keterangan_wisata.getText().toString());
                        user.put("harga_wisata",harga_wisata.getText().toString());
                        user.put("tempat_wisata",tempat_wisata.getSelectedItem().toString());
                        user.put("keterangan","tersedia");
                        //user.put("tanggal_berangkat",spinner.getText().toString());
                        user.put("tanggal_berangkat",spinner.getText().toString());
                        DocumentReference cb = firebaseFirestore.collection("produk").document();
                        cb.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                onBackPressed();
                            }
                        });
                    }
                });

            }
        });

    }


    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            gambar_wisata.setImageURI(filePath);
        }}
    }