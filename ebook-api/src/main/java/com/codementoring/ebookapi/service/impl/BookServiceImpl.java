package com.codementoring.ebookapi.service.impl;

import com.codementoring.ebookapi.exception.DataAlreadyExistsException;
import com.codementoring.ebookapi.exception.ModelNotFoundException;
import com.codementoring.ebookapi.model.Book;
import com.codementoring.ebookapi.repository.IBookRepository;
import com.codementoring.ebookapi.repository.ICategoryRepository;
import com.codementoring.ebookapi.repository.IGenericRepository;
import com.codementoring.ebookapi.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl extends CRUDImpl<Book, Integer> implements IBookService {

    //@Autowired
    private final IBookRepository repo;
    private final ICategoryRepository repositoryCategory;


    @Override
    protected IGenericRepository<Book, Integer> getRepo() {
        return repo;
    }

    @Override
    public Book save(Book book) throws Exception {
        Integer idCategory = book.getCategory().getIdCategory();

        // Verificar si existe una categoría con el idCategoria proporcionado
        boolean categoryExists = this.existsCategoryById(idCategory);

        if (categoryExists) {
            // La categoría existe, puedes continuar guardando el libro
            if (isBookDuplicate(book.getIsbn()) ) {
                throw new DataAlreadyExistsException("El libro con ISBN " + book.getIsbn() + " ya existe.");
            }
            return super.save(book);
        } else {
            // La categoría no existe, lanzar una excepción o realizar alguna acción adicional
            throw new ModelNotFoundException("La categoría con id " + idCategory + " no existe.");
        }
    }

    @Override
    public Book update(Book book, Integer idBook) throws Exception {
        Integer idCategory = book.getCategory().getIdCategory();

        // Verificar si existe una categoría con el idCategoria proporcionado
        boolean categoryExists = this.existsCategoryById(idCategory);

        // Verificar si existe una libro con el idBook proporcionado
        getRepo().findById(idBook).orElseThrow( () -> new ModelNotFoundException("El libro con id " + idBook + " no existe."));

        if (categoryExists) {
            // La categoría existe, puedes continuar guardando el libro
            return super.update(book, idBook);
        } else {
            // La categoría no existe, lanzar una excepción o realizar alguna acción adicional
            throw new ModelNotFoundException("La categoría con id " + idCategory + " no existe.");
        }
    }

    @Override
    public List<Book> findBooksByIsbn(String isbn) {
        return repo.findByIsbn(isbn);
    }

    public boolean isBookDuplicate(String isbn) {
        return repo.existsByIsbn(isbn);
    }

    public boolean existsCategoryById(Integer idCategory) {
        return repositoryCategory.existsByidCategory(idCategory);
    }
}
