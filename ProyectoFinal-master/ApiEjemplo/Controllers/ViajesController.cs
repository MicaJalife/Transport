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
        [HttpGet]
        [Route("api/viajes/traerviaje/{DNI}")]
        public IList<Viajes> TraerViajexDNI(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }

        //GET : api/ViajesUsuarioDesdeHasta
        [HttpGet]
        [Route("api/viajes/ViajesUsuarioDesdeHasta/{DNI}/{DesdeHasta}")]
        public List<Viajes> ViajesDesdeHasta (int DNI, int DesdeHasta)
        {
            return ViajesData.ObtenerViajesUsuarioDesdeHasta(DNI, DesdeHasta);
        }

        //GET : api/ViajesccDirecciones  HAY QUE FIJARSE SI ESTO SE USA!!!!!!!!!!!!
        [HttpGet]
        [Route("api/viajes/ViajesccDirecciones")]
        public List<Viajes> Get()
        {
            return ViajesData.ObtenerViajesccDirecciones();
        }

        //GET : api/ViajesccIdDiaHorario HAY QUE FIJARSE SI ESTO SE USA!!!!!!!!!!!!
        [HttpGet]
        [Route("api/viajes/ViajesccDiaHorario/{IdDia}/{IdHorario}/{DesdeHasta}")]
        public List<Viajes> ViajesconIdViajeDiaHorario (int IdDia, int IdHorario, int DesdeHasta)
        {
            return ViajesData.ObtenerViajexDiaHorario(IdDia, IdHorario, DesdeHasta);
        }

        //GET : api/ViajesccIdDiaHorarioTransporte HAY QUE FIJARSE SI ESTO SE USA!!!!!!!!!!!!
        [HttpGet]
        [Route("api/viajes/ViajesccIdDiaHorarioTransporte/{IdDia}/{IdHorario}/{IdTransporte}/{DesdeHasta}")]
        public List<Viajes> ViajesconIdViajeDiaHorarioTransporte(int IdDia, int IdHorario, int IdTransporte, int DesdeHasta)
        {
            return ViajesData.ObtenerViajexDiaHorarioTransporte( IdDia, IdHorario, IdTransporte, DesdeHasta);
        }

        //GET : api/ViajesValidacionInsert1
        [HttpGet]
        [Route("api/viajes/validacion1/{DNI}/{IdDia}/{DesdeHasta}")]
        public Viajes ValidacionInsertar1(int DNI, int IdDia, int DesdeHasta)
        {
            return ViajesData.ValidacionDeInsert1(DNI, IdDia, DesdeHasta);
        }

        //GET : api/ViajesValidacionInsert2
        [HttpGet]
        [Route("api/viajes/validacion2/{DNI}/{IdDia}/{IdHorario}")]
        public Viajes ValidacionInsertar2(int DNI, int IdDia, int IdHorario)
        {
            return ViajesData.ValidacionDeInsert2(DNI, IdDia, IdHorario);
        }

        //GET : api/ViajesDireccionSpinner
        [HttpGet]
        [Route("api/viajes/direcionspinner/{DNI}")]
        public List<Viajes> DireccionesUsuario(int DNI)
        {
            return ViajesData.DireccionesUsuario(DNI);
        }

        //GET : api/Viajescercanos
        [HttpGet]
        [Route("api/viajes/cercanos/{DireccionLatitud}/{DireccionLongitud}/")]
        public List<Viajes> ObtenerViajesMasCercanos(string DireccionLatitud, string DireccionLongitud)
        {
            return ViajesData.ObtenerViajesMasCercanos(DireccionLatitud, DireccionLongitud);
        }
    }
}