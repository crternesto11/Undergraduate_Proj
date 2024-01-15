using locker.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using uAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace locker
{
    public class AdminAdmin
    {
        //显示所有用户
        public static JArray AdminsList()
        {
            JArray admins = new JArray();
            var op = new uAccess<Admin>("dbConnect").GetAll(); //当前op为列表形式
            /*
             JSON序列化:string JsonStr= JsonConvert.SerializeObject(Entity);（对象转化为字节流）
            JSON反序列化:Class model = JsonConvert.DeserializeObject<Class>(jsonstr);
             */
            var admins_str = JsonConvert.SerializeObject(op);
            /*
             JArray将字符串转化为数组
            JObject将字符串转化为对象
             */
            admins = JArray.Parse(admins_str);

            return admins;
        }

        //根据等级筛选部分用户
        public static JArray GetByRank(int Level)
        {
            JArray admins = new JArray();
            var op = new uAccess<Admin>("dbConnect");
            var admins_str = "";
            if (Level == 666)
            {
                admins_str = JsonConvert.SerializeObject(op.GetAll());
            }
            else
            {
                admins_str = JsonConvert.SerializeObject(op.GetAll(c => c.AdminLevel == Level));
            }
            admins = JArray.Parse(admins_str);
            return admins;
        }

        //模糊查询
        public static JArray FuzzySearch(String SearchText)
        {
            JArray customers = new JArray();
            var op = new uAccess<Admin>("dbConnect"); //数据库连接对象
            var customers_str = JsonConvert.SerializeObject(op.GetAll(c => c.AdminName.Contains(SearchText)));
            customers = JArray.Parse(customers_str);
            return customers;
        }
    }
}