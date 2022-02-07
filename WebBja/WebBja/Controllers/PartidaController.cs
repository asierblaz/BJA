using BjaWeb.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebBja.Models;

namespace WebBja.Controllers
{
    public class PartidaController : Controller
    {
        private readonly ILogger<PartidaController> _logger;
        private readonly IPartidaService _partidaService;

        /// <summary>
        /// Dependentziak Injektatzen dira.
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="partidaService"></param>
        public PartidaController(ILogger<PartidaController> logger, IPartidaService partidaService)
        {
            _logger = logger;
            _partidaService = partidaService;
        }

        /// <summary>
        /// Partida guztiak bistaratzen dira.
        /// </summary>
        /// <returns></returns>
        // GET: PartidaController/guztiak
        public async Task<IActionResult> Guztiak()
        {
            return View(await _partidaService.GetPartidaGuztiak());
        }

        /// <summary>
        /// 10 partida onenak bistaratzen dira.
        /// </summary>
        /// <returns></returns>
        // GET: PartidaController/onenak
        public async Task<ActionResult> Onenak() { 

        List<Partida> partidak = new List<Partida>();
        partidak = await _partidaService.GetPartidaOnenak();
            for (int i = 0; i < partidak.Count; i++)
            {
                ViewData[ (i + 1)+""] = partidak[i].Jokalaria.Name;
            }
         
            return View(partidak);
        }

        /// <summary>
        /// 10 partida txarrenak bistaratzen dira.
        /// </summary>
        /// <returns></returns>
        // GET: PartidaController/txarrenak
        public async Task<ActionResult> Txarrenak()
        {
            return View(await _partidaService.GetPartidaTxarrenak());
        }

        /// <summary>
        /// Logeatuta dagoen erabiltzailearen partidak erakusten dira.
        /// </summary>
        /// <returns></returns>
        // GET: PartidaController/nireak
        [Authorize]
        public async Task<ActionResult> Nireak()
        {

            var user = User.Identity.Name;
            ViewData["izena"] = user;
            return View(await _partidaService.GetPartidakById(user));
        }

    }
}
