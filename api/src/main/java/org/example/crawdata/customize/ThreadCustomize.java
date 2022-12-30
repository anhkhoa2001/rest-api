package org.example.crawdata.customize;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.dto.RequestCrawBook;
import org.example.mysql.model.Book;
import org.example.mysql.service.BookService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Data
public class ThreadCustomize extends Thread{

    private RequestCrawBook crawBook;
    private BookService bookService;
    private String url;
    private String agent;
    private String cssSelectorContent;

    public ThreadCustomize(RequestCrawBook crawBook, BookService bookService,
                           String url, String agent, String cssSelectorContent) {
        this.crawBook = crawBook;
        this.bookService = bookService;
        this.url = url;
        this.agent = agent;
        this.cssSelectorContent = cssSelectorContent;
    }

    @Override
    public void run() {
        Book book = bookService.findById(crawBook.getBook_id());
        try {
            Connection connection =
                    Jsoup.connect(url + "/" + crawBook.getUrl());
            connection.userAgent(agent);
            Document doc = connection.get();
            Elements newsHeadlines = doc.select(cssSelectorContent);
            StringBuilder builder = new StringBuilder();
            for(Element element:newsHeadlines) {
                builder.append(element.text());
            }

            book.setContent(builder.toString());
            bookService.update(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
