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
    public class TransportesController : ApiController
    {
        //GET : api/Transportes
        [Route("api/transportes/traertransporte/{IdTransporte}")]
        public IHttpActionResult GetTransporte(int IdTransporte)
        {
            Transportes transporte = TransportesData.ObtenerPorId(IdTransporte);
            if (transporte == null)
            {
                return NotFound();
            }
            return Ok(transporte);
        }
    }
}