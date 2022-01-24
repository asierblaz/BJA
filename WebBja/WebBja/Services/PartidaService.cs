using WebBja.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace BjaWeb.Services
{
    public class PartidaService : IPartidaService
    {
        private Uri rutaTodos = new Uri("http://192.168.65.13:8080/");
        public async Task<IList<Partida>> GetPartidaGuztiak()
        {
            List<Partida> partidaList = new List<Partida>();
            Uri rutaPartidaGuztiak = new Uri(rutaTodos, "partidak/");
            using (var httpClient = new HttpClient())
            {
                using (var response = await httpClient.GetAsync(rutaPartidaGuztiak))
                {
                    string apiResponse = await response.Content.ReadAsStringAsync();
                    partidaList = JsonConvert.DeserializeObject<List<Partida>>(apiResponse);
                }
            }
            foreach(Partida p in partidaList)
            {
                p.Fecha = p.Fecha.Substring(0, 10);
            }
            partidaList=partidaList.OrderByDescending(o => o.Puntuazioa).ToList();
            return partidaList;
        }
    }
}
