package com.example.rocio.proyectofinalcompurochi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rocio on 26/7/2017.
 */

public class AdaptadorParaUsuarios extends BaseAdapter {

    private ArrayList<String> MiListaBuscados;
    private Context MiContexto;

    public AdaptadorParaUsuarios (ArrayList<String> ListaDeUsuarios, Context ContextoAUsar)
    {
        MiListaBuscados=ListaDeUsuarios;
        MiContexto=ContextoAUsar;
    }
    public int getCount()
    {
        return MiListaBuscados.size();
    }
    public String getItem (int PosicionObtener)
    {
        String UsuarioADevolver;
        UsuarioADevolver= MiListaBuscados.get(PosicionObtener);
        return UsuarioADevolver;
    }
    public long getItemId(int PosicionAObtener)
    {
        return PosicionAObtener;
    }
    public View getView (int PosicionActual, View VistaAcutal, ViewGroup GrupoActual)
    {
        View VistaADevolver;
        VistaADevolver=null;

        LayoutInflater InfladorDeLayouts;
        InfladorDeLayouts=(LayoutInflater)MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver=InfladorDeLayouts.inflate(R.layout.listview_usuarios_detalle_elementos,GrupoActual,false);

        TextView Nombretxt;
        Nombretxt = (TextView)VistaADevolver.findViewById(R.id.Nombretxt);

        ImageView Fototxt;
        Fototxt=(ImageView)VistaADevolver.findViewById(R.id.Fototxt);

        ImageButton Amigotxt;
        Amigotxt=(ImageButton)VistaADevolver.findViewById(R.id.imagenamigotxt);




        return VistaADevolver;
    }
}
