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
        public int Cantidad { get; set; }
        
        public int IdDia { get; set; }

        public bool DesdeHasta { get; set; }
        public string DetalleTransporte{ get; set; }
        
        public string DireccionLatitud { get; set; }
        public string DireccionLongitud { get; set; }
        public string Direccion { get; set; }
        public Usuarios usuario { get; set; }
        public Horarios horario { get; set; }
        public Transportes transporte { get; set; }
        public Dias dia { get; set; }

    }
}