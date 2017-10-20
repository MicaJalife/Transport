package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rocio.proyectofinalcompurochi.Clases.AdaptViajesUsu;
import com.example.rocio.proyectofinalcompurochi.Clases.ClaseParseo;
import com.example.rocio.proyectofinalcompurochi.Clases.Usuario;
import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityPerfil extends AppCompatActivity {

    Viaje miViaje = null;
    boolean existeviaje=false;
    int DniUsuario;

    Integer DNI;
    String Nombre;
    String Año;
    String Curso;
    String Imagen;
    String EsViajeComp;

    Funciones funciones = new Funciones();
    ArrayList<Viaje>ArrayViajesUsuario;
    ArrayList<Viaje>ArrayViajesComp;
    ClaseParseo parseo = new ClaseParseo();

    public void BotonBuscador(View Vista)
    {
        Bundle EnvioDatos;
        EnvioDatos = new Bundle();
        EnvioDatos.putInt("DNI", DNI);
        EnvioDatos.putString("Nombre", Nombre);
        EnvioDatos.putString("Año", Año);
        EnvioDatos.putString("Curso", Curso);
        EnvioDatos.putString("Imagen", Imagen);
        EsViajeComp="NO";

        EnvioDatos.putString("EsViajesCompartidos", EsViajeComp);


        Intent LlamadaActivityBuscador;
        LlamadaActivityBuscador = new Intent(this, ActivityBuscador.class);
        LlamadaActivityBuscador.putExtras(EnvioDatos);
        startActivity(LlamadaActivityBuscador);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);

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
        EsViajeComp="SI";

        EnvioDatos.putString("EsViajesCompartidos", EsViajeComp);


        Intent LLamadaActivityPerfil;
        LLamadaActivityPerfil = new Intent(this, ActivityPerfil.class);
        LLamadaActivityPerfil.putExtras(EnvioDatos);
        startActivity(LLamadaActivityPerfil);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

    }
    public void BotonChats(View Vista)
    {


    }

    public void LlamarListViewsViajesUsuariosIda()
    {
        ListView MiListViewViajesIda;
        MiListViewViajesIda=(ListView) findViewById(R.id.ListView_ViajesUsuarioIda);

        AdaptViajesUsu MiAdaptadorViajesUsuariosIda;
        MiAdaptadorViajesUsuariosIda = new AdaptViajesUsu(ArrayViajesUsuario, this);

        MiListViewViajesIda.setAdapter(MiAdaptadorViajesUsuariosIda);
    }

    public void LlamarListViewsViajesUsuariosVuelta()
    {
        ListView MiListViewViajesVuelta;
        MiListViewViajesVuelta=(ListView) findViewById(R.id.ListView_ViajesUsuarioVuelta);

        AdaptViajesUsu MiAdaptadorViajesUsuariosVuelta;
        MiAdaptadorViajesUsuariosVuelta = new AdaptViajesUsu(ArrayViajesUsuario, this);

        MiListViewViajesVuelta.setAdapter(MiAdaptadorViajesUsuariosVuelta);
    }

    public void LlamarListViewsViajesCompIda()
    {
        ListView MiListViewCompartidosIda;
        MiListViewCompartidosIda=(ListView) findViewById(R.id.ListView_ViajesCompIda);

        AdaptViajesUsu MiAdaptadorViajesCompartidosIda;
        MiAdaptadorViajesCompartidosIda = new AdaptViajesUsu(ArrayViajesComp, this);

        MiListViewCompartidosIda.setAdapter(MiAdaptadorViajesCompartidosIda);
    }

    public void LlamarListViewsViajesCompVuelta()
    {
        ListView MiListViewCompartidosVuelta;
        MiListViewCompartidosVuelta=(ListView) findViewById(R.id.ListView_ViajesCompVuelta);

        AdaptViajesUsu MiAdaptadorViajesCompartidosVuelta;
        MiAdaptadorViajesCompartidosVuelta = new AdaptViajesUsu(ArrayViajesComp, this);

        MiListViewCompartidosVuelta.setAdapter(MiAdaptadorViajesCompartidosVuelta);
    }




    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageView foto = (ImageView)findViewById(R.id.foto);
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.fotoperfil);
        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), avatar);
        roundDrawable.setCircular(true);
        foto.setImageDrawable(roundDrawable);

        TextView TxtNombre;
        TxtNombre = (TextView) findViewById(R.id.txtnombreyapellido);

        TextView TxtCurso;
        TxtCurso = (TextView) findViewById(R.id.txtcurso);

        TextView TxtAnio;
        TxtAnio = (TextView) findViewById(R.id.txtaño);

        ImageView txtImagen;
        txtImagen = (ImageView) findViewById(R.id.foto);

        Bundle  ReciboUsuario;
        ReciboUsuario = this.getIntent().getExtras();
        DNI = ReciboUsuario.getInt("DNI");
        Nombre = ReciboUsuario.getString("Nombre");
        Año = ReciboUsuario.getString("Año");
        Curso = ReciboUsuario.getString("Curso");
        Imagen = ReciboUsuario.getString("Imagen");

        TxtNombre.setText(ReciboUsuario.getString("Nombre"));
        TxtAnio.setText(ReciboUsuario.getString("Año"));
        TxtCurso.setText(ReciboUsuario.getString("Curso"));


        String par = "http://transportdale.azurewebsites.net/api/traerviaje/"+DniUsuario;
        new TraerViaje().execute(par);


        String UrlViajesUsuariosIda = "http://transportdale.azurewebsites.net/api/viajes/ViajesUsuarioDesdeHasta/" + DNI.toString()+ "/" +1;
        new TraerViajesUsuarioIda().execute(UrlViajesUsuariosIda);


        String UrlViajesUsuariosVuelta = "http://transportdale.azurewebsites.net/api/viajes/ViajesUsuarioDesdeHasta/"+ DNI.toString()+ "/" +0;
        new TraerViajesUsuarioVuelta().execute(UrlViajesUsuariosVuelta);


        String UrlViajesCompIda = ""+ 1;
        new TraigoViajesCompIda().execute(UrlViajesCompIda);

        String UrlViajesCompVuelta = ""+0;
        new TraigoViajesCompVuelta().execute(UrlViajesCompVuelta);


    }

    private class TraigoViajesCompIda extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajesComp = datos;
                LlamarListViewsViajesCompIda();
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

                        viajecito = parseo.ParseoViajesUsuario(obj);

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

    private class TraigoViajesCompVuelta extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajesComp = datos;
                LlamarListViewsViajesCompVuelta();
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

                        viajecito = parseo.ParseoViajesUsuario(obj);

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




    private class TraerViajesUsuarioIda extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajesUsuario = datos;
                LlamarListViewsViajesUsuariosIda();
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

                        viajecito = parseo.ParseoViajesUsuario(obj);

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

    private class TraerViajesUsuarioVuelta extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajesUsuario = datos;
                LlamarListViewsViajesUsuariosVuelta();
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

                        viajecito = parseo.ParseoViajesUsuario(obj);

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

    private class TraerViaje extends AsyncTask<String, Void, Viaje>
    {
        protected void OnPostExecute(Viaje datos)
        {
            super.onPostExecute(datos);

            if (datos != null)
            {
                existeviaje = true;
                miViaje = datos;
            }

        }

        @Override
        protected Viaje doInBackground(String... parametros) {
            String url = parametros[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();


            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                try {

                    JSONObject jsonViaje = new JSONObject(resultado);

                    Viaje v = new Viaje();

                    v.DNI = jsonViaje.getInt("DNI");
                    v.IdHorario = jsonViaje.getInt("IdHorario");
                    v.IdTransporte = jsonViaje.getInt("IdTransporte");
                    v.IdDia = jsonViaje.getInt("IdDia");
                    v.DesdeHasta = jsonViaje.getInt("DesdeHasta");
                    v.DetalleTransporte = jsonViaje.getString("DetalleTransporte");
                    v.DireccionLatitud = jsonViaje.getString("DireccionLatitud");
                    v.DireccionLongitud = jsonViaje.getString("DireccionLongitud");


                    return v;
                }catch (JSONException e){
                    Log.d("Error JSON", e.getMessage());
                    return null;
                }
            } catch (IOException e) {
                Log.d("Error",e.getMessage());             // Error de Network
                return null;
            }

        }

    }


}
