package com.lensen.backend.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BaseUtils {
    public static final String PARAMNAME = "URLPARAMID";

    public static int stringToInt(String str, int defaultValue) {
        if (str == null)
            return defaultValue;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {

        }
        return defaultValue;
    }

    public static String underscoreName(String name) {
        StringBuffer result = new StringBuffer();
        if (name != null && name.length() > 0) {
            result.append(name.substring(0, 1).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals(s.toUpperCase())) {
                    result.append("_");
                    result.append(s.toLowerCase());
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

    public static String decodeUnderscoreName(String name) {
        StringBuffer result = new StringBuffer();
        boolean up = false;
        if (name != null && name.length() > 0) {
            name = name.toLowerCase();
            for (int i = 0; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals("_")) {
                    up = true;
                    continue;
                }
                if (up) {
                    result.append(s.toUpperCase());
                    up = false;
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

    public static boolean stringToBoolean(String string) {
        string = StringUtils.trimToNull(string);
        if (string == null) {
            return false;
        }
        string = string.toLowerCase();
        if (string.startsWith("t") || string.startsWith("1") || string.startsWith("y") || string.startsWith("o")) {
            return true;
        }
        return false;
    }


    /**
     * <p>将文件转成base64 字符串</p>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return StringUtils.EMPTY;
        }
        File file = new File(path);
        if (!file.exists()) {
            return StringUtils.EMPTY;
        }
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     * <p>将base64字符解码保存文件</p>
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 将base64字符串专程字节码
     *
     * @param base64Code
     * @return
     * @throws Exception
     */
    public static byte[] decoderBase64File(String base64Code) throws Exception {
        if (StringUtils.isEmpty(base64Code)) {
            return null;
        }
        return new BASE64Decoder().decodeBuffer(base64Code);
    }

    /**
     * <p>将base64字符保存文本文件</p>
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
}
