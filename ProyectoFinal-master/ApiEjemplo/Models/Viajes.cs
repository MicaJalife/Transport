using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class Viajes
    {
        public int DNI { get; set; }
        public int IdViaje { get; set; }

        public int IdHorario { get; set; }
        public int IdTransporte{ get; set; }

        public bool DesdeHasta { get; set; }
        public string DetalleTransporte{ get; set; }

        public string DireccionLatitud { get; set; }
        public string DireccionLongitud { get; set; }

    }
}