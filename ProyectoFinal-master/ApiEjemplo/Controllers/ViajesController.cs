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
        [HttpPost]
        public IHttpActionResult Post(Viajes viaje)
        {
            if (viaje == null)
            {
                return BadRequest("Datos incorrectos.");

            }
            ViajesData.InsertarViaje(viaje);
            return Ok("bien");
        }      

        //GET : api/Viajes
        [Route("api/viajes/traerviaje/{DNI}")]
        public IList<Viajes> Get(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }

        //GET : api/ViajesUsuarioDesdeHasta
        [Route("api/viajes/ViajesUsuarioDesdeHasta/{DNI}/{DesdeHasta}")]
        public List<Viajes> Get (int DNI, bool DesdeHasta)
        {
            return ViajesData.ObtenerViajesUsuarioDesdeHasta(DNI, DesdeHasta);
        }

        //GET : api/ViajesccDirecciones
        [Route("api/viajes/ViajesccDirecciones")]
        public List<Viajes> Get()
        {
            return ViajesData.ObtenerViajesccDirecciones();
        }

        //GET : api/ViajesccIdDiaHorario 
        [Route("api/viajes/ViajesccIdDiaHorario/{IdViaje}/{IdDia}/{IdHorario}")]
        public Viajes Get(int IdViaje, int IdDia, int IdHorario)
        {
            return ViajesData.ObtenerViajexIdDiaHorario(IdViaje, IdDia, IdHorario);
        }

        //GET : api/ViajesccIdDiaHorarioTransporte
        [Route("api/viajes/ViajesccIdDiaHorario/{IdViaje}/{IdDia}/{IdHorario}/{IdTransporte}")]
        public Viajes Get(int IdViaje, int IdDia, int IdHorario, int IdTransporte)
        {
            return ViajesData.ObtenerViajexIdDiaHorarioTransporte(IdViaje, IdDia, IdHorario, IdTransporte);
        }

        //GET : api/ViajesValidacionInsert
        [Route("api/viajes/validacion/{DNI}/{IdDia}/{DesdeHasta}")]
        public Viajes Get(int DNI, int IdDia, bool DesdeHasta)
        {
            return ViajesData.ValidacionDeInsert(DNI, IdDia, DesdeHasta);
        }

        //GET : api/ViajesDireccionSpinner
        [Route("api/viajes/direcionspinner/DNI")]
        public List<Viajes> Get1(int DNI)
        {
            return ViajesData.DireccionesUsuario(DNI);
        }
    }
}