package com.example.rocio.proyectofinalcompurochi.Clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rocio on 27/6/2017.
 */

public class Usuario implements Parcelable{

    public int DNI;
    public String Nombre;
    public String Contrasenia;
    public int Año;
    public String Curso;
    public String Imagen;
    public boolean PrimeraEdicion;
public void Usuario (Parcel Dato)
{

}
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(DNI);
        dest.writeString(Nombre);
        dest.writeString(Contrasenia);
        dest.writeInt(Año);
        dest.writeString(Curso);
        dest.writeString(Imagen);
        dest.writeBooleanArray(new boolean[] {PrimeraEdicion});
    }

    @Override
    public int describeContents() {
        return 0;
    }
}


