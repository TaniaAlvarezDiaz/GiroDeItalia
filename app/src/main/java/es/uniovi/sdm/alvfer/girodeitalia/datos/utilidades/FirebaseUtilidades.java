package es.uniovi.sdm.alvfer.girodeitalia.datos.utilidades;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.ElementoPatrimonio;
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Etapa;

public class FirebaseUtilidades {

    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference elementosPatrimonioDatabaseReference = firebaseDatabase
            .getReference()
            .child("ElementosPatrimonio");
    public static DatabaseReference etapasDatabaseReference = firebaseDatabase
            .getReference()
            .child("Etapas");

    /**
     * Método para borrar todos los elementos del patrimonio que hay en la BBDD y volver a
     * introducirlos
     */
    public static void rellenarElementosPatrimonio() {

        // Borrar los elementos del patrimonio de la BBDD
        Query queryRef = elementosPatrimonioDatabaseReference.orderByChild("nombre");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int elementosPatrimonioEliminados = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                    elementosPatrimonioEliminados++;
                }
                Log.d("FIREBASE", "Elementos del patrimonio eliminados: " +
                        elementosPatrimonioEliminados);

                // Crear los elementos del patrimonio
                ArrayList<ElementoPatrimonio> elementosPatrimonio = new ArrayList<>();
                /*elementosPatrimonio.add(new ElementoPatrimonio("Basílica de San Petronio", "La " +
                        "Basílica " +
                        "de San Petronio es la iglesia más antigua y grande de Bolonia, y una de " +
                        "las más " +
                        "grandes del mundo.", 1, "Bolonia", "BasilicaDeSanPetronio.jpg",
                        "historico"));
                elementosPatrimonio.add(new ElementoPatrimonio("Torres de los Asinelli y " +
                        "Garisenda", "En " +
                        "el pasado la ciudad de Bolonia tenía varias torres con funciones de " +
                        "defensa y " +
                        "señalización. Hoy en día solo quedan unas veinte, de las cuales las más " +
                        "famosas " +
                        "son estas dos.", 1, "Bolonia", "TorresDeLosAsinelliYGarisenda.jpg",
                        "historico"));*/

                for (int i = 0; i < 40; i++) {
                    elementosPatrimonio.add(new ElementoPatrimonio("EPCultural" + i, "Una descripcion",
                            1, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "cultural"));
                }

                for (int i = 0; i < 40; i++) {
                    elementosPatrimonio.add(new ElementoPatrimonio("EPGeografico" + i, "Una descripcion",
                            2, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "geografico"));
                }

                for (int i = 0; i < 40; i++) {
                    elementosPatrimonio.add(new ElementoPatrimonio("EPHistorico" + i, "Una descripcion",
                            3, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "historico"));
                }
                /*
                // Elementos del patrimonio inventados
                elementosPatrimonio.add(new ElementoPatrimonio("EPCultural1", "Una descripcion",
                        1, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "cultural"));
                elementosPatrimonio.add(new ElementoPatrimonio("EPCultural2", "Una descripcion",
                        1, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "cultural"));
                elementosPatrimonio.add(new ElementoPatrimonio("EPGeografico1", "Una descripcion",
                        1, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "geografico"));
                elementosPatrimonio.add(new ElementoPatrimonio("EPGeografico2", "Una descripcion",
                        1, "Un lugar", "TorresDeLosAsinelliYGarisenda.jpg", "geografico"));*/

                Log.d("FIREBASE", "Elementos del patrimonio a insertar: " + elementosPatrimonio
                        .size());

                // Introducir los elementos del patrimonio en la BBDD
                int elementosPatrimonioInsertados = 0;
                for (ElementoPatrimonio elementoPatrimonio : elementosPatrimonio) {
                    elementosPatrimonioDatabaseReference.push().setValue(elementoPatrimonio);
                    elementosPatrimonioInsertados++;
                }
                Log.d("FIREBASE", "Elementos del patrimonio insertados: " +
                        elementosPatrimonioInsertados);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo.");
            }
        });

    }


    /**
     * Método para borrar todas las etapas que hay en la BBDD y volver a
     * introducirlas
     */
    public static void rellenarEtapas() {

        // Borrar los elementos del patrimonio de la BBDD
        Query queryRef = etapasDatabaseReference.orderByChild("salida");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int etapasEliminadas = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                    etapasEliminadas++;
                }
                Log.d("FIREBASE", "Etapas elimindas: " +
                        etapasEliminadas);

                // Crear las etapas
                // https://es.wikipedia.org/wiki/Giro_de_Italia_2019#Clasificaciones_finales
                // Hay 3 contrarrelojes individuales, en las etapas 1, 9 y 21
                ArrayList<Etapa> etapas = new ArrayList<>();
                etapas.add(new Etapa("Bologna", "Bologna (San Luca)", "Contrarreloj individual", 1, 8, "11-Mayo"));
                etapas.add(new Etapa("Bologna", "Fucecchio", "Media montaña", 2, 200, "12-Mayo"));
                etapas.add(new Etapa("Vinci", "Orbetello", "Media montaña", 3, 219, "13-Mayo"));
                etapas.add(new Etapa("Orbetello", "Frascati", "Media montaña", 4, 228, "14-Mayo"));
                etapas.add(new Etapa("Frascati", "Terracina", "Llana", 5, 140, "15-Mayo"));
                etapas.add(new Etapa("Cassino", "San Giovanni Rotondo", "Media montaña", 6, 233, "16-Mayo"));
                etapas.add(new Etapa("Vasto", "L'Aquila", "Media montaña", 7, 180, "17-Mayo"));
                etapas.add(new Etapa("Tortoreto Lido", "Pesaro", "Media montaña", 8, 235, "18-Mayo"));
                etapas.add(new Etapa("Riccione", "San Marino", "Contrarreloj individual", 9, 35, "19-Mayo"));
                etapas.add(new Etapa("Ravenna", "Modena", "Llana", 10, 147, "21-Mayo"));



                Log.d("FIREBASE", "Etapas a insertar: " + etapas
                        .size());

                // Introducir los elementos del patrimonio en la BBDD
                int etapasInsertadas = 0;
                for (Etapa etapa : etapas) {
                    etapasDatabaseReference.push().setValue(etapa);
                    etapasInsertadas++;
                }
                Log.d("FIREBASE", "Etapas insertadas: " +
                        etapasInsertadas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo.");
            }
        });

    }
}
