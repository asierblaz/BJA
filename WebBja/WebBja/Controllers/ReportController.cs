using AspNetCore.Reporting;
using BjaWeb.Services;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WebBja.Controllers
{
    public class ReportController : Controller
    {
        public readonly IPartidaService _partidaService;
        private readonly IWebHostEnvironment _webHostEnvironment;

        /// <summary>
        /// Dependentziak injektatzen dira.
        /// </summary>
        /// <param name="webHostEnvironment"></param>
        /// <param name="partidaService"></param>
        public ReportController(IWebHostEnvironment webHostEnvironment, IPartidaService partidaService)
        {
            _webHostEnvironment = webHostEnvironment;
            _partidaService = partidaService;
        }
        /// <summary>
        /// Jolastutako 10 partida txarrenen txostena inprimatzen du.
        /// </summary>
        /// <returns></returns>
        public async Task<IActionResult> txarrenakInprimatu()
        {
            Encoding.RegisterProvider(CodePagesEncodingProvider.Instance);
            string mimtype = "";
            int extension = 1;
            var path = $"{_webHostEnvironment.WebRootPath}\\Reports\\Report1.rdlc";
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            LocalReport localReport = new LocalReport(path);

            var partidak = await _partidaService.GetPartidaTxarrenenTxostena();
            parameters.Add("rp1", " TXARRENAK");
            localReport.AddDataSource("DataSet1", partidak);


            var result = localReport.Execute(RenderType.Pdf, extension, parameters, mimtype);
            return File(result.MainStream, "application/pdf");
        }

        /// <summary>
        /// Jolastutako 10 partida onenen txostena inprimatzen du.
        /// </summary>
        /// <returns></returns>
        public async Task<IActionResult> onenakInprimatu()
        {
            Encoding.RegisterProvider(CodePagesEncodingProvider.Instance);
            string mimtype = "";
            int extension = 1;
            var path = $"{_webHostEnvironment.WebRootPath}\\Reports\\Report1.rdlc";
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            LocalReport localReport = new LocalReport(path);

            var partidak = await _partidaService.GetPartidaOnenenTxostena();
            parameters.Add("rp1", " ONENAK");
            localReport.AddDataSource("DataSet1", partidak);
            

            var result = localReport.Execute(RenderType.Pdf, extension, parameters, mimtype);
            return File(result.MainStream, "application/pdf");
        }
    }
}
