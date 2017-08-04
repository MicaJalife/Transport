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
    public class DiasController : ApiController
    {

        //GET : api/Dias
        [Route("api/dias/traerdia/{IdDia}")]
        public IHttpActionResult GetDia(int IdDia)
        {
            Dias dia = DiasData.ObtenerPorId(IdDia);
            if (dia == null)
            {
                return NotFound();
            }
            return Ok(dia);
        }
    }
}