package jessicahernandez.damm8.com.testing_new_features;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlatosAdapter extends RecyclerView.Adapter<PlatosAdapter.ViewHolderPlatos> {
    ArrayList<PlatosModel> platos;

    public PlatosAdapter(ArrayList<PlatosModel> platos) {
        this.platos = platos;
    }

    @NonNull
    @Override
    public PlatosAdapter.ViewHolderPlatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reserva_item, null, false);
        return  new ViewHolderPlatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatosAdapter.ViewHolderPlatos viewHolderPlatos, int i) {

    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class ViewHolderPlatos extends RecyclerView.ViewHolder {
        public ViewHolderPlatos(@NonNull View itemView) {
            super(itemView);

        }
    }
}
