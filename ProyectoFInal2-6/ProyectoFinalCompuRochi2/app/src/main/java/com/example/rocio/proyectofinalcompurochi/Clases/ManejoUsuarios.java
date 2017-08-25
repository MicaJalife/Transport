package com.example.rocio.proyectofinalcompurochi.Clases;

/**
 * Created by Administrador on 18/8/2017.
 */

public class ManejoUsuarios {

    private static Usuario _usuarioLogueado = null;

    public static Usuario getUsuario() {return _usuarioLogueado;}
    public static void setUsuario(Usuario elUsuario) {
        _usuarioLogueado = elUsuario;
    }


}
