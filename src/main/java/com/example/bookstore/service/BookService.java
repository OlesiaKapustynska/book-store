package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookSearchParametersDto;
import com.example.bookstore.dto.CreateBookRequestDto;
import com.example.bookstore.model.Book;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<Book> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);

    List<BookDto> searchBooks(BookSearchParametersDto searchParameters);
}
