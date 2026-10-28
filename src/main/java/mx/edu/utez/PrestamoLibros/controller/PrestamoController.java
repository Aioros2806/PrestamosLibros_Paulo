package mx.edu.utez.PrestamoLibros.controller;

import jakarta.validation.Valid;
import mx.edu.utez.PrestamoLibros.dto.PrestamoDTO;
import mx.edu.utez.PrestamoLibros.model.Prestamo;
import mx.edu.utez.PrestamoLibros.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    //solicitarPrestamo
    @PostMapping
    public Prestamo solicitarPrestamo(@Valid @RequestBody PrestamoDTO dto) {
        return prestamoService.solicitarPrestamo(dto);
    }

    // devolverLibro
    @PutMapping("/{id}/devolver")
    public Prestamo devolverLibro(@PathVariable Long id) {
        return prestamoService.devolverLibro(id);
    }

    //renovarPrestamo
    @PutMapping("/{id}/renovar")
    public Prestamo renovarPrestamo(@PathVariable Long id) {
        return prestamoService.renovarPrestamo(id);
    }

    //listarPrestamosActivosLector
    @GetMapping("/lector/{lectorId}")
    public List<Prestamo> listarPrestamosActivosLector(@PathVariable Long lectorId) {
        return prestamoService.listarPrestamosActivosLector(lectorId);
    }
}