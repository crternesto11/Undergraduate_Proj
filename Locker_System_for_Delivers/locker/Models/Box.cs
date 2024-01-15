using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Web;

namespace locker.Models
{
    [Table("Box")]
    public class Box:EntityTypeConfiguration<Box>
    {
        [Key]
        public string BId { set; get; }
        public int State { set; get; }
        public int? OId { set; get; }
    }
}