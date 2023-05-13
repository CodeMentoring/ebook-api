package com.codementoring.ebookapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private Integer idBook;

    @NotNull
    private Integer idCategory;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String isbn;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String description;

    @Min(value = 1)
    @Max(value = 9999)
    private double price;

}
