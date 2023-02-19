package com.caching.inmemory.service;
import com.caching.inmemory.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;

@Component
public class CaffeineCaching {

    private static final String CACHE_NAME = "json-cache";
    private static final int MAX_CACHE_SIZE = 1000;

    @Autowired
    CacheManager cacheManager;

    private static final Gson gson = new Gson();

    @Cacheable(value = "sample")
    public  void sample() {
        // Set up the cache
//        Caffeine<String, String> cache = new CacheProperties.Caffeine().builder()
//                .maximumSize(MAX_CACHE_SIZE)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .build();

//        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//        cacheManager.setCaffeine(Caffeine.newBuilder()
//                .expireAfterWrite(5, TimeUnit.MINUTES)
//                .maximumSize(100)
//                .build());
//        Caffeine<String,String> caffeine = null;
//
//        Cache<String, String> cache = cacheManager.setCaffeine(caffeine);
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("sample");
        Cache<Object, Object> cache = caffeineCache.getNativeCache();

        // Add some data to the cache
        String json1 = "{\"id\": 1, \"name\": \"John\", \"age\": 30}"; //gson.toJson(Person.builder().age(30).id(1).name("Jhon").build());
//        String json2 = gson.toJson(Person.builder().age(25).id(2).name("Jane").build());
        String json2 = "{\"id\": 2, \"name\": \"Jane\", \"age\": 25}";
//        String json3 = gson.toJson(Person.builder().age(40).id(3).name("Bob").build());
        String json3 = "{\"id\": 3, \"name\": \"Bob\", \"age\": 40}";
        cache.put("json1", json1);
        cache.put("json2", json2);
        cache.put("json3", json3);

        // Retrieve a JSON row based on one of the fields
        Object result = cache.asMap().values().stream()
                .filter(json -> {
                    Person person = null;
                    try {
                        person = new ObjectMapper().readValue(json.toString(), Person.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return person.getName().equals("Bob") && person.getAge() == 40;
                })
                .findFirst()
                .orElse(null);

        System.out.println("Result: " + result);
    }


}
