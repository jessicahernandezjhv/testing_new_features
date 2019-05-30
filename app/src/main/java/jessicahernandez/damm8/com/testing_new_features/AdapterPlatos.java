package jessicahernandez.damm8.com.testing_new_features;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPlatos extends RecyclerView.Adapter<AdapterPlatos.ViewHolderPlatos> {
    ArrayList<ModelPlatos> listaPlatos;

    public AdapterPlatos(ArrayList<ModelPlatos> listaPlatos) {
        this.listaPlatos = listaPlatos;
    }

    @NonNull
    @Override
    public AdapterPlatos.ViewHolderPlatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plato_item, null, false);
        return new ViewHolderPlatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlatos.ViewHolderPlatos viewHolderPlatos, int position) {
        viewHolderPlatos.nombrePlato.setText("Nombre: "+listaPlatos.get(position).getNombre());
        viewHolderPlatos.ingredientesPlato.setText("Ingredientes: "+listaPlatos.get(position).getIngredientes());
        viewHolderPlatos.precioPlato.setText("Precio: "+ listaPlatos.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public class ViewHolderPlatos extends RecyclerView.ViewHolder {
        TextView nombrePlato;
        TextView ingredientesPlato;
        TextView precioPlato;

        public ViewHolderPlatos(@NonNull View itemView) {
            super(itemView);
            nombrePlato = itemView.findViewById(R.id.nombrePlatoID);
            ingredientesPlato = itemView.findViewById(R.id.ingredientesPlatoID);
            precioPlato = itemView.findViewById(R.id.precioPlatoID);
        }
    }
}
