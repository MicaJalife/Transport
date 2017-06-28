using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class UsuariosData
    {


        private static Usuarios ObtenerPorRow(DataRow row)
        {
            Usuarios u = new Usuarios();
            u.DNI = row.Field<int>("DNI");
            u.Nombre = row.Field<string>("Nombre");
            u.Año = row.Field<string>("Año");
            u.Curso = row.Field<string>("Curso");
            u.Contrasena = row.Field<string>("Contrasena");
            u.Imagen = row.Field<string>("Imagen");
            u.PrimeraEdicion = row.Field<bool>("PrimeraEdicion");
            return u;
        }

        public static Usuarios Login(int DNI, string Contraseña)
        {
            string select = "select (Nombre, Año, Curso,Imagen, PrimeraEdicion) from usuarios where DNI=" + DNI.ToString() + "and Contraseña=" + Contraseña;
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
        
       

    }
}