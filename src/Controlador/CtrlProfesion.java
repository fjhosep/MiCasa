/*
* AUTOR: Omar Figueroa
* Fecha: 18 de junio de 2019
*/
package Controlador;

import Vista.frmProfesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ConsultaProfesion;
import modelo.Profesion;


public class CtrlProfesion implements ActionListener, MouseListener, KeyListener {

    //private TableRowSorter filtro;
    private TableRowSorter trsfiltro;
    String filtro;
    
    public static void main(String[] args) {
        Profesion mod = new Profesion();
        ConsultaProfesion modConsulta = new ConsultaProfesion();
        frmProfesion frm = new frmProfesion();
        
        CtrlProfesion ctrl = new CtrlProfesion(mod, modConsulta, frm);
        ctrl.iniciar();
        frm.setVisible(true);
        frm.setResizable(false); 
        
    }    
      
    // llamamos a las clases creadas
    private Profesion mod;
    private ConsultaProfesion modConsulta;
    private frmProfesion frm;
    
    // Constructor de la Clase
    public CtrlProfesion(Profesion mod, ConsultaProfesion modConsulta, frmProfesion frm) {
       //igualamos las variables recibidas 
       this.mod = mod;
       this.modConsulta = modConsulta;
       this.frm = frm;
       
       // Declaramos los accion para cada uno de los botones
        this.frm.btnNuevo.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnSalir.addActionListener(this);
        this.frm.btnCancelar.addActionListener(this);
       //Declaramos el accion par el evento del Mouse 
        this.frm.jtProfesion.addMouseListener(this);
       //Declaramos el action para el evento de teclado
        this.frm.txtBuscar.addKeyListener(this);
        
    }
    
    // Método para crear la vista
    public void iniciar() {
        frm.setTitle("Profesión");
        frm.setLocationRelativeTo(null);
        frm.toFront();
        //frm.btnId.setVisible(false);
        inhabilitar();
        tabla();  
      
        
    } 
    
    // Métodos que van a escuchar los click a los botones
    @Override
    public void actionPerformed (ActionEvent e){
        
        // Leer Boton Nuevo
         if(e.getSource()== frm.btnNuevo) {
             this.habilitar();
         }
        
        // Leer Boton Guardar
         if(e.getSource()== frm.btnGuardar) {
             mod.setDenominacion_profesion(frm.txtProfesion.getText());
             if(modConsulta.registrar(mod)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 tabla(); 
                 limpiar();
             } else {
                 JOptionPane.showMessageDialog(null, "Registro no Guardado");
                 limpiar();
             }
         } 
         
        // Leer Boton Modificar
         if(e.getSource()== frm.btnModificar) {
             mod.setDenominacion_profesion(frm.txtProfesion.getText()); 
             mod.setDenominacion_profesion(frm.txtProfesion.getText()); 
             if(modConsulta.modificar(mod)){ 
                 JOptionPane.showMessageDialog(null, "Registro Modificado");
                 tabla(); 
                 limpiar();
             } else {
                 JOptionPane.showMessageDialog(null, "Registro no Modificado");
                 limpiar();
             }
         }    
        // Leer Boton Eliminar
         if(e.getSource()== frm.btnEliminar) {
             mod.setDenominacion_profesion(frm.txtProfesion.getText());
             if(modConsulta.eliminar(mod)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminado");
                 tabla();  
                 limpiar();
             } else {
                 JOptionPane.showMessageDialog(null, "Registro no Eliminado");
                 limpiar();
             }
         } 
             
        // Leer Boton Limpiar 
        if(e.getSource()== frm.btnLimpiar) {
              limpiar();
        }
        // Leer Boton Salir 
        if(e.getSource()== frm.btnSalir) {
              salir();
        }    
        // Leer Boton Cancelar
        if(e.getSource()== frm.btnCancelar) {
              inhabilitar();
        }          
    }
    
    // Habilitar los elementos de la tabla
    public void tabla(){
            DefaultTableModel modelo;    
            modelo = modConsulta.mostrar(mod);
            frm.jtProfesion.setModel(modelo) ;
            frm.lbtTotalRegistros.setText("Total Registros: " + Integer.toString(modConsulta.totalRegistro));
    }
      
    // Método para limpiar el formulario
    public void limpiar(){
        frm.txtProfesion.setText(null);
    }    
    
    // Método para salir del formulario
    public void salir(){
        frm.dispose();
    }
    // Método para inabilitar los objetos
    public void inhabilitar(){
        frm.txtProfesion.setEnabled(false);
        frm.btnGuardar.setEnabled(false);
        frm.btnLimpiar.setEnabled(false);
    }
    
    // Método para habilitar los objetos
    public void habilitar(){
        frm.txtProfesion.setEnabled(true); 
        frm.btnGuardar.setEnabled(true);
        frm.btnLimpiar.setEnabled(true);
    }
    
    // Eventos del Ratón
    //Evento para colocar en la caja txt el registro seleccinado de la tabla
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frm.jtProfesion ){
            PreparedStatement ps = null;
            ResultSet rs = null;
        
            int Fila = frm.jtProfesion.getSelectedRow();
            String denominacionProfesion = frm.jtProfesion.getValueAt(Fila, 0).toString();
                     
            frm.txtProfesion.setEnabled(true); 
            this.frm.txtProfesion.setText(denominacionProfesion); 
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {  }
    
    public void filtro() {
        filtro = frm.txtBuscar.getText();
        trsfiltro.setRowFilter(RowFilter.regexFilter(frm.txtBuscar.getText(), 0));
    }
    
    // Eventos del Teclado
    @Override
    public void keyTyped(KeyEvent e) {
        trsfiltro = new TableRowSorter(frm.jtProfesion.getModel());
        frm.jtProfesion.setRowSorter(trsfiltro);
    }

    @Override
    public void keyReleased(KeyEvent e) { 
          System.out.println(frm.txtBuscar.getText());
          String cadena = (frm.txtBuscar.getText());
          frm.txtBuscar.setText(cadena);
          filtro();
          
          int fila = frm.jtProfesion.getRowCount();
          frm.lbtTotalRegistros.setText("Total Registros: " + fila);          
          // System.out.println(fila); 
    }
    
    @Override
    public void  keyPressed(KeyEvent e) { }
}
