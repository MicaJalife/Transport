package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ActivityBuscador extends AppCompatActivity implements OnMapReadyCallback {

    Integer DNI;
    String Nombre;
    String Año;
    String Curso;
    String Imagen;
    String EsViajeComp;
    boolean ConfirmoDirec=false;

    String direccion;
    String dirEncontrada, coordenadas;
    GoogleMap map;
    double lat;
    double lng;

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

        EditText EditDirecs;
        EditDirecs = (EditText) findViewById(R.id.EditDirecciones);
        String Direccion = EditDirecs.getText().toString();

        Viaje ViajeGuardado = new Viaje();

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


            if(spinnerDia.getSelectedItem()!=null && spinnerBloques.getSelectedItem()!=null && ConfirmoDirec==true)
            {
                String dia = spinnerDia.getSelectedItem().toString();
                String bloque = spinnerBloques.getSelectedItem().toString();
                String transporte =spinnerTransporte.getSelectedItem().toString();




                Direcciones direcslnglatrecibida = new Direcciones();



                ViajeGuardado.DireccionLatitud=""+lat+"";
                ViajeGuardado.DireccionLongitud=""+lng+"";

                ViajeGuardado.IdDia=funciones.TraerIdDia(dia);
                ViajeGuardado.IdHorario=funciones.TraerIdHorario(bloque);
                ViajeGuardado.IdTransporte= funciones.TraerIdTransporte(transporte);

                if(spinnerTransporte.getSelectedItem()!=null)
                {
                    if(EsViajeComp.equals("SI")){
                        //Traer todos los viajes que tengan lugar y devolver (Nombre, Direccion, Horario, Dia, Transporte, IdViaje)
                        String UrlViajesCompFiltrados = "http://transportdale.azurewebsites.net/api/viajes/cercanosdiahorariotrans/"+ ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta+ "/" + ViajeGuardado.IdTransporte + "/"+DNI;
                        new TraerViajesCommpFiltrados().execute(UrlViajesCompFiltrados);

                    }else{
                        String UrlCnTransporte = "http://transportdale.azurewebsites.net/api/viajes/cercanosdiahorario/" + ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta+ "/" + ViajeGuardado.IdTransporte + "/"+DNI;
                        new TraerViajes().execute(UrlCnTransporte);
                    }


                }
                else{
                    if(EsViajeComp.equals("SI")){
                        //Traer todos los viajes que tengan lugar y devolver (Nombre, Direccion, Horario, Dia, Transporte, IdViaje)
                        String UrlViajesCompFiltrados = "http://transportdale.azurewebsites.net/api/viajes/cercanosdiahorariotrans/"+ ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta+ "/" + ViajeGuardado.IdTransporte + "/"+DNI;
                        new TraerViajesCommpFiltrados().execute(UrlViajesCompFiltrados);

                    }else{
                        String UrlSinFiltro = "http://transportdale.azurewebsites.net/api/viajes/ViajesccDiaHorario/" + ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta;
                        Log.d("Manda url", "ppppp");
                        new TraerViajes().execute(UrlSinFiltro);
                    }
                }
                
            }
            else
            {
                Cartelito = Toast.makeText(this, "Debe elegir el día y el horario y confirmar la direccion", Toast.LENGTH_SHORT);
                Cartelito.show();
            }

        }
        else
        {
            Cartelito = Toast.makeText(this, "Debe elegir si va hasta ORT o vuelve de ORT", Toast.LENGTH_SHORT);
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
        EnvioDatos.putString("EsViajesCompartidos", EsViajeComp);

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

        Bundle EnvioDatos;
        EnvioDatos = new Bundle();
        EnvioDatos.putInt("DNI", DNI);
        EnvioDatos.putString("Nombre", Nombre);
        EnvioDatos.putString("Año", Año);
        EnvioDatos.putString("Curso", Curso);
        EnvioDatos.putString("Imagen", Imagen);
        Intent LlamadaActivityPrimeraEdicion;
        LlamadaActivityPrimeraEdicion = new Intent(getApplicationContext(), ActivityPrimeraEdicion.class);
        LlamadaActivityPrimeraEdicion.putExtras(EnvioDatos);
        startActivity(LlamadaActivityPrimeraEdicion);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

    }

    public void ConfirmarDirec(View v)
    {
        EditText EditDirecs;
        EditDirecs = (EditText) findViewById(R.id.EditDirecciones);
        String Direccion = EditDirecs.getText().toString();

        if (!Direccion.isEmpty()) {
            new GeolocalizacionTask().execute(Direccion);  // Llamo a clase async con url
            ConfirmoDirec=true;
        }


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
    public void LlamarListViewsViajesComp()
    {
        ListView MiListViewViajesComp;
        MiListViewViajesComp = (ListView)findViewById(R.id.ListView_Viajes);

        Log.d("MICA", "LlamarListViews " + ArrayViajes.size() + "");

        AdaptadorParaViajesComp MiAdaptadorDeViajesComp;
        MiAdaptadorDeViajesComp = new AdaptadorParaViajesComp(ArrayViajes, this);

        MiListViewViajesComp.setAdapter(MiAdaptadorDeViajesComp);

        MiListViewViajesComp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Pos = position;

                Viaje viajeciito = new Viaje();

                viajeciito= ArrayViajes.get(Pos);

                String UrlSumarme = "http://transportdale.azurewebsites.net/api/viajescompartidos/compartirelviaje/" + viajeciito.IdViaje + "/"+ DNI;
                Log.d("Manda url", "ppppp");
                new SumarmeAUnViaje().execute(UrlSumarme);

                CartelitoSumarse();
            }
        });


    }
    public void CartelitoSumarse()
    {
        Cartelito = Toast.makeText(this, "Se pudo sumar correctamente", Toast.LENGTH_SHORT);
        Cartelito.show();

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
        EsViajeComp = ReciboUsuario.getString("EsViajesCompartidos");


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

        if(EsViajeComp=="SI")
        {
            //Traer todos los viajes que tengan lugar y devolver (Nombre, Direccion, Horario, Dia, Transporte, IdViaje)
            String UrlViajesDisponibles = "http://transportdale.azurewebsites.net/api/viajes/disponibles";
            new TraerViajesCompDisp().execute(UrlViajesDisponibles);
        }




        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap map)
    {
        this.map = map;
        map.getUiSettings().setZoomControlsEnabled(true);

    }

    public void consultarDireccion()
    {

    }


    // Utiliza la clase android.location.Geocoder
    // Parametros
    // String - la direccion a buscar que recibe doInBackground
    // Void -  Progreso (no se usa)
    // List<Address> - lo que devuelve doInBackground
    private class GeolocalizacionTask extends AsyncTask<String, Void, List<Address>>
    {

        @Override
        protected void onPostExecute(List<Address> direcciones)
        {
            super.onPostExecute(direcciones);

            if (!direcciones.isEmpty())
            {
                // Muestro la primera direccion recibida
                Address dirRecibida = direcciones.get(0);  // La primera direccion
                String addressStr = dirRecibida.getAddressLine(0);  // Primera linea del texto


                // Muestro coordenadas
                lat = dirRecibida.getLatitude(); //
                lng = dirRecibida.getLongitude();
                String coordStr = lat + "," + lng;


                //Ubico la direccion en el mapa

                if (map != null)
                {
                    CameraUpdate center =
                            CameraUpdateFactory.newLatLng(new LatLng(lat, lng));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                    map.moveCamera(center);
                    map.animateCamera(zoom);   // Posiciono la camara en las coordenadas recibidas

                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, lng))
                            .title(dirRecibida.getAddressLine(0)));  // Dibujo el marker
                }
            }
        }

        @Override
        protected List<Address> doInBackground(String... params)
        {
            String address = params[0];

            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> addresses = null;
            try {
                // Utilizo la clase Geocoder para buscar la direccion. Limito a 10 resultados
                addresses = geocoder.getFromLocationName(address, 10);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return addresses;
        }

    }

    private class SumarmeAUnViaje extends AsyncTask<String, Void, Viaje> {
        protected void onPostExecute(Viaje datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");
        }

        @Override
        protected Viaje doInBackground(String... parametros) {
            String url = parametros[0];
            Log.d("entro al doinbackground", "ppppp");
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

                       JSONObject JsonViaje = new JSONObject(resultado);
                       Log.d("crea un nuevo json", "ppppp");

                       Viaje ViajeCompartido;
                       ViajeCompartido = new Viaje();


                       ViajeCompartido = parseo.ParseoViajesComp(JsonViaje);


                       return ViajeCompartido;

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


    private class SumarmeAUnViajeee extends AsyncTask<String, Void,  Viaje> {
        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected Viaje doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];
            String resultado;

            if (method.equals("POST")) {
                String json = params[2];
                postViaje(urlApi, json);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Viaje viaje) {

            super.onPostExecute(viaje);
            //Log.d("ope :",persona.getNombre());
            if (viaje != null) {
                Cartelito = Toast.makeText(getApplicationContext(), "Se unio al viaje exitosamente", Toast.LENGTH_SHORT);
                Cartelito.show();
            }

        }


        private void postViaje(String urlApi, String json) {

            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

            RequestBody body = RequestBody.create(JSON, json);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(urlApi)
                    .post(body)
                    .build();

            try {
                okhttp3.Response response = client.newCall(request).execute();
                return;
            } catch (IOException e) {
                Log.d("Error :", e.getMessage());
                return;

            }
        }
    }


    private class TraerViajesCompDisp extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajes = datos;
                LlamarListViewsViajesComp();

                for (int i = 0; i < datos.size(); i++){
                    Viaje ViajeCompartido;
                    ViajeCompartido = new Viaje();

                    ViajeCompartido= datos.get(i);



                }


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
                        Viaje ViajeCompartido;
                        ViajeCompartido = new Viaje();
                        JSONObject obj = JsonViajes.getJSONObject(i);

                        ViajeCompartido = parseo.ParseoViajesComp(obj);

                        ArrayViajes.add(ViajeCompartido);
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


    private class TraerViajesCommpFiltrados extends AsyncTask<String, Void, ArrayList<Viaje>> {


        protected void onPostExecute(ArrayList<Viaje> datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

            if (datos != null) {

                ArrayViajes = datos;
                LlamarListViewsViajesComp();

                for (int i = 0; i < ArrayViajes.size(); i++){
                    Viaje ViajeCompartido;
                    ViajeCompartido = new Viaje();

                    ViajeCompartido= ArrayViajes.get(i);

                    Double latitud =Double.parseDouble(ViajeCompartido.DireccionLatitud) ;
                    Double longitud =Double.parseDouble(ViajeCompartido.DireccionLongitud);
                    String Direccionn= ViajeCompartido.Direccion;


                    //Ubico la direccion en el mapa

                    if (map != null)
                    {
                        CameraUpdate center =
                                CameraUpdateFactory.newLatLng(new LatLng(lat, lng));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
                        map.moveCamera(center);
                        map.animateCamera(zoom);   // Posiciono la camara en las coordenadas recibidas

                        map.addMarker(new MarkerOptions()
                             .position(new LatLng(latitud, longitud)));  // Dibujo el marker
                    }

                }



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
                        Viaje ViajeCompartido;
                        ViajeCompartido = new Viaje();
                        JSONObject obj = JsonViajes.getJSONObject(i);

                        ViajeCompartido = parseo.ParseoViajesComp(obj);

                        ArrayViajes.add(ViajeCompartido);
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
