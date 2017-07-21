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

import com.example.rocio.proyectofinalcompurochi.Clases.Usuario;
import com.example.rocio.proyectofinalcompurochi.Clases.Viaje;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ActivityPerfil extends AppCompatActivity {

    Viaje miViaje = null;
    boolean existeviaje=false;
    int DniUsuario;


    public void BotonBuscador(View Vista)
    {

        Intent LlamadaActivityBuscador;
        LlamadaActivityBuscador = new Intent(this, ActivityBuscador.class);
        startActivity(LlamadaActivityBuscador);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);

    }
    public void BotonPerfil(View Vista)
    {
        Intent LLamadaActivityPerfil;
        LLamadaActivityPerfil = new Intent(this, ActivityPerfil.class);
        startActivity(LLamadaActivityPerfil);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

    }
    public void BotonChats(View Vista)
    {


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




        String par = "http://transportdale.azurewebsites.net/api/traerviaje/"+DniUsuario;
        new TraerViaje().execute(par);


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
                    v.DesdeHasta = jsonViaje.getBoolean("DesdeHasta");
                    v.DetalleTransporte = jsonViaje.getString("DetalleTransporte");
                    v.DireccionLatitud = jsonViaje.getDouble("DireccionLatitud");
                    v.DireccionLongitud = jsonViaje.getDouble("DireccionLongitud");


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
