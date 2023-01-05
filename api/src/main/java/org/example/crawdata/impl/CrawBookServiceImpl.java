package org.example.crawdata.impl;

import org.example.crawdata.CrawBookService;
import org.example.crawdata.customize.ThreadBookCustomize;
import org.example.crawdata.customize.ThreadBookTypeCustomize;
import org.example.mysql.service.AuthorService;
import org.example.mysql.service.BookService;
import org.example.mysql.service.BookTypeService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class CrawBookServiceImpl implements CrawBookService {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private AuthorService authorService;

    @Value("${crawler.url}")
    private String url;

    @Value("${crawler.content}")
    private String cssSelectorContent;

    @Value("${crawler.agent}")
    private String agent;

    final Integer size = 5;

    @Override
    public void crawContent() {
        try {
            List<String> urlBookTypes = new ArrayList<>();
            Queue<String> urlBooks = new LinkedList<>();
            Connection connection = Jsoup.connect(url);
            connection.userAgent(agent);

            Document doc = connection.get();
            Elements elements = doc.select("body .main_home .row .item_folder");
            for(Element e:elements) {
                String urlBookType = getUrl(e.html());
                urlBookTypes.add(urlBookType);
            }
            Executor executor = Executors.newFixedThreadPool(size);
            for(int i=0; i<urlBookTypes.size(); i++) {
                String cssSelectorBook = "body .row.r_10 .item_sach div";
                Thread thread = new ThreadBookTypeCustomize(urlBooks, urlBookTypes.get(i),
                        agent, cssSelectorBook);
                executor.execute(thread);
            }

            if(urlBooks.size() == 0) {
                Thread.sleep(1000);
            }
            while (!urlBooks.isEmpty()) {
                Thread.sleep(100);
                Thread thread = new ThreadBookCustomize(bookService, bookTypeService, authorService,
                                        urlBooks.poll(), agent);

                executor.execute(thread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*Executor executor = Executors.newFixedThreadPool(size);
        for(int i=0; i<crawBooks.size(); i++) {
            executor.execute(new ThreadCustomize(crawBooks.get(i), bookService, url, agent, cssSelectorContent));
        }*/
    }

    public String getUrl(String url) {
        url = url.replaceAll("<a href=\"", "");
        url = url.substring(0, url.indexOf("\"><i class"));

        return url;
    }
}
