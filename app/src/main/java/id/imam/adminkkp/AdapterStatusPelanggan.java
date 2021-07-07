package id.imam.adminkkp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.model.ModelStatusPelanggan;

public class AdapterStatusPelanggan extends FirestoreRecyclerAdapter<ModelStatusPelanggan,AdapterStatusPelanggan.AdapterHolder>{
    OnItemClickListener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterStatusPelanggan(@NonNull FirestoreRecyclerOptions<ModelStatusPelanggan> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHolder adapterHolder, int i, @NonNull ModelStatusPelanggan modelStatusPelanggan) {
        adapterHolder.namaorderwisata.setText(modelStatusPelanggan.getNama_wisata());
        adapterHolder.hargaorderwisata.setText(String.valueOf(modelStatusPelanggan.getHarga_wisata()));
        adapterHolder.keteranganwisata.setText(modelStatusPelanggan.getKeterangan_wisata());
        adapterHolder.ketorder.setText(modelStatusPelanggan.getKeterangan());

        //adapterHolder.idtransaksiku.setText(modelPesan.getHarga());
        SimpleDateFormat spf = new SimpleDateFormat("MMM-dd-yyyy 'jam' hh:mm:ss z");
        String date = spf.format(modelStatusPelanggan.getDibuat());
        adapterHolder.waktuorder.setText(date);
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_statuspelanggan, parent, false);
        return new AdapterHolder(v);    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView namaorderwisata,hargaorderwisata,ketorder,keteranganwisata,waktuorder;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            namaorderwisata = itemView.findViewById(R.id.textpesannama);
            waktuorder = itemView.findViewById(R.id.idwaktu);
            hargaorderwisata=itemView.findViewById(R.id.hargawisata);
            ketorder = itemView.findViewById(R.id.keterangan);
            keteranganwisata = itemView.findViewById(R.id.orderketeranganwisata);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);


                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);

    }
    public void  setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
