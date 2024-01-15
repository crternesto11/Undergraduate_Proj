import dao.BaseDao;
import dao.UserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import po.Note;
import po.User;
import util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 乐字节：专注线上IT培训
 * 需要视频资料，请添加：lezijie007
 */
public class TestDB {

        @Test
        public void testDB() {
            System.out.println(DBUtil.getConnetion());
            // 使用日志
            //logger.info("获取数据库连接：{}", DBUtil.getConnetion());
        }

        @Test
        public void testquser(){
            UserDao userDao=new UserDao();
            User user=new User();
            user=userDao.queryUserByName("胡图图");

            System.out.println(user.getU_id());
        }
        @Test
        public void queryNotesByName() {
            BaseDao baseDao=new BaseDao();
            // 1. 定义sql语句
            String sql = "select * from tb_note where u_id = ?";

            // 2. 设置参数集合
            List<Object> params = new ArrayList<>();
            List list=new ArrayList();
            ArrayList<Note> NoteList=new ArrayList<>();
            params.add(1);

            // 3. 调用BaseDao的查询方法
            list = baseDao.queryRows(sql, params, Note.class);

            //4.转换为note
            for (int i=0;i< list.size();i++){
                NoteList.add((Note) list.get(i));
            }
            System.out.println(NoteList.get(1).getContent());


        }

        @Test
        public void queryUserById() {
        // 1. 定义sql语句
        String sql = "select * from users where u_id = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(1);

        // 3. 调用BaseDao的查询方法
        User user = (User) BaseDao.queryRow(sql, params, User.class);

        System.out.println(user.getU_name());
    }
    }



