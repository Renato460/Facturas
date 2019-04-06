package views;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel rutRequerido;
    private JLabel numeroRequerido;
    private JLabel nombreRequerido;
    private JLabel netoRequerido;
    private JPanel jDate;
    //private List<String> facturaRevisada;

    public Ingreso(){
        add(jPanelP);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480,400);
        this.rutRequerido.setVisible(false);
        this.numeroRequerido.setVisible(false);
        this.nombreRequerido.setVisible(false);
        this.netoRequerido.setVisible(false);
        this.setVisible(true);

        textRut.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                List<String> factura = new ArrayList<String>();
                factura.add(textRut.getText());
                factura.add(textNumero.getText());
                factura.add(textNombre.getText());
                factura.add(textNeto.getText());
                factura.add(textIva.getText());
                factura.add(textCarne.getText());
                factura.add(textHarina.getText());
                factura.add(textIla.getText());
                factura.add(textTotal.getText());
                listaValidacion(factura);

            }
        });

        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                List<String> factura = new ArrayList<String>();
                factura.add(textRut.getText());
                factura.add(textNumero.getText());
                factura.add(textNombre.getText());
                factura.add(textNeto.getText());
                factura.add(textIva.getText());
                factura.add(textCarne.getText());
                factura.add(textHarina.getText());
                factura.add(textIla.getText());
                factura.add(textTotal.getText());
                System.out.println(factura);
                Controller enviar = new Controller();

                if(!enviar.Asignacion(factura)){
                    JOptionPane.showMessageDialog(null,"Ingreso incorrecto, verifique datos","Error",1);
                }
            }
        });
    }

   private void listaValidacion(List<String> factura){
       for (int i = 0; i < factura.size(); i++ ) {
               if (factura.get(i).equals("")) {
                   if (i == 4 || i == 5 || i == 6 || i == 7) {
                       switch (i) {
                           case 4:
                               textIva.setText("0");
                               break;
                           case 5:
                               textCarne.setText("0");
                               break;
                           case 6:
                               textHarina.setText("0");
                               break;
                           case 7:
                               textIla.setText("0");
                               break;
                       }
                       //factura.set(i, "0");
                   } else if (i == 0 || i == 1 || i == 2 || i == 3) {
                       switch (i) {
                           case 0:
                               this.rutRequerido.setVisible(true);
                               break;
                           case 1:
                               this.numeroRequerido.setVisible(true);
                               break;
                           case 2:
                               this.nombreRequerido.setVisible(true);
                               break;
                           case 3:
                               this.netoRequerido.setVisible(true);
                       }
                   }
               }
       }
       System.out.println(factura);
   }
}
