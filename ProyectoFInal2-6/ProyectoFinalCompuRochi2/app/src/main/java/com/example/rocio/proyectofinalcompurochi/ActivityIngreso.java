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
import java.net.URL;

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
    AsyncTask<String, Void, Usuario> traerUsuario;

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
        String Url = "http://transportdale.azurewebsites.net/api/usuario/"+ Dni+"/"+ Contraseñaa;
        Log.d("Manda url", "ppppp");
        new TraerUsuario().execute(Url);



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
        traerUsuario= new TraerUsuario();
    }


    private class TraerUsuario extends AsyncTask<String, Void, Usuario>{


        protected void onPostExecute(Usuario datos) {
            super.onPostExecute(datos);
            Log.d("Devuelve datos", "ppppp");

   /*     if (datos != null) {
            ExisteElUsuario = true;
            miUsuario = datos;
        }

*/
        }


        @Override
        protected Usuario doInBackground(String... parametros) {
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

                    JSONObject jsonUsuario = new JSONObject(resultado);
                    Log.d("crea un nuevo json", "ppppp");

                    Usuario u = new Usuario();
                    Log.d("declara usuario", "ppppp");

                    u.DNI = jsonUsuario.getInt("DNI");
                    u.Nombre = jsonUsuario.getString("Nombre");
                    u.Año = jsonUsuario.getString("Anio");
                    u.Curso = jsonUsuario.getString("Curso");
                    u.Imagen = jsonUsuario.getString("Imagen");
                    u.PrimeraEdicion = jsonUsuario.getBoolean("PrimeraEdicion");
                    Log.d("parsea el json", "ppppp");

                    return u;
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
