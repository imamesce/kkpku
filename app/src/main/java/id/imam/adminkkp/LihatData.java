package id.imam.adminkkp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.adapter.AdapterBerhasil;
import id.imam.adminkkp.adapter.AdapterNamaBerhasil;
import id.imam.adminkkp.model.ModelBerhasil;
import id.imam.adminkkp.model.ModelNamaberhasil;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Queue;

public class LihatData extends AppCompatActivity {
FirebaseFirestore firebaseFirestore;
TextView totalpemesan,biayawisata,totalpendapatan;
    AdapterNamaBerhasil adapter;

    RecyclerView recyclerViewNama;
    Display mDisplay;
    String imagesUri;
    String path;
    Bitmap bitmap;

    int totalHeight;
    int totalWidth;
    public static final int READ_PHONE = 110;
    String file_name = "Data pelanggan";
    File myPath;
    Button btncetakk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        totalpemesan = findViewById(R.id.textView2);
        biayawisata = findViewById(R.id.textView6);
        totalpendapatan = findViewById(R.id.textView7);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewNama = findViewById(R.id.recycnamaberhasil);
        btncetakk = findViewById(R.id.cetakdata);
       // String idd = firebaseFirestore.collection("order").getId();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(Build.VERSION.SDK_INT >= 24){
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
            }else{
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PHONE);
            }
        }

        btncetakk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                btncetakk.setVisibility(View.GONE);

                takeS();
                btncetakk.setVisibility(View.VISIBLE);
            }
        });

        CollectionReference documentSnapshot = firebaseFirestore.collection("order");
        Query query3 = firebaseFirestore.collection("order").whereEqualTo("keterangan","berhasil").whereEqualTo("id_wisata",getIntent().getStringExtra("id_wisata"));
        FirestoreRecyclerOptions<ModelNamaberhasil> options = new FirestoreRecyclerOptions.Builder<ModelNamaberhasil>()
                .setQuery(query3,ModelNamaberhasil.class)
                .build();
        query3.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

            }
        });

        adapter = new AdapterNamaBerhasil(options);
        recyclerViewNama.setHasFixedSize(true);
        recyclerViewNama.setLayoutManager(new LinearLayoutManager(LihatData.this));

        recyclerViewNama.setAdapter(adapter);
        documentSnapshot.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String id = documentSnapshot.getId();



                Query query2 = firebaseFirestore.collection("order").whereEqualTo("keterangan","berhasil").whereEqualTo("id_wisata",getIntent().getStringExtra("id_wisata"));

                //   DocumentReference coll =firebaseFirestore.collection("order").document(getIntent().getStringExtra("uid"));
                    query2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            adapter.notifyDataSetChanged();

                            if (task.isSuccessful()){
                                long total = 0;
                                long totalll = 0;

                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    long orang = document.getLong("orang");
                                    String hargaaa = document.getString("harga_wisata");
                                    biayawisata.getText().toString();
                                    biayawisata.setText(hargaaa);
                                    String hargawisata = document.getString("harga_wisata");
                                    long hargaa = Long.parseLong(hargawisata);
                                   totalll+=hargaa;
                                    String harge = ""+totalll;
                                    totalpendapatan.getText().toString();
                                    totalpendapatan.setText(harge);


                                    total += orang;
                                    String totall = "" + total;
                                    totalpemesan.getText().toString();
                                    totalpemesan.setText(totall);

                                }
                            }
                        }
                    });


            }
        });





    }


    public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth){

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();

        if(bgDrawable != null){
            bgDrawable.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }

        view.draw(canvas);
        return returnedBitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void takeS() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BuktiBayarWisata/");

        if(!folder.exists()){
            boolean success = folder.mkdir();
        }

        path = folder.getAbsolutePath();
        path = path + "/" + file_name + System.currentTimeMillis() + ".pdf";

        View u = findViewById(R.id.cetaknested);

        NestedScrollView z = findViewById(R.id.cetaknested);
        totalHeight = z.getChildAt(0).getHeight();
        totalWidth = z.getChildAt(0).getWidth();

        String extr = Environment.getExternalStorageDirectory() + "/lihat data/";
        File file = new File(extr);
        if(!file.exists())
            file.mkdir();
        String fileName = file_name + ".jpg";
        myPath = new File(extr, fileName);
        imagesUri = myPath.getPath();
        bitmap = getBitmapFromView(u, totalHeight, totalWidth);

        try{
            FileOutputStream fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        createPdf();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);

        Bitmap bitmap = Bitmap.createScaledBitmap(this.bitmap, this.bitmap.getWidth(), this.bitmap.getHeight(), true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        File filePath = new File(path);
        try{
            document.writeTo(new FileOutputStream(filePath));
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Something Wrong: "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        document.close();

        if (myPath.exists())
            myPath.delete();

        openPdf(path);

    }

    private void openPdf(String pathh) {
       // File file = new File(path);
        Intent target = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(LihatData.this, BuildConfig.APPLICATION_ID + ".provider", new File(pathh));
        target.setDataAndType(uri, "application/pdf");

        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open FIle");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION  | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        try{
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "No Apps to read PDF FIle", Toast.LENGTH_SHORT).show();
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