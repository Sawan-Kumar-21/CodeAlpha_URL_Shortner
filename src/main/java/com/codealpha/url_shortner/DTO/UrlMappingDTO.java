package com.codealpha.url_shortner.DTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDTO {

    private String  id;
    private String originalUrl;
    private String shortUrl;
    private int clickCount;
    private LocalDateTime createDate;
    private String username;
}
