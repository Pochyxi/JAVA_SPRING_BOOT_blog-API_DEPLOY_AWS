package com.developez.Spring.boot.blog.API.controller;

import com.developez.Spring.boot.blog.API.payload.CategoryDto;
import com.developez.Spring.boot.blog.API.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD REST APIs per Category"
)
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    // Costruzione della addCategory REST API
    @Operation(
            summary = "Creazione di un Categoria",
            description = "Creazione di una Categoria",
            tags = { "CRUD REST APIs per Category" }
    )
    @ApiResponse(
            responseCode = "201",
            description = "Categoria creata con successo"
    )
    @SecurityRequirements({
            @SecurityRequirement(
                    name = "Bear Authentication"
            ),
            @SecurityRequirement(
                    name = "csrfToken"
            )
    })
    @PostMapping
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto ) {
        CategoryDto response = categoryService.addCategory( categoryDto );

        return new ResponseEntity<>( response, HttpStatus.CREATED );
    }

    // Costruzione della getCategory REST API
    @Operation(
            summary = "Recupero di una Categoria",
            description = "Recupero di una Categoria",
            tags = { "CRUD REST APIs per Category" }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Categoria recuperata con successo"
    )
    @GetMapping("/{categoryId}")
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId ) {
        CategoryDto response = categoryService.getCategory( categoryId );

        return new ResponseEntity<>( response, HttpStatus.OK );
    }


    // Costruzione della getAllCategories REST API
    @Operation(
            summary = "Recupero di tutte le Categorie",
            description = "Recupero di tutte le Categorie",
            tags = { "CRUD REST APIs per Category" }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tutte le Categorie recuperate con successo"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok( categoryService.getAllCategories() );
    }

    // Costruzione della updateCategory REST API
    @Operation(
            summary = "Aggiornamento di una Categoria tramite ID",
            description = "Aggiornamento di una Categoria tramite ID",
            tags = { "CRUD REST APIs per Category" }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Categoria aggiornata con successo"
    )
    @SecurityRequirements({
            @SecurityRequirement(
                    name = "Bear Authentication"
            ),
            @SecurityRequirement(
                    name = "csrfToken"
            )
    })
    @PutMapping("/{categoryId}")
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Long categoryId ) {
        CategoryDto response = categoryService.updateCategory( categoryDto, categoryId );

        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    // Costruzione della deleteCategory REST API
    @Operation(
            summary = "Eliminazione di una Categoria tramite ID",
            description = "Eliminazione di una Categoria tramite ID",
            tags = { "CRUD REST APIs per Category" }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Categoria eliminata con successo"
    )
    @SecurityRequirements({
            @SecurityRequirement(
                    name = "Bear Authentication"
            ),
            @SecurityRequirement(
                    name = "csrfToken"
            )
    })
    @DeleteMapping("/{categoryId}")
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId ) {
        categoryService.deleteCategory( categoryId );

        return new ResponseEntity<>( "Categoria eliminata con successo", HttpStatus.OK );
    }

}
