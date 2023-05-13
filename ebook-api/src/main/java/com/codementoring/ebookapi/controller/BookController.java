package com.codementoring.ebookapi.controller;

import com.codementoring.ebookapi.dto.BookDTO;
import com.codementoring.ebookapi.dto.CategoryDTO;
import com.codementoring.ebookapi.model.Book;
import com.codementoring.ebookapi.model.Category;
import com.codementoring.ebookapi.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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


    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@Valid @PathVariable("id") Integer idBook, @RequestBody BookDTO dto) throws Exception {
        dto.setIdBook(idBook);
        Book obj = service.update(convertToEntity(dto), idBook);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>>  readAll() throws Exception{
        List<BookDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Book obj = service.readById(id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<List<BookDTO>> findByIsbn(@PathVariable("isbn") String isbn) {
        List<Book> books = service.findBooksByIsbn(isbn);
        List<BookDTO> dtos = books.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private BookDTO convertToDto(Book obj){
        return mapper.map(obj, BookDTO.class);
    }

    private Book convertToEntity(BookDTO dto){
        return mapper.map(dto, Book.class);
    }
}
