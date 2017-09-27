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
            //string sInsert = "INSERT into viajes (DNI, IdHorario, IdTransporte, Cantidad, IdDia, DesdeHasta, DetalleTransporte, DireccionLatitud, DireccionLongitud) values ('" + viaje.DNI.ToString() + "','" + viaje.IdHorario.ToString() + "','" + viaje.IdTransporte.ToString() + "','" +viaje.DesdeHasta.ToString() + "','" + viaje.DetalleTransporte + "','" + viaje.DireccionLatitud + "','"  +viaje.DireccionLongitud +"')";
            string strSQL = string.Format("INSERT into viajes (DNI, IdHorario, IdTransporte, Cantidad, IdDia, DesdeHasta, DetalleTransporte, DireccionLatitud, DireccionLongitud, Direccion) values ({0}, {1}, {2}, {3}, {4}, '{5}', '{6}', '{7}', '{8}');" ,
               viaje.DNI,
               viaje.IdHorario,
               viaje.IdTransporte,
               viaje.Cantidad,
               viaje.IdDia,
               ((viaje.DesdeHasta == true) ? '1' : '0'),
               viaje.DetalleTransporte, 
               viaje.DireccionLatitud, 
               viaje.DireccionLongitud, 
               viaje.Direccion 
               );



            DBHelper.EjecutarIUD(strSQL);
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
                    viaje.usuario = UsuariosData.ObtenerPorId(Convert.ToInt32(viaje.DNI));
                    viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                    viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);
                    viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                    ListaViajes.Add(viaje);
                }
                viaje = ObtenerPorRow(dt.Rows[0]);
            }
            return ListaViajes;
        }
        public static List<Viajes> ObtenerViajesUsuarioDesdeHasta(int DNI, int DesdeHasta)
        {
            string select = "select * from viajes where DNI=" + DNI.ToString() + " and DesdeHasta=" + DesdeHasta.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaDesdeHastaViajes = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                    viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                    viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);                   
                    ListaDesdeHastaViajes.Add(viaje);
                }
                viaje = ObtenerPorRow(dt.Rows[0]);
            }
            return ListaDesdeHastaViajes;
        }

        /// <summary>
        ///     Estemetodo......
        /// </summary>
        /// <param name="strLat"></param>
        /// <param name="strLng"></param>
        /// <returns></returns>
        public static List<Viajes> ObtenerViajesMasCercanos(string strLat, string strLng)
        {    //"34.434234, 54.222"
            //strLat, string strLng
            string select="";

            select += "SELECT * ";
            select += " FROM viajes as v";
            select += " WHERE test.Func_Distancia("+ strLat + ", "+ strLng + ", v.DireccionLatitud, v.DireccionLongitud) < 1";
            
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaDesdeHastaViajes = new List<Viajes>();
            Viajes viaje;
             if (dt.Rows.Count > 0)
             {
                 foreach (DataRow row in dt.Rows)
                 {
                     viaje = ObtenerPorRow(row);
                     viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                     viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                     viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);
                     ListaDesdeHastaViajes.Add(viaje);
                 }                
                 return ListaDesdeHastaViajes;
             }
             else {
                 return null;
             }                        
        }

        public static List<Viajes> ObtenerViajesMasCercanosDiaHorario(string strLat, string strLng, int dia, int horario, int desdehasta, int idusuario)
        {    //"34.434234, 54.222"
            //strLat, string strLng
            string select = "";

            select += "SELECT * ";
            select += " FROM viajes as v";
            select += " WHERE test.Func_Distancia(" + strLat + ", " + strLng + ", v.DireccionLatitud, v.DireccionLongitud) < 1 and IdDia=" + dia.ToString() + " and IdHorario=" + horario.ToString() + " and DesdeHasta=" + desdehasta.ToString() + " and DNI!=" + idusuario.ToString(); 

            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaDesdeHastaViajes = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    viaje.usuario = UsuariosData.ObtenerPorId(Convert.ToInt32(viaje.DNI));
                    viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                    viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                    viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);
                    ListaDesdeHastaViajes.Add(viaje);
                }
                return ListaDesdeHastaViajes;
            }
            else
            {
                return null;
            }
        }

        public static List<Viajes> ObtenerViajesMasCercanosDiaHorarioTransporte(string strLat, string strLng, int dia, int horario,int desdehasta, int transporte, int idusuario)
        {    //"34.434234, 54.222"
            //strLat, string strLng
            string select = "";

            select += "SELECT * ";
            select += " FROM viajes as v";
            select += " WHERE test.Func_Distancia(" + strLat + ", " + strLng + ", v.DireccionLatitud, v.DireccionLongitud) < 1 and IdDia=" + dia.ToString() + " and IdHorario=" + horario.ToString()+ " and DesdeHasta="+ desdehasta.ToString()+ " and IdTransporte="+ transporte.ToString() + " and DNI!=" + idusuario.ToString(); 

            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaDesdeHastaViajes = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    viaje.usuario = UsuariosData.ObtenerPorId(Convert.ToInt32(viaje.DNI));
                    viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                    viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                    viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);
                    ListaDesdeHastaViajes.Add(viaje);
                }
                return ListaDesdeHastaViajes;
            }
            else
            {
                return null;
            }


        }

        public static List<Viajes> ObtenerViajesccDirecciones()
        {
            string select = "select IdViaje, DireccionLatitud, DireccionLongitud from viajes";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaViajeccDirecciones = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count>0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    ListaViajeccDirecciones.Add(viaje);
                }
                return ListaViajeccDirecciones;
            }
            else
            {
                return null;
            }
       }

        //NO SE USA ↓↓↓↓
        public static List<Viajes> ObtenerViajexDiaHorario( int IdDia, int IdHorario, int DesdeHasta)
        {
            string select = "select * from viajes where IdDia=" + IdDia.ToString() + " and IdHorario="+ IdHorario.ToString() + " and DesdeHasta=" + DesdeHasta.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaViajeccDiaHorario = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    viaje.usuario = UsuariosData.ObtenerPorId(Convert.ToInt32(viaje.DNI));
                    viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                    viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);
                    viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                    ListaViajeccDiaHorario.Add(viaje);
                }
                return ListaViajeccDiaHorario;
            }
            else
            {
                return null;
            }
        }
        //NO SE USA ↓↓↓↓
        public static List<Viajes>  ObtenerViajexDiaHorarioTransporte(int IdDia, int IdHorario, int IdTransporte, int DesdeHasta)
        {
            string select = "select * from viajes where IdDia=" + IdDia.ToString() + " and IdHorario=" + IdHorario.ToString() + " and IdTransporte=" + IdTransporte.ToString() + " and DesdeHasta=" + DesdeHasta.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaViajeccDiaHorarioTransporte = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerPorRow(row);
                    viaje.usuario = UsuariosData.ObtenerPorId(Convert.ToInt32(viaje.DNI));
                    viaje.horario = HorariosData.ObtenerPorId(viaje.IdHorario);
                    viaje.transporte = TransportesData.ObtenerPorId(viaje.IdTransporte);
                    viaje.dia = DiasData.ObtenerPorId(viaje.IdDia);
                    ListaViajeccDiaHorarioTransporte.Add(viaje);
                }
                return ListaViajeccDiaHorarioTransporte;
            }
            else
            {
                return null;
            }

        }
        public static Viajes ValidacionDeInsert1 (int DNI, int IdDia,int DesdeHasta)
        {
            string select = "select * from viajes where DNI=" + DNI.ToString() + " and IdDia=" + IdDia.ToString() + " and DesdeHasta=" + DesdeHasta.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                viaje = ObtenerPorRow(dt.Rows[0]);              
                return viaje;
            }
            else
            {
                return null;
            }
        }

        public static Viajes ValidacionDeInsert2(int DNI, int IdDia, int IdHorario)
        {
            string select = "select * from viajes where DNI=" + DNI.ToString() + " and IdDia=" + IdDia.ToString() + " and IdHorario=" + IdHorario.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                viaje = ObtenerPorRow(dt.Rows[0]);
                return viaje;
            }
            else
            {
                return null;
            }
        }
        public static List<Viajes> DireccionesUsuario(int DNI)
        {
            string select = "select DireccionLatitud, DireccionLongitud, Direccion from viajes where DNI=" + DNI.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Viajes> ListaDireccionesUsuario = new List<Viajes>();
            Viajes viaje;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    viaje = ObtenerxRowDirecciones(row);
                    ListaDireccionesUsuario.Add(viaje);
                }
                return ListaDireccionesUsuario;
            }  
            else
            {
                return null;
            }          
        }

        public static int ObtenerCantidadxId(int IdViaje)
        {
            string select = "select Cantidad from viajes where IdViaje=" + IdViaje.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            int resultado=0;                
           
            resultado= Convert.ToInt32(dt.Rows);
            
            return resultado;
        }
        
        private static Viajes ObtenerxRowDirecciones(DataRow row)
        {
            Viajes v = new Viajes();
            v.DireccionLatitud= row.Field<string>("DireccionLatitud");
            v.DireccionLongitud = row.Field<string>("DireccionLongitud");
            v.Direccion = row.Field<string>("Direccion");
            return v;
        }
        private static Viajes ObtenerPorRow(DataRow row)
        {
            Viajes v = new Viajes();
            v.DNI = row.Field<int>("DNI");
            v.IdHorario = row.Field<int>("IdHorario");
            v.IdTransporte = row.Field<int>("IdTransporte");
            v.Cantidad= row.Field<int>("Cantidad");
            v.IdDia = row.Field<int>("IdDia");
            v.DesdeHasta = row.Field<bool>("DesdeHasta");
            v.DetalleTransporte= row.Field<string>("DetalleTransporte");
            v.DireccionLatitud = row.Field<string>("DireccionLatitud");
            v.DireccionLongitud = row.Field<string>("DireccionLongitud");
            v.Direccion = row.Field<string>("Direccion");    
            
            return v;
        }







    }
}