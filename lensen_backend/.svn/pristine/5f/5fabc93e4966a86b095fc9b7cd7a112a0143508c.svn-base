package com.lensen.backend.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class HttpClient {
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String REQUEST_METHOD_GET = "GET";

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    private HttpURLConnection connection;
    private String paramStr;
    private boolean error;

    public HttpClient(String urlStr, String method) {
        this(urlStr, method, 30 * 1000);
    }

    /**
     * @param urlPath 路径
     * @return
     * @throws IOException
     */
    public static String urlRequest(String urlPath, String encoding) throws IOException {
        URL url;
        HttpURLConnection conn = null;
        BufferedReader br = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(40 * 1000);
            conn.setReadTimeout(40 * 1000);
            conn.connect();
            InputStream in = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, encoding));
            String strLine = br.readLine();
            List<String> list = new ArrayList<String>();

            while (StringUtils.isNotBlank(strLine)) {
                list.add(strLine);
                strLine = br.readLine();
            }

            return StringUtils.join(list, "");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LOGGER.error("请求超时=" + e);
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (br != null) {
                br.close();
            }
        }
    }

    /**
     * @param urlStr
     * @param method
     * @param timerout
     */
    public HttpClient(String urlStr, String method, int timerout) {

        URL url = null;

        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setConnectTimeout(timerout);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            error = true;
        } catch (IOException e) {
            e.printStackTrace();
            error = true;
        }

    }

    public HttpClient addParams(String key, String value) {
        if (paramStr == null || paramStr.length() == 0)
            paramStr = key + "=" + value;
        else
            paramStr += "&" + key + "=" + value;
        return this;
    }

    public void addParams(Map<String, Object> params) {
        Set<String> keys = params.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            String key = it.next();
            addParams(key, params.get(key).toString());
        }
    }

    public InputStream excuteInputStream() {
        InputStream in = null;
        if (!error) {
            try {
                if (paramStr != null && paramStr.length() > 0) {
                    connection.getOutputStream().write(paramStr.getBytes());
                }
                in = connection.getInputStream();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return in;
    }

    public String excute() throws IOException {
        String info = "";
        if (!error) {
            if (paramStr != null && paramStr.length() > 0) {
                connection.getOutputStream().write(paramStr.getBytes());
            }
            InputStream inputStream = connection.getInputStream();

            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String s = null;
            while ((s = bf.readLine()) != null) {
                info += s;
            }

        } else
            info = null;
        return info;
    }

    public int getLength() {
        return connection.getContentLength();
    }

    public boolean isError() {
        return error;
    }

    /**
     * 创建服务端 交互 并 返回响应
     *
     * @param form    参数 格式 "?xx=x1&bb=b1"
     * @param toUrl   访问 地址
     * @param method  访问方式 默认POST
     * @param charset 编码方式 默认UTF-8
     * @return
     * @throws Exception
     */
    public static HttpURLConnection getHttpURLConnection(String form, String toUrl, String method, String charset) throws Exception {

        URL url = new URL(toUrl);
        byte[] data = form.getBytes();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.equals("") ? REQUEST_METHOD_POST : REQUEST_METHOD_GET);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", charset.equals("") ? "UTF-8" : charset);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(data);
        outStream.flush();
        return conn;
    }

    /**
     * 创建服务端 交互 并 返回响应
     *
     * @param form    参数 格式 "?xx=x1&bb=b1"
     * @param toUrl   访问 地址
     * @param name    查找的关键字
     * @param method  访问方式 默认POST
     * @param charset 编码方式 默认UTF-8
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static HttpURLConnection getHttpURLSearch(String form, String toUrl, String name, String method, String charset) throws Exception {

        byte[] data = form.getBytes();
        URL url = new URL(toUrl + java.net.URLEncoder.encode(name));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.equals("") ? REQUEST_METHOD_POST : REQUEST_METHOD_GET);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", charset.equals("") ? "UTF-8" : charset);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        outStream.write(data);
        outStream.flush();
        return conn;
    }

    public static String SendGET(String url, String code) {
        LOGGER.info(">>>>>>进入请求第三方接口" + url);
        if (StringUtils.isBlank(code)) {
            code = "utf-8";

        }
        String result = "";// 访问返回结果
        BufferedReader read = null;// 读取访问结果

        try {
            // 创建url
            URL realurl = new URL(url);
            LOGGER.info(">>>>>>进入请求第三方接口 #### URL realurl");
            // 打开连接
            URLConnection connection = realurl.openConnection();
            //设置超时
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立连接
            LOGGER.info(">>>>>>进入请求第三方接口 #### 准备连接");
            connection.connect();
            LOGGER.info(">>>>>>进入请求第三方接口 #### 连接完成");
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(connection.getInputStream(), code));
            LOGGER.info(">>>>>>进入请求第三方接口 #### 返回数据");
            String line;// 循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
            LOGGER.info(">>>>>>进入请求第三方接口 #### 返回数据===" + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {// 关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info(">>>>>>进入请求第三方接口 完成 " + url);
        return result;
    }

}
