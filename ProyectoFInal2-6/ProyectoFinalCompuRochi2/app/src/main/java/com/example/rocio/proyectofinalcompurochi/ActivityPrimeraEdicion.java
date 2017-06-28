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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityPrimeraEdicion extends AppCompatActivity implements OnMapReadyCallback {

    Funciones funcion = new Funciones();


    EditText direccion;
    TextView dirEncontrada, coordenadas;
    GoogleMap map;
    Toast Cartelito;
    int TransporteSeleccionado = 0;
    boolean IngresoCorrecto=false;
    boolean EligioTranspPublic = false;
    String DatosAMostrar = "";
    boolean ErrorLineas = false;
    double lat;
    double lng;

    Usuario usuariorecibido;

    public void BotonAgregarDireccion(View Vista)
    {
        TextView Nombre;
        Nombre = (TextView) findViewById(R.id.txtnombreyapellido);
        Nombre.setText(usuariorecibido.Nombre);


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


        Spinner spinnerDia;
        spinnerDia = (Spinner) findViewById(R.id.SpinnerDia);

        Spinner spinnerBloques;
        spinnerBloques = (Spinner) findViewById(R.id.SpinnerBloque);

        CheckBox Cbox;
        Cbox = (CheckBox) findViewById(R.id.CheckBox);


        Viaje viaje = new Viaje();

        if (spinnerDia.isSelected() == false || spinnerBloques.isSelected() == false || TransporteSeleccionado == 0 || DireccionValidada.length() == 0)
        {
            if (TransporteSeleccionado == 2 || TransporteSeleccionado == 3)
            {

                EligioTranspPublic = true;
            }
            if (EligioTranspPublic == false)
            {
            }
            else
            {
                if (EligioTranspPublic == true && LineasFrec.length() > 0) {

                } else {
                    ErrorLineas = true;
                    Cartelito = Toast.makeText(this, "Si elige un transporte publico debe especificar las lineas que utiliza", Toast.LENGTH_SHORT);
                    Cartelito.show();
                }
            }


            if (ErrorLineas == false) {

                if (RadioIda.isChecked() == true) {

                    viaje.DesdeHasta = true;
                    viaje.IdDia = funcion.TraerIdDia(spinnerDia.getSelectedItem().toString());
                    viaje.IdHorario = funcion.TraerIdHorario(spinnerBloques.getSelectedItem().toString());
                    viaje.IdTransporte = TransporteSeleccionado;
                    viaje.DetalleTransporte = LineasFrec;
                    viaje.DireccionLatitud = lat;
                    viaje.DireccionLongitud = lng;


                    DatosAMostrar = DatosAMostrar + "\n" + "Los " + spinnerDia.getSelectedItem().toString() + " a las " + spinnerBloques.getSelectedItem().toString() + " va a " + DireccionValidada + " con el transporte " + TransporteSeleccionado;
                    DatosCompletos.setText(DatosAMostrar);

                    IngresoCorrecto=true;

                } else
                {
                    if (RadioVuelta.isChecked() == true) {
                        viaje.DesdeHasta = false;
                        viaje.IdDia = funcion.TraerIdDia(spinnerDia.getSelectedItem().toString());
                        viaje.IdHorario = funcion.TraerIdHorario(spinnerBloques.getSelectedItem().toString());
                        viaje.IdTransporte = TransporteSeleccionado;
                        viaje.DetalleTransporte = LineasFrec;
                        viaje.DireccionLatitud = lat;
                        viaje.DireccionLongitud = lng;

                        DatosAMostrar = DatosAMostrar + "\n" + "Los " + spinnerDia.getSelectedItem().toString() + " a las " + spinnerBloques.getSelectedItem().toString() + " va a " + DireccionValidada + " con el transporte " + TransporteSeleccionado;
                        DatosCompletos.setText(DatosAMostrar);
                        IngresoCorrecto=true;

                    }
                }
            } else {
                Cartelito = Toast.makeText(this, "Debe seleccionar el dia, el bloque, el transporte y mostrar su direccion", Toast.LENGTH_SHORT);
                Cartelito.show();
            }
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


    public void SiguientePrimeraEdicion(View Vista) {


        if ( IngresoCorrecto==true) {
            Cartelito = Toast.makeText(this, "Debe ingresar al menos un recorrido", Toast.LENGTH_SHORT);
            Cartelito.show();
        } else {
            String par = "localhost/api/ingresarviaje";
            new IngresarViaje().execute(par);


            Intent LlamadaActivityPerfil;
            LlamadaActivityPerfil = new Intent(this, ActivityPerfil.class);
            startActivity(LlamadaActivityPerfil);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_edicion);


        Intent intentLlamador = getIntent();
        usuariorecibido = intentLlamador.getParcelableExtra("EnvioUsuario");


        RadioButton RadioIda;
        RadioIda = (RadioButton) findViewById(R.id.RadioButtonIda);
        RadioIda.setChecked(true);

        Spinner spinnerDia;
        spinnerDia = (Spinner) findViewById(R.id.SpinnerDia);

        Spinner spinnerBloques;
        spinnerBloques = (Spinner) findViewById(R.id.SpinnerBloque);


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
    private class IngresarViaje extends AsyncTask<String, Void, Viaje> {

        protected void onPostExecute(Viaje datos) {
            super.onPostExecute(datos);

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
                    v.DesdeHasta = jsonViaje.getBoolean("DesdeHasta");
                    v.DetalleTransporte = jsonViaje.getString("DetalleTransporte");
                    v.DireccionLatitud = jsonViaje.getDouble("DireccionLatitud");
                    v.DireccionLongitud = jsonViaje.getDouble("DireccionLongitud");


                    return v;
                } catch (JSONException e) {
                    Log.d("Error JSON", e.getMessage());
                    return null;
                }
            } catch (IOException e) {
                Log.d("Error", e.getMessage());             // Error de Network
                return null;
            }

        }
}





}

