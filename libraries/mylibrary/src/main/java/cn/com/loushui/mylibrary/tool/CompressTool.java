package cn.com.loushui.mylibrary.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import net.bither.util.NativeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/12/22.
 */
public class CompressTool {

    public static void compress(InputStream in, int quality, String path) throws IOException {
        Bitmap bit = BitmapFactory.decodeStream(in);
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File originalFile = new File(dirFile, "original.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(
                originalFile);
        bit.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
        File jpegTrueFile = new File(dirFile, "jpegtrue.jpg");
        File jpegFalseFile = new File(dirFile, "jpegfalse.jpg");
        NativeUtil.compressBitmap(bit, quality,
                jpegTrueFile.getAbsolutePath(), true);
        NativeUtil.compressBitmap(bit, quality,
                jpegFalseFile.getAbsolutePath(), false);
    }
}
