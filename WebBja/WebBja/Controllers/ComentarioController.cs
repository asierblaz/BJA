using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebBja.Data;
using WebBja.Models;

namespace WebBja.Controllers
{
    public class ComentarioController : Controller
    {
        private readonly BjaDbContext _context;

        public ComentarioController(BjaDbContext context)
        {
            _context = context;
        }

        // GET: ComentarioController
        public ActionResult Index()
        {

            IEnumerable<Comentario> listaComent = _context.Comentario;
            return View(listaComent);
        }

        // GET: ComentarioController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: ComentarioController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: ComentarioController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Comentario com)
        {
            com.Fecha = DateTime.Now;
            com.Username = User.Identity.Name;

            if (ModelState.IsValid)
            {
                _context.Add(com);
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

        // GET: ComentarioController/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: ComentarioController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, IFormCollection collection)
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

        // GET: ComentarioController/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: ComentarioController/Delete/5
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
