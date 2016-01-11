package cn.com.loushui.mylibrary.util;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

public class FileUtil {
    public FileUtil() {
    }

    public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;

        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            FileChannel e = fi.getChannel();
            FileChannel out = fo.getChannel();
            e.transferTo(0L, e.size(), out);
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            try {
                if (fo != null) {
                    fo.close();
                }

                if (fi != null) {
                    fi.close();
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

    }

    public static String formatFileSizeToString(long fileLen) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileLen < 1024L) {
            fileSizeString = df.format((double) fileLen) + "B";
        } else if (fileLen < 1048576L) {
            fileSizeString = df.format((double) fileLen / 1024.0D) + "K";
        } else if (fileLen < 1073741824L) {
            fileSizeString = df.format((double) fileLen / 1048576.0D) + "M";
        } else {
            fileSizeString = df.format((double) fileLen / 1.073741824E9D) + "G";
        }

        return fileSizeString;
    }

    public static boolean deleteFile(File file) throws IOException {
        return file != null && file.delete();
    }

    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }

        return filename;
    }
}
