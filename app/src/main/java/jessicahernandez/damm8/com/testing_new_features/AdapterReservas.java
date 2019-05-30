package jessicahernandez.damm8.com.testing_new_features;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterReservas extends RecyclerView.Adapter<AdapterReservas.ReservasViewHolder> {
    // Declaramos una lista para que almacene los datos de firebase
    ArrayList<ModelReservas> listaReservas;
    //Intent intent;

    public AdapterReservas(ArrayList<ModelReservas> listaReservas) {
        this.listaReservas = listaReservas;
    }

    @NonNull
    @Override
    public ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reserva_item, null, false);
        return new ReservasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasViewHolder reservasViewHolder, int position) {
        reservasViewHolder.verFecha.setText("Fecha: "+listaReservas.get(position).getFecha());
        reservasViewHolder.verComensales.setText("Personas: "+listaReservas.get(position).getComensales());
    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }


    public class ReservasViewHolder extends RecyclerView.ViewHolder {
        TextView verFecha;
        TextView verComensales;

        public ReservasViewHolder(@NonNull View itemView) {
            super(itemView);
            verFecha = itemView.findViewById(R.id.verFechaID);
            verComensales = itemView.findViewById(R.id.verComensalesID);

            /*
            // Enviamos los datos a la Activity con la vista detalle
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    intent = new Intent(view.getContext(), ModelReservas.class);
                    intent.putExtra("fecha_reserva", listaReservas.get(position).getFecha());
                    intent.putExtra("comensales_reserva", listaReservas.get(position).getComensales());
                    view.getContext().startActivity(intent);
                }
            }); */
        }

        /*public void asignarDatos(ModelReservas hacerReservaModel) {

        }*/
    }
}