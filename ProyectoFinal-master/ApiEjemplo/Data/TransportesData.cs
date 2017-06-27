using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;


namespace ApiEjemplo.Data
{
    public class TransportesData
    {
        public static Transportes ObtenerPorId(int id)
        {
            string select = "select TipoTransporte from horarios where id=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Transportes t;
            if (dt.Rows.Count > 0)
            {
                t = ObtenerPorRow(dt.Rows[0]);
                return t;
            }
            return null;
        }
        private static Transportes ObtenerPorRow(DataRow row)
        {
            Transportes t = new Transportes();
            t.IdTransporte = row.Field<int>("IdTransporte");
            t.TipoTransporte = row.Field<string>("TipoTransporte");
            return t;
        }
    }
}