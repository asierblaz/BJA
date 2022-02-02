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
        public PartidaController(ILogger<PartidaController> logger, IPartidaService partidaService)
        {
            _logger = logger;
            _partidaService = partidaService;
        }
        // GET: PartidaController
        public ActionResult Index()
        {
            return View();
  
        }

        // GET: PartidaController/guztiak
        public async Task<IActionResult> Guztiak()
        {


            return View(await _partidaService.GetPartidaGuztiak());
        }
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
        // GET: PartidaController/txarrenak
        public async Task<ActionResult> Txarrenak()
        {

            return View(await _partidaService.GetPartidaTxarrenak());
        }
        // GET: PartidaController/nireak
        [Authorize]
        public async Task<ActionResult> Nireak()
        {

            var user = User.Identity.Name;
            ViewData["izena"] = user;
            return View(await _partidaService.GetPartidakById(user));
        }

        // GET: PartidaController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: PartidaController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: PartidaController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: PartidaController/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: PartidaController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}
