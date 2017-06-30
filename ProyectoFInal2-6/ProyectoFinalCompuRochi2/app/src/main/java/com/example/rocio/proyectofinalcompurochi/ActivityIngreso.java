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
import android.widget.ImageButton;
import android.widget.Toast;

import android.os.Parcelable;

import com.example.rocio.proyectofinalcompurochi.Clases.Usuario;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ActivityIngreso extends AppCompatActivity {

    boolean ExisteElUsuario = false;

    public int DNI;
    String Nombre;
    String Año;
    String Curso;
    String Contraseña;
    String Imagen;
    boolean PrimeraEdicion;
    Toast Cartelito;
    Usuario miUsuario = new Usuario();


    public void BotonIngresar(View Vista)
    {
        EditText ElDNIText;
        ElDNIText = (EditText) findViewById(R.id.ingresoDNI);
        String Dni = ElDNIText.getText().toString();
        DNI = Integer.parseInt(Dni);

        EditText Pass;
        Pass = (EditText) findViewById(R.id.ingresoContraseña);
        String Contraseñaa = Pass.getText().toString();
        Contraseña = Contraseñaa;


        //String par = "localhost/api/usuario/"+ Dni+"/"+ Contraseñaa;
        new TraerUsuario().execute(Dni, Contraseñaa);



        if (Dni.length()>0 && Contraseña.length()>0)
        {
            if (ExisteElUsuario==true)
            {
                DNI = miUsuario.DNI;
                Nombre = miUsuario.Nombre;
                Año = miUsuario.Año;
                Curso = miUsuario.Curso;
                Imagen = miUsuario.Imagen;

                if (PrimeraEdicion==true)
                {
                    Bundle EnvioDatos;
                    EnvioDatos = new Bundle();
                    EnvioDatos.putInt("DNI", DNI);
                    EnvioDatos.putString("Nombre", Nombre);
                    EnvioDatos.putString("Año", Año);
                    EnvioDatos.putString("Curso", Curso);
                    EnvioDatos.putString("Imagen", Imagen);
                    Intent LlamadaActivityPrimeraEdicion;
                    LlamadaActivityPrimeraEdicion = new Intent(this, ActivityPrimeraEdicion.class);
                    LlamadaActivityPrimeraEdicion.putExtra("EnvioUsuario", EnvioDatos);
                    startActivity(LlamadaActivityPrimeraEdicion);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                }else
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
                    LlamadaActivityPerfil.putExtra("EnvioUsuario", EnvioDatos);
                    startActivity(LlamadaActivityPerfil);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                }

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
    public void Loguearse(int DNI, String contraseña) {
        new TraerUsuario().execute(DNI, contraseña);
    }

    private class TraerUsuario extends AsyncTask<String, Void, Usuario>{

        protected void onPostExecute(Usuario datos) {
            super.onPostExecute(datos);
        if (datos != null) {
            ExisteElUsuario = true;
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

                    u.DNI = jsonUsuario.getInt("DNI");
                    u.Nombre = jsonUsuario.getString("Nombre");
                    u.Año = jsonUsuario.getString("Anio");
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
