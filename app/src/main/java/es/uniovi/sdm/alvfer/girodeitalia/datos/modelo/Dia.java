package es.uniovi.sdm.alvfer.girodeitalia.datos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Dia implements Parcelable {

    private int id;
    private String fecha; // dia-mes, Ej: 14-Mayo
    private String evento;

    public Dia (int id, String fecha, String evento) {
        this.id = id;
        this.fecha = fecha;
        this.evento = evento;
    }

    protected Dia(Parcel in) {
        this.id = in.readInt();
        this.fecha = in.readString();
        this.evento = in.readString();
    }

    public Dia() {
    }

    public static final Creator<Dia> CREATOR = new Creator<Dia>() {
        @Override
        public Dia createFromParcel(Parcel in) {
            return new Dia(in);
        }

        @Override
        public Dia[] newArray(int size) {
            return new Dia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getFecha());
        dest.writeString(getEvento());
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEvento() {
        return evento;
    }

    @Override
    public String toString() {
        return getFecha() + " - " + getEvento();
    }
}
