using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using locker.Services;

namespace WebApi.Controllers
{
    [RoutePrefix("api/login")]
    public class loginController : ApiController
    {
        [Route("login"), HttpGet]
        public JObject Login()
        {
            var req = HttpContext.Current.Request;
            var a = req["ad_id"];
            var b = req["ad_password"];
            var c = int.Parse(req["ad_id"]);
            return MyLogin.adlogin(int.Parse(req["ad_id"]), req["ad_password"]);
            //var a=MyLogin.adlogin(int.Parse("1"),"123");
            //return a;

        }

    }
}