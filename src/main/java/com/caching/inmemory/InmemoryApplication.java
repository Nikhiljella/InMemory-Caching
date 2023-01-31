package com.caching.inmemory;

import com.caching.inmemory.model.BookDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@SpringBootApplication
public class InmemoryApplication {

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

}
