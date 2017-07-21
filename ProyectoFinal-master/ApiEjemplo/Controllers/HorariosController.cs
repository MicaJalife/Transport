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
    public class HorariosController : ApiController
    {
        //GET : api/Horarios
        [Route("api/horarios/traerhorario/{IdHorario}")]
        public IHttpActionResult GetHorario(int IdHorario)
        {
            Horarios horario = HorariosData.ObtenerPorId(IdHorario);
            if (horario == null)
            {
                return NotFound();
            }
            return Ok(horario);
        }
    }
}
