package com.lensen.backend.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("restriction")
public class Encrypt {

    /**
     * md5加密
     *
     * @param input
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String MD5(String input) {
        return MD5(input, Charset.defaultCharset());
    }

    /**
     * md5加密
     *
     * @param input
     * @param charset
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String MD5(String input, String charset) {
        return MD5(input, Charset.forName(charset));
    }

    /**
     * md5加密
     *
     * @param input
     * @param charset
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String MD5(String input, Charset charset) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException arg8) {
            arg8.printStackTrace();
        }
        md.update(input.getBytes(charset));
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f'};
        byte[] tmp = md.digest();
        char[] str = new char[32];
        int k = 0;
        for (int result = 0; result < 16; ++result) {
            byte byte0 = tmp[result];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }
        String arg9 = new String(str);
        return arg9;
    }

    /**
     * encryptSES加密
     *
     * @param input
     * @param key
     * @return
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String encryptSES(String input, String key) throws Exception {
        return encryptSES(input, key, Charset.forName("GB2312"));
    }

    /**
     * encryptSES加密
     *
     * @param input
     * @param key
     * @param charset
     * @return
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String encryptSES(String input, String key, Charset charset) throws Exception {
        Ses ses = new Ses(key);
        byte[] byte_input = input.getBytes(charset);
        int len = ses.getEncryptResultLength(byte_input);
        byte[] output = new byte[len];
        ses.encrypt(byte_input, output);
        return (new BASE64Encoder()).encode(output);
    }

    /**
     * encryptSES加密
     *
     * @param input
     * @param key
     * @return
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String decryptSES(String input, String key) throws Exception {
        return decryptSES(input, key, Charset.forName("GB2312"));
    }

    /**
     * decryptSES加密
     *
     * @param input
     * @param key
     * @param charset
     * @return
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String decryptSES(String input, String key, Charset charset) throws Exception {
        Ses ses = new Ses(key);
        byte[] byte_input = (new BASE64Decoder()).decodeBuffer(input);
        byte[] temp_output = new byte[input.length()];
        int output_len = ses.decrypt(byte_input, byte_input.length, temp_output);
        byte[] ouput = new byte[output_len];
        System.arraycopy(temp_output, 0, ouput, 0, output_len);
        return new String(ouput, charset);
    }

    /**
     * encrypt3DES处理
     *
     * @param input
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String encrypt3DES(String input, String key) {
        return encrypt3DES(input, key, Charset.forName("GB2312"));
    }

    /**
     * encrypt3DES处理
     *
     * @param input
     * @param key
     * @param charset
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String encrypt3DES(String input, String key, Charset charset) {
        try {
            return Byte.byte2hex(Des.encrypt(input.getBytes(charset.name()), key.getBytes()));
        } catch (Exception arg3) {
            return "";
        }
    }

    /**
     * decrypt3DES处理
     *
     * @param input
     * @param key
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String decrypt3DES(String input, String key) {
        return decrypt3DES(input, key, Charset.forName("GB2312"));
    }

    /**
     * decrypt3DES处理
     *
     * @param input
     * @param key
     * @param charset
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String decrypt3DES(String input, String key, Charset charset) {
        try {
            return new String(Des.decrypt(Byte.hex2byte(input.getBytes()), key.getBytes()), charset.name());
        } catch (Exception arg3) {
            return "";
        }
    }
}
