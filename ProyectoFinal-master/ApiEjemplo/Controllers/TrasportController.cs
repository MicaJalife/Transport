using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Controllers
{
    public class TrasportController
    {
        // GET: api/Persona
        public IList<Usuarios> Get()
        {
            return TransportData.Login();
        }

        // GET: api/Persona/5
        [ResponseType(typeof(Usuarios))]
        public IHttpActionResult Get(int DNI, string Contraseña)
        {
            Usuarios uusuario = TransportData.Login(DNI, Contraseña);
            if (uusuario == null)
            {
                return NotFound();
            }
            return Ok(uusuario);
        }

        [ResponseType(typeof(Persona))]
        public IHttpActionResult Post(Persona persona)
        {
            if (persona == null || string.IsNullOrEmpty(persona.Nombre))//validamos nombre
            {
                return BadRequest("Datos incorrectos.");
            }
            PersonaData.Insert(persona);
            return Ok();
        }
    }
}