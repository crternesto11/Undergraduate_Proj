using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
//using System.Net.Http;
//using System.Web.Http;

namespace postpost
{
    public class Get
    {

        public class login
        {
            //[HttpGet]
            public string Get()
            {
                SqlHelper sqlHelper = new SqlHelper();
                DataRow dr = null;
                DataTable res = sqlHelper.ExecuteTable("SELECT * FROM post");
                if (res.Rows.Count > 0)
                {
                    dr = res.Rows[0];
                }
                var resUserNo = dr["post_content"].ToString();
                //var resPassword = dr["Password"].ToString();
                //if (resUserNo == userNo && resPassword == password)
                //{
                //    return "登录成功！";
                //}
                //else
                //{
                //    return "用户名或密码错误";
                //}
                return resUserNo;
            }
            //[HttpPost]
            ////public string Insert(string userNo, string userName, int userLevel, string password)
            //{
            //    SqlConnection conn = new SqlConnection("server=.;database=MyBBSDb;uid=sa;pwd=1qaz2wsx");
            //    conn.Open();
            //    SqlCommand cmd = new SqlCommand($"INSERT INTO Users(UserNo,UserName,UserLevel,Password) VALUES('{userNo}','{userName}',{userLevel},'{password}')", conn);
            //    cmd.ExecuteNonQuery();
            //    return "B站：全栈ACE";
            //}
            //[HttpPut]
            //public string Update()
            //{
            //    SqlConnection conn = new SqlConnection("server=.;database=MyBBSDb;uid=sa;pwd=1qaz2wsx");
            //    conn.Open();
            //    SqlCommand cmd = new SqlCommand($"UPDATE Users Set Password = '333' WHERE Id = 1", conn);
            //    cmd.ExecuteNonQuery();
            //    return "B站：全栈ACE";
            //}
            //[HttpDelete]
            //public string Remove(string userNo, string userName)
            //{
            //    SqlConnection conn = new SqlConnection("server=.;database=MyBBSDb;uid=sa;pwd=1qaz2wsx");
            //    conn.Open();
            //    SqlCommand cmd = new SqlCommand($"DELETE FROM Users WHERE UserNo = @UserNo AND UserName = @UserName", conn);
            //    SqlParameter[] sqlParameters = new SqlParameter[] {
            //new SqlParameter("@UserNo",userNo),
            //new SqlParameter("@UserName",userName)
            //};
            //    cmd.Parameters.AddRange(sqlParameters);

            //    cmd.ExecuteNonQuery();
            //    return "B站：全栈ACE";
            //}
        }
    }
}