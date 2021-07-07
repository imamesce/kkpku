package id.imam.adminkkp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.R;
import id.imam.adminkkp.model.ModelKonfirmasiPembatalan;

public class AdapterKonfirmasiPembatalan extends FirestoreRecyclerAdapter<ModelKonfirmasiPembatalan,AdapterKonfirmasiPembatalan.AdapterHolderPembatalan> {
    OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterKonfirmasiPembatalan(@NonNull FirestoreRecyclerOptions<ModelKonfirmasiPembatalan> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHolderPembatalan adapterHolderPembatalan, int i, @NonNull ModelKonfirmasiPembatalan modelKonfirmasiPembatalan) {
        adapterHolderPembatalan.hargaorderwisata.setText(modelKonfirmasiPembatalan.getHarga_wisata());
        adapterHolderPembatalan.namaorderwisata.setText(modelKonfirmasiPembatalan.getNama_pemesan());
        adapterHolderPembatalan.waktuorder.setText(modelKonfirmasiPembatalan.getAlamat_email());
       // adapterHolderPembatalan.keteranganwisata.setText(modelKonfirmasiPembatalan.getKeterangan_pembatalan());
        adapterHolderPembatalan.ketorder.setText(modelKonfirmasiPembatalan.getTelepon_pemesan());
    }

    @NonNull
    @Override
    public AdapterHolderPembatalan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vp = LayoutInflater.from(parent.getContext()).inflate(R.layout.pembatalan, parent, false);
        return new AdapterHolderPembatalan(vp);
    }

    public class AdapterHolderPembatalan extends RecyclerView.ViewHolder {
        TextView namaorderwisata,hargaorderwisata,ketorder,keteranganwisata,waktuorder;
        public AdapterHolderPembatalan(@NonNull View itemView) {
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
