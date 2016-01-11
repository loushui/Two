package cn.com.loushui.mylibrary.tool;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LiteOrmTool {

    protected static LiteOrm liteOrm;


    protected LiteOrm getLiteOrm() {
        return liteOrm;
    }

    public static void init(Context context, String dbName) {
        liteOrm = LiteOrm.newSingleInstance(context, dbName);
    }

}
