package com.example.rocio.proyectofinalcompurochi.Clases;

import android.util.Log;

import com.example.rocio.proyectofinalcompurochi.Direccion;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rocio on 6/8/2017.
 */

public class ClaseParseo {
    public Direcciones ParseoDireccion (JSONObject json)
    {
        try {
            Direcciones midirec = new Direcciones();


            midirec.Latitud=json.getString("DireccionLatitud");
            midirec.Longitud= json.getString("DireccionLongitud");
            midirec.Direccion= json.getString("Direccion");

            return midirec;

        }
        catch (JSONException e)
        {Log.d("Error JSON",e.getMessage());
            return null;
        }
    }

    public Viaje ParseoViajes(JSONObject json)
    {
        Viaje miviaje = new Viaje();

        /*
        miviaje.DNI=json.getInt("DNI");
        miviaje.IdViaje=json.getInt("IdViaje");
        miviaje.Direccion = json.getString("Direccion");
        miviaje.Nombre=json.getString("Nombre");
        */

        return miviaje;
    }






}
