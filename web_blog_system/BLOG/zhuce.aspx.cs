using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web
{
    public partial class zhuce : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn = new SqlConnection("server=DESKTOP-TL2I65F\\SQLEXPRESS;user id=sa;password=xxbbyy1101;Database=forum");
            conn.Open();
            var Name = Request.Form["Name"];
            var Password = Request.Form["Password"];

            var sql = "INSERT INTO users(user_name,user_password,user_state) VALUES ('" + Name + "','" + Password + "'," + 0 + ")";
            SqlCommand cmdSelect = new SqlCommand(sql, conn);
            cmdSelect.ExecuteNonQuery();

            string sql1 = "select users_id from users where user_name='" + Name + "' and user_password='" + Password + "' and user_state=0";
            SqlCommand cmd1 = new SqlCommand(sql1, conn);
            object obj1 = cmd1.ExecuteScalar();
            conn.Close();
            Response.Write("注册成功！您的id为：" + obj1);
            Response.End();
        }
    }
}