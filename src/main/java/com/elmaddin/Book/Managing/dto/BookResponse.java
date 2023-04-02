package com.elmaddin.Book.Managing.dto;
import com.elmaddin.Book.Managing.model.UserEntity;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private int price;
    private String author;
    private int pageCount;
    private UserEntity user;


}
