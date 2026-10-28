package mx.edu.utez.PrestamoLibros.service;

import mx.edu.utez.PrestamoLibros.model.Libro;
import mx.edu.utez.PrestamoLibros.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }
}