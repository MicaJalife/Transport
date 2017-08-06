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
        public IList<Viajes> TraerViajexDNI(int DNI)
        {
            return ViajesData.ObtenerViajesXDNI(DNI);
        }

        //GET : api/ViajesUsuarioDesdeHasta
        [Route("api/viajes/ViajesUsuarioDesdeHasta/{DNI}/{DesdeHasta}")]
        public List<Viajes> ViajesDesdeHasta (int DNI, bool DesdeHasta)
        {
            return ViajesData.ObtenerViajesUsuarioDesdeHasta(DNI, DesdeHasta);
        }

        //GET : api/ViajesccDirecciones  HAY QUE FIJARSE SI ESTO SE USA!!!!!!!!!!!!
        [Route("api/viajes/ViajesccDirecciones")]
        public List<Viajes> Get()
        {
            return ViajesData.ObtenerViajesccDirecciones();
        }

        //GET : api/ViajesccIdDiaHorario HAY QUE FIJARSE SI ESTO SE USA!!!!!!!!!!!!
        [Route("api/viajes/ViajesccIdDiaHorario/{IdViaje}/{IdDia}/{IdHorario}")]
        public Viajes ViajesconIdViajeDiaHorario (int IdViaje, int IdDia, int IdHorario)
        {
            return ViajesData.ObtenerViajexIdDiaHorario(IdViaje, IdDia, IdHorario);
        }

        //GET : api/ViajesccIdDiaHorarioTransporte HAY QUE FIJARSE SI ESTO SE USA!!!!!!!!!!!!
        [Route("api/viajes/ViajesccIdDiaHorario/{IdViaje}/{IdDia}/{IdHorario}/{IdTransporte}")]
        public Viajes ViajesconIdViajeDiaHorarioTransporte(int IdViaje, int IdDia, int IdHorario, int IdTransporte)
        {
            return ViajesData.ObtenerViajexIdDiaHorarioTransporte(IdViaje, IdDia, IdHorario, IdTransporte);
        }

        //GET : api/ViajesValidacionInsert1
        [Route("api/viajes/validacion/{DNI}/{IdDia}/{DesdeHasta}")]
        public Viajes ValidacionInsertar1(int DNI, int IdDia, bool DesdeHasta)
        {
            return ViajesData.ValidacionDeInsert1(DNI, IdDia, DesdeHasta);
        }

        //GET : api/ViajesValidacionInsert2
        [Route("api/viajes/validacion/{DNI}/{IdDia}/{IdHorario}")]
        public Viajes ValidacionInsertar2(int DNI, int IdDia, int IdHorario)
        {
            return ViajesData.ValidacionDeInsert2(DNI, IdDia, IdHorario);
        }

        //GET : api/ViajesDireccionSpinner
        [Route("api/viajes/direcionspinner/{DNI}")]
        public List<Viajes> DireccionesUsuario(int DNI)
        {
            return ViajesData.DireccionesUsuario(DNI);
        }
    }
}