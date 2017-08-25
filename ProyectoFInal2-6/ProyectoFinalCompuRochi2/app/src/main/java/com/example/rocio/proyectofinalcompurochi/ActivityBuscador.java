package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rocio.proyectofinalcompurochi.Clases.ClaseParseo;
import com.example.rocio.proyectofinalcompurochi.Clases.Direcciones;
import com.example.rocio.proyectofinalcompurochi.Clases.Usuario;
import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ActivityBuscador extends AppCompatActivity {

    Integer DNI;
    String Nombre;
    String Año;
    String Curso;
    String Imagen;

    ArrayList<Direcciones>ArrayObjDirecs;
    ArrayList<Viaje>ArrayViajes;

    Funciones funciones = new Funciones();

    ClaseParseo parseo = new ClaseParseo();

    Toast Cartelito;

    boolean TraeDireccion = false;

    public void BotonBusqueda(View vista)
    {
        Spinner spinnerDia;
        spinnerDia = (Spinner) findViewById(R.id.SpinnerDia);

        Spinner spinnerBloques;
        spinnerBloques = (Spinner) findViewById(R.id.SpinnerBloques);

        Spinner spinnerTransporte;
        spinnerTransporte = (Spinner) findViewById(R.id.SpinnerTransporte);

        RadioButton RadioButtonIda;
        RadioButtonIda = (RadioButton) findViewById(R.id.RadioButtonIda);

        RadioButton RadioButtonVuelta;
        RadioButtonVuelta = (RadioButton) findViewById(R.id.RadioButtonVuelta);

        Spinner SpinnerDirec;
        SpinnerDirec = (Spinner) findViewById(R.id.Spinnerdirecciones);

        Viaje ViajeGuardado = new Viaje();

        if (SpinnerDirec.getSelectedItem()!=null)
        {



        if (RadioButtonIda.isChecked() || RadioButtonVuelta.isChecked())
        {
            if (RadioButtonIda.isChecked()==true)
            {
                ViajeGuardado.DesdeHasta= 1;
            }
            else
            {
                ViajeGuardado.DesdeHasta= 0;
            }

            //PONER BIEN LA URL CON Longitud Latitud Más adelante


            if(spinnerDia.getSelectedItem()!=null && spinnerBloques.getSelectedItem()!=null)
            {
                String dia = spinnerDia.getSelectedItem().toString();
                String bloque = spinnerBloques.getSelectedItem().toString();
                String transporte =spinnerTransporte.getSelectedItem().toString();
                String Direccion = SpinnerDirec.getSelectedItem().toString();

                Direcciones direcslnglatrecibida = new Direcciones();
                direcslnglatrecibida= DevuelvoLatLngDireccion(Direccion);

                ViajeGuardado.DireccionLatitud=direcslnglatrecibida.Latitud;
                ViajeGuardado.DireccionLongitud=direcslnglatrecibida.Longitud;

                ViajeGuardado.IdDia=funciones.TraerIdDia(dia);
                ViajeGuardado.IdHorario=funciones.TraerIdHorario(bloque);
                ViajeGuardado.IdTransporte= funciones.TraerIdTransporte(transporte);

                if(spinnerTransporte.getSelectedItem()!=null)
                {
                    String UrlCnTransporte = "http://transportdale.azurewebsites.net/api/viajes/cercanosdiahorario/" + ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta+ "/" + ViajeGuardado.IdTransporte + "/"+DNI;
                    new TraerViajes().execute(UrlCnTransporte);
                }
                else{
                    String UrlSinFiltro = "http://transportdale.azurewebsites.net/api/viajes/ViajesccDiaHorario/" + ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta;
                    Log.d("Manda url", "ppppp");
                    new TraerViajes().execute(UrlSinFiltro);
                }
                
            }
            else
            {
                Cartelito = Toast.makeText(this, "Debe elegir el día y el horario", Toast.LENGTH_SHORT);
                Cartelito.show();
            }

        }
        else
        {
            Cartelito = Toast.makeText(this, "Debe elegir si va hasta ORT o vuelve de ORT", Toast.LENGTH_SHORT);
            Cartelito.show();
        }

        }else
        {
            Cartelito = Toast.makeText(this, "Debe elegir una de sus direcciones", Toast.LENGTH_SHORT);
            Cartelito.show();
        }

    }

    public void BotonBuscador(View Vista)
    {

        Bundle EnvioDatos;
        EnvioDatos = new Bundle();
        EnvioDatos.putInt("DNI", DNI);
        EnvioDatos.putString("Nombre", Nombre);
        EnvioDatos.putString("Año", Año);
        EnvioDatos.putString("Curso", Curso);
        EnvioDatos.putString("Imagen", Imagen);

        Intent LlamadaActivityBuscador;
        LlamadaActivityBuscador = new Intent(this, ActivityBuscador.class);
        LlamadaActivityBuscador.putExtras(EnvioDatos);
        startActivity(LlamadaActivityBuscador);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

    }
    public void BotonPerfil(View Vista)
    {
        Bundle EnvioDatos;
        EnvioDatos = new Bundle();
        EnvioDatos.putInt("DNI", DNI);
        EnvioDatos.putString("Nombre", Nombre);
        EnvioDatos.putString("Año", Año);
        EnvioDatos.putString("Curso", Curso);
        EnvioDatos.putString("Imagen", Imagen);

        Intent LLamadaActivityPerfil;
        LLamadaActivityPerfil = new Intent(this, ActivityPerfil.class);
        LLamadaActivityPerfil.putExtras(EnvioDatos);
        startActivity(LLamadaActivityPerfil);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

    }
    public void BotonChats(View Vista)
    {


    }

    public Direcciones DevuelvoLatLngDireccion(String DireccionABuscar)
    {
        //ArrayList<Direcciones>ArrayObjDirecs
        int PosicionEncontrada=-1;

        for (int i = 0; i < ArrayObjDirecs.size(); i++){
            Direcciones viajecito;
            viajecito= ArrayObjDirecs.get(i);
            if(viajecito.Direccion.compareTo(DireccionABuscar)==0)
            {
                PosicionEncontrada=i;
            }


        }
        return ArrayObjDirecs.get(PosicionEncontrada);
    }

    private void MostrarCartelitos()
    {
            Cartelito = Toast.makeText(this, "No hay ningun viaje en comun", Toast.LENGTH_SHORT);
            Cartelito.show();
    }


    public void LlamarListViews()
    {
        ListView MiListViewViajes;
        MiListViewViajes = (ListView)findViewById(R.id.ListView_Viajes);

        Log.d("MICA", "LlamarListViews " + ArrayViajes.size() + "");

        AdaptadorParaViajes MiAdaptadorDeViajes;
        MiAdaptadorDeViajes = new AdaptadorParaViajes(ArrayViajes, this);

        MiListViewViajes.setAdapter(MiAdaptadorDeViajes);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);


        Bundle  ReciboUsuario;
        ReciboUsuario = this.getIntent().getExtras();
        DNI = ReciboUsuario.getInt("DNI");
        Nombre = ReciboUsuario.getString("Nombre");
        Año = ReciboUsuario.getString("Año");
        Curso = ReciboUsuario.getString("Curso");
        Imagen = ReciboUsuario.getString("Imagen");


        Spinner spinnerDia;
        spinnerDia = (Spinner) findViewById(R.id.SpinnerDia);

        Spinner spinnerBloques;
         spinnerBloques = (Spinner) findViewById(R.id.SpinnerBloques);

        Spinner spinnerTransporte;
        spinnerTransporte = (Spinner) findViewById(R.id.SpinnerTransporte);

        RadioButton RadioButtonIda;
        RadioButtonIda = (RadioButton) findViewById(R.id.RadioButtonIda);

        RadioButton RadioButtonVuelta;
        RadioButtonVuelta = (RadioButton) findViewById(R.id.RadioButtonVuelta);

        RadioButtonIda.setChecked(true);

        ArrayList<String> ListaDias;
        ListaDias = new ArrayList<>();
        ListaDias.add("Lunes");
        ListaDias.add("Martes");
        ListaDias.add("Miercoles");
        ListaDias.add("Jueves");
        ListaDias.add("Viernes");

        ArrayAdapter<String> AdaptadorDias;
        AdaptadorDias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaDias);
        AdaptadorDias.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDia.setAdapter(AdaptadorDias);

        ArrayList<String> ListaBloques;
        ListaBloques = new ArrayList<>();
        ListaBloques.add("7:45");
        ListaBloques.add("9:20");
        ListaBloques.add("10:55");
        ListaBloques.add("12:15");
        ListaBloques.add("15:30");
        ListaBloques.add("16:00");
        ListaBloques.add("17:30");

        ArrayAdapter<String> AdaptadorBloques;
        AdaptadorBloques = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaBloques);
        AdaptadorBloques.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBloques.setAdapter(AdaptadorBloques);

        ArrayList<String> ListaTransporte;
        ListaTransporte = new ArrayList<>();
        ListaTransporte.add("Auto");
        ListaTransporte.add("Colectivo");
        ListaTransporte.add("Subte");
        ListaTransporte.add("Bicicleta");
        ListaTransporte.add("Caminando");
        ArrayAdapter<String> AdaptadorTransporte;
        AdaptadorTransporte = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaTransporte);
        AdaptadorTransporte.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTransporte.setAdapter(AdaptadorTransporte);


        String UrlTraerDirec = "http://transportdale.azurewebsites.net/api/viajes/direcionspinner/" + DNI.toString();
        new TraerDirecciones().execute(UrlTraerDirec);

    }



    private void ListadeDirecciones (ArrayList<String> direcs)
    {
        Spinner spinnerDirec2;
        spinnerDirec2 = (Spinner) findViewById(R.id.Spinnerdirecciones);
        ArrayAdapter<String> AdaptadorDirecs;
        AdaptadorDirecs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, direcs);
        AdaptadorDirecs.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDirec2.setAdapter(AdaptadorDirecs);
    }




    private class TraerDirecciones extends AsyncTask<String, Void, ArrayList<Direcciones>> {


        protected void onPostExecute(ArrayList<Direcciones> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {
                TraeDireccion = true;

                ArrayList<String> UsuarioDirecs;
                UsuarioDirecs = new ArrayList<>();


                for(int i =0; i<datos.size();i++)
                {
                    Direcciones direc;
                    direc= datos.get(i);
                    UsuarioDirecs.add(direc.Direccion);
                }
               ListadeDirecciones(UsuarioDirecs);

            }

        }

        @Override
        protected ArrayList<Direcciones> doInBackground(String... parametros) {
            String url = parametros[0];
            Log.d("entro al doinbackground", "ppppp");

            ArrayList<Direcciones> ListaDireccion = new ArrayList<Direcciones>();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Log.d("vuelve desp del build", "ppppp");

            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();

                Log.d("trae el resultado", "ppppp");
                try {
                    JSONArray JsonDirecciones = new JSONArray(resultado);
                    Log.d("crea un nuevo json", "ppppp");


                    for (int i = 0; i < JsonDirecciones.length(); i++){
                        Direcciones direcs;
                        direcs = new Direcciones();
                        JSONObject obj = JsonDirecciones.getJSONObject(i);

                        direcs = parseo.ParseoDireccion(obj);

                        ListaDireccion.add(direcs);
                    }

                    ArrayObjDirecs= ListaDireccion;

                    return ListaDireccion;

                }catch (JSONException e){
                    Log.d("Error JSON", e.getMessage());
                    Log.d("error en el json", "ppppp");

                    return null;
                }
            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                Log.d("error de network" + e.getMessage(), "ppppp");

                return null;
            }

        }
    }


    private class TraerViajes extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajes = datos;
                LlamarListViews();
            }
            else {
                MostrarCartelitos();
            }

        }

        @Override
        protected ArrayList<Viaje> doInBackground(String... parametros) {
            String url = parametros[0];
            Log.d("entro al doinbackground", "ppppp");

            ArrayList<Viaje> ArrayViajes = new ArrayList<Viaje>();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Log.d("vuelve desp del build", "ppppp");

            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();

                Log.d("trae el resultado", "ppppp");
                try {
                    JSONArray JsonViajes = new JSONArray(resultado);
                    Log.d("crea un nuevo json", "ppppp");

                    for (int i = 0; i < JsonViajes.length(); i++){
                        Viaje viajecito;
                        viajecito = new Viaje();
                        JSONObject obj = JsonViajes.getJSONObject(i);

                        viajecito = parseo.ParseoViajes(obj);

                        ArrayViajes.add(viajecito);
                    }

                    return ArrayViajes;

                }catch (JSONException e){
                    Log.d("Error JSON", e.getMessage());
                    Log.d("error en el json", "ppppp");

                    return null;
                }
            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                Log.d("error de network" + e.getMessage(), "ppppp");

                return null;
            }

        }
    }






}
