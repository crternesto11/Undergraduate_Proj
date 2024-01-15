using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using uAccess;
using locker.Models;
using Newtonsoft.Json;

namespace locker.Services
{
    public class MyLogin
    {
        public static JObject adlogin(int ad_id, string ad_password)
        {
            var op = new uAccess<Admin>("dbConnect");
            var pr = op.GetFirst(c => c.AdminId == ad_id );


            if (pr == null)
            {
                JObject ad_state_null = new JObject
                {
                {"ad_state",0}
                };
                return ad_state_null;
            }


            var state = 0;
            if (pr.AdminPwd == ad_password)
            {
                state = 1;
            }

            JObject ad_state = new JObject
            {
                {"ad_state",state}
            };
            var b = ad_state;
            return ad_state;
        }
    }
}