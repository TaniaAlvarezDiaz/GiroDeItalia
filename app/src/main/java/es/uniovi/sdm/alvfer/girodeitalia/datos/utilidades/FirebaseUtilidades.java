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
import es.uniovi.sdm.alvfer.girodeitalia.datos.modelo.LugarMitico;
import es.uniovi.sdm.alvfer.girodeitalia.vista.activities.LugaresMiticosActivity;

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
    public static DatabaseReference lugaresMiticosDatabaseReference = firebaseDatabase
            .getReference()
            .child("LugaresMiticos");

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
                        " es la más importante de la ciudad de Empoli. Debe su nombre a la fuente " +
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
                                "en cuyos alrededores existen numerosas iglesias, poblaciones " +
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
                elementosPatrimonio.add(new ElementoPatrimonio("Catedral de Santa María Assunta",
                        "Construida entre 1514 y 1774, fue diseñada por Baldassarre Peruzzi, " +
                                "quien estuvo en contacto con Leonardo da Vinci.", 11, "Carpi",
                        "CatedralDiSantaMariaAssunta.jpg", "historico",
                        44.784713, 10.886276));
                elementosPatrimonio.add(new ElementoPatrimonio("Palacio Ducal",
                        "Erigido por el Duque Octavio Farnesio en 1561 sobre un antiguo castillo de la familia Sforza, " +
                                "posee un fresco del famoso artista " +
                                "Parmigianino.", 11, "Parma", "PalacioDucal.jpg",
                        "historico", 44.807838, 10.322316));
                elementosPatrimonio.add(new ElementoPatrimonio("Catedral de la Asunción y Santa Justina",
                        "Empezada a construir en el siglo XII, es uno de los ejemplos más destacados " +
                                "del arte Románico en Italia. Su fachada está hecha con mármol rosa de Verona " +
                                "y arenisca, mientras en su interior la cúpula está decorada con frescos de " +
                                "“il Guercino”.", 11, "Piacenza", "CatedralDePiacenza.jpg", "historico",
                        45.050316, 9.697510));
                elementosPatrimonio.add(new ElementoPatrimonio("Museo de los Campeones",
                        "Dedicado a los famosos ciclistas Constante Girardengo (1893-1978) y " +
                                "Fausto Coppi (1919-1960), alberga también información sobre la historia del ciclismo.",
                        11, "Novi Ligure", "MuseoDeLosCampeones.jpg", "cultural",
                        44.763836, 8.778455));
                elementosPatrimonio.add(new ElementoPatrimonio("Iglesia de San Giovanni",
                        "Del siglo XIII y de estilo gótico, destaca por su sobriedad exterior y " +
                                "las bóvedas de crucería que alberga en su interior, donde también se " +
                                "encuentran la capilla funeraria de los marqueses de Saluzzo y la tumba " +
                                "del marqués Ludovico II.", 12, "Saluzzo",
                        "IglesiaSaluzzo.jpg", "historico",
                        44.643572, 7.487894));
                elementosPatrimonio.add(new ElementoPatrimonio("Catedral de San Donato",
                        "Situada en pleno centro histórico, en la Plaza de San Donato, data del " +
                                "siglo XI y ha sido restaurada en varias ocasiones: en el siglo XVIII se " +
                                "le añadieron motivos barrocos que fueron retirados en el siglo XIX para " +
                                "recuperar sus formas originales, de estilo gótico.", 12, "Pinerolo",
                        "CatedralDeSanDonato.jpg", "historico",
                        44.885719, 7.330134));
                elementosPatrimonio.add(new ElementoPatrimonio("Santuario de Nuestra Señora de los Dolores",
                        "Situado en una colina, fue construido en el siglo XVIII. " +
                                "Cuanta la leyenda que un hombre que se rompió una pierna en un lugar " +
                                "solitario invocó a la Virgen, que lo sanó al instante. " +
                                "Cada año se realizan en el tres procesiones, en los meses de junio, agosto y septiembre.",
                        13, "Colle del Lys", "SantuarioNuestraSeñoraDeLosDolores.jpg",
                        "historico", 45.159145, 7.405798));
                elementosPatrimonio.add(new ElementoPatrimonio("Lago Serrú",
                        "Se trata de un lago artificial que forma parte de un complejo " +
                                "de lagos artificiales que alimentan varias plantas hidroeléctricas. " +
                                "Aunque el lago no es natural, el entorno que lo rodea es de una gran " +
                                "belleza: se trata del Parque Nacional del Gran Paraíso.", 13,
                        "Ceresole Reale", "LagoSerru.jpg", "geografico",
                        45.459751, 7.125824));
                elementosPatrimonio.add(new ElementoPatrimonio("Museo de Arte Sacro",
                        "Situado en la iglesia parroquial, alberga diversos objetos como relicarios y vestimentas.",
                        14, "Saint-Vincent", "IglesiaSanVincent.jpg",
                        "cultural", 45.750537, 7.649729));
                elementosPatrimonio.add(new ElementoPatrimonio("Teatro romano",
                        "Su fachada sur de 22 metros de altura aún está en pie, " +
                                "con varias arcadas superpuestas. También se conservan la parte inferior " +
                                "de la cávea (gradas) y los cimientos de la scaena. Durante la Edad Media " +
                                "se adosaron al mismo varias construcciones que recientemente se han demolido "
                                + "para restaurar el teatro.", 14, "Aosta",
                        "TeatroDeAosta.jpg", "cultural", 45.738563,
                        7.322363));
                elementosPatrimonio.add(new ElementoPatrimonio("Catedral de Santa María Assunta",
                        "Comenzada a construir en el siglo IX, fue terminada a mediados del " +
                                "siglo XIX, por lo que su construcción posee características del Románico, del " +
                                "Barroco y del estilo neoclásico. Parece que bajo la misma hubo un templo romano " +
                                "que data del siglo I a.C. y, posteriormente, iglesias cristianas datadas de los siglos IV y V.",
                        15, "Ivrea", "CatedralDeIvrea.jpg",
                        "historico", 45.468172, 7.875436));
                elementosPatrimonio.add(new ElementoPatrimonio("Castillo de Ivrea",
                        "Conocido como el “castillo de las torres rojas”, se comenzó a " +
                                "construir a mediados del siglo XIV con funciones defensivas. Entre 1700 y " +
                                "1970 fue utilizado como prisión.", 15, "Ivrea",
                        "CastilloDeIvrea.jpeg", "historico",
                        45.467940, 7.874129));
                elementosPatrimonio.add(new ElementoPatrimonio("Oasi WWF del Bassone-Torbiere di Albate",
                        "Se trata de una reserva natural de 90 hectáreas de terreno " +
                                "situada 6 km al sur de Como. Consta de un césped, una zona arbolada y un " +
                                "pantano donde habitan casi un centenar de especies.", 15,
                        "Como", "OasiComo.jpg", "geografico",
                        45.766832, 9.092011));
                elementosPatrimonio.add(new ElementoPatrimonio("Galería de la Academia de Bellas Artes Tadini",
                        "Situada en el Palacio Tadini, de estilo Neoclásico y la sede " +
                                "de la Academia, esta galería alberga una prestigiosa colección de arte que " +
                                "incluye pinturas de Giovanni Battista Crespi, entre otros, además de una " +
                                "colección de terracota, porcelana, armaduras y mobiliario.", 16,
                        "Lovere", "GaleriaLovere.jpg", "cultural",
                        45.814976, 10.073104));
                elementosPatrimonio.add(new ElementoPatrimonio("Lago di Valbione",
                        "Un estanque donde se puede practicar la pesca. Además, " +
                                "el lugar dispone de un club de golf y un bar-restaurante. Se puede llegar " +
                                "al lugar en telesilla, en coche o a pie a través de senderos.", 16,
                        "Ponte di Legno", "LagoDiValbione.jpg", "geografico",
                        46.247488, 10.507010));
                elementosPatrimonio.add(new ElementoPatrimonio("Pista Südtirol Arena",
                        "Esta pista está diseñada para practicar biatlón, " +
                                "un deporte de invierno que combina el esquí con el tiro deportivo. " +
                                "Está situada en un paraje de gran belleza donde el 98 % de la población " +
                                "habla alemán.", 17, "Rasun Anterselva/Rasen-Antholz",
                        "PistaBiatlonAnterselva.jpg", "geografico",
                        46.884438, 12.153711));
                elementosPatrimonio.add(new ElementoPatrimonio("Castillo Roncolo",
                        "Este castillo medieval del siglo XIII está situado sobre " +
                                "un peñasco en el municipio de Renon, aunque es propiedad de la ciudad de Bolzano. " +
                                "Lo más destacado es que posee en su interior una gran cantidad de frescos " +
                                "del siglo XV que representan aspectos de la vida y la cultura cortesanas, " +
                                "por los que el castillo ha sido apodado “la mansión ilustrada”.", 17,
                        "Bolzano/Bozen", "CastilloRoncolo.jpg", "historico",
                        46.517388, 11.358953));
                elementosPatrimonio.add(new ElementoPatrimonio("Villa " +
                        "Farsetti", "Inspirada en el palacio francés de " +
                        "Versalles, fue construida en el siglo XVIII por " +
                        "voluntad del abad Filippo Farsetti. De estética " +
                        "rococó, posee tres pisos y está decorada con 38 " +
                        "columnas procedentes del Templo de la Concordia, " +
                        "situado en el Foro romano de Roma.", 18, "Santa " +
                        "Maria di Sala", "VillaFarsetti.jpg", "historico",
                        45.507008, 12.033467));
                elementosPatrimonio.add(new ElementoPatrimonio("Parque " +
                        "Natural de Fanes-Sennes-Braies", "Este parque de más" +
                        " de 25 hectáreas es uno de los más grandes del Tirol" +
                        " del Sur. Posee picos de 3000 m de altura, grandes " +
                        "mesetas y seis lagos. Aunque poseyó lobos, osos y " +
                        "linces, estos se extinguieron en el siglo XIX. Ahora" +
                        " viven corzos, venados, tejones, zorros, íbices de " +
                        "los Alpes y urogallos negros, así como marmotas " +
                        "alpinas, símbolo del parque.", 18, "Valdaora/Olang",
                        "LagoDiBraisParqueFannes.jpg", "geografico",
                        46.666222, 12.034924));
                elementosPatrimonio.add(new ElementoPatrimonio("Catedral de " +
                        "San Pedro Apóstol", "Originaria del siglo VI, en los" +
                        " siglos XI y XII adoptó el estilo Románico de la " +
                        "época. Ya en el siglo XVIII, se reconstruyó el " +
                        "edificio al estilo Neoclásico. Destacan sus siete " +
                        "cúpulas y su majestuoso interior. Además, una cripta" +
                        " alberga las tumbas de los obispos de Treviso.", 19,
                        "Treviso", "CatedralSanPedroApostol.jpg", "historico",
                        45.666389,12.242778));
                elementosPatrimonio.add(new ElementoPatrimonio("Parque " +
                        "Natural Paneveggio-Pale di San Martino",
                        "Establecido en 1967, alberga una gran cantidad de " +
                                "fauna y flora. Destaca el Pale di San " +
                                "Martino, un grupo de montañas que es el más " +
                                "grande de todos los Dolomitas (un conjunto " +
                                "de sierras de los Alpes orientales " +
                                "italianos). El Cimon della Pala es el pico " +
                                "más famoso del Pale di San Martino, con 3184" +
                                " m de altura.", 19, "San Martino di " +
                        "Castrozza", "ParqueDeSanMartino.jpg", "geografico",
                        46.278913, 11.780173));
                elementosPatrimonio.add(new ElementoPatrimonio("Borgo " +
                        "Colmellere", "Pequeña población originaria del siglo" +
                        " X situada al suroeste de Combai. Conserva su " +
                        "estructura medieval, con varias casas, un juzgado " +
                        "con patio, un pozo y un horno. El pueblo, que posee " +
                        "un restaurante, está rodeado de viñedos.", 19,
                        "Combai", "BorgoColmellere.jpg", "geografico",
                        45.926664, 12.063986));
                elementosPatrimonio.add(new ElementoPatrimonio("Galería de " +
                        "Arte Moderno Carlo Rizzarda", "Situada en el " +
                        "interior del Palazzo Cumano (s. XVI), la galería " +
                        "custodia 196 obras entre las que se incluyen " +
                        "pinturas, muebles y objetos de arte decorativo de " +
                        "artistas como Felice Casorati y Carlo Carrà. Además," +
                        " la galería alberga más de 400 obras de hierro " +
                        "forjado del artista italiano de Art Nouveau Carlo " +
                        "Rizzarda, uno de los más importantes del país a " +
                        "principios del siglo XX.", 20, "Feltre",
                        "GaleriaDeArteFeltre.jpg", "cultural", 46.018205,
                        11.907843));
                elementosPatrimonio.add(new ElementoPatrimonio("Arena di " +
                        "Verona", "Se trata de un anfiteatro de época romana " +
                        "situado en el centro de la ciudad, enfrente de la " +
                        "Gran Guardia. Es el tercero más grande de Italia " +
                        "después del Coliseo de Roma y el Anfiteatro de Capua" +
                        " y el mejor conservado de todos gracias a las " +
                        "continuas obras de restauración que se han venido " +
                        "haciendo desde el siglo XVII. Con capacidad para 32 " +
                        "056 personas, desde 1913 se utiliza especialmente en" +
                        " verano para celebrar el Festival de la Ópera de " +
                        "Verona, en el que se representan cuatro óperas, " +
                        "además de conciertos de música sinfónica y ballet.",
                        21, "Verona", "ArenaDiVerona.jpg", "historico",
                        45.439146, 10.994357));
                elementosPatrimonio.add(new ElementoPatrimonio("Palazzo della" +
                        " Gran Guardia", "Situado frente al Arena, este " +
                        "palacio que comenzó a construirse en el siglo XVII " +
                        "no fue terminado hasta el siglo XIX. De estilo " +
                        "barroco, el edificio consta de dos cuerpos: el " +
                        "inferior está compuesto por un pórtico de sillares " +
                        "de piedra con trece arcos, mientras que el cuerpo " +
                        "superior consta de quince ventanales entre los que " +
                        "se encuentran columnas pareadas. Algunos de los " +
                        "vanos están coronados con pequeños frontones curvos " +
                        "y triangulares. En la actualidad, la Gran Guardia se" +
                        " usa como palacio de exposiciones, congresos y " +
                        "eventos culturales.", 21, "Verona", "PalacioVerona" +
                        ".jpg", "historico", 45.437995, 10.992260));


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

                // Introducir las etapas en la BBDD
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

                // Introducir los dias del calendario en la BBDD
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

        // Borrar los ganadores de la BBDD
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

                // Introducir los ganadores en la BBDD
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


    /**
     * Método para borrar todos los lugares miticos que hay en la BBDD y volver a
     * introducirlos
     */
    public static void rellenarLugaresMiticos() {

        Query queryRef = lugaresMiticosDatabaseReference.orderByChild("nombre");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int lugaresMiticosEliminados = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                    lugaresMiticosEliminados++;
                }
                Log.d("FIREBASE", "Lugares miticos elimindos: " +
                        lugaresMiticosEliminados);

                //Crear los lugares míticos
                ArrayList<LugarMitico> lugaresMiticos = new ArrayList<>();
                lugaresMiticos.add(new LugarMitico("Colle dell'Agnello", "Es " +
                        "un paso alpino de 2748 metros de altitud situado en " +
                        "el contexto orográfico de los Alpes de Cott que conecta" +
                        " Italia con Francia. Su colina fue destino de varias" +
                        " etapas del Tour de Francia y del Giro de Italia.",
                        "Ha sido escalada 4 veces en el Giro de Italia: en " +
                                "1994, en el 2000, en 2007 y en 2016. Los " +
                                "primeros ciclistas que llegaron a la cima " +
                                "son, respectivamente: Stefano Zanini (Italia), José Jaime " +
                                "González (Colombia), Yoann Le Boulanger " +
                                "(Francia) y Michele Scarponi (Italia).",
                        "ColleDellAgnello.jpg",44.683800, 6.979500));
                lugaresMiticos.add(new LugarMitico("Monte Zoncolan", "Es una " +
                        "montaña de 1750 metros en los Alpes Cárnicos que " +
                        "cuenta con una estación de esquí con casi 30 km de " +
                        "pistas, una de las más importantes de la región de " +
                        "Friul-Venecia Julia.", "Esta montaña fue ascendida 6" +
                        " veces durante el Giro de Italia. Los dos primeros " +
                        "años, en 2003 y en 2007, el ganador fue Gilberto " +
                        "Simoni (Italia), por lo que a partir de este " +
                        "momento, el monte Zoncolan fue apodado la montaña " +
                        "Simoni. Además, fue ascendida en 2010, cuyo ganador " +
                        "fue Ivan Basso (Italia); en 2011, siendo vencedor " +
                        "el español Igor Antón; en 2014, donde se hizo con la" +
                        " victoria Michael Rogers (Australia); y en 2018, " +
                        "donde el ganador fue Chris Froome (Reino Unido).",
                        "CimaZoncalan.jpg", 46.499701, 12.919230));
                lugaresMiticos.add(new LugarMitico("Paso Gavia", "Es un paso de montaña ubicado " +
                        "en los Alpes Réticos meridionales y es uno de los pasos más altos de " +
                        "Europa.", "Ha sido ascendido 10 veces en el Giro de Italia: en 1960, donde Imerio " +
                        "Massignan (Italia) se hizo con la victoria; en 1988, donde Johan Van der" +
                        " Velde (Países Bajos) fue el ganador; en el 2000 fue el colombiano Chepe " +
                        "González quien se hizo con la victoria; en 2014 el ganador fue Robinson " +
                        "Chalapud (Colombia). Este paso fue Cima Coppi en 6 ocasiones: en 1996 " +
                        "(el colombiano Hernán Buenahora se hizo con la victoria); en 1999 (Chepe" +
                        " González, Colombia); en 2004 (Vladimir Miholjević, Croacia); en 2006 " +
                        "(el español Juan Manuel Gárate fue el ganador); en 2008 (el mexicano Julio Alberto " +
                        "Pérez Capio se hizo con la victoria) y en 2010 (Johann Tschopp, " +
                        "Suiza)", "PassoDiGavia.jpg", 46.343611, 10.484611));
                lugaresMiticos.add(new LugarMitico("Paso Giau", "Es un paso de montaña de 2236 " +
                        "metros de altitud situado en las Dolomitas.", "Fue Cima Coppi en 1973, " +
                        "donde el español José Manuel Fuente se hizo con la victoria; y en 2011, " +
                        "cuando el italiano Stefano Garzelli fue el primero en pasar por la cumbre" +
                        ".", "PassoDiGiau.jpg", 46.483114, 12.054164));
                lugaresMiticos.add(new LugarMitico("Paso Pordoi", "Paso de montaña de 2239 " +
                        "metros de altitud situado en las Dolomitas.", "Ha sido Cima Coppi en 13 " +
                        "ocasiones. Fue ascendido por primera vez en 1940, donde el italiano Gino " +
                        "Bartali se hizo con la victoria. Esta cima fue 4 veces final de etapa: " +
                        "en 1990, donde el vencedor fue Charly Mottet (Francia); en 1991, donde " +
                        "el italiano Franco Chioccioli fue el vencedor; en 1996, siendo esta vez " +
                        "Enrico Zaina (Italia) el ganador; y en 2001, donde el vencedor fue el " +
                        "mexicano Julio Alberto Pérez Cuapio.", "PassoPordoi.jpg", 46.488817,
                        11.811757));
                lugaresMiticos.add(new LugarMitico("Paso Stelvio", "Paso de montaña de 2758 " +
                        "metros de altitud. Es el paso pavimentado de mayor elevación de los " +
                        "Alpes Orientales y el segundo más alto de los Alpes.", "Ha sido " +
                        "ascendido en 13 ocasiones en el Giro de Italia. La primera de ellas fue " +
                        "en 1953, donde el vencedor fue el italiano Fauto Coppi. También fue " +
                        "ascendido en 1956 y 1961. Fue 9 veces Cima Coppi: en 1965, 1972, 1975, " +
                        "1980, 1994, 2005, 2012, 2014 y 2017, siendo en este último año el " +
                        "español Mikel Landa el vencedor.", "Stelvio.jpg", 46.530000, 10.454000));
                lugaresMiticos.add(new LugarMitico("Puerto de Mortirolo", "Puerto de montaña " +
                        "italiano, conocido también como Puerto de la Foppa, que comunica las " +
                        "zonas alpinas de la alta Valtellina y la alta Val Camonica.", "Fue " +
                        "ascendido en 12 ocasiones en el Giro de Italia: en 1990, donde se hizo " +
                        "con la victoria el venezolano Leonardo Sierra; en 1991 (Franco " +
                        "Chioccioli, Italia); en 1994 (Marco Pantani, Italia); en 1996 y 1999 " +
                        "(Ivan Gotti, Italia); en 1997 (Waldimir Belli, Italia); en 2004 " +
                        "(Raffaele Illiano, Italia); en 2006 y 2010 (Ivan Basso, Italia); en " +
                        "2008 se hizo con la victoria el español Toni Colom; en 2012 (Oliver " +
                        "Zaugg, Suiza); en 2015 (Steven Kruijswijk, Países Bajos); y en 2017, cuando el " +
                        "vencedor fue el español Luis León Sánchez.", "PassoDelMortirolo.JPG",
                        46.247940, 10.298304));
                lugaresMiticos.add(new LugarMitico("Tres Cimas de Lavaredo", "Son tres picos " +
                        "compuestos por dolomías. Sus nombres son: Cima Piccola (es el pico " +
                        "pequeño), Cima Grande (el pico de mayor altura) y Cima Ovest (el pico " +
                        "más occidental).", "Estas cimas han sido 7 veces final de etapa del Giro" +
                        " de Italia: en 1967, cuando el italiano Felice Gimondi resultó ganador; " +
                        "en 1968 (Eddy Merckx, Bélgica); en 1974 (José Manuel Fuente, España); en" +
                        " 1981 (Beat Breu, Suiza); en 1989 (Lucho Herrera, Colombia); en 2007 " +
                        "(Riccardo Riccò, Italia); y en 2013 (Vicenzo Nibali, Italia).",
                        "TresCimasLavaredo.jpg", 46.618896, 12.302696));

                Log.d("FIREBASE", "Lugares miticos a insertar: " + lugaresMiticos
                        .size());

                // Introducir los lugares miticos en la BBDD
                int lugaresMiticosInsertados = 0;
                for (LugarMitico lugarMitico : lugaresMiticos) {
                    lugaresMiticosDatabaseReference.push().setValue(lugarMitico);
                    lugaresMiticosInsertados++;
                }
                Log.d("FIREBASE", "Lugares míticos insertados: " +
                        lugaresMiticosInsertados);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE", "Ha ocurrido un fallo.");
            }
        });
    }
}
