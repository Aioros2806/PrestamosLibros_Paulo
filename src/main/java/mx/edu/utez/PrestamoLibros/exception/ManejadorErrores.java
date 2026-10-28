package mx.edu.utez.PrestamoLibros.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(MiExcepcion.class)
    public ResponseEntity<Map<String, String>> manejarMiExcepcion(MiExcepcion ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> respuesta = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                respuesta.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(respuesta);
    }
}