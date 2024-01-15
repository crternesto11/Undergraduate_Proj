using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web
{
    public partial class comment : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn2 = new SqlConnection(@"server =DESKTOP-TL2I65F\SQLEXPRESS; user id = sa;password =xxbbyy1101;database=forum");
            conn2.Open();
            SqlDataAdapter adpt = new SqlDataAdapter("select reply.reply_id,reply.replyer_id,reply.reply_content,reply.post_id from reply ,users where reply.replyer_id=users.users_id", conn2);
            DataTable table = new DataTable();
            int v = adpt.Fill(table);
            StringBuilder sbJson = new StringBuilder();
            sbJson.Append("[");
            for (int i = table.Rows.Count-1; i >= 0; i--)
            {
                sbJson.Append("{");
                for (int j = 0; j < table.Columns.Count; j++)
                {
                    string colName = table.Columns[j].ColumnName.ToString();
                    string colData = table.Rows[i][j].ToString();
                    sbJson.Append("\"" + colName + "\":\"" + colData + "\"");
                    if (j < table.Columns.Count - 1)
                    {
                        sbJson.Append(",");
                    }
                }
                sbJson.Append("}");
                if (i > 0)
                {
                    sbJson.Append(",");
                }
            }


            sbJson.Append("]");
            Response.Write(sbJson);
            Response.End();
        }
    }
}