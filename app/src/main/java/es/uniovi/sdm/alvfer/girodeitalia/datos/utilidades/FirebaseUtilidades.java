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
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.Ganador;

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
    public static DatabaseReference ganadoresDatabaseReference = firebaseDatabase
            .getReference()
            .child("Ganadores");

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
                                "colección de pinturas de los Carracci y sus seguidores.", 1,
                        "Bologna",
                        "PinacotecaNacionalDeBologna.jpeg", "cultural", 44.497952, 11.353498));
                elementosPatrimonio.add(new ElementoPatrimonio("Plaza de los Leones", "Esta plaza" +
                        " es la más importante de la ciudad e Empoli. Debe su nombre a la fuente " +
                        "que se encuentra en su centro, la cual está adornada con leones, data de" +
                        " 1827 y es obra de Luigi Pampaloni.", 2, "Empoli", "PlazaDeLosLeones" +
                        ".jpg", "cultural", 43.719893, 10.945628));
                elementosPatrimonio.add(new ElementoPatrimonio("Museo del vidrio", "En pleno " +
                        "centro histórico de Empoli se encuentra el museo del vidrio, industria " +
                        "emblemática y característica de esta ciudad.", 2, "Empoli",
                        "MuseoDelVidrio.jpg", "cultural", 43.721006, 10.947627));
                elementosPatrimonio.add(new ElementoPatrimonio("Palacio Ghibellino", "Este " +
                        "palacio fue propiedad de los condes Guidi. Su construcción data del " +
                        "siglo XI, pero una restauración llevada a cabo en el siglo XVI dejó " +
                        "pocos restos del edificio original. En su interior se encuentra el Museo" +
                        " de la Paleontología.", 2, "Empoli",
                        "PalacioGhibellino.jpg", "historico", 43.719699, 10.945576));
                elementosPatrimonio.add(new ElementoPatrimonio("Museo de Leonardo da Vinci",
                        "Museo dedicado a la figura más destacada del Renacimiento italiano, " +
                                "Leonardo da Vinci. En él se pueden encontrar y contemplar muchas" +
                                " de sus creaciones.", 3, "Vinci",
                        "MuseoDeLeonardoDaVinci.jpg", "cultural", 43.787807, 10.927010));
                elementosPatrimonio.add(new ElementoPatrimonio("Colinas de Montalbano",
                        "Las colinas de Montalbano son una zona ideal para aquellos que practican" +
                                " ciclismo y trekking, debido a la cantidad senderos naturales " +
                                "que" +
                                " existen en ellas. Se encuentran en una zona natural protegida, " +
                                "en cuyos alrededores existen numerosas iglesisas, poblaciones " +
                                "históricas y casas rurales.", 3, "Vinci",
                        "ColinasDeMontalbano.jpg", "geografico", 43.791473, 10.980283));
                elementosPatrimonio.add(new ElementoPatrimonio("Laguna de Orbetello",
                        "La laguna de Orbetello atrae a numeosos turistas en cualquier época del " +
                                "año debido a sus maravillosas vistas. Es desde 1971 una zona " +
                                "protegida por el Fondo Mundial del Ambiente.", 4, "Orbetello",
                        "LagunaDeOrbetello.jpg", "geografico", 42.438414, 11.194040));
                elementosPatrimonio.add(new ElementoPatrimonio("Playa de Feniglia",
                        "Playa situada dentro de una zona natural protegida. Su arena es fina y " +
                                "clara, y sus aguas transparentes. Junto a ella existe un gran " +
                                "bosque de pinos donde poder comer o hacer excursiones a la " +
                                "sombra.", 4, "Orbetello",
                        "PlayaDeFeniglia.jpg", "geografico", 42.409916, 11.208737));
                elementosPatrimonio.add(new ElementoPatrimonio("Monte Circeo",
                        "El Monte Circeo es la principal atracción del parque nacional del " +
                                "Circeo, el cual fue formado en 1934 y tiene una extensión de 84 " +
                                "kilómetros cuadrados.", 5, "Terracina",
                        "MonteCirceo.jpg", "geografico", 41.239133, 13.045709));
                elementosPatrimonio.add(new ElementoPatrimonio("Templo de Júpiter Anxur",
                        "Este templo se encuentra en la cumbre del monte Sant’Angelo y es un " +
                                "símbolo de la población de Terracina. Su construcción data del " +
                                "siglo IV antes de Cristo, y posee un estilo pompeyano.", 5,
                        "Terracina",
                        "TemploDeJupiterAnxur.jpg", "historico", 41.291008, 13.260097));
                elementosPatrimonio.add(new ElementoPatrimonio("Torre Paola",
                        "Torre de estilo medieval construida con fines defensivos en un lugar " +
                                "estratégico junto a la costa.", 5,
                        "Terracina",
                        "TorrePaola.JPG", "historico", 41.246429, 13.034840));
                elementosPatrimonio.add(new ElementoPatrimonio("Abadía de Montecassino",
                        "La Abadía de Montecasino es una abadía benedictina, famosa por ser el " +
                                "lugar en el cual Benito de Nursia estableció en el año 529 su " +
                                "primer monasterio. Numerosas batallas al fin de la Segunda " +
                                "Guerra Mundial tuvieron lugar en los alrededores de esta abadía" +
                                ".", 6,
                        "Cassino",
                        "AbadiaDeMontecassino.JPG", "historico", 41.492486, 13.815729));
                elementosPatrimonio.add(new ElementoPatrimonio("Museo paleontológico del " +
                        "donosaurio",
                        "Museo en el que descubrir todo tipo de datos acerca de los dinosaurios y" +
                                " contemplar sus maquetas e imágenes. Una visita enriquecedora " +
                                "para todos los miembros de la familia.", 6,
                        "San Giovanni Rotondo",
                        "MuseoPaleontologicoDelDinosaurio.jpg", "cultural", 41.706866, 15.652246));
                elementosPatrimonio.add(new ElementoPatrimonio("Reserva natural Punta Aderci",
                        "Dentro de esta reserva natural se encuentra una famosa playa de piedras," +
                                " la cual está rodeada por acantilados con vistas panorámicas al " +
                                "mar.", 7,
                        "Vasto",
                        "ReservaNaturalPuntaAderci.jpg", "geografico", 42.180261, 14.687680));
                elementosPatrimonio.add(new ElementoPatrimonio("Basílica de San Bernardino",
                        "Esta basílica fue construida en el año 1444 tras la muerte de San " +
                                "Bernardino de Siena con la intención de custodiar su cuerpo. " +
                                "Posee una fachada renacentista y un interior de estilo barroco " +
                                "reconstruido tras un terremoto en 1703.", 7,
                        "L'Aquila",
                        "BasilicaDeSanBernardino.jpg", "historico", 42.350754, 13.402455));
                elementosPatrimonio.add(new ElementoPatrimonio("Monte San Bartolo",
                        "Montaña más icónica del parque natural del Monte San Bartolo. Esta " +
                                "montaña forma el principio de un sistema de colinas costeras que" +
                                " recorren el centro de la costa de Italia, siguiendo las playas " +
                                "del mar Adriático", 8,
                        "Pesaro",
                        "MonteSanBartolo.jpg", "geografico", 43.920736, 12.895404));
                elementosPatrimonio.add(new ElementoPatrimonio("Gran Esfera de A. Pomodoro",
                        "La Gran Esfera de A. Pomodoro es una escultura de bronce hecha por el " +
                                "escultor italiano Arnaldo Pomodoro. Esta escultura está formada " +
                                "por dos esferas, una dentro de otra, estando ambas fracturadas" +
                                ".", 8,
                        "Pesaro",
                        "GranEsferaDePomodoro.jpg", "cultural", 43.914324, 12.918456));
                elementosPatrimonio.add(new ElementoPatrimonio("Fortaleza Guaita",
                        "Emblemática fortaleza del siglo XI en el monte Titano, con exposiciones " +
                                "y vistas panorámicas de la ciudad de San Marino.", 9,
                        "San Marino",
                        "FortalezaGuaita.jpg", "historico", 43.935552, 12.449677));
                elementosPatrimonio.add(new ElementoPatrimonio("Torre Falesia",
                        "Histórica torre con un pequeño museo de armas y vistas panorámicas de la" +
                                " ciudad de San Marino, el mar y la montaña.", 9,
                        "San Marino",
                        "TorreFalesia.jpg", "historico", 43.932288, 12.452058));
                elementosPatrimonio.add(new ElementoPatrimonio("Iglesia de San Vital de Ravenna",
                        "Emblemática iglesia de cúpula octogonal conocida por sus elaborados " +
                                "mosaicos bizantinos de colores vivos.", 10,
                        "Ravenna",
                        "IglesiaDeSanVitalDeRavenna.JPG", "historico", 44.420801, 12.196376));
                elementosPatrimonio.add(new ElementoPatrimonio("Museo de Enzo Ferrari",
                        "Museo centrado en la vida y obra de Enzo Ferrari, fundador de la marca " +
                                "Ferrari. El edificio que alberga este museo es futurista y " +
                                "cuenta con paredes de vidrio.", 10,
                        "Modena",
                        "MuseoDeEnzoFerrari.jpg", "cultural", 44.652280, 10.936881));
                elementosPatrimonio.add(new ElementoPatrimonio("Mausoleo de Gala Placidia",
                        "Capilla del siglo V con forma de cruz latina y decorada con coloridos " +
                                "mosaicos romanos. Es una de las estructuras de Ravenna inscritas" +
                                " en la lista del Patrimonio de la Humanidad en 1996.", 10,
                        "Ravenna",
                        "MausoleoDeGalaPlacidia.jpg", "historico", 44.421228, 12.197081));


                // Elementos del patrimonio buscados por Tania


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
                        "Contrarreloj individual", 1, 8, "11-Mayo"));
                etapas.add(new Etapa("Bologna", "Fucecchio", "Media montaña",
                        2, 200, "12-Mayo"));
                etapas.add(new Etapa("Vinci", "Orbetello", "Media montaña",
                        3, 219, "13-Mayo"));
                etapas.add(new Etapa("Orbetello", "Frascati", "Media montaña",
                        4, 228, "14-Mayo"));
                etapas.add(new Etapa("Frascati", "Terracina", "Llana", 5,
                        140, "15-Mayo"));
                etapas.add(new Etapa("Cassino", "San Giovanni Rotondo",
                        "Media montaña", 6, 233, "16-Mayo"));
                etapas.add(new Etapa("Vasto", "L'Aquila", "Media montaña",
                        7, 180, "17-Mayo"));
                etapas.add(new Etapa("Tortoreto Lido", "Pesaro", "Media montaña",
                        8, 235, "18-Mayo"));
                etapas.add(new Etapa("Riccione", "San Marino",
                        "Contrarreloj individual", 9, 35, "19-Mayo"));
                etapas.add(new Etapa("Ravenna", "Modena", "Llana",
                        10, 147, "21-Mayo"));
                etapas.add(new Etapa("Carpi", "Novi Ligure", "Llana", 11,
                        206, "22-Mayo"));
                etapas.add(new Etapa("Cuneo", "Pinerolo", "Media montaña",
                        12, 146, "23-Mayo"));
                etapas.add(new Etapa("Pinerolo", "Ceresole Reale (Lago Serrú)",
                        "Alta montaña", 13, 188, "24-Mayo"));
                etapas.add(new Etapa("Saint-Vincent", "Courmayeur (Skyway Monte Bianco)",
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
                        "Media montaña", 19, 151, "31-Mayo"));
                etapas.add(new Etapa("Feltre", "Croce d'Aune-Monte Avena",
                        "Alta montaña", 20, 193, "1-Junio"));
                etapas.add(new Etapa("Verona", "Verona", "Contrarreloj individual",
                        21, 16, "2-Junio"));

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
     * Método para borrar todos los dias del calendario que hay en la BBDD y volver a
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

    /**
     * Método para borrar todos los ganadores que hay en la BBDD y volver a
     * introducirlos
     */
    public static void rellenarGanadores() {

        // Borrar las etapas de la BBDD
        Query queryRef = ganadoresDatabaseReference.orderByChild("year");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int ganadoresEliminados = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                    ganadoresEliminados++;
                }
                Log.d("FIREBASE", "Ganadores elimindos: " +
                        ganadoresEliminados);

                // Crear los ganadores
                // Se introducen los ganadores del Giro de Italia desde el año 2000
                ArrayList<Ganador> ganadores = new ArrayList<>();
                ganadores.add(new Ganador("C. Froome", "GBR.png", 2018));
                ganadores.add(new Ganador("T. Dumoulin", "HOL.png", 2017));
                ganadores.add(new Ganador("V. Nibali", "ITA.png", 2016));
                ganadores.add(new Ganador("A. Contador", "ESP.png", 2015));
                ganadores.add(new Ganador("N. Quintana", "COL.png", 2014));
                ganadores.add(new Ganador("V. Nibali", "ITA.png", 2013));
                ganadores.add(new Ganador("R. Hesjedal", "CAN.png", 2012));
                ganadores.add(new Ganador("M. Scarponi", "ITA.png", 2011));
                ganadores.add(new Ganador("I. Basso", "ITA.png", 2010));
                ganadores.add(new Ganador("D. Menshov", "RUS.png", 2009));
                ganadores.add(new Ganador("A. Contador", "ESP.png", 2008));
                ganadores.add(new Ganador("D. Di Luca", "ITA.png", 2007));
                ganadores.add(new Ganador("I. Basso", "ITA.png", 2006));
                ganadores.add(new Ganador("P. Savoldelli", "ITA.png", 2005));
                ganadores.add(new Ganador("D. Cunego", "ITA.png", 2004));
                ganadores.add(new Ganador("G. Simoni", "ITA.png", 2003));
                ganadores.add(new Ganador("P. Savoldelli", "ITA.png", 2002));
                ganadores.add(new Ganador("G. Simoni", "ITA.png", 2001));
                ganadores.add(new Ganador("S. Garzelli", "ITA.png", 2000));

                Log.d("FIREBASE", "Ganadores a insertar: " + ganadores
                        .size());

                // Introducir los elementos en la BBDD
                int ganadoresInsertados = 0;
                for (Ganador ganador : ganadores) {
                    ganadoresDatabaseReference.push().setValue(ganador);
                    ganadoresInsertados++;
                }
                Log.d("FIREBASE", "Ganadores insertados: " +
                        ganadoresInsertados);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo.");
            }
        });
    }
}
