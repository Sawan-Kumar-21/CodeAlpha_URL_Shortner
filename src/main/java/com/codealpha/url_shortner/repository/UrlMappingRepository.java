package com.codealpha.url_shortner.repository;


import com.codealpha.url_shortner.entity.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UrlMappingRepository extends MongoRepository<UrlMapping, String > {
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUserId(String userId);
}
