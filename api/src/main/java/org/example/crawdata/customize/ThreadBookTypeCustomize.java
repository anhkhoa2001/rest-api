package org.example.crawdata.customize;

import lombok.Data;
import org.elasticsearch.client.license.LicensesStatus;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Queue;
import java.util.Stack;


@Data
public class ThreadBookTypeCustomize extends Thread {

    private Queue<String> urlBooks;
    private String url;
    private String agent;
    private String cssSelectorContent;

    public ThreadBookTypeCustomize(Queue<String> urlBooks, String url, String agent, String cssSelectorContent) {
        this.urlBooks = urlBooks;
        this.url = url;
        this.agent = agent;
        this.cssSelectorContent = cssSelectorContent;
    }

    @Override
    public void run() {
        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent(agent);
            Document doc = connection.get();
            Elements newsHeadlines = doc.select(cssSelectorContent);
            for(Element e:newsHeadlines) {
                urlBooks.add(getUrl(e.html()));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUrl(String txt) {
        txt = txt.replaceAll("<a href=\"", "");
        txt = txt.substring(0, txt.indexOf("\"><img class="));

        return txt;
    }
}
