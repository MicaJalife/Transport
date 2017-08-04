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
    public class UsuarioController: ApiController
    {
        //GET api/Usuario
        [Route("api/usuario/{DNI}/{Contrasena}")]
        public IHttpActionResult Get(int DNI, string Contrasena)
        {
            Usuarios usuario = UsuariosData.Login(DNI, Contrasena);
            if (usuario == null)
            {
                return NotFound();
            }
            return Ok(usuario);
        }

        //GET api/UsuarioNombre
        [Route("api/usuario/{DNI}")]
        public string Get(int DNI)
        {
            //return UsuariosData.ObtenerPorId(Convert.ToInt32(DNI));
        }
    }
}