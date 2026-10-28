package mx.edu.utez.PrestamoLibros.repository;

import mx.edu.utez.PrestamoLibros.model.EstadoPrestamo;
import mx.edu.utez.PrestamoLibros.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    long countByLectorIdAndEstadoIn(Long lectorId, List<EstadoPrestamo> estados);
    List<Prestamo> findByLectorIdAndEstadoIn(Long lectorId, List<EstadoPrestamo> estados);
}