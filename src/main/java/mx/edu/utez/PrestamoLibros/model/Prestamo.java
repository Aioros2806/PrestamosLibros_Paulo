package mx.edu.utez.PrestamoLibros.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector lector;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionMaxima;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    public Prestamo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Lector getLector() { return lector; }
    public void setLector(Lector lector) { this.lector = lector; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucionMaxima() { return fechaDevolucionMaxima; }
    public void setFechaDevolucionMaxima(LocalDate fechaDevolucionMaxima) { this.fechaDevolucionMaxima = fechaDevolucionMaxima; }

    public EstadoPrestamo getEstado() { return estado; }
    public void setEstado(EstadoPrestamo estado) { this.estado = estado; }
}