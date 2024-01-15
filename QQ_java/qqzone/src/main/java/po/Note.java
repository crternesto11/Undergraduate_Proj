package po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Note {

    private int noteID; // 云记ID
    private String title; // 云记标题
    private String content; // 云记内容
    private int u_id;//用户id


}
