package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityBuscador extends AppCompatActivity {

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
    }
}
