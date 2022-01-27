using BjaWeb.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebBja.Models;

namespace WebBja.ViewComponents
{
    [ViewComponent(Name ="Partida")]
    public class PartidaViewComponent : ViewComponent
    {
        private readonly IPartidaService _partidaService;

        public PartidaViewComponent(IPartidaService partidaService)
        {
            _partidaService = partidaService;
        }
        public async Task<IViewComponentResult> InvokeAsync(int id)
        {
            List<Partida> partidas = await _partidaService.GetPartidaGuztiak();
            return View(partidas);

        }       
        

    }
}


