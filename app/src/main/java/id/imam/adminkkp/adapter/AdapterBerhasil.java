package id.imam.adminkkp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.AdapterStatusPelanggan;
import id.imam.adminkkp.R;
import id.imam.adminkkp.model.ModelBerhasil;

public class AdapterBerhasil  extends FirestoreRecyclerAdapter<ModelBerhasil,AdapterBerhasil.AdapterHolderBerhasil> {
    OnItemClickListener listener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterBerhasil(@NonNull FirestoreRecyclerOptions<ModelBerhasil> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHolderBerhasil adapterHolderBerhasil, int i, @NonNull ModelBerhasil modelBerhasil) {
        adapterHolderBerhasil.tempatWisata.setText(modelBerhasil.getTempat_wisata());
        Picasso.get().load(modelBerhasil.getGambar_wisata()).into(adapterHolderBerhasil.imagehome);


    }

    @NonNull
    @Override
    public AdapterHolderBerhasil onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pp= LayoutInflater.from(parent.getContext()).inflate(R.layout.berhasil, parent, false);
        return new AdapterHolderBerhasil(pp);
    }

    public class AdapterHolderBerhasil extends RecyclerView.ViewHolder {
        private TextView tempatWisata;
        private ImageView imagehome;
        public AdapterHolderBerhasil(@NonNull View itemView) {
            super(itemView);
            tempatWisata = itemView.findViewById(R.id.textView);
            imagehome = itemView.findViewById(R.id.imageHome);
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
            this.listener =  listener;
        }

    }
