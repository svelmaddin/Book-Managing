package com.elmaddin.Book.Managing.service;

import com.elmaddin.Book.Managing.dto.BookResponse;
import com.elmaddin.Book.Managing.dto.BookSaveRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    void saveBook(BookSaveRequest bookSaveRequest);

    List<BookResponse> getUsersBook();

    List<BookResponse> getAllBook();

    BookResponse getById(Long id);

    void updateBook(Long id, BookSaveRequest request);

    void deleteBook(Long id);

    List<BookResponse> searchBook(String query);
}
