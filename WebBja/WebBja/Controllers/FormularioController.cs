using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebBja.Data;
using WebBja.Models;
using Microsoft.AspNetCore.Authorization;

namespace WebBja.Controllers
{
    public class FormularioController : Controller
    {
        private readonly BjaDbContext _context;

        /// <summary>
        /// Dependentziak injektatzen dira.
        /// </summary>
        /// <param name="context"></param>
        public FormularioController(BjaDbContext context)
        {
            _context = context;
        }
        /// <summary>
        /// Formularioen bista erakusten du, formulario lista bat kargatuz.
        /// </summary>
        /// <returns></returns>
        /// 
        // GET: FormularioController
        public ActionResult Index()
        {
            if (!User.Identity.Name.Equals("Admin"))
            {
                return RedirectToAction("Index", "Home");
            }

            IEnumerable<Formulario> listaForms = _context.Formulario;
            decimal puntuacionMedia = 0;

            foreach(Formulario f in listaForms)
            {
                puntuacionMedia += f.Puntuacion;
            }
            puntuacionMedia = puntuacionMedia / listaForms.Count();

            ViewBag.media = puntuacionMedia;

            return View(listaForms);
        }

        /// <summary>
        /// Formulario berriaren bista erakusten du.
        /// </summary>
        /// <returns></returns>        
        // GET: FormularioController/Create
        [Authorize]
        public ActionResult Create()
        {
            if (User.Identity.Name.Equals("Admin"))
            {
                return RedirectToAction("Index", "Home");
            }

            ViewData["existe"] = "0";
            var forms = from f in _context.Formulario
                        where f.Username.Equals(User.Identity.Name)
                        select f;

            if (forms.Count() > 0)
            {

                ViewData["info"] = "Zure formularioa gordeta dago, ezin dituzu formulario gehiago gorde";
                ViewData["existe"] = "1";
            }

            return View();
        }


        /// <summary>
        /// Eskaera jaso eta datuak gordetzen ditu.
        /// </summary>
        /// <param name="form"></param>
        /// <returns></returns>
        // POST: FormularioController/Create
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Formulario form)
        {
            form.Username = User.Identity.Name;

            if (ModelState.IsValid)
            {
                _context.Add(form);
                _context.SaveChanges();

                TempData["mensaje"] = "Zure formulario ondo bete da!";

                return RedirectToAction(nameof(Create));
            }


            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// Borratu nahi den formularioaren datuak erakusten ditu.
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        // GET: FormularioController/Delete/5
        [Authorize]
        public ActionResult Delete(int id)
        {
            if (!User.Identity.Name.Equals("Admin"))
            {
                return RedirectToAction("Index", "Home");
            }
            var f = _context.Formulario.Find(id);
            ViewBag.puntuazioa = (int)f.Puntuacion;
            return View(f);
        }

        /// <summary>
        /// Aukeratutako formularioa ezabatzen du.
        /// </summary>
        /// <param name="form"></param>
        /// <returns></returns>
        // POST: FormularioController/Delete/5
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(Formulario form)
        {
            _context.Remove(form);
            _context.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}
