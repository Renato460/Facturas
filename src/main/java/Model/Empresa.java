package Model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @Column(name = "rut")
    @NotNull
    private String rut;

    @Column(name = "nombre")
    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "empresa")
    private Set<Facturas> facturas;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String empresa) {
        this.nombre = empresa;
    }
}
