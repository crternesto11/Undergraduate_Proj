package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties properties = new Properties();

    static {
        try {
            // 加载配置文件（输入流）
            // 通过配置文件对象的getProperty()方法获取驱动名，并加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnetion() {
        Connection connection = null;
        try {
            // 得到数据库连接的相关信息
            String dbUrl = "jdbc:mysql://localhost:3306/oldqq?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
            String dbName = "root";
            String dbPwd = "123456";
            // 得到数据库连接
            connection = DriverManager.getConnection(dbUrl, dbName,dbPwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }


    /**
     * 关闭资源
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public static void close(ResultSet resultSet,
                             PreparedStatement preparedStatement,
                             Connection connection) {

        try {
            // 判断资源对象如果不为空，则关闭
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
