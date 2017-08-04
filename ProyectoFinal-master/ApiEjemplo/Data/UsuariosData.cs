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
            u.Anio = row.Field<string>("Anio");
            u.Curso = row.Field<string>("Curso");
            u.Contrasenia = row.Field<string>("Contrasenia");
            u.NombreImagen = row.Field<string>("Imagen");
            u.PrimeraEdicion = row.Field<bool>("PrimeraEdicion");
            return u;
        }

        public static Usuarios Login(int DNI, string Contrasenia)
        {
            string select = "select DNI, Nombre, Anio, Curso, Contrasenia, Imagen, PrimeraEdicion from usuarios where DNI=" + DNI.ToString() + " and Contrasenia='" + Contrasenia+"'";
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

        public static Usuarios ObtenerPorId(int DNI)
        {
            string select = "select * from usuarios where DNI=" + DNI.ToString();
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