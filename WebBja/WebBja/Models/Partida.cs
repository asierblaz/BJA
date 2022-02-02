﻿using System;
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
    public class Jokalaria
    {
        public string Dni { get; set; }
        public string Name { get; set; }
        public int Saldo { get; set; }

    }


}
