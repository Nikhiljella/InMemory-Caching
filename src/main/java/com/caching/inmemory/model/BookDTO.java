package com.caching.inmemory.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Setter
public class BookDTO {
    private String bookName;
    private String bookId;
    private String bookType;
}
