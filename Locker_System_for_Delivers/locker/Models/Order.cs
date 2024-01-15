using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Web;

namespace locker.Models
{
    [Table("Order")]
    public class Order : EntityTypeConfiguration<Order>
    {
        [Key]
        public int OId { get; set; }
        public int SId { get; set; }
        public int GId { get; set; }
        public int CId { get; set; }
        public string BId { get; set; }
        public DateTime Date { get; set; }
        public int GNum { get; set; }
        public float GPrice_All { get; set; }
    }
}