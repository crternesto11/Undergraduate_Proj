using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace postpost
{
    public partial class SqlHelper : System.Web.UI.Page
    {
        public string ConnectionString { get; set; } = @"server =DESKTOP-TL2I65F\SQLEXPRESS; user id = sa;password =xxbbyy1101;database=forum";
        public DataTable ExecuteTable(string cmdText, params SqlParameter[] sqlParameters)
        {
            SqlConnection conn = new SqlConnection(ConnectionString);
            conn.Open();
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            cmd.Parameters.AddRange(sqlParameters);
            SqlDataAdapter sda = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            sda.Fill(ds);
            return ds.Tables[0];
        }

        //internal DataTable ExecuteTable(string v)
        //{
        //    throw new NotImplementedException();
        //}

        public int ExecuteNonQuery(string cmdText, params SqlParameter[] sqlParameters)
        {

            SqlConnection conn = new SqlConnection(ConnectionString);
            conn.Open();
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            cmd.Parameters.AddRange(sqlParameters);
            return cmd.ExecuteNonQuery();
        }
    }
}