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

          /*  Formulario fo = new Formulario();

            var forms = from f in _context.Formulario
                        where f.Username.Equals(User.Identity.Name)
                        select f;

            fo = forms.First();

            if(fo.Username == User.Identity.Name)
            {
                return RedirectToAction(nameof(Index));
            } else
            {*/
                return View();
            


        }

        // POST: FormularioController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Formulario form)
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

        // GET: FormularioController/Edit/5
        [Authorize]
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: FormularioController/Edit/5
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
