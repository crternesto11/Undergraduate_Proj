using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Text.RegularExpressions;
using System.Configuration;

namespace Web
{
    public partial class comment2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn = new SqlConnection(@"server =DESKTOP-TL2I65F\SQLEXPRESS; user id = sa;password =xxbbyy1101;database=forum");
            conn.Open();
            var reply = Request.Form["Comment"];
            var login4 = Request.Form["Login4"];
            var ider= Request.Form["ider"];
            var titler = Request.Form["titler"];

            //var time = DateTime.Now.ToString("yyyy.MM.dd hh:mm:ss");

            if (reply.Length == 0) 
            {
                Response.Write("<script>alert('请登录！')</script>");
                return;
            }

            string[] arr1 = new string[] { "靠", "tmd", "nmd", "妈的 ", "狗屎" };
            foreach (var i in arr1)
            {
                if (reply.Contains(i))
                {
                    Response.Write("<script>alert('你发表的评论存在违规词，请修改后再发表！')</script>");
                    return;
                }
            }

            var sql = "INSERT INTO reply(reply_content,replyer_id,post_id,post_title) VALUES ('" + reply + "','" + login4 + "','" + ider + "','" + titler + "')";
            //Response.Write(sql);
            Response.Write("<br/>");

            SqlCommand cmdSelect = new SqlCommand(sql, conn);
            cmdSelect.ExecuteNonQuery();

            ////创建SqlDataAdapter 对象，执行SQL语句
            //SqlDataAdapter adpt = new SqlDataAdapter("select * from reply ", conn); //将服务器数据库内的数据储存到该对象内
            ////用SqlDataAdapter 填充数据集
            //DataSet ds1 = new DataSet();  //新建了一个数据集叫ds1
            //adpt.Fill(ds1, " 1"); //“1”是一个映射标志，代表加入DataSet对象中的表，可以任意指定，便于引用。

            //String str="";
            //foreach (DataRow dr1 in ds1.Tables[" 1"].Rows)
            //{
            //    if(dr1["reply_content"].ToString()==reply)
            //    str += "id = " + dr1["replyer_id"].ToString() + "time = "+dr1["reply_time"].ToString()+"reply = " + dr1["reply_comment"].ToString();
            //}
            //Response.Write(str);

            Response.Write("<script>alert('发表成功!')</script>");
            Response.Write("<meta http-equiv='refresh' content='0; single.html'charset='utf-8' />");
            conn.Close();
            Response.End();
        }
    }
}