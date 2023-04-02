package com.elmaddin.Book.Managing.controller;

import com.elmaddin.Book.Managing.dto.BookResponse;
import com.elmaddin.Book.Managing.dto.BookSaveRequest;
import com.elmaddin.Book.Managing.model.UserEntity;
import com.elmaddin.Book.Managing.security.SecurityUtil;
import com.elmaddin.Book.Managing.service.BookService;
import com.elmaddin.Book.Managing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookWebController {
    private final BookService bookService;
    private final UserService userService;

    public BookWebController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "layout";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        List<BookResponse> books = bookService.getUsersBook();
        model.addAttribute("books", books);
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/book/new")
    public String createBookForm(Model model) {
        BookSaveRequest book = new BookSaveRequest();
        model.addAttribute("book", book);
        return "create-book";
    }

    @PostMapping("/book/new")
    public String saveBook(@ModelAttribute("book") BookSaveRequest book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "create-book";
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBook(@RequestParam(value = "query") String query, Model model) {
        List<BookResponse> book = bookService.searchBook(query);
        model.addAttribute("books", book);
        return "book-list";
    }

    @GetMapping("/books/{bookId}/edit")
    public String editBookForm(@PathVariable("bookId") Long bookId, Model model) {
        BookResponse book = bookService.getById(bookId);
        model.addAttribute("book", book);
        return "update";
    }

    @PostMapping("/books/{bookId}/edit")
    public String updateBook(@PathVariable("bookId") Long bookId,
                             @Valid @ModelAttribute("book") BookSaveRequest book,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "update";
        }
        bookService.updateBook(bookId, book);
        return "redirect:/books";
    }

}

