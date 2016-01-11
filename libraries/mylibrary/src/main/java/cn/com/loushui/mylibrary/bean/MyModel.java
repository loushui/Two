package cn.com.loushui.mylibrary.bean;

import com.litesuits.orm.db.annotation.Check;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * 所有Model的顶层父类
 *
 * @author NapoleonBai
 */
public class MyModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 设置为主键,自增
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    public int _id;

    // @Check条件检测
    @Check("_description NOT NULL")
    public String _description = "字段描述";


}
