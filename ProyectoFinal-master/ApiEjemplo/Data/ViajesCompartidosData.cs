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
            string unirse;
            string mismapersona;
            unirse = SePuedeUnir(viajecompartido.IdViaje);
            mismapersona = EsLaMismaPersona(viajecompartido.IdViaje, viajecompartido.IdUsuario);
            if (unirse == "SI" && mismapersona== "NO")
            {
                //string sInsert = "INSERT into viajescompartidos (IdViaje, IdUsuario) values ('" + viaje.DNI.ToString() + "','" + viaje.IdHorario.ToString() + "','" + viaje.IdTransporte.ToString() + "','" +viaje.DesdeHasta.ToString() + "','" + viaje.DetalleTransporte + "','" + viaje.DireccionLatitud + "','"  +viaje.DireccionLongitud +"')";
                string strSQL = string.Format("INSERT into viajescompartidos (IdViaje, IdUsuario) values ({0}, {1});",
                   viajecompartido.IdViaje,
                   viajecompartido.IdUsuario
                   );
                DBHelper.EjecutarIUD(strSQL);
            }
        }   

    
        public static string SePuedeUnir(int IdViaje)
        {
            string select = "select * from viajescompartidos where IdViaje=" + IdViaje.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<ViajesCompartidos> ListaCompartenMismoViaje = new List<ViajesCompartidos>();
            ViajesCompartidos viajescomparten;
            Viajes viaje = new Viajes();            
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {                    
                    viajescomparten = ObtenerPorRow(row);                    
                    ListaCompartenMismoViaje.Add(viajescomparten);
                }               
            }
            int compartiendo = ListaCompartenMismoViaje.Count;
            viaje = ViajesData.ObtenerViajexID(IdViaje);
            int calculando = viaje.CantPasajeros - compartiendo;
            if (calculando <= 0)
            {
                return "NO";
            }
            else
            {
                return "SI";
            }
            
        }

        public static string EsLaMismaPersona (int IdViaje, int IdUsuario)
        {
            string select = "select * from viajescompartidos where IdViaje=" + IdViaje.ToString();
            string MismaPersona= "NO";
            
            DataTable dt = DBHelper.EjecutarSelect(select);
            ViajesCompartidos viajescomparten;
            Viajes viaje;
            viaje = ViajesData.ObtenerViajexID(IdViaje);
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viajescomparten = ObtenerPorRow(row);
                    if (viajescomparten.IdUsuario == IdUsuario)
                    {
                        MismaPersona = "SI";
                    }              
                    
                }
            }

            return MismaPersona;
            
        }
       public static List<Viajes> ViajesdelaPersona(int IdUsuario, int IdaVuelta)
        {
            string select = "select IdViaje from viajescompartidos where IdUsuario=" + IdUsuario.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaComparte = new List<Viajes>();
            Viajes viaje = new Viajes();
            
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {   
                                   
                    viaje = ViajesData.ObtenerViajexID(Convert.ToInt32(row[0]));
                    if (Convert.ToInt32(viaje.DesdeHasta)== IdaVuelta)
                    {
                        ListaComparte.Add(viaje);
                    }
                   
                }
                return ListaComparte;
            }
            return ListaComparte;
        }

        private static ViajesCompartidos ObtenerPorRow(DataRow row)
        {
            ViajesCompartidos v = new ViajesCompartidos();
            v.IdViajeCompartido = row.Field<int>("IdViajeCompartido");
            v.IdViaje = row.Field<int>("IdViaje");    
            v.IdUsuario= row.Field<int>("IdUsuario");        
            return v;
        }

    }
}