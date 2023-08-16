/*
* AUTOR: Omar Figueroa
* Fecha: 16 de Julio de 2019
*/

package Controlador;

import Vista.frmMinisterio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ConsultaMinisterio;
import modelo.Ministerio;


public class CtrlMinisterio implements ActionListener, MouseListener, KeyListener {
    
    // Instanciamos el formulario, el modelo, y la consulta
    public static void main(String[] args) {
        Ministerio mod = new Ministerio();
        ConsultaMinisterio modConsulta = new ConsultaMinisterio();
        frmMinisterio frm = new frmMinisterio();
        
        CtrlMinisterio ctrl = new CtrlMinisterio(mod, modConsulta, frm);
        ctrl.iniciar();   
    } 

    // llamamos a las clases creadas
    private Ministerio mod;
    private ConsultaMinisterio modConsulta;
    private frmMinisterio frm;
    
    //definimos la variable tipo TableRowSorter para el filtro de datos;
    private TableRowSorter trsfiltro;
    
    // Constructor de la Clase
    public CtrlMinisterio(Ministerio mod, ConsultaMinisterio modConsulta, frmMinisterio frm) {
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
        this.frm.jtListado_Ministerio.addMouseListener(this);
       //Declaramos el action para el evento de teclado
        this.frm.txtBuscar.addKeyListener(this);       
    }
    
    // Método para crear la vista
    public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Ministerio");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false);    
        frm.lbtId_Ministerio.setVisible(false);
        frm.txtId_Ministerio.setVisible(false);
        inhabilitar();
        tabla();        
    }
    
    // Habilitar los elementos de la tabla
    public void tabla(){
             
        DefaultTableModel modelo;        
        modelo = modConsulta.mostrar(mod);  
        frm.jtListado_Ministerio.setModel(modelo) ;
        
        frm.lbtTotal_Registros.setText("Total Registros: " + Integer.toString(modConsulta.totalRegistro));    
        //Definir el tamaño de las columanas
        int[] anchos = {10,200,200}; 
            for(int x = 0; x<3; x++) {
                frm.jtListado_Ministerio.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
        //Ocultamos laColumna del ID
        frm.jtListado_Ministerio.getColumnModel().getColumn(0).setMaxWidth(0);
        frm.jtListado_Ministerio.getColumnModel().getColumn(0).setMinWidth(0);
        frm.jtListado_Ministerio.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
       
    // Método para inabilitar los objetos
    public void inhabilitar(){
        frm.txtDenominacion_Ministerio.setEnabled(false);
        frm.txtPastor_Asignado.setEnabled(false);
        frm.btnGuardar.setEnabled(false);
        frm.btnModificar.setEnabled(false);
        frm.btnEliminar.setEnabled(false);
        frm.btnLimpiar.setEnabled(false); 
    }
    
    // Método para habilitar los objetos
    public void habilitar(){
        frm.txtDenominacion_Ministerio.setEnabled(true);
        frm.txtPastor_Asignado.setEnabled(true);
        frm.btnGuardar.setEnabled(true);
        frm.btnLimpiar.setEnabled(true);
    }
    
    // Método para limpiar el formulario
    public void limpiar(){
        frm.txtDenominacion_Ministerio.setText(null);
        frm.txtPastor_Asignado.setText(null);
    }    
    
    // Método para salir del formulario
    public void salir(){
        frm.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Leer Boton Nuevo
        if(ae.getSource()== frm.btnNuevo) {
            this.habilitar();
        }
        // Leer Boton Guardar
        if(ae.getSource()== frm.btnGuardar) {
            mod.setDenominacion_ministerio(frm.txtDenominacion_Ministerio.getText());
            mod.setPastor_ministerio(frm.txtPastor_Asignado.getText());
            if(modConsulta.registrar(mod)){
                JOptionPane.showMessageDialog(null, "El Registro ha sido guardado");
                tabla(); 
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Registro no Guardado. Revise los Datos");
                limpiar();
            }
        }
        // Leer Boton Modificar
         if(ae.getSource()== frm.btnModificar) {
             mod.setId_ministerio(Integer.parseInt(frm.txtId_Ministerio.getText()));
             mod.setDenominacion_ministerio(frm.txtDenominacion_Ministerio.getText()); 
             mod.setPastor_ministerio(frm.txtPastor_Asignado.getText()); 
             if(modConsulta.modificar(mod)){ 
                 JOptionPane.showMessageDialog(null, "El Registro ha sido Modificado");
                 tabla(); 
                 limpiar();
                 inhabilitar();
             } else {
                 JOptionPane.showMessageDialog(null, "El Registro Registro no ha Modificado");
                 limpiar();
             }
         }    
         
         // Leer Boton Eliminar
         if(ae.getSource()== frm.btnEliminar) {
             if (JOptionPane.showConfirmDialog(null, "Se Eliminará el Registro ¿Desea continuar?", "Eliminar Registro",  
                 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    mod.setId_ministerio(Integer.parseInt(frm.txtId_Ministerio.getText()));
                    if(modConsulta.eliminar(mod)){ 
                        JOptionPane.showMessageDialog(null, "El Registro ha sido Eliminado");
                        tabla(); 
                        limpiar();
                        inhabilitar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El Registro no ha sido eliminado");
                        limpiar();
                    }
             }
         }  
        // Leer Boton Limpiar 
        if(ae.getSource()== frm.btnLimpiar) {
              limpiar();
        }
        // Leer Boton Cancelar
        if(ae.getSource()== frm.btnCancelar) {
              limpiar();
              inhabilitar();
        }   
        // Leer Boton Salir 
        if(ae.getSource()== frm.btnSalir) {
              salir();
        }    
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getSource() == frm.jtListado_Ministerio){ 
            frm.btnModificar.setEnabled(true);
            frm.btnEliminar.setEnabled(true);
            int Fila = frm.jtListado_Ministerio.getSelectedRow();
            String idMinisterio = frm.jtListado_Ministerio.getValueAt(Fila, 0).toString();
            String denominacionMinisterio = frm.jtListado_Ministerio.getValueAt(Fila, 1).toString();
            String pastorMinisterio = frm.jtListado_Ministerio.getValueAt(Fila, 2).toString();
            
            frm.txtDenominacion_Ministerio.setEnabled(true); 
            frm.txtPastor_Asignado.setEnabled(true); 
            this.frm.txtId_Ministerio.setText(idMinisterio);
            this.frm.txtDenominacion_Ministerio.setText(denominacionMinisterio); 
            this.frm.txtPastor_Asignado.setText(pastorMinisterio);      
        }        
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
    public void keyReleased(KeyEvent ke) {       
        trsfiltro = new TableRowSorter(frm.jtListado_Ministerio.getModel());
        frm.jtListado_Ministerio.setRowSorter(trsfiltro);
        trsfiltro.setRowFilter(RowFilter.regexFilter(frm.txtBuscar.getText().toUpperCase(), 1));

        // Tomamos el numero de registro de la tabla
        int fila = frm.jtListado_Ministerio.getRowCount();
        frm.lbtTotal_Registros.setText("Total Registros: " + fila); 

    }
    
    @Override
    public void keyTyped(KeyEvent ke) {  }
    @Override
    public void keyPressed(KeyEvent ke) { }



}
