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

    public static Empresa getUserById(String rut) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");;
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction = null;
       try{
           entityTransaction = entityManager.getTransaction();
           entityTransaction.begin();

           Empresa empresa = entityManager.find(Empresa.class, rut);
           entityTransaction.commit();
           return empresa;
       }catch (RuntimeException e){
           if (entityTransaction.isActive())entityTransaction.rollback();
           throw e;
       }
    }

    public Set<Facturas> getFacturas() {
        return facturas;
    }

    public void setFacturas(Set<Facturas> facturas) {
        this.facturas = facturas;
    }

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
