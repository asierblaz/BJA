using WebBja.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BjaWeb.Services
{
    public interface IPartidaService
    {
        Task<IList<Partida>> GetPartidaGuztiak();
    }
}
