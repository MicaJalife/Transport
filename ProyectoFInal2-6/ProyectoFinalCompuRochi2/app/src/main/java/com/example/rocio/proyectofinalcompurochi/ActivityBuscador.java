package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class ActivityBuscador extends AppCompatActivity {

    Toast Cartelito;

    public void BotonBusqueda(View vista)
    {
        MaterialBetterSpinner spinnerDia;
        spinnerDia = (MaterialBetterSpinner) findViewById(R.id.SpinnerDia);

        MaterialBetterSpinner spinnerBloques;
        spinnerBloques = (MaterialBetterSpinner) findViewById(R.id.SpinnerBloque);

        MaterialBetterSpinner spinnerAnio;
        spinnerAnio = (MaterialBetterSpinner) findViewById(R.id.SpinnerAnio);

        MaterialBetterSpinner spinnerTransporte;
        spinnerTransporte = (MaterialBetterSpinner) findViewById(R.id.SpinnerTransporte);

        RadioButton RadioButtonIda;
        RadioButtonIda = (RadioButton) findViewById(R.id.RadioButtonIda);

        RadioButton RadioButtonVuelta;
        RadioButtonVuelta = (RadioButton) findViewById(R.id.RadioButtonVuelta);

        TextView Desde;
        Desde =(TextView)findViewById(R.id.BusquedaDireccion1);

        Spinner Hasta;
        Hasta =(Spinner)findViewById(R.id.BusquedaDireccion2);

        if (RadioButtonIda.isSelected() || RadioButtonVuelta.isSelected())
        {
            if(spinnerDia.getText().length()!=0||spinnerBloques.getText().length()!=0)
            {
                
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





    public void BotonBuscador(View Vista)
    {

        Intent LlamadaActivityBuscador;
        LlamadaActivityBuscador = new Intent(this, ActivityBuscador.class);
        startActivity(LlamadaActivityBuscador);
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

    }
    public void BotonPerfil(View Vista)
    {
        Intent LLamadaActivityPerfil;
        LLamadaActivityPerfil = new Intent(this, ActivityPerfil.class);
        startActivity(LLamadaActivityPerfil);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

    }
    public void BotonChats(View Vista)
    {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        MaterialBetterSpinner spinnerDia;
        spinnerDia = (MaterialBetterSpinner) findViewById(R.id.SpinnerDia);

        MaterialBetterSpinner spinnerBloques;
        spinnerBloques = (MaterialBetterSpinner) findViewById(R.id.SpinnerBloque);

        MaterialBetterSpinner spinnerAnio;
        spinnerAnio = (MaterialBetterSpinner) findViewById(R.id.SpinnerAnio);

        MaterialBetterSpinner spinnerTransporte;
        spinnerTransporte = (MaterialBetterSpinner) findViewById(R.id.SpinnerTransporte);

        RadioButton RadioButtonIda;
        RadioButtonIda = (RadioButton) findViewById(R.id.RadioButtonIda);

        RadioButton RadioButtonVuelta;
        RadioButtonVuelta = (RadioButton) findViewById(R.id.RadioButtonVuelta);

        RadioButtonIda.setChecked(true);

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

        ArrayList<String> ListaAnios;
        ListaAnios = new ArrayList<>();
        ListaAnios.add("Elegir");
        ListaAnios.add("1");
        ListaAnios.add("2");
        ListaAnios.add("3");
        ListaAnios.add("4");
        ListaAnios.add("5");
        ListaAnios.add("6");

        ArrayAdapter<String> AdaptadorAnios;
        AdaptadorAnios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaAnios);
        AdaptadorAnios.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerAnio.setAdapter(AdaptadorAnios);

        ArrayList<String> ListaTransporte;
        ListaTransporte = new ArrayList<>();
        ListaTransporte.add("Elegir");
        ListaTransporte.add("Auto");
        ListaTransporte.add("Colectivo");
        ListaTransporte.add("Subte");
        ListaTransporte.add("Bicicleta");
        ListaTransporte.add("Caminando");
        ArrayAdapter<String> AdaptadorTransporte;
        AdaptadorTransporte = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ListaTransporte);
        AdaptadorTransporte.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTransporte.setAdapter(AdaptadorTransporte);







    }
}
