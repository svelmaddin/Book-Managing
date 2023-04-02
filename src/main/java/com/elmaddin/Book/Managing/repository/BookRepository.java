package com.elmaddin.Book.Managing.repository;


import com.elmaddin.Book.Managing.model.BookEntity;
import com.elmaddin.Book.Managing.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByUserId(Long id);

    @Query("SELECT c from BookEntity c WHERE c.name LIKE CONCAT('%', :query, '%') AND c.user=:user")
    List<BookEntity> searchBooks(UserEntity user, String query);

    @Modifying
    @Query("DELETE from BookEntity c WHERE c.user=:user AND c.id=:bookId")
    void deleteBookEntityByUser(Long bookId, UserEntity user);

}
