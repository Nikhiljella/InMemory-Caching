package com.caching.inmemory;

import com.caching.inmemory.model.BookDTO;
import com.caching.inmemory.service.CaffeineCaching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@SpringBootApplication
@EnableCaching
public class InmemoryApplication implements CommandLineRunner {

	@Autowired
	CaffeineCaching caching;

	@GetMapping
	public String bookEcho()
	{
		BookDTO bookDTO = BookDTO.builder().bookId("123").bookType("sample").bookName("test").build();
		bookDTO.setBookId("223");
		return bookDTO.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(InmemoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		caching.sample();
	}
}
