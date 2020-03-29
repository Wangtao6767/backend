package com.lensen.mobile.service.blow.impl;

import com.lensen.backend.dal.domain.blow.BlowDto;
import okhttp3.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类,调用另一系统接口发送数据
 */
public class HttpClientUtils {
    public static void sendCSBTestData(BlowDto blowDto){
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        try {
            httpClientUtils.sendRecords(blowDto);
            httpClientUtils.sendImages(blowDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送车牌号等数据（不包括照片）
     * @param blowDto
     * @throws Exception
     */
    public void sendRecords(BlowDto blowDto) throws Exception{
        Long id = blowDto.getId();
        String licensePlateNumber = blowDto.getLicensePlateNumber();
        String carType = blowDto.getCarType();
        String illegalType = blowDto.getIllegalType();
        String region = blowDto.getRegion();
        String address = blowDto.getAddress();
        String travelDirection = blowDto.getTravelDirection();
        String video = blowDto.getVideo();
        Date createTime = blowDto.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String time = simpleDateFormat.format(createTime);
        String str =  "{  \"licensePlateNumber\":\""+licensePlateNumber+"\", " +
                " \"id\":\""+id+"\",  " +
                " \"carType\":\""+carType+"\",  " +
                "\"illegalType\":\""+illegalType+"\",  " +
                "\"region\":\""+region+"\",  " +
                "\"address\":\""+address+"\", " +
                "\"createTime\":\""+time+"\", " +
                " \"travelDirection\":\""+travelDirection+"\",  " +
//                "\"images\":[\"www1\", \"www2\"],  " +
                "\"video\":\""+video+"\"}";
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body =
                RequestBody.create(mediaType,str);
//        System.out.println(str);
        Request request = new Request.Builder()
                .url("http://47.98.44.225:8081/mobile-data/blow/blow")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        Response response = client.newCall(request).execute();
}
    /**
     * 发送照片
     * @param blowDto
     * @throws Exception
     */
    public void sendImages(BlowDto blowDto) throws Exception {
        Long blowId = blowDto.getId();
        List<String> images = blowDto.getImages();
        Map map = new HashMap<>();
        for (String image : images) {
            map.put(image, images);
            String str1 = "[{ \"blowId\":\"" + blowId + "\", " +
                    " \"imgUrl\":\"" + image + "\"" +
                    "}]";
            MediaType mediaType = MediaType.parse("application/json");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, str1);
            Request request = new Request.Builder()
                    .url("http://47.98.44.225:8081/mobile-data/blow/insert_images")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
        }
    }
}
