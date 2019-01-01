package es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Dia;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.MyViewHolder> {

    private List<Dia> dias;

    public CalendarioAdapter(List<Dia> dias) {
        this.dias = dias;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fecha, evento;

        public MyViewHolder(View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.textViewFechaCalendario);
            evento = itemView.findViewById(R.id.textViewEvento);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .calendario_recycler_view_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Dia dia = dias.get(position);
        holder.fecha.setText(dia.getFecha());
        holder.evento.setText(dia.getEvento());
    }

    @Override
    public int getItemCount() {
        return dias.size();
    }
}

