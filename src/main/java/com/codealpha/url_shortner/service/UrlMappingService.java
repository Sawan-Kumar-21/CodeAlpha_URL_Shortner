package com.codealpha.url_shortner.service;
import com.codealpha.url_shortner.DTO.UrlMappingDTO;
import com.codealpha.url_shortner.entity.UrlMapping;
import com.codealpha.url_shortner.repository.UrlMappingRepository;
import com.codealpha.url_shortner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UrlMappingService {
    private UserRepository userRepository;
    private UrlMappingRepository urlMappingRepository;

    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUserId(user.getId());
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping saveUrlMapping = urlMappingRepository.save(urlMapping);
        return convertToDto(saveUrlMapping);
    }

    private UrlMappingDTO convertToDto(UrlMapping urlMapping) {
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setCreateDate(urlMapping.getCreatedDate());
        User user = userRepository.findById(urlMapping.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        urlMappingDTO.setUsername(user.getUsername());
        return urlMappingDTO;
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    public List<UrlMappingDTO> getUrlsByUsers(User user) {
        return urlMappingRepository.findByUserId(user.getId()).stream()
                .map(this::convertToDto)
                .toList();
        //.collect(Collectors.toUnmodifiableList());
    }
}