package Controller;

import java.util.List;

public class Controller {
    private Double textHarina;
    private Double textTotal;
    private Double textCarne;
    private String textRut;
    private Double textIva;
    private Double textIla;
    private String textNumero;
    private String textNombre;
    private Double textNeto;

    public Controller(){

    }

    public boolean Asignacion (@org.jetbrains.annotations.NotNull List<String> factura){
        try {

            this.textRut = factura.get(0);
            this.textNumero = factura.get(1);
            this.textNombre = factura.get(2);
            this.textNeto = Double.parseDouble(factura.get(3));
            System.out.println("neto"+this.textNeto);
            this.textIva = Double.parseDouble(factura.get(4));
            System.out.println("iva"+this.textIva);
            this.textCarne = Double.parseDouble(factura.get(5));
            System.out.println("carne"+this.textCarne);
            this.textHarina = Double.parseDouble(factura.get(6));
            System.out.println("harina"+this.textHarina);
            this.textIla = Double.parseDouble(factura.get(7));
            System.out.println("ila"+this.textIla);
            this.textTotal = Double.parseDouble(factura.get(8));
            System.out.println("total"+this.textTotal);
            return true;
        }catch (Exception e){
            //System.out.println(factura);
            System.out.println(e);
            return false;
        }
    }

    public List<Double> TotalAuto(){
        return null;
    }
}