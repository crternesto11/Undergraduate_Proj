using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Web;

namespace locker.Models
{
    [Table("Goods")]
    public class Goods : EntityTypeConfiguration<Goods>
    {
        [Key]
        public int GId { get; set; }
        public string GName { get; set; }
        public float GPrice { get; set; }
    }
}