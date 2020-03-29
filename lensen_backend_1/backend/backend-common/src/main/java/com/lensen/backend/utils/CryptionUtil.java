package com.lensen.backend.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 自定义加解密算法
 *
 * @author
 */
public class CryptionUtil {
    /**
     * MD5加密密码
     *
     * @return
     */
    public static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 参数排序签名
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String getSignData(Map<String, Object> params) {
        StringBuffer content = new StringBuffer();
        // 按照key做排序
        try {
            List<String> keys = new ArrayList<String>(params.keySet());
            Collections.sort(keys);

            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                System.out.println(keys);

                String valueStr = (String) params.get(key);

                // 具体逻辑修改下面的路径进行拼接
                content.append(valueStr);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return md5(content.toString());
    }
}




