using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class HorariosData
    {

        public static Horarios ObtenerPorId(int id)
        {
            string select = "select Horario from horarios where IdHorario=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Horarios h;
            if (dt.Rows.Count > 0)
            {
                h = ObtenerPorRow(dt.Rows[0]);
                return h;
            }
            return null;
        }
        private static Horarios ObtenerPorRow(DataRow row)
        {
            Horarios h = new Horarios();
            //h.IdHorario = row.Field<int>("IdHorario");
            h.Horario = row.Field<string>("Horario");            
            return h;
        }



    }
}