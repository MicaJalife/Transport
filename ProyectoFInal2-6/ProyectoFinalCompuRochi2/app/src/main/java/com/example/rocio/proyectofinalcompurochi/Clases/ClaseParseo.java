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

    public Viaje ParseoViajes(JSONObject json) {
        Viaje miviaje = new Viaje();

        try {
            miviaje.DNI = Integer.parseInt(json.getString("DNI"));
            miviaje.Direccion = json.getString("Direccion");
            miviaje.Nombre = json.getJSONObject("usuario").getString("Nombre");

        }
        catch(JSONException e)
        {
            return null;
        }


        return miviaje;
    }

    public Viaje ParseoViajesComp(JSONObject json) {
        Viaje miviaje = new Viaje();

        try {
            miviaje.DNI = Integer.parseInt(json.getString("DNI"));
            miviaje.IdViaje = Integer.parseInt(json.getString("IdViaje"));
            miviaje.Direccion = json.getString("Direccion");
            miviaje.Nombre = json.getJSONObject("usuario").getString("Nombre");
            miviaje.Horario = json.getString("Horario");
            miviaje.Dia = json.getString("Dia");
            miviaje.Transporte = json.getString("Transporte");
        }
        catch(JSONException e)
        {
            return null;
        }
        return miviaje;
    }



    public Viaje ParseoViajesUsuario(JSONObject json) {
        Viaje miviaje = new Viaje();

        try {
            miviaje.Horario = json.getJSONObject("horario").getString("Horario");
            miviaje.Dia = json.getJSONObject("dia").getString("Dia");
            miviaje.Transporte = json.getJSONObject("transporte").getString("TipoTransporte");
            miviaje.Direccion = json.getString("Direccion");

        }
        catch(JSONException e)
        {
            return null;
        }


        return miviaje;
    }






}
