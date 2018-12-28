package es.uniovi.sdm.alvfer.girodeitalia.datos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class ElementoPatrimonio implements Parcelable {

    private String nombre;
    private String descripcion;
    private String lugar;
    private int etapa;
    private String nombreImagen;


    public ElementoPatrimonio(String nombre, String descripcion, int etapa, String lugar, String
            nombreImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.etapa = etapa;
        this.lugar = lugar;
        this.nombreImagen = nombreImagen;
    }

    protected ElementoPatrimonio(Parcel in) {
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.etapa = in.readInt();
        this.lugar = in.readString();
        this.nombreImagen = in.readString();
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

    @Override
    public String toString() {
        return nombre;
    }
}
