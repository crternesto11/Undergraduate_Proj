import dao.BaseDao;
import dao.UserDao;
import po.Note;
import po.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static dao.BaseDao.queryRows;

public class Testupdate {

    @Test
    public void testquser(){
        UserDao userDao=new UserDao();
        User user=new User();
        //user=userDao.queryUserByName("admin");

        System.out.println(user);
    }

    @Test
    public void queryNotesByName() {
        // 1. 定义sql语句
        String sql = "select * from tb_note where userId = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        List list=new ArrayList();
        ArrayList<Note> NoteList=new ArrayList<>();
        params.add(124);

        // 3. 调用BaseDao的查询方法
       // list = queryRows(sql, params, Note.class);

        //4.转换为note
        for (int i=0;i< list.size();i++){
            NoteList.add((Note) list.get(i));
        }


    }

    @Test
    public void testqueryRow() {
        String sql = "select * from tb_user where uname = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add("admin");
        List list =null;// queryRows(sql, params, User.class);
        Object object = null;
        // 如果集合不为空，则获取查询的第一条数据
        if (list != null && list.size() > 0) {
            object = list.get(0);
        }

        System.out.println(list);
    }
@Test
    public void InsertIntoNotes(){
        BaseDao baseDao=new BaseDao();
        String sql="insert into tb_note (title,content,userId) values (?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add("back");
        params.add("love you, my baby");
        params.add(124);
        int row = BaseDao.executeUpdate(sql,params);
        System.out.println(row);
    }

}
