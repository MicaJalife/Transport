package com.example.rocio.proyectofinalcompurochi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ActivityPerfil extends AppCompatActivity {

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

    }
}
