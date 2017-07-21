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
    public class ViajesController : ApiController
    {
        //POST : api/Viajes
        [Route("api/ingresarviaje")]
        public IHttpActionResult Post(Viajes viaje)
        {
            if (viaje == null)
            {
                return BadRequest("Datos incorrectos.");

            }
            ViajesData.InsertarViaje(viaje);
            return Ok();
        }



        //GET : api/Viajes
        [Route("api/viajes/traerviaje/{DNI}")]
        public IList<Viajes> Get(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }

    }
}