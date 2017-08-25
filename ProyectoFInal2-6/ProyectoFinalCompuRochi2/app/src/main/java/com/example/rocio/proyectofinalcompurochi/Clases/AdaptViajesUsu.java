package com.example.rocio.proyectofinalcompurochi.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rocio.proyectofinalcompurochi.Funciones;
import com.example.rocio.proyectofinalcompurochi.R;

import java.util.ArrayList;

/**
 * Created by Administrador on 25/8/2017.
 */

public class AdaptViajesUsu extends BaseAdapter {
    private ArrayList<Viaje> MiListaViajeUsuarios;
    private Context MiContexto;
    Funciones funciones = new Funciones();

    public AdaptViajesUsu (ArrayList<Viaje> ListaDeViajesUsuarios, Context ContextoAUsar)
    {
        MiListaViajeUsuarios=ListaDeViajesUsuarios;
        MiContexto= ContextoAUsar;
    }


    public View getView (int PosicionActual, View VistaActual, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        VistaADevolver= null;

        //Declaro el inflador de Layouts
        LayoutInflater InfladorDeLayouts;
        // Inicializo el inflador, por medio de una llamada a un servicio del SO
        InfladorDeLayouts = (LayoutInflater) MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Le digo al inflador que infle la View, en el grupo actual de vistas, y no en el raiz de la View
        VistaADevolver =InfladorDeLayouts.inflate(R.layout.listview_viajesusuarios_detalle_elementos,GrupoActual,false);


        TextView Dias;
        Dias = (TextView)VistaADevolver.findViewById(R.id.TextViewDia);
        TextView Horarios;
        Horarios = (TextView)VistaADevolver.findViewById(R.id.TextViewHora);
        TextView Direcciones;
        Direcciones = (TextView)VistaADevolver.findViewById(R.id.TextViewDirec);

        Viaje ViajeDeLaPosicionActual;
        ViajeDeLaPosicionActual=getItem(PosicionActual);

        Dias.setText(ViajeDeLaPosicionActual.Dia);
        Horarios.setText(ViajeDeLaPosicionActual.Horario);
        Direcciones.setText(ViajeDeLaPosicionActual.Direccion);


        return VistaADevolver;
    }

    public int getCount()
    {
        return MiListaViajeUsuarios.size();
    }
    public Viaje getItem (int PosicionAObtener)
    {
        Viaje ViajeADevolver;
        ViajeADevolver=MiListaViajeUsuarios.get(PosicionAObtener);
        return ViajeADevolver;
    }
    public long getItemId (int PosicionAObtener)
    {
        return PosicionAObtener;
    }

}
