package po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int u_id;//主键
    private String u_name;//姓名
    private String u_pwd;//密码
    private String u_ip;//IP地址
    private String u_state;//状态，-1或空表示不在线，0表示在线，1表示Q我，2表示离开，3表示忙碌，4表示隐身
    private String u_gender;//性别
    private String u_email;//邮箱
    private String u_last_login;//最后一次登录
    private String u_last_exit;//最后一次退出
    private String u_remarke;//备用
    private String u_signature;//个性签名
    private String u_head_img;//头像
    private String u_type;//类型
    private String u_birthday;//生日


}
