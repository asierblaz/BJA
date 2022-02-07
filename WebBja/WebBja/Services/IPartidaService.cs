using WebBja.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BjaWeb.Services
{
    /// <summary>
    /// Zerbitzuak erabiliko dituen metodoak definitzen dituen Interfazea.
    /// </summary>
    public interface IPartidaService
    {
        Task<List<Partida>> GetPartidaGuztiak();
        Task<List<Partida>> GetPartidakById(string name);
        Task<List<Partida>> GetPartidaOnenak();
        Task<List<Partida>> GetPartidaTxarrenak();
        Task<List<ReportModel>> GetPartidaOnenenTxostena();
        Task<List<ReportModel>> GetPartidaTxarrenenTxostena();

    }
}
