package com.codementoring.ebookapi.repository;

import com.codementoring.ebookapi.model.Book;

import java.util.List;

public interface IBookRepository extends IGenericRepository<Book, Integer> {
    boolean existsByIsbn(String isbn);

    //método de consulta para obtener los datos de un libro por isbn
    List<Book> findByIsbn(String isbn);
}
