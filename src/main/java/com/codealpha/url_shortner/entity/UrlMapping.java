package com.codealpha.url_shortner.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "url_mapping")
@Data
public class UrlMapping {
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private int clickCount = 0;
    //private LocalDateTime createdDate;
    private LocalDateTime createdDate = LocalDateTime.now();

    private String userId;

    @DBRef
    private List<ClickEvent> clickEvents;
}