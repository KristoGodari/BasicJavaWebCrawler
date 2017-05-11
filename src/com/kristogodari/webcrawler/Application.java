package com.kristogodari.webcrawler;

public class Application {

    public static void main(String[] args){

        String websiteToCrawl = "http://kristogodari.com/";

        WebCrawler webCrawler = new WebCrawler();
        webCrawler.crawl(websiteToCrawl);
    }
}
