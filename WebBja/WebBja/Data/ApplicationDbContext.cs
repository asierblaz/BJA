using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using WebBja.Models;

namespace WebBja.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<WebBja.Models.Formulario> Formulario { get; set; }
        public DbSet<WebBja.Models.Comentario> Comentario { get; set; }
      
    }
}
