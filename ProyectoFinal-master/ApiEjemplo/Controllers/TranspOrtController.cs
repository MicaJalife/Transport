using ApiEjemplo.Data;
using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ApiEjemplo.Controllers
{
    public class TranspOrtController
    {
        //GET api/Usuario
        [ResponseType(typeof(Usuarios))]
        public IHttpActionResult Get(int DNI, string Contraseña)
        {
            Usuarios usuario = UsuariosData.Login(DNI, Contraseña);
            if(usuario == null )
            {
                return NotFound();
            }
            return Ok(usuario);
        }

        //POST : api/Viajes
        [ResponseType(typeof(Viajes))]
        public IHttpActionResult Post(Viajes viaje)
        {
            if (viaje==null)
            {
                return BadRequest("Datos incorrectos.");
            
            }
            ViajesData.InsertarViaje(viaje);
            return Ok();
        }

        //GET : api/Viajes
        public IList<Viajes> Get(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }






    }
}