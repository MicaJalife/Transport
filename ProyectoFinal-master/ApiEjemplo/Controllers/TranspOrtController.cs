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
        [Route("api/viajes/traerviaje/{DNI}")]
        public IList<Viajes> Get(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }

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