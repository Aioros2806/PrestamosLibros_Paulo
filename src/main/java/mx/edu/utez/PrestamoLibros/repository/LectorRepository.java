package mx.edu.utez.PrestamoLibros.repository;

import mx.edu.utez.PrestamoLibros.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Long> {
}