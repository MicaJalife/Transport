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
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityPrimeraEdicion extends AppCompatActivity implements OnMapReadyCallback {

    EditText direccion;
    TextView dirEncontrada, coordenadas;
    GoogleMap map;
    Toast Cartelito;
    int TransporteSeleccionado = 0;
    String HoraSeleccionada;
    String DireccionSeleccionada;
    String Horario1L;
    String Horario1M;
    String Horario1X;
    String Horario1J;
    String Horario1V;
    String Horario2L;
    String Horario2M;
    String Horario2X;
    String Horario2J;
    String Horario2V;
    String Direccion1L;
    String Direccion1M;
    String Direccion1X;
    String Direccion1J;
    String Direccion1V;
    String Direccion2L;
    String Direccion2M;
    String Direccion2X;
    String Direccion2J;
    String Direccion2V;
    int Transporte1L = 0;
    int Transporte1M = 0;
    int Transporte1X = 0;
    int Transporte1J = 0;
    int Transporte1V = 0;
    int Transporte2L = 0;
    int Transporte2M = 0;
    int Transporte2X = 0;
    int Transporte2J = 0;
    int Transporte2V = 0;
    boolean EligioTranspPublic = false;
    String DatosAMostrar = "";
    boolean ErrorLineas = false;

    Usuario usuariorecibido;

    public void BotonAgregarDireccion(View Vista) {
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


        if (spinnerDia.isSelected() == false || spinnerBloques.isSelected() == false || TransporteSeleccionado == 0 || DireccionValidada.length() == 0) {
            if (TransporteSeleccionado == 2 || TransporteSeleccionado == 3) {

                EligioTranspPublic = true;
            }
            if (EligioTranspPublic == false) {
            } else {
                if (EligioTranspPublic == true && LineasFrec.length() > 0) {

                } else {
                    ErrorLineas = true;
                    Cartelito = Toast.makeText(this, "Si elige un transporte publico debe especificar las lineas que utiliza", Toast.LENGTH_SHORT);
                    Cartelito.show();
                }
            }


            if (ErrorLineas == false) {

                if (RadioIda.isChecked() == true) {

                    String DiasSeleccionado = spinnerDia.getSelectedItem().toString();
                    String NombreTransp = "";
                    switch (DiasSeleccionado) {
                        case "Lunes":
                            HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                            DireccionSeleccionada = Direc.toString();
                            Horario1L = HoraSeleccionada;
                            Transporte1L = TransporteSeleccionado;
                            Direccion1L = DireccionSeleccionada;


                            switch (TransporteSeleccionado) {
                                case 1:
                                    NombreTransp = "Auto";
                                    break;
                                case 2:
                                    NombreTransp = "Colectivo";
                                    break;
                                case 3:
                                    NombreTransp = "Subte";
                                    break;
                                case 4:
                                    NombreTransp = "Bicicleta";
                                    break;
                                case 5:
                                    NombreTransp = "Caminando";
                                    break;
                            }


                            DatosAMostrar = DatosAMostrar + "\n" + "Los lunes a las " + Horario1L + " va a " + Direccion1L + " con el transporte " + NombreTransp;
                            DatosCompletos.setText(DatosAMostrar);

                            break;
                        case "Martes":
                            HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                            DireccionSeleccionada = Direc.toString();
                            Horario1M = HoraSeleccionada;
                            Transporte1M = TransporteSeleccionado;
                            Direccion1M = DireccionSeleccionada;


                            switch (TransporteSeleccionado) {
                                case 1:
                                    NombreTransp = "Auto";
                                    break;
                                case 2:
                                    NombreTransp = "Colectivo";
                                    break;
                                case 3:
                                    NombreTransp = "Subte";
                                    break;
                                case 4:
                                    NombreTransp = "Bicicleta";
                                    break;
                                case 5:
                                    NombreTransp = "Caminando";
                                    break;
                            }


                            DatosAMostrar = DatosAMostrar + "\n" + "Los martes a las " + Horario1M + " va a " + Direccion1M + " con el transporte " + NombreTransp;
                            DatosCompletos.setText(DatosAMostrar);
                            break;
                        case "Miercoles":
                            HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                            DireccionSeleccionada = Direc.toString();
                            Horario1X = HoraSeleccionada;
                            Transporte1X = TransporteSeleccionado;
                            Direccion1X = DireccionSeleccionada;


                            switch (TransporteSeleccionado) {
                                case 1:
                                    NombreTransp = "Auto";
                                    break;
                                case 2:
                                    NombreTransp = "Colectivo";
                                    break;
                                case 3:
                                    NombreTransp = "Subte";
                                    break;
                                case 4:
                                    NombreTransp = "Bicicleta";
                                    break;
                                case 5:
                                    NombreTransp = "Caminando";
                                    break;
                            }

                            DatosAMostrar = DatosAMostrar + "\n" + "Los miercoles a las " + Horario1X + " va a " + Direccion1X + " con el transporte " + NombreTransp;
                            DatosCompletos.setText(DatosAMostrar);
                            break;
                        case "Jueves":
                            HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                            DireccionSeleccionada = Direc.toString();
                            Horario1J = HoraSeleccionada;
                            Transporte1J = TransporteSeleccionado;
                            Direccion1J = DireccionSeleccionada;


                            switch (TransporteSeleccionado) {
                                case 1:
                                    NombreTransp = "Auto";
                                    break;
                                case 2:
                                    NombreTransp = "Colectivo";
                                    break;
                                case 3:
                                    NombreTransp = "Subte";
                                    break;
                                case 4:
                                    NombreTransp = "Bicicleta";
                                    break;
                                case 5:
                                    NombreTransp = "Caminando";
                                    break;
                            }


                            DatosAMostrar = DatosAMostrar + "\n" + "Los jueves a las " + Horario1J + " va a " + Direccion1J + " con el transporte " + NombreTransp;
                            DatosCompletos.setText(DatosAMostrar);
                            break;
                        case "Viernes":
                            HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                            DireccionSeleccionada = Direc.toString();
                            Horario1V = HoraSeleccionada;
                            Transporte1V = TransporteSeleccionado;
                            Direccion1V = DireccionSeleccionada;


                            switch (TransporteSeleccionado) {
                                case 1:
                                    NombreTransp = "Auto";
                                    break;
                                case 2:
                                    NombreTransp = "Colectivo";
                                    break;
                                case 3:
                                    NombreTransp = "Subte";
                                    break;
                                case 4:
                                    NombreTransp = "Bicicleta";
                                    break;
                                case 5:
                                    NombreTransp = "Caminando";
                                    break;
                            }
                            DatosAMostrar = DatosAMostrar + "\n" + "Los viernes a las " + Horario1V + " va a " + Direccion1V + " con el transporte " + NombreTransp;
                            DatosCompletos.setText(DatosAMostrar);
                            break;


                    }

                } else {
                    if (RadioVuelta.isChecked() == true) {
                        String DiasSeleccionado = spinnerDia.getSelectedItem().toString();

                        String NombreTransp = "";

                        switch (DiasSeleccionado) {
                            case "Lunes":
                                HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                                DireccionSeleccionada = DireccionValidada.toString();
                                Horario2L = HoraSeleccionada;
                                Transporte2L = TransporteSeleccionado;
                                Direccion2L = DireccionSeleccionada;


                                switch (TransporteSeleccionado) {
                                    case 1:
                                        NombreTransp = "Auto";
                                        break;
                                    case 2:
                                        NombreTransp = "Colectivo";
                                        break;
                                    case 3:
                                        NombreTransp = "Subte";
                                        break;
                                    case 4:
                                        NombreTransp = "Bicicleta";
                                        break;
                                    case 5:
                                        NombreTransp = "Caminando";
                                        break;
                                }

                                DatosAMostrar = DatosAMostrar + "\n" + "Los lunes a las " + Horario2L + " va a " + Direccion2L + " con el transporte " + NombreTransp;
                                DatosCompletos.setText(DatosAMostrar);

                                break;
                            case "Martes":
                                HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                                DireccionSeleccionada = DireccionValidada.toString();
                                Horario2M = HoraSeleccionada;
                                Transporte2M = TransporteSeleccionado;
                                Direccion2M = DireccionSeleccionada;
                                switch (TransporteSeleccionado) {
                                    case 1:
                                        NombreTransp = "Auto";
                                        break;
                                    case 2:
                                        NombreTransp = "Colectivo";
                                        break;
                                    case 3:
                                        NombreTransp = "Subte";
                                        break;
                                    case 4:
                                        NombreTransp = "Bicicleta";
                                        break;
                                    case 5:
                                        NombreTransp = "Caminando";
                                        break;
                                }

                                DatosAMostrar = DatosAMostrar + "\n" + "Los martes a las " + Horario2M + " va a " + Direccion2M + " con el transporte " + NombreTransp;
                                DatosCompletos.setText(DatosAMostrar);
                                break;
                            case "Miercoles":
                                HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                                DireccionSeleccionada = DireccionValidada.toString();
                                Horario2X = HoraSeleccionada;
                                Transporte2X = TransporteSeleccionado;
                                Direccion2X = DireccionSeleccionada;
                                switch (TransporteSeleccionado) {
                                    case 1:
                                        NombreTransp = "Auto";
                                        break;
                                    case 2:
                                        NombreTransp = "Colectivo";
                                        break;
                                    case 3:
                                        NombreTransp = "Subte";
                                        break;
                                    case 4:
                                        NombreTransp = "Bicicleta";
                                        break;
                                    case 5:
                                        NombreTransp = "Caminando";
                                        break;
                                }

                                DatosAMostrar = DatosAMostrar + "\n" + "Los miercoles a las " + Horario2X + " va a " + Direccion2X + " con el transporte " + NombreTransp;
                                DatosCompletos.setText(DatosAMostrar);
                                break;
                            case "Jueves":
                                HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                                DireccionSeleccionada = DireccionValidada.toString();
                                Horario2J = HoraSeleccionada;
                                Transporte2J = TransporteSeleccionado;
                                Direccion2J = DireccionSeleccionada;
                                switch (TransporteSeleccionado) {
                                    case 1:
                                        NombreTransp = "Auto";
                                        break;
                                    case 2:
                                        NombreTransp = "Colectivo";
                                        break;
                                    case 3:
                                        NombreTransp = "Subte";
                                        break;
                                    case 4:
                                        NombreTransp = "Bicicleta";
                                        break;
                                    case 5:
                                        NombreTransp = "Caminando";
                                        break;
                                }

                                DatosAMostrar = DatosAMostrar + "\n" + "Los jueves a las " + Horario2J + " va a " + Direccion2J + " con el transporte " + NombreTransp;
                                DatosCompletos.setText(DatosAMostrar);
                                break;
                            case "Viernes":
                                HoraSeleccionada = spinnerBloques.getSelectedItem().toString();
                                DireccionSeleccionada = DireccionValidada.toString();
                                Horario2V = HoraSeleccionada;
                                Transporte2V = TransporteSeleccionado;
                                Direccion2V = DireccionSeleccionada;
                                switch (TransporteSeleccionado) {
                                    case 1:
                                        NombreTransp = "Auto";
                                        break;
                                    case 2:
                                        NombreTransp = "Colectivo";
                                        break;
                                    case 3:
                                        NombreTransp = "Subte";
                                        break;
                                    case 4:
                                        NombreTransp = "Bicicleta";
                                        break;
                                    case 5:
                                        NombreTransp = "Caminando";
                                        break;
                                }

                                DatosAMostrar = DatosAMostrar + "\n" + "Los viernes a las " + Horario2V + " va a " + Direccion2V + " con el transporte " + NombreTransp;
                                DatosCompletos.setText(DatosAMostrar);
                                break;

                        }
                    }
                }
            }

        } else {
            Cartelito = Toast.makeText(this, "Debe seleccionar el dia, el bloque, el transporte y mostrar su direccion", Toast.LENGTH_SHORT);
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


    public void SiguientePrimeraEdicion(View Vista) {


        if (Horario1L.length() == 0 && Horario1M.length() == 0 && Horario1X.length() == 0 && Horario1J.length() == 0 && Horario1V.length() == 0 && Horario2L.length() == 0 && Horario2M.length() == 0 && Horario2X.length() == 0 && Horario2J.length() == 0 && Horario2V.length() == 0 && Direccion1L.length() == 0 && Direccion1M.length() == 0 && Direccion1X.length() == 0 && Direccion1J.length() == 0 && Direccion1V.length() == 0 && Direccion2L.length() == 0 && Direccion2M.length() == 0 && Direccion2X.length() == 0 && Direccion2J.length() == 0 && Direccion2V.length() == 0 && Transporte1L == 0 && Transporte1M == 0 && Transporte1X == 0 && Transporte1J == 0 && Transporte1V == 0 && Transporte2L == 0 && Transporte2M == 0 && Transporte2X == 0 && Transporte2J == 0 && Transporte2V == 0) {
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
                double lat = dirRecibida.getLatitude(); //
                double lng = dirRecibida.getLongitude();
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
                    v.Transporte = jsonViaje.getString("IdTransporte");
                    v.DesdeHasta = jsonViaje.getBoolean("DesdeHasta");
                    v.DetalleTransporte = jsonViaje.getString("DetalleTransporte");
                    v.DireccionLatitud = jsonViaje.getString("DireccionLatitud");
                    v.DireccionLongitud = jsonViaje.getString("DireccionLongitud");


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

