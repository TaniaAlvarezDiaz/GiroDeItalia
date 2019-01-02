package es.uniovi.sdm.alvfer.girodeitalia.datos.utilidades;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Dia;
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
    public static DatabaseReference calendarioDatabaseReference = firebaseDatabase
            .getReference()
            .child("Calendario");

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
                elementosPatrimonio.add(new ElementoPatrimonio("Basílica de San Petronio", "La " +
                        "Basílica " +
                        "de San Petronio es la iglesia más antigua y grande de Bologna, y una de " +
                        "las más " +
                        "grandes del mundo.", 1, "Bologna", "BasilicaDeSanPetronio.jpg",
                        "historico", 44.493204, 11.343118));
                elementosPatrimonio.add(new ElementoPatrimonio("Torres de los Asinelli y " +
                        "Garisenda", "En " +
                        "el pasado la ciudad de Bologna tenía varias torres con funciones de " +
                        "defensa y " +
                        "señalización. Hoy en día solo quedan unas veinte, de las cuales las más " +
                        "famosas " +
                        "son estas dos.", 1, "Bologna", "TorresDeLosAsinelliYGarisenda.jpg",
                        "historico", 44.494236, 11.346732));
                elementosPatrimonio.add(new ElementoPatrimonio("Santuario de San Luca", "El " +
                        "santuario de Nuestra Señora de San Luca, ubicado sobre la cima del Colle" +
                        " della Guardia, está dedicado al culto católico mariano y es la meta de " +
                        "una gran cantidad de peregrinos todos los años.", 1, "Bologna",
                        "SantuarioDeSanLuca.jpg", "historico", 44.479322, 11.298053));
                elementosPatrimonio.add(new ElementoPatrimonio("Pinacoteca Nacional de Bologna",
                        "La Pinacoteca Nacional de Bologna es uno de los museos más importantes y" +
                                " relevantes de Italia. Entre sus obras más importantes está la " +
                                "colección de pinturas de los Carracci y sus seguidores ", 1,
                        "Bologna",
                        "PinacotecaNacionalDeBologna.jpeg", "cultural", 44.497952, 11.353498));
                /*elementosPatrimonio.add(new ElementoPatrimonio("Plaza de los Leones", "Esta plaza" +
                        " es la más importante de la ciudad e Empoli. Debe su nombre a la fuente " +
                        "que se encuentra en su centro, la cual está adornada con leones, data de" +
                        " 1827 y es obra de Luigi Pampaloni.", 2, "Empoli", "PlazaDeLosLeones" +
                        ".jpg", "cultural"));*/
                // Siguientes elementos del patrimonio buscados por Pedro


                // Elementos del patrimonio buscados por Tania



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

        // Borrar las etapas de la BBDD
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
                etapas.add(new Etapa("Bologna", "Bologna (San Luca)",
                        "Contrarreloj individual",1, 8, "11-Mayo"));
                etapas.add(new Etapa("Bologna", "Fucecchio", "Media montaña",
                        2, 200, "12-Mayo"));
                etapas.add(new Etapa("Vinci", "Orbetello", "Media montaña",
                        3, 219, "13-Mayo"));
                etapas.add(new Etapa("Orbetello", "Frascati", "Media montaña",
                        4, 228, "14-Mayo"));
                etapas.add(new Etapa("Frascati", "Terracina", "Llana", 5,
                        140, "15-Mayo"));
                etapas.add(new Etapa("Cassino", "San Giovanni Rotondo",
                        "Media montaña", 6, 233,"16-Mayo"));
                etapas.add(new Etapa("Vasto", "L'Aquila", "Media montaña",
                        7, 180, "17-Mayo"));
                etapas.add(new Etapa("Tortoreto Lido", "Pesaro", "Media montaña",
                        8, 235,"18-Mayo"));
                etapas.add(new Etapa("Riccione", "San Marino",
                        "Contrarreloj individual", 9, 35,"19-Mayo"));
                etapas.add(new Etapa("Ravenna", "Modena", "Llana",
                        10, 147, "21-Mayo"));
                etapas.add(new Etapa("Carpi", "Novi Ligure", "Llana", 11,
                        206, "22-Mayo"));
                etapas.add(new Etapa("Cuneo", "Pinerolo", "Media montaña",
                        12, 146, "23-Mayo"));
                etapas.add(new Etapa("Pinerolo", "Ceresole Reale (Lago Serrú)",
                        "Alta montaña", 13, 188, "24-Mayo"));
                etapas.add(new Etapa("Saint-Vincent", "Courmayeur (Skyway Monte Bianco",
                        "Alta montaña", 14, 131, "25-Mayo"));
                etapas.add(new Etapa("Ivrea", "Como", "Alta montaña", 15,
                        237, "26-Mayo"));
                etapas.add(new Etapa("Lovere", "Ponte di Legno", "Alta montaña",
                        16, 226, "28-Mayo"));
                etapas.add(new Etapa("Commezzadura (Val di Sole)", "Anterselva/Antholz",
                        "Media montaña", 17, 180, "29-Mayo"));
                etapas.add(new Etapa("Valdaora/Olang", "Santa Maria di Sala",
                        "Llana", 18, 220, "30-Mayo"));
                etapas.add(new Etapa("Treviso", "San Martino di Castrozza",
                        "Media montaña",19, 151, "31-Mayo"));
                etapas.add(new Etapa("Feltre", "Croce d'Aune-Monte Avena",
                        "Alta montaña", 20, 193, "01-Junio"));
                etapas.add(new Etapa("Verona", "Verona", "Contrarreloj individual",
                        21, 16, "02-Junio"));

                Log.d("FIREBASE", "Etapas a insertar: " + etapas
                        .size());

                // Introducir los elementos de las etapas en la BBDD
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

    /**
     * Método para borrar todas los dias del calendario que hay en la BBDD y volver a
     * introducirlos
     */
    public static void rellenarCalendario() {

        // Borrar los dias del calendariode la BBDD
        Query queryRef = calendarioDatabaseReference.orderByChild("id");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int diasEliminados = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                    diasEliminados++;
                }
                Log.d("FIREBASE", "Dias eliminados: " +
                        diasEliminados);

                // Crear los dias del calendario
                ArrayList<Dia> dias = new ArrayList<>();
                dias.add(new Dia(1, "11 de mayo", "Etapa 1"));
                dias.add(new Dia(2, "12 de mayo", "Etapa 2"));
                dias.add(new Dia(3, "13 de mayo", "Etapa 3"));
                dias.add(new Dia(4, "14 de mayo", "Etapa 4"));
                dias.add(new Dia(5, "15 de mayo", "Etapa 5"));
                dias.add(new Dia(6, "16 de mayo", "Etapa 6"));
                dias.add(new Dia(7, "17 de mayo", "Etapa 7"));
                dias.add(new Dia(8, "18 de mayo", "Etapa 8"));
                dias.add(new Dia(9, "19 de mayo", "Etapa 9"));
                dias.add(new Dia(10, "20 de mayo", "Día de descanso"));
                dias.add(new Dia(11, "21 de mayo", "Etapa 10"));
                dias.add(new Dia(12, "22 de mayo", "Etapa 11"));
                dias.add(new Dia(13, "23 de mayo", "Etapa 12"));
                dias.add(new Dia(14, "24 de mayo", "Etapa 13"));
                dias.add(new Dia(15, "25 de mayo", "Etapa 14"));
                dias.add(new Dia(16, "26 de mayo", "Etapa 15"));
                dias.add(new Dia(17, "27 de mayo", "Día de descanso"));
                dias.add(new Dia(18, "28 de mayo", "Etapa 16"));
                dias.add(new Dia(19, "29 de mayo", "Etapa 17"));
                dias.add(new Dia(20, "30 de mayo", "Etapa 18"));
                dias.add(new Dia(21, "31 de mayo", "Etapa 19"));
                dias.add(new Dia(22, "01 de junio", "Etapa 20"));
                dias.add(new Dia(23, "02 de junio", "Etapa 21"));


                Log.d("FIREBASE", "Dias a insertar: " + dias
                        .size());

                // Introducir los elementos de las etapas en la BBDD
                int diasInsertados = 0;
                for (Dia d : dias) {
                    calendarioDatabaseReference.push().setValue(d);
                    diasInsertados++;
                }
                Log.d("FIREBASE", "Dias insertados: " +
                        diasInsertados);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo.");
            }
        });
    }
}
