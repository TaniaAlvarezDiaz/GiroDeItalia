package es.uniovi.sdm.alvfer.girodeitalia.datos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Etapa implements Parcelable {

    private String salida;
    private String meta;
    private String tipo; // Llana, Media montaña, Alta montaña o Contrarreloj individual
    private int numero;
    private int kilometros;
    private String fecha; // dia-mes, Ej: 14-Mayo

    public Etapa(String salida, String meta, String tipo, int numero, int kilometros, String fecha) {
        this.salida = salida;
        this.meta = meta;
        this.tipo = tipo;
        this.numero = numero;
        this.kilometros = kilometros;
        this.fecha = fecha;
    }

    protected Etapa(Parcel in) {
        this.salida = in.readString();
        this.meta = in.readString();
        this.tipo = in.readString();
        this.numero = in.readInt();
        this.kilometros = in.readInt();
        this.fecha = in.readString();
    }

    public Etapa() {
    }

    public static final Creator<Etapa> CREATOR = new Creator<Etapa>() {
        @Override
        public Etapa createFromParcel(Parcel in) {
            return new Etapa(in);
        }

        @Override
        public Etapa[] newArray(int size) {
            return new Etapa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getSalida());
        dest.writeString(getMeta());
        dest.writeString(getTipo());
        dest.writeInt(getNumero());
        dest.writeInt(getKilometros());
        dest.writeString(getFecha());
    }

    public String getSalida() {
        return salida;
    }

    public String getMeta() {
        return meta;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }

    public int getKilometros() {
        return kilometros;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return salida + " - " + meta;
    }
}
