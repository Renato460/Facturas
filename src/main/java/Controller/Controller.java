package Controller;

import Model.Empresa;
import Model.Facturas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.List;

public class Controller {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Controller(){

    }

    public boolean Asignacion (@org.jetbrains.annotations.NotNull List<String> factura){
        try {
            Facturas facturaNueva = new Facturas();
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            facturaNueva.setTipo(factura.get(0));
            facturaNueva.setFecha(formatter1.parse(factura.get(1)));
            facturaNueva.setEmpresa(Empresa.getUserById(factura.get(2)));
            facturaNueva.setNumFactura(factura.get(3));
            if (factura.get(0).equals("Factura"))
            {

                facturaNueva.setNeto(Double.parseDouble(factura.get(4)));
                //System.out.println("neto"+this.Neto);
                facturaNueva.setIva(Double.parseDouble(factura.get(5)));
                //System.out.println("iva"+this.Iva);
                facturaNueva.setCarne(Double.parseDouble(factura.get(6)));
                //System.out.println("carne"+this.Carne);
                facturaNueva.setHarina(Double.parseDouble(factura.get(7)));
                //System.out.println("harina"+this.Harina);
                facturaNueva.setIla(Double.parseDouble(factura.get(8)));
                //System.out.println("ila"+this.Ila);
                facturaNueva.setTotal(Double.parseDouble(factura.get(9)));
                //System.out.println("total"+this.Total);
                //PushFactura(facturaNueva);
                return PushFactura(facturaNueva);
            }else if (factura.get(0).equals("Nota de Credito")){

                facturaNueva.setNeto((-1)*Double.parseDouble(factura.get(4)));
                //System.out.println("neto"+this.Neto);
                facturaNueva.setIva((-1)*Double.parseDouble(factura.get(5)));
                //System.out.println("iva"+this.Iva);
                facturaNueva.setCarne((-1)*Double.parseDouble(factura.get(6)));
                //System.out.println("carne"+this.Carne);
                facturaNueva.setHarina((-1)*Double.parseDouble(factura.get(7)));
                //System.out.println("harina"+this.Harina);
                facturaNueva.setIla((-1)*Double.parseDouble(factura.get(8)));
                //System.out.println("ila"+this.Ila);
                facturaNueva.setTotal((-1)*Double.parseDouble(factura.get(9)));
                //System.out.println("total"+this.Total);
                //PushFactura(facturaNueva);
                return PushFactura(facturaNueva);
            }else {
                return false;
            }
        }catch (Exception e){
            //System.out.println(factura);
            //System.out.println(e);
            return false;
        }
    }

     private boolean PushFactura(Facturas factura){
        try{
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(factura);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}