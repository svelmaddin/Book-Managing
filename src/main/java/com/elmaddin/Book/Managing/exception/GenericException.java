package com.elmaddin.Book.Managing.exception;

import com.elmaddin.Book.Managing.dto.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException{
    private HttpStatus httpStatus;
    private ErrorCode errorCode;
    private String errorMessage;
}
