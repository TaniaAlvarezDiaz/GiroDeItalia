package es.uniovi.sdm.alvfer.girodeitalia.vista;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;


public class InformacionEtapaFragment extends Fragment {

    private Etapa etapa;

    public InformacionEtapaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.etapa =  arguments.getParcelable(EtapaActivity.ETAPA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_informacion_etapa, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView textViewPrueba = getActivity().findViewById(R.id.textViewPrueba);
        textViewPrueba.setText(etapa.toString());
        TextView textViewPrueba2 = getActivity().findViewById(R.id.textViewPrueba2);
        textViewPrueba2.setText(etapa.toString());
    }

}
