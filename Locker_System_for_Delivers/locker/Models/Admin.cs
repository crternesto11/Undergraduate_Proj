using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Web;

namespace locker.Models
{
    [Table("Admin")]
    public class Admin:EntityTypeConfiguration<Admin>
    {
        [Key]
        public int AdminId { get; set; }
        public string AdminName { get; set; }
        public string AdminPwd { get; set; }
        public int AdminLevel { get; set; }
    }
}