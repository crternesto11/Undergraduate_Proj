using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Web;

namespace locker.Models
{
    [Table("Shop")]
    public class Shop : EntityTypeConfiguration<Shop>
    {
        [Key]
        public int SId { get; set; }
        public string SName { get; set; }
        public string SMail { get; set; }
    }
}