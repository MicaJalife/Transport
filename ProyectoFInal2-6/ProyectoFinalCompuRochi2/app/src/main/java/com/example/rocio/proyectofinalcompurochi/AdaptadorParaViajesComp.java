package com.example.rocio.proyectofinalcompurochi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;

import java.util.ArrayList;

/**
 * Created by Rocio on 16/10/2017.
 */

public class AdaptadorParaViajesComp extends BaseAdapter {

    private ArrayList<Viaje> MiListaViajes;
    private Context MiContexto;


    public AdaptadorParaViajesComp (ArrayList<Viaje> ListaDeViajes, Context ContextoAUsar)
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
        VistaADevolver=InfladorDeLayouts.inflate(R.layout.listview_viajescomp_detalle_elementos, GrupoActual, false);

        TextView Nombree;
        Nombree = (TextView) VistaADevolver.findViewById(R.id.Nombretxt);
        TextView Direc;
        Direc = (TextView) VistaADevolver.findViewById(R.id.Direcciontxt);
        TextView Hora;
        Hora = (TextView) VistaADevolver.findViewById(R.id.Horatxt);
        TextView Dia;
        Dia = (TextView) VistaADevolver.findViewById(R.id.Diatxt);
        TextView Transporte;
        Transporte = (TextView) VistaADevolver.findViewById(R.id.Transportetxt);
        TextView Id;
        Id = (TextView) VistaADevolver.findViewById(R.id.Idtxt);
        Button BotonSumarse;
        BotonSumarse =(Button)VistaADevolver.findViewById(R.id.BotonSumarsee);

        Viaje ViajeDeLaPosicionActual;
        ViajeDeLaPosicionActual = getItem(PosicionActual);


        String nombre = ViajeDeLaPosicionActual.Nombre;
        String direc = ViajeDeLaPosicionActual.Direccion;
        String hora = ViajeDeLaPosicionActual.Horario;
        String dia = ViajeDeLaPosicionActual.Dia;
        String transporte = ViajeDeLaPosicionActual.Transporte;
        int id = ViajeDeLaPosicionActual.IdViaje;
        Log.d("MICA", "getView: " + nombre+  " :  "+ direc +  " "+ hora+  " :  "+ dia+  " :  "+ transporte+  " :  "+ id+  " :  ");
        //Se va a romper aca
        Nombree.setText(ViajeDeLaPosicionActual.Nombre);
        Direc.setText(ViajeDeLaPosicionActual.Direccion);
        Hora.setText(ViajeDeLaPosicionActual.Horario);
        Dia.setText(ViajeDeLaPosicionActual.Dia);
        Transporte.setText(ViajeDeLaPosicionActual.Transporte);
        Id.setText(ViajeDeLaPosicionActual.IdViaje);


        return VistaADevolver;
    }
}
