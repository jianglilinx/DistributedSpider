package com.jiang;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by jiang on 17-7-5.
 */
public class ImgSpider {
    public static void main(String[] args) {
        getImageUrl();
    }

    private static void getImageUrl(){
        try {
            Document doc = Jsoup.connect("http://tieba.baidu.com/p/5080413304").userAgent("Mozilla").get();
            Elements links =  doc.getElementsByTag("img");
            for (Element link : links){
                String linkHref = link.attr("src");
                String pattern = "http://imgsrc.baidu.com/forum/.*";
                if (Pattern.matches(pattern,linkHref)){
                    System.out.println(linkHref);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
