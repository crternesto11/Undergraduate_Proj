using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;

namespace locker.Controllers
{
    /*
 在类内写 [Route("api/admin/customer/customerList")]，则在前端访问时地址为http://localhost:6568/api/admin/customer/customerList
但这样里面方法每次写的时候都要加上http://localhost:6568/api/admin/customer/，过于麻烦
因此，我们用到RoutePrefix，我们在类最外层加上[RoutePrefix("api/admin/customer")]
在类内只需要[Route("customerList"), HttpGet]
 */

    [RoutePrefix("api/admins")]
    public class AdminController : ApiController
    {

        //显示所有用户
        [Route("adminsList"), HttpGet]
        public JArray AdminsList()
        {
            return AdminAdmin.AdminsList();
        }

        //根据用户等级获取用户信息
        [Route("adminsByRank"), HttpGet]
        public JArray AdminsByRank()
        {
            var req = HttpContext.Current.Request;
            return AdminAdmin.GetByRank(int.Parse(req["selectRank"]));
        }

        //模糊查询
        [Route("adminFuzzy"), HttpGet]
        public JArray CustomerFuzzy()
        {
            var req = HttpContext.Current.Request;
            
            //return AdminAdmin.FuzzySearch("五");
            return AdminAdmin.FuzzySearch(req["searchText"]);
        }

        ////根据用户id获取用户信息
        //[Route("customerInfo"), HttpPost]
        //public JObject CustomerInfo()
        //{
        //    var req = HttpContext.Current.Request;
        //    return AdminUser.GetUser(req["C_Id"]);
        //}





        //public IEnumerable<Customers> GetByRank(int Grade)
        //{
        //    //按会员等级筛选返回
        //    List<Customers> CusList = new List<Customers>();
        //    return op.GetAll(c => c.C_Rank == Grade);
        //}

        //public String test()
        //{
        //    var req = HttpContext.Current.Request;
        //    return req["C_Id"];
        //}

        //[Route("list"), HttpGet]
        //public List<Customers> List()
        //{
        //    return AdminUser.list();
        //}

        //[Route("customerslist"), HttpGet]
        //public string Test()
        //{
        //    return "ok";
        //}


        //[Route("list"), HttpGet]
        //public JArray list()
        //{
        //    return AdminUser.list();
        //}

        //[Route("inventorylist"), HttpGet]
        //public JArray InventoryList()
        //{
        //    return MyGoods.InventoryList();
        //}


        //private Services<Customers> op = new Services<Customers>("dbConnect");
        //[HttpGet]
        //public IEnumerable<Customers> Get()
        //{
        //    //遍历所有的用户并返回，以IEnumerable<Customers>的格式返回
        //    return op.GetAll().AsEnumerable();
        //}

        //[HttpGet]
        //public IEnumerable<Customers> GetByRank(int Grade)
        //{
        //    //按会员等级筛选返回
        //    List<Customers> CusList = new List<Customers>();
        //    return op.GetAll(c => c.C_Rank == Grade);
        //}
    }
}