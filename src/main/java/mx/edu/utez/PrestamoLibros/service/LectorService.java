package mx.edu.utez.PrestamoLibros.service;

import mx.edu.utez.PrestamoLibros.exception.MiExcepcion;
import mx.edu.utez.PrestamoLibros.model.Lector;
import mx.edu.utez.PrestamoLibros.repository.LectorRepository;
import org.springframework.stereotype.Service;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Lector crearLector(Lector lector) {
        return lectorRepository.save(lector);
    }

    //rehabilitarLector
    public Lector rehabilitarLector(Long id) {
        Lector lector = lectorRepository.findById(id)
                .orElseThrow(() -> new MiExcepcion("El lector no existe"));

        lector.setActivo(true);
        return lectorRepository.save(lector);
    }
}