using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class ViajesData
    {

       public static void InsertarViaje (Viajes viaje)
        {
            string sInsert = "INSERT into viajes (DNI, IdHorario, IdTransporte, DesdeHasta, DetalleTransporte, DireccionLatitud, DireccionLongitud) values ('" + viaje.DNI.ToString() + "','" + viaje.IdHorario.ToString() + "','" + viaje.IdTransporte.ToString() + "','" +viaje.DesdeHasta.ToString() + "','" + viaje.DetalleTransporte + "','" + viaje.DireccionLatitud + "','"  +viaje.DireccionLongitud +"')";
            DBHelper.EjecutarIUD(sInsert);
        }

        public static List<Viajes> ObtenerViajesXDNI(int DNI)
        {
            string select = "select * from viajes where DNI=" + DNI.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaViajes = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach(DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    ListaViajes.Add(viaje);
                }
                viaje = ObtenerPorRow(dt.Rows[0]);
            }
            return ListaViajes;
        }




        private static Viajes ObtenerPorRow(DataRow row)
        {
            Viajes v = new Viajes();
            v.DNI = row.Field<int>("DNI");
            v.IdHorario = row.Field<int>("IdHorario");
            v.IdTransporte = row.Field<int>("IdTransporte");
            v.DesdeHasta = row.Field<bool>("DesdeHasta");
            v.DetalleTransporte= row.Field<string>("DetalleTransporte");
            v.DireccionLatitud = row.Field<string>("DireccionLatitud");
            v.DireccionLongitud = row.Field<string>("DireccionLongitud");
            return v;
        }








        public static Usuarios Login(int DNI, string Contraseña)
        {
            string select = "select (Nombre, Año, Curso,Imagen, PrimeraEdicion) from Usuarios where DNI=" + DNI.ToString() + "and Contraseña=" + Contraseña;
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