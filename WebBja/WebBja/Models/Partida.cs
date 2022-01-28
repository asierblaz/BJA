using System;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebBja.Models
{
    public class Partida
    {
        public int Puntuazioa { get; set; }
        public string Fecha { get; set; }
        public Jokalaria Jokalaria { get; set; }
    }


}
