using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
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



        // GET: ComentarioController/Create
        [Authorize]
        public ActionResult Create()
        {
            return View();
        }

        // POST: ComentarioController/Create
        [Authorize]
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

                return RedirectToAction("Index", "Home");
            }
            catch
            {
                return View();
            }
        }



        // GET: ComentarioController/Delete/5
        [Authorize]
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: ComentarioController/Delete/5
        [Authorize]
        [HttpPost]    
        public ActionResult Delete(Comentario coment)
        {
            _context.Remove(coment);
            _context.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}
