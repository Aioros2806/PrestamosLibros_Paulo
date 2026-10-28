package mx.edu.utez.PrestamoLibros.controller;

import mx.edu.utez.PrestamoLibros.model.Libro;
import mx.edu.utez.PrestamoLibros.service.LibroService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.crearLibro(libro);
    }
}