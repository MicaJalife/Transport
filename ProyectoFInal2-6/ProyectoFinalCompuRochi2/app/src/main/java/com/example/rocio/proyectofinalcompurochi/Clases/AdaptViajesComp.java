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
 * Created by Administrador on 27/10/2017.
 */

public class AdaptViajesComp  extends BaseAdapter {

    private ArrayList<Viaje> MiListaViajeComp;
    private Context MiContexto;
    Funciones funciones = new Funciones();

    public AdaptViajesComp (ArrayList<Viaje> ListaDeViajesUsuarios, Context ContextoAUsar)
    {
        MiListaViajeComp=ListaDeViajesUsuarios;
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
        VistaADevolver =InfladorDeLayouts.inflate(R.layout.listview_viajescomp_detalle_elementos,GrupoActual,false);


        TextView Nombre;
        Nombre = (TextView)VistaADevolver.findViewById(R.id.Nombretxt);
        TextView Direcciones;
        Direcciones = (TextView)VistaADevolver.findViewById(R.id.TextViewDirec);
        TextView Dias;
        Dias = (TextView)VistaADevolver.findViewById(R.id.TextViewDia);
        TextView Horarios;
        Horarios = (TextView)VistaADevolver.findViewById(R.id.TextViewHora);
        TextView Transporte;
        Transporte = (TextView)VistaADevolver.findViewById(R.id.Transportetxt);
        TextView DesdeHasta;
        DesdeHasta = (TextView)VistaADevolver.findViewById(R.id.DesdeHastatxt);


        Viaje ViajeDeLaPosicionActual;
        ViajeDeLaPosicionActual=getItem(PosicionActual);

        Nombre.setText(ViajeDeLaPosicionActual.Nombre);
        Direcciones.setText(ViajeDeLaPosicionActual.Direccion);
        Dias.setText(ViajeDeLaPosicionActual.Dia);
        Horarios.setText(ViajeDeLaPosicionActual.Horario);
        Transporte.setText(ViajeDeLaPosicionActual.Transporte);

        if (ViajeDeLaPosicionActual.DesdeHasta==1){
            DesdeHasta.setText("Va a ort");
        }else
        {
            DesdeHasta.setText("Vuelve de ort");
        }



        return VistaADevolver;
    }

    public int getCount()
    {
        return MiListaViajeComp.size();
    }
    public Viaje getItem (int PosicionAObtener)
    {
        Viaje ViajeADevolver;
        ViajeADevolver=MiListaViajeComp.get(PosicionAObtener);
        return ViajeADevolver;
    }
    public long getItemId (int PosicionAObtener)
    {
        return PosicionAObtener;
    }
}
