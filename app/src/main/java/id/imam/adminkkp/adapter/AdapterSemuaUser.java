package id.imam.adminkkp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.imam.adminkkp.R;
import id.imam.adminkkp.model.ModelSemuaUser;

public class AdapterSemuaUser extends FirestoreRecyclerAdapter<ModelSemuaUser,AdapterSemuaUser.AdapterHolderuser> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterSemuaUser(@NonNull FirestoreRecyclerOptions<ModelSemuaUser> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHolderuser adapterHolderuser, int i, @NonNull ModelSemuaUser modelSemuaUser) {
        adapterHolderuser.namauser.setText(modelSemuaUser.getUsername());
       // adapterHolderuser.biodatauser.setText(modelSemuaUser.getBiodata());
        adapterHolderuser.teleponuser.setText(modelSemuaUser.getTelepon());
        adapterHolderuser.emailuser.setText(modelSemuaUser.getEmail());
    }

    @NonNull
    @Override
    public AdapterHolderuser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.semua_user, parent, false);
        return new AdapterHolderuser(vv);
    }

    public class AdapterHolderuser extends RecyclerView.ViewHolder {
        private TextView namauser,emailuser,teleponuser,biodatauser;
        public AdapterHolderuser(@NonNull View itemView) {
            super(itemView);
            namauser = itemView.findViewById(R.id.textView15);
            emailuser = itemView.findViewById(R.id.textView17);
            teleponuser = itemView.findViewById(R.id.textView16);
        }
    }
}
