package mx.edu.utez.PrestamoLibros.repository;

import mx.edu.utez.PrestamoLibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}