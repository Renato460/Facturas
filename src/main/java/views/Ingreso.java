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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    private JButton pathFile;
    //private List<String> facturaRevisada;
    private Double neto;
    private Double iva;
    private Double carne;
    private Double harina;
    private Double ila;
    private Double total;
    private String path;// = "/home/beto/Escritorio/Proyecto/Facturas-master/src/main/resources/facturas.txt";

    public Ingreso(){
        //this.setIconImage(new ImageIcon("list.png").getImage());
        add(jPanelP);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Facturas y notas de Crédito");
        this.setSize(768,480);
        this.setLocationRelativeTo(null);
        this.rutRequerido.setVisible(false);
        this.numeroRequerido.setVisible(false);
        this.nombreRequerido.setVisible(false);
        this.netoRequerido.setVisible(false);
        this.setVisible(true);
        this.textCarne.setText("0");
        this.textHarina.setText("0");
        this.textIla.setText("0");
        /*this.textRut.setText("81.229.500-4");
        this.textNombre.setText("ANSALDI Y CIA. LTDA.");
        this.textNumero.setText("43");
        this.textNeto.setText("3000");
        this.textIva.setText("234");
        this.textCarne.setText("0");
        this.textHarina.setText("0");
        this.textIla.setText("0");
        this.textTotal.setText("4555");*/


        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!Facturas.getFacturaById(textRut.getText(), textNumero.getText())){
                    List<String> factura = new ArrayList<String>();
                    if (notaCheck.isSelected()) {
                        factura.add("Nota de Credito");
                    } else {
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
                    if (!exito) {
                        JOptionPane.showMessageDialog(null, "Ingreso incorrecto, verifique datos", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
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
                }else {
                    JOptionPane.showMessageDialog(null, "Factura ya ingresada","Error",JOptionPane.ERROR_MESSAGE);
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
                    ee.printStackTrace();
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
                    ee.printStackTrace();

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
                    ee.printStackTrace();
                }
            }
        });
        pathFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File archivo;
                JFileChooser chooser = new JFileChooser(); // ruta donde se va a abrir por defecto el selector de archivos
                chooser.setCurrentDirectory(new java.io.File(".txt"));  //validamos el tipo de extensión
                if((chooser.showDialog(null, "Abrir"))== JFileChooser.APPROVE_OPTION){ // si le dieron clic en el botón abrir
                    archivo = chooser.getSelectedFile(); // selecciona el archivo y lo guarda en archivo
                    if(archivo.canRead()){ // si se puede leer el archivo
                        if(archivo.getName().endsWith("txt")){ // se valida el tipo de extensión denuevo
                            //path =archivo.getAbsoluteFile().toString(); // la ruta del archivo se la asigna a ruta
                            Properties p = new Properties();
                            try {
                                InputStream propertiesStream = ClassLoader.getSystemResourceAsStream("config.properties");
                                //InputStream propertiesStream = new FileInputStream("config.properties");
                                p.load(propertiesStream);
                                p.setProperty("path", archivo.getAbsoluteFile().toString());
                                File file = new FileWriter(getClass().getResource("config.properties").getFile());
                                p.store( new FileWriter(file) , "Archivo de salida txt");
                                System.out.println(p.getProperty("path"));
                                propertiesStream.close();
                            }catch (IOException ex){
                                ex.printStackTrace();
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "Archivo con diferente extensión","Error al cargar el archivo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
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

   private void facturaATexto(@NotNull List<String> factura){
       Properties p = new Properties();
       try {
           InputStream configInput = ClassLoader.getSystemResourceAsStream("config.properties");
           //InputStream configInput= new FileInputStream("config.properties");
           //p.load(new FileReader("resources/Propiedades/config.properties"));
           //p.load(propertiesStream);
           //InputStream configInput = new FileInputStream("/home/beto/Proyectos/web/Facturas-master/src/main/resources/config.properties");
           p.load(configInput);
           System.out.println(p.getProperty("path"));
           path = p.getProperty("path");
       } catch (IOException e) {
           e.printStackTrace();
       }


       File TextFile = new File(path);
       BufferedWriter bw = null;
       FileWriter fw;
       StringBuilder facturas = new StringBuilder(textNombre.getText() + ";");
       for (String fac: factura) {
           facturas.append(fac.toUpperCase()).append(";");
       }
       try {
           fw = new FileWriter(TextFile, true);
           bw = new BufferedWriter(fw);
            bw.write(facturas+System.getProperty("line.separator"));
            bw.flush();
           System.out.println(facturas);
       }catch(IOException ex ){
           ex.printStackTrace();

       }finally {

           if (bw != null)
               try {
                   bw.close();

               } catch (IOException ioe2)
               {
                   ioe2.printStackTrace();
               }
       }
   }
}