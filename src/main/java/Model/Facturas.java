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
    private Integer neto;

    @Column (name = "iva")
    @NotNull
    private Integer iva;

    @Column (name = "harina")
    private Integer harina;

    @Column (name = "ila")
    private Integer ila;

    @Column (name = "carne")
    private Integer carne;

    @Column (name = "total")
    @NotNull
    private Integer total;

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

    public Integer getNeto() {
        return neto;
    }

    public void setNeto(Integer neto) {
        this.neto = neto;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Integer getHarina() {
        return harina;
    }

    public void setHarina(Integer harina) {
        this.harina = harina;
    }

    public Integer getIla() {
        return ila;
    }

    public void setIla(Integer ila) {
        this.ila = ila;
    }

    public Integer getCarne() {
        return carne;
    }

    public void setCarne(Integer carne) {
        this.carne = carne;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
