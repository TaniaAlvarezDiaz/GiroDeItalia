package es.uniovi.sdm.alvfer.girodeitalia.datos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class ElementoPatrimonio implements Parcelable {

    private String nombre;
    private String descripcion;
    private int etapa;
    private String lugar;
    private String nombreImagen;
    private String tipoPatrimonio; // historico, cultural o geografico
    private double latitud;
    private double longitud;


    public ElementoPatrimonio(String nombre, String descripcion, int etapa, String lugar, String
            nombreImagen, String tipoPatrimonio, double latitud, double longitud) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.etapa = etapa;
        this.lugar = lugar;
        this.nombreImagen = nombreImagen;
        this.tipoPatrimonio = tipoPatrimonio;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    protected ElementoPatrimonio(Parcel in) {
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.etapa = in.readInt();
        this.lugar = in.readString();
        this.nombreImagen = in.readString();
        this.tipoPatrimonio = in.readString();
        this.latitud = in.readDouble();
        this.longitud = in.readDouble();
    }

    public ElementoPatrimonio() {
    }

    public static final Creator<ElementoPatrimonio> CREATOR = new Creator<ElementoPatrimonio>() {
        @Override
        public ElementoPatrimonio createFromParcel(Parcel in) {
            return new ElementoPatrimonio(in);
        }

        @Override
        public ElementoPatrimonio[] newArray(int size) {
            return new ElementoPatrimonio[size];
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
        dest.writeInt(getEtapa());
        dest.writeString(getLugar());
        dest.writeString(getNombreImagen());
        dest.writeString(getTipoPatrimonio());
        dest.writeDouble(getLatitud());
        dest.writeDouble(getLongitud());
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEtapa() {
        return etapa;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public String getTipoPatrimonio() {
        return tipoPatrimonio;
    }

    public double getLatitud() { return latitud; }

    public double getLongitud() { return longitud; }

    @Override
    public String toString() {
        return nombre;
    }
}
