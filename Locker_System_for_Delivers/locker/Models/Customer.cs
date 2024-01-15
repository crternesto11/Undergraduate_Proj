using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Web;

namespace locker.Models
{
    [Table("Customer")]
    public class Customer : EntityTypeConfiguration<Customer>
    {
        [Key]
        public int CId { set; get; }
        public string CName{ set; get; }
        public string CMail{ set; get; }
    }
}