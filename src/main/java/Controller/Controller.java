package Controller;

import Model.Empresa;
import Model.Facturas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controller {
    private Date fecha;
    private Double Harina;
    private Double Total;
    private Double Carne;
    private String Rut;
    private Double Iva;
    private Double Ila;
    private String Numero;
    private String Nombre;
    private Double Neto;

    public Controller(){

    }

    public boolean Asignacion (@org.jetbrains.annotations.NotNull List<String> factura){
        try {
            Facturas facturaNueva = new Facturas();
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            facturaNueva.setFecha(formatter1.parse(factura.get(0)));
            facturaNueva.setEmpresa(Empresa.getUserById(factura.get(1)));
            facturaNueva.setNumFactura(factura.get(2));
            facturaNueva.setNeto(Double.parseDouble(factura.get(3)));
            //System.out.println("neto"+this.Neto);
            facturaNueva.setIva(Double.parseDouble(factura.get(4)));
            //System.out.println("iva"+this.Iva);
            facturaNueva.setCarne(Double.parseDouble(factura.get(5)));
            //System.out.println("carne"+this.Carne);
            facturaNueva.setHarina(Double.parseDouble(factura.get(6)));
            //System.out.println("harina"+this.Harina);
            facturaNueva.setIla(Double.parseDouble(factura.get(7)));
            //System.out.println("ila"+this.Ila);
            facturaNueva.setTotal(Double.parseDouble(factura.get(8)));
            //System.out.println("total"+this.Total);
            Push(facturaNueva);
            return true;
        }catch (Exception e){
            //System.out.println(factura);
            //System.out.println(e);
            return false;
        }
    }

    public boolean Push (Facturas factura){

        /*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        Facturas nuevaFactura = factura;
        session.save(nuevaFactura);
        tx1.commit();
        sessionFactory.close();*/
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(factura);
        entityManager.getTransaction().commit();

            entityManager.close();

        return true;
    }

    public List<Double> TotalAuto(){
        return null;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getHarina() {
        return Harina;
    }

    public void setHarina(Double harina) {
        this.Harina = harina;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        this.Total = total;
    }

    public Double getCarne() {
        return Carne;
    }

    public void setCarne(Double carne) {
        this.Carne = carne;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String rut) {
        this.Rut = rut;
    }

    public Double getIva() {
        return Iva;
    }

    public void setIva(Double iva) {
        this.Iva = iva;
    }

    public Double getIla() {
        return Ila;
    }

    public void setIla(Double ila) {
        this.Ila = ila;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        this.Numero = numero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public Double getNeto() {
        return Neto;
    }

    public void setNeto(Double neto) {
        this.Neto = neto;
    }
}