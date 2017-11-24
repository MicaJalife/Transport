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
    public class ViajesCompartidosController : ApiController
    {
        // GET: ViajesCompartidos
      

        //POST : api/CompartirunViaje
        [Route("api/viajescompartidos/compartirelviaje")]
        [HttpPost]
        public IHttpActionResult Post(ViajesCompartidos viajeacompartir)
        {
            if (viajeacompartir == null)
            {
                return BadRequest("Datos incorrectos.");

            }
            ViajesCompartidosData.InsertarViaje(viajeacompartir);
            return Ok("bien");
        }

        //GET : api/SepuedeCompartiroNO
        [HttpGet]
        [Route("api/viajescompartidos/{IdViaje}")]
        public string SePuedeUnir(int IdViaje)
        {
            return ViajesCompartidosData.SePuedeUnir(IdViaje);
        }

        //GET : api/EsLaMismaPersona
        [HttpGet]
        [Route("api/viajescompartido/{IdViaje}/{IdUsuario}")]
        public string EsLaMismaPersona(int IdViaje, int IdUsuario)
        {
            return ViajesCompartidosData.EsLaMismaPersona(IdViaje, IdUsuario);
        }


        //GET : api/ViajesQueComparte
        [HttpGet]
        [Route("api/viajesquecomparte/{IdUsuario}/{IdaVuelta}")]
        public List<Viajes> ViajesQueComparte(int IdUsuario, int IdaVuelta)
        {
            return ViajesCompartidosData.ViajesdelaPersona(IdUsuario, IdaVuelta);
        }

    }


}