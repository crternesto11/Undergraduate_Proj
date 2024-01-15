package dao;

import po.Note;
import po.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /**
     * 通过用户名查询用户对象
     *  1. 定义sql语句
     *  2. 设置参数集合
     *  3. 调用BaseDao的查询方法
     * @param userName
     * @return
     */
    public User queryUserByName(String userName) {
        // 1. 定义sql语句
        String sql = "select * from users where u_name = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(userName);

        // 3. 调用BaseDao的查询方法
        User user = (User) BaseDao.queryRow(sql, params, User.class);

        return user;
    }

    public User queryUserById(int id) {
        // 1. 定义sql语句
        String sql = "select * from users where u_id = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(id);

        // 3. 调用BaseDao的查询方法
        User user = (User) BaseDao.queryRow(sql, params, User.class);

        return user;
    }

    /**
     通过用户名查询用户对象， 返回用户对象
     1. 获取数据库连接
     2. 定义sql语句
     3. 预编译
     4. 设置参数
     5. 执行查询，返回结果集
     6. 判断并分析结果集
     7. 关闭资源
     * @param userName
     * @return
     */
    public User queryUserByName02(String userName) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 获取数据库连接
            connection = DBUtil.getConnetion();
            // 2. 定义sql语句
            String sql = "select * from users where u_name = ?";
            // 3. 预编译
            preparedStatement = connection.prepareStatement(sql);
            // 4. 设置参数
            preparedStatement.setString(1, userName);
            // 5. 执行查询，返回结果集
            resultSet = preparedStatement.executeQuery();
            // 6. 判断并分析结果集
            if (resultSet.next()) {
                user = new User();
                user.setU_id(resultSet.getInt("u_id"));
                user.setU_name(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭资源
            DBUtil.close(resultSet,preparedStatement,connection);
        }

        return user;
    }


    public ArrayList<Note> queryNotesById(int userId) {
        // 1. 定义sql语句
        BaseDao baseDao=new BaseDao();
        // 1. 定义sql语句
        String sql = "select * from tb_note where u_id = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        List list=new ArrayList();
        ArrayList<Note> NoteList=new ArrayList<>();
        params.add(userId);

        // 3. 调用BaseDao的查询方法
        list = baseDao.queryRows(sql, params, Note.class);

        //4.转换为note
        for (int i=0;i< list.size();i++){
            NoteList.add((Note) list.get(i));
        }

        return NoteList;
    }

    public int InsertIntoNotes(String title,String Content,int userId){
        BaseDao baseDao=new BaseDao();
        String sql="insert into tb_note (title,content,u_id) values (?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(title);
        params.add(Content);
        params.add(userId);
        int row = BaseDao.executeUpdate(sql,params);
        return row;
    }

}
