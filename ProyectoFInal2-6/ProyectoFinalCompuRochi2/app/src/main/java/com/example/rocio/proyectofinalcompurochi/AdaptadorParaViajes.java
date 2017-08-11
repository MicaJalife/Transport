package com.example.rocio.proyectofinalcompurochi;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;

import java.util.ArrayList;

/**
 * Created by Rocio on 10/8/2017.
 */

public class AdaptadorParaViajes extends BaseAdapter {

    private ArrayList<Viaje> MiListaViajes;
    private Context MiContexto;

    public AdaptadorParaViajes (ArrayList<Viaje> ListaDeViajes, Context ContextoAUsar)
    {
        MiListaViajes= ListaDeViajes;
        MiContexto=ContextoAUsar;
    }
    public View getView(int PosicionActual, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        VistaADevolver=null;

        LayoutInflater InfladorDeLayouts;
        InfladorDeLayouts=(LayoutInflater) MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=InfladorDeLayouts.inflate(R.layout.listview_viajes_detalle_elementos, GrupoActual, false);

        TextView Nombre;
        Nombre = (TextView) VistaADevolver.findViewById(R.id.Nombretxt);
        ImageView Imagen;
        Imagen = (ImageView) VistaADevolver.findViewById(R.id.Fototxt);
        ImageButton ImagenAmigo;
        ImagenAmigo =(ImageButton)VistaADevolver.findViewById(R.id.imagenamigo);

        Viaje ViajeDeLaPosicionActual;
        ViajeDeLaPosicionActual = getItem(PosicionActual);

        Nombre.setText(ViajeDeLaPosicionActual.Nombre);

        return VistaADevolver;
    }
    public int getCount()
    {
        return MiListaViajes.size();
    }
    public Viaje getItem (int PosicionAObtener)
    {
        Viaje ViajeADevolver;
        ViajeADevolver = MiListaViajes.get(PosicionAObtener);
        return ViajeADevolver;
    }
    public long getItemId(int PosicionAObtener)
    {
        return PosicionAObtener;
    }

















}
