package com.jiang;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiang on 17-7-1.
 */
public class MySpider {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) {
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null){
                InputStream in = httpEntity.getContent();
                int l;
                byte[] tmp = new byte[2048];
                while ((l = in.read(tmp)) != -1) {
                    System.out.println(new String(tmp, 0, l, "utf-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
