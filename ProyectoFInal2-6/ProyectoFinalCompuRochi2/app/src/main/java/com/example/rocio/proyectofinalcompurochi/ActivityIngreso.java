package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityIngreso extends AppCompatActivity {

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
        boolean Validacion = true;

        if (Dni.length()>0 && Contraseña.length()>0)
        {
            if (Validacion==true)
            {

                Bundle EnvioDNI;
                EnvioDNI = new Bundle();
                EnvioDNI.putString("MandoDNI", Dni);

                Intent LlamadaActivityPrimeraEdicion;
                LlamadaActivityPrimeraEdicion = new Intent(this, ActivityPrimeraEdicion.class);
                LlamadaActivityPrimeraEdicion.putExtras(EnvioDNI);
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
}
