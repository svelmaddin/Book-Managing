package com.elmaddin.Book.Managing.dto;


import com.elmaddin.Book.Managing.model.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {
    private Long id;
    private String name;
    private int price;
    private String author;
    private int pageCount;
    private UserEntity user;
}
