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
        /// <summary>
        /// Apia, zein helbidetan aurkitzen den.
        /// </summary>
        private Uri rutaTodos = new Uri("http://192.168.65.8:8080/");

        /// <summary>
        /// Api-tik partida guztiak hartzen ditu.
        /// </summary>
        /// <returns>jokatutako partida guztien lista bueltatzen du</returns>
        public async Task<List<Partida>> GetPartidaGuztiak()
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

        /// <summary>
        /// Api-tik logeatuta dagoen erabiltzailearen partidak hartzen ditu.
        /// </summary>
        /// <returns>Erabiltzaile batek jokatutako partida guztien lista bat bueltatzen du</returns>
        public async Task<List<Partida>> GetPartidakById(string name)
        {
            List<Partida> partidaList = new List<Partida>();
            Uri rutaPartidaGuztiak = new Uri(rutaTodos, "partidakById?name="+name);
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

        /// <summary>
        /// Api-tik partida onenak hartzen ditu.
        /// </summary>
        /// <returns>Jolastutako partida hoberenen lista bueltatzen du</returns>
        public async Task<List<Partida>> GetPartidaOnenak()
        {
            List<Partida> partidaList = new List<Partida>();
            Uri rutaPartidaGuztiak = new Uri(rutaTodos, "onenak");
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

        /// <summary>
        /// Api-tik partida onenak hartzen ditu eta inprimatuko den formaturako prestatzen ditu.
        /// </summary>
        /// <returns>Jolastutako partida onenen lista bueltatzen du, txostena inprimatzeko formatuan</returns>
        public async Task<List<ReportModel>> GetPartidaOnenenTxostena()
        {
            List<Partida> partidaList = new List<Partida>();
            Uri rutaPartidaGuztiak = new Uri(rutaTodos, "onenak");
            using (var httpClient = new HttpClient())
            {
                using (var response = await httpClient.GetAsync(rutaPartidaGuztiak))
                {
                    string apiResponse = await response.Content.ReadAsStringAsync();
                    partidaList = JsonConvert.DeserializeObject<List<Partida>>(apiResponse);
                }
            }
            List<ReportModel> rmList = new List<ReportModel>();
            foreach (Partida p in partidaList)
            {
                ReportModel rm = new ReportModel();
                rm.Name = p.Jokalaria.Name;
                rm.Puntuazioa = p.Puntuazioa;
                rm.Fecha = p.Fecha.Substring(0, 10);
                rmList.Add(rm);
            }
            rmList = rmList.OrderByDescending(o => o.Puntuazioa).ToList();
            return rmList;
        }

        /// <summary>
        /// Api-tik partida txarrenak hartzen ditu.
        /// </summary>
        /// <returns>Jolastutako partida txarrenen lista bueltatzen du</returns>
        public async Task<List<Partida>> GetPartidaTxarrenak()
        {
            List<Partida> partidaList = new List<Partida>();
            Uri rutaPartidaGuztiak = new Uri(rutaTodos, "txarrenak");
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

        /// <summary>
        /// Api-tik partida txarrenak hartzen ditu eta inprimatuko den formaturako prestatzen ditu.
        /// </summary>
        /// <returns>Jolastutako partida txarrenen lista bueltatzen du, txostena inprimatzeko formatuan</returns>
        public async Task<List<ReportModel>> GetPartidaTxarrenenTxostena()
        {
            List<Partida> partidaList = new List<Partida>();
            Uri rutaPartidaGuztiak = new Uri(rutaTodos, "txarrenak");
            using (var httpClient = new HttpClient())
            {
                using (var response = await httpClient.GetAsync(rutaPartidaGuztiak))
                {
                    string apiResponse = await response.Content.ReadAsStringAsync();
                    partidaList = JsonConvert.DeserializeObject<List<Partida>>(apiResponse);
                }
            }
            List<ReportModel> rmList = new List<ReportModel>();
            foreach (Partida p in partidaList)
            {
                ReportModel rm = new ReportModel();
                rm.Name = p.Jokalaria.Name;
                rm.Puntuazioa = p.Puntuazioa;
                rm.Fecha = p.Fecha.Substring(0, 10);
                rmList.Add(rm);
            }
            rmList = rmList.OrderByDescending(o => o.Puntuazioa).ToList();
            return rmList;
        }

    }
}
