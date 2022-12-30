package org.example.crawdata.impl;

import org.example.crawdata.CrawBookService;
import org.example.crawdata.customize.ThreadCustomize;
import org.example.dto.RequestCrawBook;
import org.example.mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class CrawBookServiceImpl implements CrawBookService {

    @Autowired
    private BookService bookService;

    @Value("${crawler.url}")
    private String url;

    @Value("${crawler.content}")
    private String cssSelectorContent;

    @Value("${crawler.agent}")
    private String agent;

    @Override
    public void crawContent(List<RequestCrawBook> crawBooks) {
        int size = crawBooks.size();
        Executor executor = Executors.newFixedThreadPool(size);
        for(int i=0; i<size; i++) {
            executor.execute(new ThreadCustomize(crawBooks.get(i), bookService, url, agent, cssSelectorContent));
        }
    }
}
