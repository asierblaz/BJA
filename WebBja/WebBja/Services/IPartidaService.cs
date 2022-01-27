using WebBja.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BjaWeb.Services
{
    public interface IPartidaService
    {
        Task<List<Partida>> GetPartidaGuztiak();
        Task<List<Partida>> GetPartidakById(string name);
        Task<List<Partida>> GetPartidaOnenak();
        Task<List<Partida>> GetPartidaTxarrenak();

    }
}
