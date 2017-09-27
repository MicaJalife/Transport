using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class ViajesCompartidosData
    {
        public static void InsertarViaje(ViajesCompartidos viajecompartido)
        {
            //string sInsert = "INSERT into viajescompartidos (IdViaje, IdUsuario) values ('" + viaje.DNI.ToString() + "','" + viaje.IdHorario.ToString() + "','" + viaje.IdTransporte.ToString() + "','" +viaje.DesdeHasta.ToString() + "','" + viaje.DetalleTransporte + "','" + viaje.DireccionLatitud + "','"  +viaje.DireccionLongitud +"')";
            string strSQL = string.Format("INSERT into viajescompartidos (IdViaje, IdUsuario) values ({0}, {1});",
               viajecompartido.IdViaje,
               viajecompartido.DNI           
               );
            DBHelper.EjecutarIUD(strSQL);
        }

        public static string SePuedeUnir(int IdViaje)
        {
            string select = "select * from viajescompartidos where IdViaje=" + IdViaje.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<ViajesCompartidos> ListaCompartenMismoViaje = new List<ViajesCompartidos>();
            ViajesCompartidos viajescomparten;
            Viajes viaje = new Viajes();
            viaje=  ViajesData.ObtenerCantidadxId(Convert.ToInt32(IdViaje));
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {                    
                    viajescomparten = ObtenerPorRow(row);                    
                    ListaCompartenMismoViaje.Add(viajescomparten);
                }
                viajescomparten = ObtenerPorRow(dt.Rows[0]);
            }
            int compartiendo = ListaCompartenMismoViaje.Count;
            int calculando = viaje.Cantidad - compartiendo;
            if (calculando <= 0)
            {
                return "NO";
            }
            else
            {
                return "SI";
            }
            
        }

        private static ViajesCompartidos ObtenerPorRow(DataRow row)
        {
            ViajesCompartidos v = new ViajesCompartidos();
            v.IdViajeCompartido = row.Field<int>("IdViajeCompartido");
            v.IdViaje = row.Field<int>("IdViaje");    
            v.DNI= row.Field<int>("IdUsuario");        
            return v;
        }

    }
}