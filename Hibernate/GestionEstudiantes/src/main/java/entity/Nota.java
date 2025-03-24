package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    // Constructor vacío
    public Nota() {}

    // Constructor con parámetros
    public Nota(Double valor, Date fecha, Curso curso, Estudiante estudiante) {
        this.valor = valor;
        this.fecha = fecha;
        this.curso = curso;
        this.estudiante = estudiante;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public Curso getCurso() {
        return curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", valor=" + valor +
                ", fecha=" + fecha +
                ", curso=" + curso +
                ", estudiante=" + estudiante +
                '}';
    }
}
