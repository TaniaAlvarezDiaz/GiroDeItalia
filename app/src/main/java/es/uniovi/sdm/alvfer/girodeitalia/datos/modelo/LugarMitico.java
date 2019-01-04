package es.uniovi.sdm.alvfer.girodeitalia.datos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class LugarMitico implements Parcelable {

    private String nombre;
    private String descripcion;
    private String hechosHistoricos;
    private String nombreImagen;
    private double latitud;
    private double longitud;


    public LugarMitico(String nombre, String descripcion, String hechosHistoricos, String
            nombreImagen, double latitud, double longitud) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hechosHistoricos = hechosHistoricos;
        this.nombreImagen = nombreImagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    protected LugarMitico(Parcel in) {
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.hechosHistoricos = in.readString();
        this.nombreImagen = in.readString();
        this.latitud = in.readDouble();
        this.longitud = in.readDouble();
    }

    public LugarMitico() {
    }

    public static final Creator<LugarMitico> CREATOR = new Creator<LugarMitico>() {
        @Override
        public LugarMitico createFromParcel(Parcel in) {
            return new LugarMitico(in);
        }

        @Override
        public LugarMitico[] newArray(int size) {
            return new LugarMitico[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNombre());
        dest.writeString(getDescripcion());
        dest.writeString(getHechosHistoricos());
        dest.writeString(getNombreImagen());
        dest.writeDouble(getLatitud());
        dest.writeDouble(getLongitud());
    }

    public String getHechosHistoricos() {
        return hechosHistoricos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
