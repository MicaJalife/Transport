package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.service.carrier.CarrierService;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rocio.proyectofinalcompurochi.Clases.ManejoUsuarios;
import com.example.rocio.proyectofinalcompurochi.Clases.Usuario;
import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tagmanager.Container;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ActivityPrimeraEdicion extends AppCompatActivity implements OnMapReadyCallback
{

    Funciones funcion = new Funciones();

    Integer DNI;
    String Nombre;
    String Año;
    String Curso;
    String Imagen;

    EditText direccion;
    TextView dirEncontrada, coordenadas;
    GoogleMap map;
    Toast Cartelito;
    int TransporteSeleccionado = 0;
    boolean IngresoCorrecto = false;
    boolean EligioTranspPublic = false;
    String DatosAMostrar = "";
    double lat;
    double lng;
    boolean seIngreso = false;

    Viaje miViaje = new Viaje();

    public void BotonAgregarDireccion(View Vista) {
        RadioButton RadioIda;
        RadioIda = (RadioButton) findViewById(R.id.RadioButtonIda);

        RadioButton RadioVuelta;
        RadioVuelta = (RadioButton) findViewById(R.id.RadioButtonVuelta);

        EditText LineasFrecuentes;
        LineasFrecuentes = (EditText) findViewById(R.id.IngresoLineas);
        String LineasFrec = LineasFrecuentes.getText().toString();

        TextView DireccionValidada;
        DireccionValidada = (TextView) findViewById(R.id.dirEncontrada);
        String Direc = DireccionValidada.getText().toString();

        TextView DatosCompletos;
        DatosCompletos = (TextView) findViewById(R.id.DatosCompletos);
        String DatosCompletados = DatosCompletos.getText().toString();


        MaterialBetterSpinner spinnerDia;
        spinnerDia = (MaterialBetterSpinner) findViewById(R.id.SpinnerDia);

        MaterialBetterSpinner spinnerBloques;
        spinnerBloques = (MaterialBetterSpinner) findViewById(R.id.SpinnerBloque);

        CheckBox Cbox;
        Cbox = (CheckBox) findViewById(R.id.CheckBox);

        if (Direc.length() != 0 && TransporteSeleccionado != 0) {
            if (spinnerDia != null && spinnerBloques != null) {
                if (TransporteSeleccionado == 2 || TransporteSeleccionado == 3) {
                    EligioTranspPublic = true;
                }
                if (EligioTranspPublic == true && LineasFrec.length() <= 0) {
                    Cartelito = Toast.makeText(this, "Si elige un transporte publico debe especificar las lineas que utiliza", Toast.LENGTH_SHORT);
                    Cartelito.show();
                } else {
                    if (RadioIda.isChecked() == true) {
                        miViaje.DNI = DNI;
                        miViaje.IdHorario = funcion.TraerIdHorario(spinnerBloques.getText().toString());
                        miViaje.IdTransporte = TransporteSeleccionado;
                        miViaje.IdDia = funcion.TraerIdDia(spinnerDia.getText().toString());
                        miViaje.DesdeHasta = 1;
                        miViaje.DetalleTransporte = LineasFrec;
                        miViaje.DireccionLatitud = String.valueOf(lat);
                        miViaje.DireccionLongitud = String.valueOf(lng);
                        miViaje.Direccion = dirEncontrada.getText().toString();

                        DatosAMostrar = DatosAMostrar + "\n" + "Los " + spinnerDia.getText().toString() + " a las " + spinnerBloques.getText().toString() + " va desde " + Direc + " con el transporte " + funcion.TraerElTransporte(TransporteSeleccionado);
                        DatosCompletos.setText(DatosAMostrar);
                        IngresoCorrecto = true;

                        String UrlValidacionDesdeHasta = "http://transportdale.azurewebsites.net/api/viajes/validacion1/" +  miViaje.DNI + "/" + miViaje.IdDia + "/" + miViaje.DesdeHasta;
                        new ValidarCnDH().execute(UrlValidacionDesdeHasta);

                    } else {
                        miViaje.DNI = DNI;
                        miViaje.IdHorario = funcion.TraerIdHorario(spinnerBloques.getText().toString());
                        miViaje.IdTransporte = TransporteSeleccionado;
                        miViaje.IdDia = funcion.TraerIdDia(spinnerDia.getText().toString());
                        miViaje.DesdeHasta = 0;
                        miViaje.DetalleTransporte = LineasFrec;
                        miViaje.DireccionLatitud = String.valueOf(lat);
                        miViaje.DireccionLongitud = String.valueOf(lng);
                        miViaje.Direccion = dirEncontrada.getText().toString();

                        DatosAMostrar = DatosAMostrar + "\n" + "Los " + spinnerDia.getText().toString() + " a las " + spinnerBloques.getText().toString() + " va a " + Direc + " con el transporte " + funcion.TraerElTransporte(TransporteSeleccionado);
                        DatosCompletos.setText(DatosAMostrar);
                        IngresoCorrecto = true;

                        String UrlValidacionDesdeHasta = "http://transportdale.azurewebsites.net/api/viajes/validacion1/" +  miViaje.DNI + "/" + miViaje.IdDia + "/" + miViaje.DesdeHasta;
                        new ValidarCnDH().execute(UrlValidacionDesdeHasta);
                    }

                }
            }
        }

    }

    private void MostrarCartelitos (String Validacion)
    {
        if (Validacion== "validacion1")
        {

        }
        else
        {
            Cartelito = Toast.makeText(this, "Ya ingreso un recorrido de ida/vuelta con ese horario", Toast.LENGTH_SHORT);
            Cartelito.show();
        }

    }

    public void EligioAuto(View Vista) {
        TransporteSeleccionado = 1;
        Cartelito = Toast.makeText(this, "Eligio auto", Toast.LENGTH_SHORT);
        Cartelito.show();
    }

    public void EligioColectivo(View Vista) {
        TransporteSeleccionado = 2;
        Cartelito = Toast.makeText(this, "Eligio colectivo", Toast.LENGTH_SHORT);
        Cartelito.show();
    }

    public void EligioSubte(View Vista) {
        TransporteSeleccionado = 3;
        Cartelito = Toast.makeText(this, "Eligio subte", Toast.LENGTH_SHORT);
        Cartelito.show();
    }

    public void EligioBicicleta(View Vista) {
        TransporteSeleccionado = 4;
        Cartelito = Toast.makeText(this, "Eligio bicicleta", Toast.LENGTH_SHORT);
        Cartelito.show();
    }

    public void EligioCaminando(View Vista) {
        TransporteSeleccionado = 5;
        Cartelito = Toast.makeText(this, "Eligio caminar", Toast.LENGTH_SHORT);
        Cartelito.show();
    }


    private class ValidarCnDH extends AsyncTask<String,Void,Viaje>
    {

        protected void onPostExecute(Viaje datos){
            super.onPostExecute(datos);

            if (datos != null)
            {
                MostrarCartelitos("validacion1");

            }
            else {
                String UrlValidacionHorario = "http://transportdale.azurewebsites.net/api/viajes/validacion2/" +  miViaje.DNI + "/" + miViaje.IdDia+ "/" + miViaje.IdHorario;
                new ValidarCnH().execute(UrlValidacionHorario);
            }
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

                    Log.d("crea un nuevo json", "ppppp");

                        JSONObject jsonViaje = new JSONObject(resultado);

                        Viaje viaje = new Viaje();
                        Log.d("declara usuario", "ppppp");

                        viaje.DNI = jsonViaje.getInt("DNI");
                        viaje.IdDia = jsonViaje.getInt("IdDia");
                        viaje.DesdeHasta = jsonViaje.getInt("DesdeHasta");

                        Log.d("parsea el json", "ppppp");

                        return viaje;


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



    private class ValidarCnH extends AsyncTask<String,Void,Viaje>{

        protected void onPostExecute(Viaje datos){
            super.onPostExecute(datos);

            if (datos != null)
            {
                MostrarCartelitos("validacion2");
            }
            else {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                System.out.println(gson.toJson(miViaje));

                String urlDeApi ="http://transportdale.azurewebsites.net/api/ingresarviaje/";
                new IngresarViaje().execute("POST",urlDeApi, gson.toJson(miViaje));
            }
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
                    JSONObject jsonViaje = new JSONObject(resultado);
                    Log.d("crea un nuevo json", "ppppp");

                        Viaje viaje = new Viaje();
                        Log.d("declara usuario", "ppppp");

                        viaje.DNI = jsonViaje.getInt("DNI");
                        viaje.IdDia = jsonViaje.getInt("IdDia");
                        viaje.IdHorario = jsonViaje.getInt("IdHorario");

                        Log.d("parsea el json", "ppppp");

                        return viaje;

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


    private class IngresarViaje extends AsyncTask<String, Void,  Viaje> {
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
                Cartelito = Toast.makeText(getApplicationContext(), "Guardo su viaje con exito", Toast.LENGTH_SHORT);
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

    public void SiguientePrimeraEdicion(View Vista)
    {
        if  (IngresoCorrecto!=true)
        {
            Cartelito = Toast.makeText(this, "Debe ingresar al menos un recorrido", Toast.LENGTH_SHORT);
            Cartelito.show();
        }
        else
        {
            Bundle EnvioDatos;
            EnvioDatos = new Bundle();
            EnvioDatos.putInt("DNI", DNI);
            EnvioDatos.putString("Nombre", Nombre);
            EnvioDatos.putString("Año", Año);
            EnvioDatos.putString("Curso", Curso);
            EnvioDatos.putString("Imagen", Imagen);
            Intent LlamadaActivityPerfil;
            LlamadaActivityPerfil = new Intent(this, ActivityPerfil.class);
            LlamadaActivityPerfil.putExtras(EnvioDatos);
            startActivity(LlamadaActivityPerfil);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_edicion);

        Usuario usuarioActual = ManejoUsuarios.getUsuario();

        TextView Nombre;
        Nombre = (TextView) findViewById(R.id.txtnombreyapellido);

        TextView Anio;
        Anio = (TextView) findViewById(R.id.txtaño);

        TextView Curso;
        Curso = (TextView) findViewById(R.id.txtcurso);

        ImageView Imagen;
        Imagen = (ImageView) findViewById(R.id.fotoperfil);

        Bundle  ReciboUsuario;
        ReciboUsuario = this.getIntent().getExtras();
        DNI = ReciboUsuario.getInt("DNI");
        Nombre.setText(ReciboUsuario.getString("Nombre"));
        Anio.setText(ReciboUsuario.getString("Año"));
        Curso.setText(ReciboUsuario.getString("Curso"));

        String Imagenn = ReciboUsuario.getString("Imagen");


        RadioButton RadioIda;
        RadioIda = (RadioButton) findViewById(R.id.RadioButtonIda);
        RadioIda.setChecked(true);

       MaterialBetterSpinner spinnerDia;
       spinnerDia = (MaterialBetterSpinner) findViewById(R.id.SpinnerDia);

        MaterialBetterSpinner spinnerBloques;
        spinnerBloques = (MaterialBetterSpinner) findViewById(R.id.SpinnerBloque);


        ArrayList<String> ListaDias;
        ListaDias = new ArrayList<>();
        ListaDias.add("Elegir");
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
        ListaBloques.add("Elegir");
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


        ImageView foto = (ImageView) findViewById(R.id.fotoperfil);
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.fotoperfil);
        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), avatar);
        roundDrawable.setCircular(true);
        foto.setImageDrawable(roundDrawable);

        direccion = (EditText) findViewById(R.id.IngresoDireccion);
        dirEncontrada = (TextView) findViewById(R.id.dirEncontrada);
        coordenadas = (TextView) findViewById(R.id.coordenadas);


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

    public void consultarDireccion(View v)
    {
        String dirStr = direccion.getText().toString();
        if (!dirStr.isEmpty()) {
            new GeolocalizacionTask().execute(dirStr);  // Llamo a clase async con url
        }
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
                dirEncontrada.setText(addressStr);

                // Muestro coordenadas
                lat = dirRecibida.getLatitude(); //
                lng = dirRecibida.getLongitude();
                String coordStr = lat + "," + lng;
                coordenadas.setText(coordStr);  // Muestro coordenadas en pantalla


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

}

