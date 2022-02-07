using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebBja.Models
{
    /// <summary>
    /// Comentario klasea definitzen da.
    /// </summary>
    public class Comentario
    {
        [Key]
        public int Id { get; set; }
        
        public string Username { get; set; }
        [Required]
        public string Tema { get; set; }
        [Required]
        public string Texto { get; set; }

        public DateTime Fecha{ get; set; }

    }
}
