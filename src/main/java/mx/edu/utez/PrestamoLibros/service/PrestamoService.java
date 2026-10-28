package mx.edu.utez.PrestamoLibros.service;

import mx.edu.utez.PrestamoLibros.dto.PrestamoDTO;
import mx.edu.utez.PrestamoLibros.exception.MiExcepcion;
import mx.edu.utez.PrestamoLibros.model.*;
import mx.edu.utez.PrestamoLibros.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LectorRepository lectorRepository;
    private final LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           LectorRepository lectorRepository,
                           LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.lectorRepository = lectorRepository;
        this.libroRepository = libroRepository;
    }

    //solicitarPrestamo
    public Prestamo solicitarPrestamo(PrestamoDTO dto) {
        Lector lector = lectorRepository.findById(dto.getLectorId())
                .orElseThrow(() -> new MiExcepcion("El lector no existe"));

        if (!lector.getActivo()) {
            throw new MiExcepcion("El lector está inactivo o bloqueado");
        }

        Libro libro = libroRepository.findById(dto.getLibroId())
                .orElseThrow(() -> new MiExcepcion("El libro no existe"));

        if (libro.getEjemplaresDisponibles() < 1) {
            throw new MiExcepcion("No hay disponibles");
        }

        List<EstadoPrestamo> estadosActivos = List.of(EstadoPrestamo.PRESTADO, EstadoPrestamo.VENCIDO);
        long prestamosActivos = prestamoRepository.countByLectorIdAndEstadoIn(lector.getId(), estadosActivos);
        int limite = Boolean.TRUE.equals(lector.getMembresiaPremium()) ? 5 : 3;

        if (prestamosActivos >= limite) {
            throw new MiExcepcion("El lector ha alcanzado el límite máximo (" + limite + ")");
        }

        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setLector(lector);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionMaxima(LocalDate.now().plusDays(14));
        prestamo.setEstado(EstadoPrestamo.PRESTADO);

        return prestamoRepository.save(prestamo);
    }

    // devolverLibro (30 pts)
    public Prestamo devolverLibro(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new MiExcepcion("El prestamo no existe"));

        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new MiExcepcion("El prestamo ya fue devuelto ");
        }

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        Libro libro = prestamo.getLibro();
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
        libroRepository.save(libro);

        if (LocalDate.now().isAfter(prestamo.getFechaDevolucionMaxima())) {
            Lector lector = prestamo.getLector();
            lector.setActivo(false);
            lectorRepository.save(lector);
        }

        return prestamoRepository.save(prestamo);
    }

    // renovarPrestamo
    public Prestamo renovarPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new MiExcepcion("El prestamo no existe"));

        if (prestamo.getEstado() == EstadoPrestamo.VENCIDO || prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new MiExcepcion("No se puede renovar un prestamo en estado " + prestamo.getEstado());
        }

        prestamo.setFechaDevolucionMaxima(prestamo.getFechaDevolucionMaxima().plusDays(7));
        return prestamoRepository.save(prestamo);
    }

    //listarPrestamosActivosLector (10 pts)
    public List<Prestamo> listarPrestamosActivosLector(Long lectorId) {
        if (!lectorRepository.existsById(lectorId)) {
            throw new MiExcepcion("El lector no existe");
        }
        List<EstadoPrestamo> estadosActivos = List.of(EstadoPrestamo.PRESTADO, EstadoPrestamo.VENCIDO);
        return prestamoRepository.findByLectorIdAndEstadoIn(lectorId, estadosActivos);
    }
}