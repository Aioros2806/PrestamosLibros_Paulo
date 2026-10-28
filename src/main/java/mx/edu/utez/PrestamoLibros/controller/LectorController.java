package mx.edu.utez.PrestamoLibros.controller;

import mx.edu.utez.PrestamoLibros.model.Lector;
import mx.edu.utez.PrestamoLibros.service.LectorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectores")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @PostMapping
    public Lector crearLector(@RequestBody Lector lector) {
        return lectorService.crearLector(lector);
    }

    //rehabilitarLector
    @PutMapping("/{id}/rehabilitar")
    public Lector rehabilitarLector(@PathVariable Long id) {
        return lectorService.rehabilitarLector(id);
    }
}