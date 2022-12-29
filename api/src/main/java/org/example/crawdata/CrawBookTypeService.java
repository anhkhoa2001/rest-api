package org.example.crawdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrawBookTypeService {

    @Value("${crawler.url}")
    private String urlCraw;


}
