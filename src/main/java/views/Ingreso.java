package views;

import Controller.Controller;
import Model.Empresa;
import Model.Facturas;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JCheckBox notaDeCréditoCheckBox;
    private JTextField textFecha;
    private JLabel rutRequerido;
    //private List<String> facturaRevisada;

    public Ingreso(){
        add(jPanelP);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(768,480);
        this.rutRequerido.setVisible(false);
        this.numeroRequerido.setVisible(false);
        this.nombreRequerido.setVisible(false);
        this.netoRequerido.setVisible(false);
        this.setVisible(true);


        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(notaDeCréditoCheckBox.isSelected()){
                    List<String> nota = new ArrayList<String>();
                    nota.add(textFecha.getText());
                    nota.add(textRut.getText());
                    nota.add(textNumero.getText());
                    nota.add(textNombre.getText());
                    nota.add(textNeto.getText());
                    nota.add(textIva.getText());
                    nota.add(textCarne.getText());
                    nota.add(textHarina.getText());
                    nota.add(textIla.getText());
                    nota.add(textTotal.getText());
                    System.out.println(nota);
                    Controller enviar = new Controller();
                    listaValidacion(nota);
                    if(!enviar.Asignacion(nota)){
                        JOptionPane.showMessageDialog(null,"Ingreso incorrecto, verifique datos","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else
                    {
                        List<String> factura = new ArrayList<String>();
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
                        if(!enviar.Asignacion(factura)){
                            JOptionPane.showMessageDialog(null,"Ingreso incorrecto, verifique datos","Error",JOptionPane.ERROR_MESSAGE);
                        }else{
                            Facturas facturas = new Facturas();

                        }

                    }
            }
        });

       /* buttonFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Empresa empresa = getUserById(textRut.getText());
                    textNombre.setText(empresa.getNombre());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"No existe Empresa", "Upss", JOptionPane.ERROR_MESSAGE);
                }
            }
        });*/
        textRut.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    Empresa empresa = getUserById(textRut.getText());
                    textNombre.setText(empresa.getNombre());
                }catch (Exception ex){
                    textNombre.setText("No existe empresa");

                    //JOptionPane.showMessageDialog(null,"No existe Empresa", "Upss", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        textRut.addKeyListener(new KeyAdapter() {
        });
        textRut.addKeyListener(new KeyAdapter() {
        });
    }

   private void listaValidacion(@NotNull List<String> factura){
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

                   } else if (i == 0 || i == 1 || i == 2 || i == 3) {
                       switch (i) {
                           case 0:
                               this.rutRequerido.setVisible(true);
                           case 1:
                               this.netoRequerido.setVisible(true);
                               break;
                           case 2:
                               this.numeroRequerido.setVisible(true);
                               break;
                           case 3:
                               this.nombreRequerido.setVisible(true);
                               break;
                       }
                   }
               }
       }
       System.out.println(factura);
   }
}
