package es.uniovi.sdm.alvfer.girodeitalia.vista;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import es.uniovi.sdm.alvfer.girodeitalia.R;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;


public class PatrimonioHistoricoFragment extends ListFragment {

    private ArrayList<ElementoPatrimonio> elementosPatrimonio;

    public PatrimonioHistoricoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            elementosPatrimonio = arguments.getParcelableArrayList(NuevaPatrimonioActivity
                    .ELEMENTOS_PATRIMONIO_HISTORICO);
            Log.d("ARGUMENTS", "Elementos del patrimonio recibidos por el fragment: " + elementosPatrimonio);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<ElementoPatrimonio>(getActivity(), android.R.layout
                .simple_list_item_1, elementosPatrimonio));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ElementoPatrimonio elementoPatrimonio = elementosPatrimonio.get(position);
        Intent intent = new Intent(getActivity().getApplicationContext(), ElementoPatrimonioActivity
                .class);
        intent.putExtra(NuevaPatrimonioActivity.ELEMENTO_PATRIMONIO, elementoPatrimonio);
        startActivity(intent);
    }

}
