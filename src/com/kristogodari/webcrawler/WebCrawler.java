package com.kristogodari.webcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<String> linksQueue;

    private List<String> visitedLinks;

    public WebCrawler(){
        this.linksQueue = new LinkedList<String>();
        this.visitedLinks = new ArrayList<String>();
    }

    public void crawl(String url){

        this.linksQueue.add(url);
        this.visitedLinks.add(url);

        while (!linksQueue.isEmpty()){

            String linkToVisit = this.linksQueue.remove();
            String linkHtml = getUrlHtml(linkToVisit);

            String regEx = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(linkHtml);

            while (matcher.find()){

                String  actualUrl = matcher.group();
                if(!visitedLinks.contains(actualUrl)){
                    visitedLinks.add(actualUrl);
                    linksQueue.add(actualUrl);

                    System.out.println("Website has been found: " + actualUrl);
                }
            }

        }
    }

    /**
     * Returns the html content of a url.
     *
     * @param link
     * @return
     */
    private String getUrlHtml(String link){

        String html = "";
        try {

            URL url = new URL(link);
            BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = "";
            while ((inputLine = bufferedReader.readLine()) != null){
                html+= inputLine;
            }

            bufferedReader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return html;
    }
}
