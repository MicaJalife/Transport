package com.example.rocio.proyectofinalcompurochi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rocio.proyectofinalcompurochi.Clases.ClaseParseo;
import com.example.rocio.proyectofinalcompurochi.Clases.Direcciones;
import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;
import com.example.rocio.proyectofinalcompurochi.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityViajesCompartidos extends AppCompatActivity {

    Integer DNI;
    String Nombre;
    String Año;
    String Curso;
    String Imagen;

    ArrayList<Direcciones> ArrayObjDirecs;
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


            if(spinnerDia.getSelectedItem()!=null && spinnerBloques.getSelectedItem()!=null)
            {
                String dia = spinnerDia.getSelectedItem().toString();
                String bloque = spinnerBloques.getSelectedItem().toString();
                String transporte =spinnerTransporte.getSelectedItem().toString();
                String Direccion = EditDirecs.toString();

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
                    new ActivityViajesCompartidos().TraerViajes().execute(UrlCnTransporte);
                }
                else{
                    String UrlSinFiltro = "http://transportdale.azurewebsites.net/api/viajes/ViajesccDiaHorario/" + ViajeGuardado.DireccionLatitud + "/"+ViajeGuardado.DireccionLongitud+"/"+ ViajeGuardado.IdDia+"/"+ViajeGuardado.IdHorario+"/"+ViajeGuardado.DesdeHasta;
                    Log.d("Manda url", "ppppp");
                    new ActivityViajesCompartidos.TraerViajes().execute(UrlSinFiltro);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes_compartidos);
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
