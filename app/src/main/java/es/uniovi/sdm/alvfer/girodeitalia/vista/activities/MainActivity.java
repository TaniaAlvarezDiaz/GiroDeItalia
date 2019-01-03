package es.uniovi.sdm.alvfer.girodeitalia.vista.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.uniovi.sdm.alvfer.girodeitalia.datos.utilidades.FirebaseUtilidades;
import es.uniovi.sdm.alvfer.girodeitalia.vista.utilidades.ExpandableListAdapter;
import es.uniovi.sdm.alvfer.girodeitalia.R;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expListView;
    ExpandableListAdapter listAdapterExpandable;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private Intent intent;
    public static final String OPCION_ESCOGIDA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Obtener imageView del logo y la mascota de la pantalla inicial
        ImageView logoImage = (ImageView) findViewById(R.id.logoGiro);
        StorageReference imageStorageReference = FirebaseStorage.getInstance().getReference().child("Logo");
        StorageReference photoRef = imageStorageReference.child("Logo.png");
        Glide.with(this).load(photoRef).apply(new RequestOptions().error(R.drawable.error_imagen)).into(logoImage);

        ImageView mascotaImage = (ImageView) findViewById(R.id.mascotaGiro);
        imageStorageReference = FirebaseStorage.getInstance().getReference().child("Mascota");
        photoRef = imageStorageReference.child("Mascota.png");
        Glide.with(this).load(photoRef).apply(new RequestOptions().error(R.drawable.error_imagen)).into(mascotaImage);

        //Obtener ExpandableListView
        expListView = (ExpandableListView) findViewById(R.id.expandable_list);
        //Preparar datos para Header y Listado en ExpandableListView.
        prepareListData();
        //Crear Adapter.
        listAdapterExpandable = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        //Configurar Adapter en ExpandableListView.
        expListView.setAdapter(listAdapterExpandable);
        //Para expandir los grupos por default.
        int count = listAdapterExpandable.getGroupCount();
        for (int i = 0; i < count; i++) {
            //Si no es el grupo "Historia" lo expando
            if (i != 3) {
                expListView.expandGroup(i);
            }
        }

        //Cambiar el groupIndicator (flecha)
        expListView.setGroupIndicator(getResources().getDrawable(R.drawable.my_group_statelist));

        // Crear intent
        intent = new Intent(this, MainActivity2.class);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //Si no tiene hijos llamamos a la activity correspondiente
                if (listDataChild.get(listDataHeader.get(groupPosition)).isEmpty()) {
                    if (listDataHeader.get(groupPosition).toString().equals("Recorrido general")) {
                        intent = new Intent(getApplicationContext(), RecorridoActivity.class);
                    } else if (listDataHeader.get(groupPosition).toString().equals("Etapas")) {
                        intent = new Intent(getApplicationContext(), EtapasActivity.class);
                    } else if (listDataHeader.get(groupPosition).toString().equals("Calendario")) {
                        intent = new Intent(getApplicationContext(), CalendarioActivity.class);
                    } else if (listDataHeader.get(groupPosition).toString().equals("Patrimonio")) {
                        intent = new Intent(getApplicationContext(), PatrimonioActivity.class);
                    }
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (listDataChild.get(listDataHeader.get
                        (groupPosition)).get(childPosition).toString().equals("Palmarés")) {
                    intent = new Intent(getApplicationContext(), PalmaresActivity.class);
                } else {
                    intent.putExtra(OPCION_ESCOGIDA, listDataChild.get(listDataHeader.get
                            (groupPosition)).get(childPosition).toString());
                }
                startActivity(intent);
                return true;
            }
        });


        // Operaciones con la BBDD, colocarlas aquí para que se hagan al iniciar la app
        //FirebaseUtilidades.rellenarElementosPatrimonio();
        //FirebaseUtilidades.rellenarEtapas();
        //FirebaseUtilidades.rellenarCalendario();
        //FirebaseUtilidades.rellenarGanadores();
    }

    /**
     * Método para crear las secciones del menú
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Agrega Encabezados.
        listDataHeader.add("Recorrido general");
        listDataHeader.add("Etapas");
        listDataHeader.add("Calendario");
        listDataHeader.add("Historia");
        listDataHeader.add("Patrimonio");

        // Agrega datos.
        List<String> historia = new ArrayList<String>();
        historia.add("Palmarés");
        historia.add("Lugares míticos");

        List<String> vacio = new ArrayList<String>();

        listDataChild.put(listDataHeader.get(0), vacio);
        listDataChild.put(listDataHeader.get(1), vacio);
        listDataChild.put(listDataHeader.get(2), vacio);
        listDataChild.put(listDataHeader.get(3), historia);
        listDataChild.put(listDataHeader.get(4), vacio);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
