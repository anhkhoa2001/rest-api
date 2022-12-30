package org.example.crawdata.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.crawdata.CrawBookTypeService;
import org.example.mysql.model.BookType;
import org.example.mysql.service.BookTypeService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class CrawBookTypeSeviceImpl implements CrawBookTypeService {

    @Value("${crawler.url}")
    private String urlCraw;

    @Autowired
    private BookTypeService bookTypeService;

    @Override
    public List<BookType> crawBookTypes(String url) {
        try {
            Connection connection = Jsoup.connect(urlCraw + "/" + url);
            connection.userAgent("Mozilla/5.0");

            Document doc = connection.get();
            Elements elements = doc.select("body .sub-menu li a");
            List<BookType> rs = new ArrayList<>();
            for (Element e : elements) {
                String name = e.text();
                BookType bookType = new BookType();
                bookType.setName(name);
                bookType = bookTypeService.addBookType(bookType);
                bookType.setBooks(new HashSet<>());
                if(bookType.getType_id() != null) {
                    rs.add(bookType);
                }
            }

            log.info("Đã craw được thêm "+ rs.size() +" thể loại khác trên " + urlCraw + "/" + url);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
