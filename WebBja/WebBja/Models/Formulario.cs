using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace WebBja.Models
{
    public class Formulario
    {
        [Key]
        public int Id { get; set; }

        public string Username { get; set; } 
        [Required]
        [Display (Name ="Puntuazioa")]
        public int Puntuacion { get; set; }

        [Required]
        [Display(Name = "Zenbat ordu jolastu dituzu gure jokura?:")]
        public string Horas { get; set; }

        [Required]
        [Display(Name = "Zer hobetuko zenuke?:")]
        public string Mejoras { get; set; }

    }
}
