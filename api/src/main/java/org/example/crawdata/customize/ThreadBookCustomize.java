package org.example.crawdata.customize;

import org.example.dto.BookDTO;
import org.example.mysql.model.Author;
import org.example.mysql.model.Book;
import org.example.mysql.model.BookType;
import org.example.mysql.service.AuthorService;
import org.example.mysql.service.BookService;
import org.example.mysql.service.BookTypeService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ThreadBookCustomize extends Thread {
    private BookService bookService;
    private String url;
    private String agent;
    private BookTypeService bookTypeService;
    private AuthorService authorService;

    public ThreadBookCustomize(BookService bookService, BookTypeService bookTypeService,
                           AuthorService authorService, String url, String agent) {
        this.bookService = bookService;
        this.url = url;
        this.agent = agent;
        this.bookTypeService = bookTypeService;
        this.authorService = authorService;
    }

    @Override
    public void run() {
        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent(agent);
            Document doc = connection.get();

            Elements elementContents = doc.select("body .container .content_page .content_p.content_p_al");
            Elements elementAuthors = doc.select("body .container .content_page .row div.mg-t-10");
            Elements elementTypes = doc.select("body .container .content_page .row div.mg-tb-10 .tblue");
            Elements elementNames = doc.select("body .container .content_page .row h1.tblue.fs-20");
            if(!elementNames.isEmpty()) {
                String name = elementNames.get(0).text();
                Book book = bookService.getByName(name);
                if(book == null) {
                    BookDTO dto = new BookDTO();
                    for (Element e:elementAuthors) {
                        if(e.text().contains("Tác giả:")) {
                            String author_name = e.text().replace("Tác giả:", "");
                            if(!author_name.isEmpty()) {
                                Author author = authorService.getByName(author_name);
                                if(author == null) {
                                    author = new Author();
                                    author.setName(author_name);
                                    author = authorService.addAuthor(author);
                                }
                                dto.setAuthor_id(author.getId());
                            }
                        }
                    }

                    for (Element e:elementTypes) {
                        String type_name = e.text();
                        if(!type_name.isEmpty()) {
                            BookType bookType = bookTypeService.getByName(type_name);
                            if(bookType == null) {
                                bookType = new BookType();
                                bookType.setName(type_name);
                                bookType = bookTypeService.addBookType(bookType);
                            }
                            dto.setType_id(bookType.getType_id());
                        }
                    }
                    StringBuilder content = new StringBuilder();
                    for (Element e:elementContents) {
                        if(!e.text().isEmpty()) {
                            content.append(e.text());
                        }
                    }
                    dto.setContent(content.toString());
                    for(Element e:elementNames) {
                        dto.setName(e.text());
                    }
                    bookService.addBook(dto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
