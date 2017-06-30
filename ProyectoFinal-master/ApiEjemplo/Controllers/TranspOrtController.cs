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
    public class TranspOrtController : ApiController
    {
        //GET api/Usuario
        [Route("api/usuario/{DNI}/{Contrasena}")]
        public IHttpActionResult Get(int DNI, string Contrasena)
        {
            Usuarios usuario = UsuariosData.Login(DNI, Contrasena);
            if(usuario == null )
            {
                return NotFound();
            }
            return Ok(usuario);
        }
        

        //POST : api/Viajes
        [Route("api/ingresarviaje")]
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
        [Route("api/traerviaje/{DNI}")]
        public IList<Viajes> Get(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }






    }
}