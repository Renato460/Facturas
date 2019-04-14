package Model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "facturas")
public class Facturas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column (name = "fecha")
    @NotNull
    @Type(type="date")
    private Date fecha;

    @Column (name = "numFactura")
    @NotNull
    private String numFactura;

    @Column (name = "neto")
    @NotNull
    private Double neto;

    @Column (name = "iva")
    @NotNull
    private Double iva;

    @Column (name = "harina")
    private Double harina;

    @Column (name = "ila")
    private Double ila;

    @Column (name = "carne")
    private Double carne;

    @Column (name = "total")
    @NotNull
    private Double total;

    @ManyToOne
    @JoinColumn(name="rut", nullable=false)
    private Empresa empresa;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public Double getNeto() {
        return neto;
    }

    public void setNeto(Double neto) {
        this.neto = neto;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getHarina() {
        return harina;
    }

    public void setHarina(Double harina) {
        this.harina = harina;
    }

    public Double getIla() {
        return ila;
    }

    public void setIla(Double ila) {
        this.ila = ila;
    }

    public Double getCarne() {
        return carne;
    }

    public void setCarne(Double carne) {
        this.carne = carne;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
