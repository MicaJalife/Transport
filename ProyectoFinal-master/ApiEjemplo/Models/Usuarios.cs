using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class Usuarios
    {
        public int DNI { get; set; }
        public string Nombre { get; set; }
        public string Contrasena { get; set; }
        public string Año { get; set; }
        public string Curso { get; set; }       
        public string Imagen { get; set; }
        public bool PrimeraEdicion { get; set; }

    }
}