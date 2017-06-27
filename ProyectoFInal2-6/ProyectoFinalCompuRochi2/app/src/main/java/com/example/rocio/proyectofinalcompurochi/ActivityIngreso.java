package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.database.sqlite.SQLiteMisuseException;
import android.os.AsyncTask;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rocio.proyectofinalcompurochi.Clases.Usuario;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ActivityIngreso extends AppCompatActivity {

    boolean estaLogeado = false;
    Usuario miUsuario = null;

    public void BotonIngresar(View Vista)
    {
        EditText DNI;
        DNI = (EditText) findViewById(R.id.ingresoDNI);
        String Dni = DNI.getText().toString();

        EditText Pass;
        Pass = (EditText) findViewById(R.id.ingresoContraseña);
        String Contraseña = Pass.getText().toString();

        //TODAS LAS VARIABLES DECLARADAS ACA
        Toast Cartelito;

        String par = "localhost/api/usuario/"+Dni+"/"+Contraseña;
        new TraerUsuario().execute(par);



        if (Dni.length()>0 && Contraseña.length()>0)
        {
            if (estaLogeado==true)
            {

                /*Bundle EnvioDatos;
                EnvioDatos = new Bundle();
                EnvioDatos.putString("MandoNombre", miUsuario.Nombre);
                EnvioDatos.putInt("MandoAño", miUsuario.Año);
                EnvioDatos.putString("MandoCurso", miUsuario.Curso);
                EnvioDatos.putString("MandoImagen", miUsuario.Imagen);
                EnvioDatos.putBoolean("MandoPrimeraEdicion", miUsuario.PrimeraEdicion);*/

                Intent LlamadaActivityPrimeraEdicion;
                LlamadaActivityPrimeraEdicion = new Intent(this, ActivityPrimeraEdicion.class);
                LlamadaActivityPrimeraEdicion.putExtra("EnvioUsuario",miUsuario);
                startActivity(LlamadaActivityPrimeraEdicion);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);

            }
            else
            {
                Cartelito = Toast.makeText(this, "El DNI o contraseña es incorrecto", Toast.LENGTH_SHORT);
                Cartelito.show();
            }

        }
        else
        {
            Cartelito = Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT);
            Cartelito.show();
        }
    }









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
    }


    private class TraerUsuario extends AsyncTask<String, Void, Usuario>{

        protected void onPostExecute(Usuario datos) {
            super.onPostExecute(datos);
        if (datos != null) {
            estaLogeado = true;
            miUsuario = datos;
        }
        }


        @Override
        protected Usuario doInBackground(String... parametros) {
            String url = parametros[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();


            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();
                try {

                    JSONObject jsonUsuario = new JSONObject(resultado);

                    Usuario u = new Usuario();

                    u.Nombre = jsonUsuario.getString("Nombre");
                    u.Año = jsonUsuario.getInt("Año");
                    u.Curso = jsonUsuario.getString("Curso");
                    u.Imagen = jsonUsuario.getString("Imagen");
                    u.PrimeraEdicion = jsonUsuario.getBoolean("PrimeraEdicion");


                    return u;
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
