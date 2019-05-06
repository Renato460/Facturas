package views;

import Controller.Controller;
import Model.Empresa;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Model.Empresa.getUserById;

public class Ingreso extends JFrame {
    private JTextField textHarina;
    private JTextField textTotal;
    private JTextField textIla;
    private JTextField textRut;
    private JTextField textIva;
    private JTextField textCarne;
    private JTextField textNumero;
    private JTextField textNombre;
    private JTextField textNeto;
    private JButton ingresarButton;
    private JPanel jPanelP;
    private JLabel numeroRequerido;
    private JLabel nombreRequerido;
    private JLabel netoRequerido;
    private JCheckBox notaCheck;
    private JTextField textFecha;
    private JLabel rutRequerido;
    private JLabel labelFac;
    //private List<String> facturaRevisada;
    private Double neto;
    private Double iva;
    private Double carne;
    private Double harina;
    private Double ila;
    private Double total;

    public Ingreso(){
        add(jPanelP);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(768,480);
        this.setLocationRelativeTo(null);
        this.rutRequerido.setVisible(false);
        this.numeroRequerido.setVisible(false);
        this.nombreRequerido.setVisible(false);
        this.netoRequerido.setVisible(false);
        this.setVisible(true);
        this.textNeto.setText("0");
        this.textIva.setText("0");
        this.textCarne.setText("0");
        this.textHarina.setText("0");
        this.textIla.setText("0");


        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> factura = new ArrayList<String>();
                if(notaCheck.isSelected()){
                       factura.add("Nota de Credito");
                }else
                    {
                       factura.add("Factura");
                    }

                factura.add(textFecha.getText());
                factura.add(textRut.getText());
                factura.add(textNumero.getText());
                factura.add(textNeto.getText());
                factura.add(textIva.getText());
                factura.add(textCarne.getText());
                factura.add(textHarina.getText());
                factura.add(textIla.getText());
                factura.add(textTotal.getText());
                System.out.println(factura);
                Controller enviar = new Controller();
                listaValidacion(factura);
                boolean exito = enviar.Asignacion(factura);
                if(!exito){
                    JOptionPane.showMessageDialog(null,"Ingreso incorrecto, verifique datos","Error",JOptionPane.ERROR_MESSAGE);
                }else if(exito){
                    facturaATexto(factura);
                    textFecha.setText("");
                    textRut.setText("");
                    textNumero.setText("");
                    textNombre.setText("");
                    textNeto.setText("0");
                    textIva.setText("0");
                    textCarne.setText("0");
                    textHarina.setText("0");
                    textIla.setText("0");
                    textTotal.setText("0");
                    System.out.println("llegue aca");
                }
            }
        });

        textRut.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    Empresa empresa = getUserById(textRut.getText());
                    textNombre.setText(empresa.getNombre());
                }catch (Exception ex){
                    textNombre.setText("No existe empresa");
                }
            }
        });
        notaCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(notaCheck.isSelected()){
                    ingresarButton.setText("Ingresar Nota");
                    labelFac.setText("N° Nota Cred:");

                }else
                {
                    ingresarButton.setText("Ingresar Factura");
                    labelFac.setText("N° Factura");
                }
            }
        });
        textNeto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try{
                    neto = Double.parseDouble(textNeto.getText());
                    iva = (neto*19)/100;
                    textIva.setText(iva.toString());
                    carne = Double.parseDouble(textCarne.getText());
                    harina = Double.parseDouble(textHarina.getText());
                    ila = Double.parseDouble(textIla.getText());
                    total = neto + iva + carne + harina + ila;
                    textTotal.setText(total.toString());
                   // System.out.println(total);
                }catch (Exception ex){

                    System.out.println(textNeto.getText());
                }
            }
        });
        textCarne.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try{
                    carne = Double.parseDouble(textCarne.getText());
                    total=neto + iva + carne + harina + ila;
                    textTotal.setText(total.toString());
                }catch (Exception ee){

                }
            }
        });
        textHarina.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try{
                    harina = Double.parseDouble(textHarina.getText());
                    total=neto + iva + carne + harina + ila;
                    textTotal.setText(total.toString());
                }catch (Exception ee){

                }
            }
        });
        textIla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try{
                    ila = Double.parseDouble(textIla.getText());
                    total=neto + iva + carne + harina + ila;
                    textTotal.setText(total.toString());
                }catch (Exception ee){

                }
            }
        });
    }

   private void listaValidacion(@NotNull List<String> factura){
       for (int i = 0; i < factura.size(); i++ ) {
               if (factura.get(i).equals("")) {
                   if (i == 5 || i == 6 || i == 7 || i == 8) {
                       switch (i) {
                           case 5:
                               textIva.setText("0");
                               break;
                           case 6:
                               textCarne.setText("0");
                               break;
                           case 7:
                               textHarina.setText("0");
                               break;
                           case 8:
                               textIla.setText("0");
                               break;
                       }

                   } else if (i == 1 || i == 2 || i == 3 || i == 4) {
                       switch (i) {
                           case 1:
                               this.rutRequerido.setVisible(true);
                           case 2:
                               this.netoRequerido.setVisible(true);
                               break;
                           case 3:
                               this.numeroRequerido.setVisible(true);
                               break;
                           case 4:
                               this.nombreRequerido.setVisible(true);
                               break;
                       }
                   }
               }
       }
       System.out.println(factura);
   }

   private void facturaATexto(List<String> factura){
        String path = "/home/beto/Escritorio/Proyecto/Facturas-master/src/main/resources/facturas.txt";
        File TextFile = new File(path);
       FileWriter TextOut = null;
       try {
           TextOut = new FileWriter(TextFile, true);
       } catch (IOException e) {
           e.printStackTrace();
       }
       String facturaDatos="";
       for ( String f: factura ) {
           facturaDatos+=";"+f;
       }
       try {
           TextOut.write(facturaDatos);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
