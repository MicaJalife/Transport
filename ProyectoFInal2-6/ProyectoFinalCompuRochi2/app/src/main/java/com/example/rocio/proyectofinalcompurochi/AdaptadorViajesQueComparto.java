package com.example.rocio.proyectofinalcompurochi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;

import java.util.ArrayList;

/**
 * Created by Administrador on 20/10/2017.
 */

public class AdaptadorViajesQueComparto extends BaseAdapter {

    private ArrayList<Viaje> MiListaViajes;
    private Context MiContexto;


    public AdaptadorViajesQueComparto (ArrayList<Viaje> ListaDeViajes, Context ContextoAUsar)
    {
        MiListaViajes= ListaDeViajes;
        Log.d("MICA", "Constructor " + ListaDeViajes.size() + "");
        MiContexto=ContextoAUsar;
    }

    public int getCount()
    {
        return MiListaViajes.size();
    }

    public Viaje getItem (int PosicionAObtener)
    {
        Viaje ViajeADevolver;
        ViajeADevolver = MiListaViajes.get(PosicionAObtener);
        Log.d("MICA", "getItem " + String.valueOf(PosicionAObtener) +  "");
        return ViajeADevolver;
    }

    public long getItemId(int PosicionAObtener)
    {
        return PosicionAObtener;
    }

    public View getView(int PosicionActual, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        VistaADevolver=null;

        LayoutInflater InfladorDeLayouts;
        InfladorDeLayouts=(LayoutInflater) MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=InfladorDeLayouts.inflate(R.layout.listview_viajes_detalle_elementos, GrupoActual, false);

        TextView Nombree;
        Nombree = (TextView) VistaADevolver.findViewById(R.id.Nombretxt);
        ImageView Imagen;
        Imagen = (ImageView) VistaADevolver.findViewById(R.id.Fototxt);
        //Button BotonSumarse;
        //BotonSumarse =(Button)VistaADevolver.findViewById(R.id.imagenamigo);

        Viaje ViajeDeLaPosicionActual;
        ViajeDeLaPosicionActual = getItem(PosicionActual);


        String nombre = ViajeDeLaPosicionActual.Nombre;
        String direc = ViajeDeLaPosicionActual.Direccion;
        Log.d("MICA", "getView: " + nombre+  " :  "+ direc +  "");
        //Se va a romper aca
        Nombree.setText(ViajeDeLaPosicionActual.Nombre);

        return VistaADevolver;
    }
}