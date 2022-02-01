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

        public FormularioController(BjaDbContext context)
        {
            _context = context;
        }

        // GET: FormularioController
        public ActionResult Index()
        {
            if (!User.Identity.Name.Equals("Admin")) {

                return RedirectToAction("Index", "Home");

            }
            IEnumerable<Formulario> listaForms = _context.Formulario;


            return View(listaForms);
        }

        // GET: FormularioController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: FormularioController/Create
        [Authorize]
        public ActionResult Create()
        {
            ViewData["existe"] = "0";
            var forms = from f in _context.Formulario
                        where f.Username.Equals(User.Identity.Name)
                        select f;


            if (forms.Count()>0 ) {

                ViewData["info"] = "Zure formularioa gordeta dago, ezin dituzu formulario gehiago gorde";
                ViewData["existe"] = "1";
            }


                return View();
            


        }

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

        // GET: FormularioController/Edit/5
        [Authorize]
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: FormularioController/Edit/5
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(Formulario form)
        {

            form.Username = User.Identity.Name;


            if (ModelState.IsValid)
            {
                _context.Add(form);
                _context.SaveChanges();

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

        // GET: FormularioController/Delete/5
        
        [Authorize]
        public ActionResult Delete()
        {

            return View();
        }

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
