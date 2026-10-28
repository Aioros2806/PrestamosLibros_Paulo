package mx.edu.utez.PrestamoLibros.dto;

import jakarta.validation.constraints.NotNull;

public class PrestamoDTO {

    @NotNull(message = "El ID es obligatorio")
    private Long lectorId;

    @NotNull(message = "El ID_Libro es obligatorio")
    private Long libroId;

    public Long getLectorId() { return lectorId; }
    public void setLectorId(Long lectorId) { this.lectorId = lectorId; }

    public Long getLibroId() { return libroId; }
    public void setLibroId(Long libroId) { this.libroId = libroId; }
}