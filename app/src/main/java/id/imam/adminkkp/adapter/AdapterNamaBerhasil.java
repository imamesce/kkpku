package id.imam.adminkkp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.R;
import id.imam.adminkkp.model.ModelNamaberhasil;

public class AdapterNamaBerhasil extends FirestoreRecyclerAdapter<ModelNamaberhasil,AdapterNamaBerhasil.AdapterHoldeNama> {
    AdapterBerhasil.OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterNamaBerhasil(@NonNull FirestoreRecyclerOptions<ModelNamaberhasil> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHoldeNama adapterHoldeNama, int i, @NonNull ModelNamaberhasil modelNamaberhasil) {
        adapterHoldeNama.namapemesan.setText(modelNamaberhasil.getNama_pemesan());
    }

    @NonNull
    @Override
    public AdapterHoldeNama onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ppp= LayoutInflater.from(parent.getContext()).inflate(R.layout.nama_berhasil, parent, false);
        return new AdapterHoldeNama(ppp);
    }

    public class AdapterHoldeNama extends RecyclerView.ViewHolder {
        TextView namapemesan;
        public AdapterHoldeNama(@NonNull View itemView) {
            super(itemView);

            namapemesan=itemView.findViewById(R.id.namaygikut);
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null){
                listener.onItemClick(getSnapshots().getSnapshot(position),position);
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);

    }
    public void  setOnItemClickListener(AdapterBerhasil.OnItemClickListener listener){
        this.listener =  listener;
    }
}
