using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebBja.Models
{
    public class Comentario
    {
        [Key]
        public int Id { get; set; }

        public string Username { get; set; }

        public string Texto { get; set; }

        public DateTime Fecha{ get; set; }

    }
}
