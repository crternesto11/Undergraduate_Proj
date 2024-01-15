using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web
{
    public partial class about : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            var Name = Request.Form["Name"];
            var Password = Request.Form["Password"];
            if (Name != null && Password != null)
            {
                string sql = "select count(0) from users where users_id=" + Name + " and user_password='" + Password + "' and user_state=0";
                string sql1 = "select count(0) from users where users_id=" + Name;
                string sql2 = "select count(0) from users where users_id=" + Name + " and user_password='" + Password + "'";
                string sql3 = "select count(0) from users where users_id=" + Name + " and user_password='" + Password + "' and user_state='True'";
                SqlConnection conn = new SqlConnection("server=DESKTOP-TL2I65F\\SQLEXPRESS;user id=sa;password=xxbbyy1101;Database=forum");
                conn.Open();

                SqlCommand cmd1 = new SqlCommand(sql1, conn);
                object obj1 = cmd1.ExecuteScalar();
                int n1 = (int)obj1;
                if (n1 <= 0)
                {
                    conn.Close();
                    Response.Write("此用户不存在！");
                    Response.End();
                }
                else
                {
                    SqlCommand cmd2 = new SqlCommand(sql2, conn);
                    object obj2 = cmd2.ExecuteScalar();
                    int n2 = (int)obj2;
                    if (n2 <= 0)
                    {
                        conn.Close();
                        Response.Write("您的密码错误！");
                        Response.End();
                    }
                    else
                    {
                        SqlCommand cmd3 = new SqlCommand(sql3, conn);
                        object obj3 = cmd3.ExecuteScalar();
                        int n3 = (int)obj3;
                        if (n3 > 0)
                        {
                            conn.Close();
                            Response.Write("您已登录，不可重复登录！");
                            Response.End();
                        }
                        else
                        {
                            SqlCommand cmd = new SqlCommand(sql, conn);
                            object obj = cmd.ExecuteScalar();
                            int i = (int)obj;
                            if (i <= 0)
                            {
                                conn.Close();
                                Response.Redirect("请求超时，登录失败，请返回再试");
                                Response.End();
                            }
                            else
                            {
                                conn.Close();
                                Response.Write("登录成功！" + Name);
                                Response.End();

                            }
                        }
                    }
                }
                conn.Close();
            }
            else
            {
                return;
            }

        }

    }
}