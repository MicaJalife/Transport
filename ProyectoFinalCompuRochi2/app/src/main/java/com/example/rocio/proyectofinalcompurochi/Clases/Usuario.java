package com.example.rocio.proyectofinalcompurochi.Clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rocio on 27/6/2017.
 */

public class Usuario {

    public int DNI;
    public String Nombre;
    public String Contraseña;
    public String Año;
    public String Curso;
    public String Imagen;
    public boolean PrimeraEdicion;


    /*
    //Constructor
    public void Usuario (String DNI,String Nombre,String Contraseña,String Año,String Curso,String Imagen,String PrimeraEdicion)
    {
        this.DNI=DNI;
        this.Nombre=Nombre;
        this.Contraseña=Contraseña;
        this.Año=Año;
        this.Curso=Curso;
        this.Imagen=Imagen;
        this.PrimeraEdicion=PrimeraEdicion;

    }

    public Usuario (Parcel in)
    {
        String[] data = new String[7];

        in.readStringArray(data);

        this.DNI=data[0];
        this.Nombre=data[1];
        this.Contraseña=data[2];
        this.Año=data[3];
        this.Curso=data[4];
        this.Imagen=data[5];
        this.PrimeraEdicion=data[6];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String []{this.DNI, this.Nombre, this.Contraseña,this.Año,
                this.Curso,this.Imagen,this.PrimeraEdicion});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
     public Usuario createFromParcel(Parcel in) {
         return new Usuario(in);
     }
     public Usuario[] newArray(int size){
         return new Usuario[size];
     }

    };


*/

}


