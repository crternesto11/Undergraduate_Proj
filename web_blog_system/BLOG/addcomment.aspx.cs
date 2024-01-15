using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace postpost
{
    public partial class addcomment : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn = new SqlConnection(@"server =DESKTOP-TL2I65F\SQLEXPRESS; user id = sa;password =xxbbyy1101;database=forum");
            conn.Open();
            SqlDataAdapter adpt = new SqlDataAdapter("select reply_id,post_id,replyer_id,reply_content,post_title from reply", conn);

            DataTable table = new DataTable();
            int v = adpt.Fill(table);
            StringBuilder sbJson = new StringBuilder();
            sbJson.Append("[");
            for (int i = 0; i < table.Rows.Count; i++)
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
                if (i < table.Rows.Count - 1)
                {
                    sbJson.Append(",");
                }
            }
            sbJson.Append("]");
            Response.Write(sbJson);
            Response.End();
            //var post_title = dr["post_title"].ToString();
            //var post_content = dr["post_content"].ToString();
        }
    }
}