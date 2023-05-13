package com.codementoring.ebookapi.controller;

import com.codementoring.ebookapi.dto.BookDTO;
import com.codementoring.ebookapi.model.Book;
import com.codementoring.ebookapi.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService service;

    @Qualifier("bookMapper")
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO dto) throws Exception {
        Book obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    private BookDTO convertToDto(Book obj){
        return mapper.map(obj, BookDTO.class);
    }

    private Book convertToEntity(BookDTO dto){
        return mapper.map(dto, Book.class);
    }
}
