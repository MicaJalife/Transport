package com.example.rocio.proyectofinalcompurochi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;

import java.util.ArrayList;

/**
 * Created by Rocio on 16/10/2017.
 */

public class AdaptadorParaViajesComp extends BaseAdapter {

    private ArrayList<Viaje> MiListaViajes;
    private Context MiContexto;
    View VistaADevolver;

    ActivityBuscador ActividadAnfitriona;


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

    public View getView(final int PosicionActual, final View VistaActual, final ViewGroup GrupoActual)
    {
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
        TextView DesdeHasta;
        DesdeHasta = (TextView) VistaADevolver.findViewById(R.id.DesdeHastatxt);
        TextView TxtId;
        TxtId = (TextView) VistaADevolver.findViewById(R.id.Idtxt);



        /*Button BotonVer;
        BotonVer =(Button)VistaADevolver.findViewById(R.id.BotonVerRecorrido);
        BotonVer.setOnClickListener(this);*/


        final Viaje ViajeDeLaPosicionActual;
        ViajeDeLaPosicionActual = getItem(PosicionActual);


        String nombre = ViajeDeLaPosicionActual.Nombre;
        String direc = ViajeDeLaPosicionActual.Direccion;
        String hora = ViajeDeLaPosicionActual.Horario;
        String dia = ViajeDeLaPosicionActual.Dia;
        String transporte = ViajeDeLaPosicionActual.Transporte;
        int DesHast = ViajeDeLaPosicionActual.DesdeHasta;
        int idd = ViajeDeLaPosicionActual.IdViaje;
        Log.d("MICA", "getView: " + nombre+  " :  "+ direc +  " "+ hora+  " :  "+ dia+  " :  "+ transporte+  " :  "+ idd+  " :  ");
        //Se va a romper aca
        Nombree.setText(ViajeDeLaPosicionActual.Nombre);
        Direc.setText(ViajeDeLaPosicionActual.Direccion);
        Hora.setText(ViajeDeLaPosicionActual.Horario);
        Dia.setText(ViajeDeLaPosicionActual.Dia);
        Transporte.setText(ViajeDeLaPosicionActual.Transporte);
        TxtId.setText(idd+"");
        if (DesHast == 1)
        {
            DesdeHasta.setText("Va hasta ort");
        }
        else{
            DesdeHasta.setText("Vuelve de ort");
        }



        return VistaADevolver;

    }




}
