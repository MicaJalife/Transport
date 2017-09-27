using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class ViajesCompartidos
    {
        public int IdViajeCompartido { get; set; }
        public int IdViaje { get; set; }
        public int DNI { get; set; }
        public Viajes viaje { get; set; }
        public int Cantidad { get; set; }
    }
}