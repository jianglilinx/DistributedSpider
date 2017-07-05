package com.jiang;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * Created by jiang on 17-7-1.
 */
public class MySpider {
    private static AtomicInteger num = new AtomicInteger(-5);
    public static void main(String[] args) {
        if (args.length == 0)
            args = new String[1];
        args[0] = "4";

        for (int i = 0;i<Integer.parseInt(args[0]);i++){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    while (num.get() < 26600) {
                        putUrlInRedis(num.addAndGet(5));
                    }
                }
            });
            thread.run();
        }



    }

    private static void putUrlInRedis(int now){
        try {
            Document doc = Jsoup.connect("http://tieba.baidu.com/f?kw=%E9%A3%8E%E6%99%AF&ie=utf-8&pn="+now).userAgent("Mozilla").get();
            Elements links =  doc.getElementsByTag("a");
            for (Element link : links){
                String linkHref = link.attr("href");
                String pattern = "/p/.*";
                if (Pattern.matches(pattern,linkHref)){
                    System.out.println(linkHref);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
