using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;


namespace ApiEjemplo.Data
{
    public class DiasData
    {
        public static Dias ObtenerPorId(int id)
        {
            string select = "select Dia from dias where IdDia=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Dias d;
            if (dt.Rows.Count > 0)
            {
                d = ObtenerPorRow(dt.Rows[0]);
                return d;
            }
            return null;
        }
        private static Dias ObtenerPorRow(DataRow row)
        {
            Dias d = new Dias();
            //d.IdDia = row.Field<int>("IdDia");
            d.Dia = row.Field<string>("Dia");
            return d;
        }
    }
}