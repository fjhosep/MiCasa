/*
* AUTOR: Omar Figueroa
* Fecha: 09 de Septiembre de 2019
*/

package Controlador;

import Vista.frmConsultaIglesia;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ConsultaIglesia;
import modelo.Iglesia;

public class CtrlConsultaIglesia implements ActionListener, MouseListener, KeyListener, ChangeListener {
    
    // Instanciamos el formulario, el modelo, y la consulta
    public static void main(String[] args) {
        Iglesia mod = new Iglesia();
        ConsultaIglesia modConsulta = new ConsultaIglesia();
        frmConsultaIglesia frm = new frmConsultaIglesia();
                                 
        CtrlConsultaIglesia ctrl = new CtrlConsultaIglesia(mod, modConsulta, frm);
        ctrl.iniciar();  
    }
          
    // llamamos a las clases creadas
    private Iglesia mod;
    private ConsultaIglesia modConsulta;
    private frmConsultaIglesia frm;
     
    //definimos la variable tipo TableRowSorter para el filtro de datos;
    private TableRowSorter trsfiltro;
    private Component rootPane;
    
    private Integer idIglesia           = null;
    private String  nombreIglesia       = null;
    private String  pastorIglesia       = null;
    private String  direccionIglesia    = null;
    private String  ciudadIglesia       = null;          
    private String  estadoIglesia       = null;
    private String  paisIglesia         = null;
    private String  tlfIglesia          = null;
    private String  tlf1Iglesia         = null;
     
    // Constructor de la Clase
    public CtrlConsultaIglesia(Iglesia mod, ConsultaIglesia modConsulta, frmConsultaIglesia frm) {
       
        //igualamos las variables recibidas 
       this.mod = mod;
       this.modConsulta = modConsulta;
       this.frm = frm; 
            
       // Declaramos el action del boton salir
       this.frm.btnSalir.addActionListener(this);
       //Declaranmos el action para el evento de teclado
       this.frm.txtBuscar.addKeyListener(this);
       this.frm.jtIglesias.addMouseListener(this);
       //javax.swing.event.ChangeListener l = null;
       this.frm.jTabbedRegistro.addChangeListener(this);
    } 
    
    public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Iglesia Comunidad de Amor (Consulta de Iglesias)");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false); 
        frm.toFront();
        frm.grupoBusqueda.add(frm.jRIglesia);
        frm.grupoBusqueda.add(frm.jRPastor);
        tabla();        
    }     

        // Habilitar los elementos de la tabla
    public void tabla(){        
        DefaultTableModel modelo;        
        modelo = modConsulta.mostrar(mod);  
        frm.jtIglesias.setModel(modelo) ;
        //Definir el tamaño de las columanas
        int[] anchos = {150, 150, 150, 150, 100, 100, 100, 100}; 
            for(int x =0; x<8; x++) {
                frm.jtIglesias.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
        //Ocultamos laColumna del ID
        frm.jtIglesias.getColumnModel().getColumn(0).setMaxWidth(0);
        frm.jtIglesias.getColumnModel().getColumn(0).setMinWidth(0);
        frm.jtIglesias.getColumnModel().getColumn(0).setPreferredWidth(0);
           
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtIglesias.getRowCount();
        frm.lbtTotal_Registro.setText("Total Registros: " + fila);             
    }
    
    // Método para salir del formulario
    public void salir(){
        frm.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       // Leer Boton Salir 
        if(ae.getSource()== frm.btnSalir) {
              salir();
        }  
    }
     
    @Override
    public void mouseClicked(MouseEvent me) {
            int Fila = frm.jtIglesias.getSelectedRow();  
            idIglesia           = Integer.valueOf(frm.jtIglesias.getValueAt(Fila, 0).toString());
            nombreIglesia       = frm.jtIglesias.getValueAt(Fila, 1).toString();
            pastorIglesia       = frm.jtIglesias.getValueAt(Fila, 2).toString();
            direccionIglesia    = frm.jtIglesias.getValueAt(Fila, 3).toString();
            ciudadIglesia       = frm.jtIglesias.getValueAt(Fila, 4).toString();           
            estadoIglesia       = frm.jtIglesias.getValueAt(Fila, 5).toString();
            paisIglesia         = frm.jtIglesias.getValueAt(Fila, 6).toString();
            tlfIglesia          = frm.jtIglesias.getValueAt(Fila, 7).toString();
            tlf1Iglesia         = frm.jtIglesias.getValueAt(Fila, 8).toString();
    }

    @Override
    public void mousePressed(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) { }

    @Override
    public void mouseExited(MouseEvent me) { }
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        JTabbedPane tabbedPane = (JTabbedPane) ce.getSource();
        int selectedIndex = frm.jTabbedRegistro.getSelectedIndex();
        if (selectedIndex == 1){       
            this.frm.txtIglesia.setText(nombreIglesia);
            this.frm.txtPastor.setText(pastorIglesia);
            this.frm.txtDireccion.setText(direccionIglesia); 
            this.frm.txtCiudad.setText(ciudadIglesia);
            this.frm.txtEstado.setText(estadoIglesia);
            this.frm.txtPais.setText(paisIglesia);
            this.frm.txtTelefono.setText(tlfIglesia);
            this.frm.txtTelefono1.setText(tlf1Iglesia);
        } 
    }    
    
    @Override
    public void keyReleased(KeyEvent ke) {
            if (ke.getSource()== frm.txtBuscar) {
            trsfiltro = new TableRowSorter(frm.jtIglesias.getModel());
            frm.jtIglesias.setRowSorter(trsfiltro);
            
            if (frm.jRIglesia.isSelected() == true) {
                trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+frm.txtBuscar.getText(), 1));
            } else if (frm.jRPastor.isSelected() == true){
                trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+frm.txtBuscar.getText(), 2));                
            } else {
               JOptionPane.showMessageDialog(null, "Seleccione una opción para la búsqueda");   
            }
        }
        
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtIglesias.getRowCount();
        frm.lbtTotal_Registro.setText("Total Registros: " + fila); 
    }    

    @Override
    public void keyTyped(KeyEvent ke) { }

    @Override
    public void keyPressed(KeyEvent ke) { }   
}
