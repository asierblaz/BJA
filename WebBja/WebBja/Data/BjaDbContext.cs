using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using WebBja.Models;

namespace WebBja.Data
{
    public class BjaDbContext : IdentityDbContext
    {
        public BjaDbContext(DbContextOptions<BjaDbContext> options)
            : base(options)
        {
        }
        public DbSet<Formulario> Formulario { get; set; }
        public DbSet<Comentario> Comentario { get; set; }


    }
}
