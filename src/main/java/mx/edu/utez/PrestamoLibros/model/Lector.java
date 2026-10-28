package mx.edu.utez.PrestamoLibros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lectores")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Boolean activo = true;
    private Boolean membresiaPremium = false;

    public Lector() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Boolean getMembresiaPremium() { return membresiaPremium; }
    public void setMembresiaPremium(Boolean membresiaPremium) { this.membresiaPremium = membresiaPremium; }
}