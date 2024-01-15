using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Text.RegularExpressions;

namespace postpost
{
    public partial class post : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn = new SqlConnection(@"server =DESKTOP-TL2I65F\SQLEXPRESS; user id = sa;password =xxbbyy1101;database=forum");

            conn.Open();
            var title = Request.Form["Title"];
            var content = Request.Form["Content"];
            var login2 = Request.Form["Login2"];
            //var time = DateTime.Now.ToString("yyyy.MM.dd hh:mm:ss");
            String str = title + content;
            string[] arr = new string[] { "靠", "tmd", "nmd", "妈的 ", "狗屎" };

            if (str.Length == 0)
            {
                Response.Write("<script>alert('请登录！')</script>");
                return;
            }

            foreach (var i in arr)
            {
                if (str.Contains(i))
                {
                    Response.Write("<script>alert('你发布的帖子存在违规词，请修改后再发布！')</script>");
                    return;
                }
            }

            var sql = "INSERT INTO post(post_title,post_content,poster_id) VALUES ('" + title + "','" + content +"','"+ login2 + "')";
            //Response.Write(sql);
            Response.Write("<br/>");

            SqlCommand cmdSelect = new SqlCommand(sql, conn);
            cmdSelect.ExecuteNonQuery();

            Response.Write("<script>alert('发布成功！按确定返回主页！')</script>");
            Response.Write("<meta http-equiv='refresh' content='0; index.html'charset='utf-8' />");
            conn.Close();
            Response.End();
        }
    }
}