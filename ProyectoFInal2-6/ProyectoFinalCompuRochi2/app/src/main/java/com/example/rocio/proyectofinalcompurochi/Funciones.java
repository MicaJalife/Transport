package com.example.rocio.proyectofinalcompurochi;

/**
 * Created by Rocio on 27/6/2017.
 */

public class Funciones {

    public int TraerIdHorario(String Horario)
    {
        int DevuelvoId=0;

        switch (Horario) {
            case "7:45":
                DevuelvoId = 1;
                break;
            case "9:05":
                DevuelvoId = 2;
                break;
            case "10:40":
                DevuelvoId = 3;
                break;
            case "12:15":
                DevuelvoId = 4;
                break;
            case "14:30":
                DevuelvoId = 5;
                break;
            case "16:00":
                DevuelvoId = 6;
                break;
            case "17:30":
                DevuelvoId = 7;
                break;
    }
        return DevuelvoId;

    }
    public int TraerIdDia (String Dia)
    {
        int DevuelvoId=0;

        switch (Dia) {
            case "Lunes":
                DevuelvoId = 1;
                break;
            case "Martes":
                DevuelvoId = 2;
                break;
            case "Miercoles":
                DevuelvoId = 3;
                break;
            case "Jueves":
                DevuelvoId = 4;
                break;
            case "Viernes":
                DevuelvoId = 5;
                break;

        }
        return DevuelvoId;

    }
    public int TraerIdTransporte (String Transporte)
    {
        int DevuelvoId=0;

        switch (Transporte) {
            case "Auto":
                DevuelvoId = 1;
                break;
            case "Colectivo":
                DevuelvoId = 2;
                break;
            case "Subte":
                DevuelvoId = 3;
                break;
            case "Bicicleta":
                DevuelvoId = 4;
                break;
            case "Caminando":
                DevuelvoId = 5;
                break;

        }
        return DevuelvoId;
    }
    public String TraerElTransporte (int id)
    {
        String devuelvotransporte="";

        switch (id) {
            case 1:
                devuelvotransporte = "Auto";
                break;
            case 2:
                devuelvotransporte = "Colectivo";
                break;
            case 3:
                devuelvotransporte = "Subte";
                break;
            case 4:
                devuelvotransporte = "Bicicleta";
                break;
            case 5:
                devuelvotransporte = "Caminando";
                break;

        }
        return devuelvotransporte;
    }
    public String TraerElDia (int id)
    {
        String devuelvodia="";

        switch (id) {
            case 1:
                devuelvodia = "Lunes";
                break;
            case 2:
                devuelvodia = "Martes";
                break;
            case 3:
                devuelvodia = "Miercoles";
                break;
            case 4:
                devuelvodia = "Jueves";
                break;
            case 5:
                devuelvodia = "Viernes";
                break;

        }
        return devuelvodia;
    }
    public String DevuelvoElHorario(int Id)
    {
        String devuelvoelhorario="";

        switch (Id) {
            case 1:
                devuelvoelhorario = "7:45";
                break;
            case 2:
                devuelvoelhorario = "9:05";
                break;
            case 3:
                devuelvoelhorario = "10:40";
                break;
            case 4:
                devuelvoelhorario = "12:15";
                break;
            case 5:
                devuelvoelhorario = "14:30";
                break;
            case 6:
                devuelvoelhorario = "16:00";
                break;
            case 7:
                devuelvoelhorario = "17:30";
                break;
        }
        return devuelvoelhorario;

    }


}
