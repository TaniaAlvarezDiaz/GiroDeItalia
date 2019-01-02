package es.uniovi.sdm.alvfer.girodeitalia.datos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Ganador implements Parcelable {

    private String nombre;
    private String nombreImagenBandera;
    private int year;

    public Ganador(String nombre, String nombreImagenBandera, int year) {
        this.nombre = nombre;
        this.nombreImagenBandera = nombreImagenBandera;
        this.year = year;
    }

    protected Ganador(Parcel in) {
        this.nombre = in.readString();
        this.nombreImagenBandera = in.readString();
        this.year = in.readInt();
    }

    public Ganador() {
    }

    public static final Creator<Ganador> CREATOR = new Creator<Ganador>() {
        @Override
        public Ganador createFromParcel(Parcel in) {
            return new Ganador(in);
        }

        @Override
        public Ganador[] newArray(int size) {
            return new Ganador[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNombre());
        dest.writeString(getNombreImagenBandera());
        dest.writeInt(getYear());
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreImagenBandera() {
        return nombreImagenBandera;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return nombre;
    }
}