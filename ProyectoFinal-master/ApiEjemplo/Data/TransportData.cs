using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TransportData
    {
        public static Usuarios Login(int DNI, string Contraseña)
        {
            string select = "select (Nombre, Año, Curso, PrimeraEdicion) from Usuarios where DNI=" + id.ToString() + "and Contraseña=" + Contraseña ;
            DataTable dt = DBHelper.EjecutarSelect(select);
            Usuarios uusuario;
            if (dt.Rows.Count > 0)
            {
                uusuario = ObtenerPorRow(dt.Rows[0]);
                return uusuario;
            }
            else
            {
                return null;
            }
        }

        public static void CargarViaje(Viajes viaje)
        {
            string sCargarViaje="Insert into Viajes () "
        }
        public static void Insert(Persona persona)
        {
            string sInsert = "Insert into personas (Nombre,FechaNac) values ('" + persona.Nombre + "','" + persona.FechaNac.ToString("yyyy-MM-dd HH:mm") + "')";
            DBHelper.EjecutarIUD(sInsert);
        }



    }
}