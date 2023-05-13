package com.codementoring.ebookapi.repository;

import com.codementoring.ebookapi.model.Book;

public interface IBookRepository extends IGenericRepository<Book, Integer> {
    boolean existsByIsbn(String isbn);
}
