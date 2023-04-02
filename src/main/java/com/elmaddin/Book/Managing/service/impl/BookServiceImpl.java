package com.elmaddin.Book.Managing.service.impl;


import com.elmaddin.Book.Managing.dto.BookResponse;
import com.elmaddin.Book.Managing.dto.BookSaveRequest;
import com.elmaddin.Book.Managing.exception.GenericException;
import com.elmaddin.Book.Managing.model.BookEntity;
import com.elmaddin.Book.Managing.model.UserEntity;
import com.elmaddin.Book.Managing.repository.BookRepository;
import com.elmaddin.Book.Managing.repository.UserRepository;
import com.elmaddin.Book.Managing.security.SecurityUtil;
import com.elmaddin.Book.Managing.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saveBook(BookSaveRequest bookSaveRequest) {
        final BookEntity book = BookEntity.builder()
                .name(bookSaveRequest.getName())
                .price(bookSaveRequest.getPrice())
                .author(bookSaveRequest.getAuthor())
                .pageCount(bookSaveRequest.getPageCount())
                .build();
        book.setUser(currentUser());
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getUsersBook() {
        return bookRepository.findByUserId(currentUser().getId()).stream().map(
                        this::mapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getAllBook() {
        return bookRepository.findAll().stream().map(
                        this::mapToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getById(Long id) {
        final BookEntity fromDb = bookRepository.findById(id).orElseThrow(GenericException::new);
        return mapToBookDto(fromDb);
    }

    @Override
    public void updateBook(Long id, BookSaveRequest request) {
        BookEntity fromDb = bookRepository.findById(id).orElseThrow(GenericException::new);
        fromDb.setName(request.getName());
        fromDb.setAuthor(request.getAuthor());
        fromDb.setPrice(request.getPrice());
        fromDb.setPageCount(request.getPageCount());
        bookRepository.save(fromDb);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteBookEntityByUser(id, currentUser());
    }

    @Override
    public List<BookResponse> searchBook(String query) {
        return bookRepository.searchBooks(currentUser(), query).stream().map(
                        this::mapToBookDto)
                .collect(Collectors.toList());
    }

    private BookResponse mapToBookDto(BookEntity book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .price(book.getPrice())
                .author(book.getAuthor())
                .pageCount(book.getPageCount())
                .build();
    }

    private UserEntity currentUser() {
        String username = SecurityUtil.getSessionUser();
        return userRepository.findByUsername(username);
    }


}