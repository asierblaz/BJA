using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using WebBja.Data;
using WebBja.Models;

namespace WebBja.Controllers
{
    public class HomeController : Controller
    {
        private readonly BjaDbContext _context;

        public HomeController(BjaDbContext context)
        {            
            _context = context;
        }

        public IActionResult Index()
        {
            ViewData["ruta"] = HttpContext.Request.Host;//HttpContext.Current.Request.Url.AbsolutePath;
            IEnumerable<Comentario> listaComent = _context.Comentario;
            return View(listaComent);
        }
       
        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
