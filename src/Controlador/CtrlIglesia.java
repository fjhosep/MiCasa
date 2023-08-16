/*
* AUTOR: Omar Figueroa
* Fecha: 08 de Agosto de 2019
*/

package Controlador;

import Vista.frmIglesia;
import java.awt.Component;
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
import modelo.ConsultaIglesia;
import modelo.Iglesia;

public class CtrlIglesia implements ActionListener, MouseListener, KeyListener{

    
    // Instanciamos el formulario, el modelo, y la consulta
    public static void main(String[] args) {
        Iglesia mod = new Iglesia();
        ConsultaIglesia modConsulta = new ConsultaIglesia();
        frmIglesia frm = new frmIglesia();
                        
        CtrlIglesia ctrl = new CtrlIglesia(mod, modConsulta, frm);
        ctrl.iniciar();  
    }   
    
   // llamamos a las clases creadas
    private Iglesia mod;
    private ConsultaIglesia modConsulta;
    private frmIglesia frm;
    
 
    //definimos la variable tipo TableRowSorter para el filtro de datos;
    private TableRowSorter trsfiltro;
    private Component rootPane;
    

    
    // Constructor de la Clase
    public CtrlIglesia(Iglesia mod, ConsultaIglesia modConsulta, frmIglesia frm) {
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
       this.frm.jtIglesias.addMouseListener(this);
       //Declaramos el action para el evento de teclado
       this.frm.txtBuscar.addKeyListener(this);   
    }
 
       public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Iglesia Comunidad de Amor (Directorio de Iglesia)");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false); 
        frm.toFront();
        frm.grupoBusqueda.add(frm.jRIglesia);
        frm.grupoBusqueda.add(frm.jRPastor);
        
        frm.lbtId_iglesia.setVisible(true);
        frm.lbtId_iglesia.setVisible(false);
        frm.txtId.setVisible(false);
        inhabilitar();
        tabla();        
    }     
    
       
    // Método para inabilitar los objetos
    public void habilitar(){
        frm.txtIglesia.setEnabled(true);
        frm.txtPastor.setEnabled(true);
        frm.txtDireccion.setEnabled(true);
        frm.txtCiudad.setEnabled(true);
        frm.txtEstado.setEnabled(true);
        frm.txtPais.setEnabled(true);
        frm.txtTelefono.setEnabled(true);
        frm.txtTelefono1.setEnabled(true);
        frm.btnGuardar.setEnabled(true);
        frm.btnModificar.setEnabled(true);
        //frm.btnEliminar.setEnabled(false);
        frm.btnLimpiar.setEnabled(true);  
    }   
       
    // Método para inabilitar los objetos
    public void inhabilitar(){
        frm.txtIglesia.setEnabled(false);
        frm.txtPastor.setEnabled(false);
        frm.txtDireccion.setEnabled(false);
        frm.txtCiudad.setEnabled(false);
        frm.txtEstado.setEnabled(false);
        frm.txtPais.setEnabled(false);
        frm.txtTelefono.setEnabled(false);
        frm.txtTelefono1.setEnabled(false);
        frm.btnGuardar.setEnabled(false);
        frm.btnModificar.setEnabled(false);
        //frm.btnEliminar.setEnabled(false);
        frm.btnLimpiar.setEnabled(false);  
    }
    
    // Método para limpiar el formulario
    public void limpiar(){
        frm.txtId.setText(null);
        frm.txtIglesia.setText(null);
        frm.txtPastor.setText(null);
        frm.txtDireccion.setText(null);  
        frm.txtCiudad.setText(null); 
        frm.txtEstado.setText(null);
        frm.txtPais.setText(null);
        frm.txtTelefono.setText(null);
        frm.txtTelefono1.setText(null);
        frm.btnGuardar.setEnabled(true);        

    }
    
    // Habilitar los elementos de la tabla
    public void tabla(){        
        DefaultTableModel modelo;        
        modelo = modConsulta.mostrar(mod);  
        frm.jtIglesias.setModel(modelo) ;
        //Definir el tamaño de las columanas
        //frm.jtIglesias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] anchos = {10, 150, 150, 200, 100, 100, 100, 50, 50}; 
            for(int x =0; x<9; x++) {
                frm.jtIglesias.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
        //Ocultamos laColumna del ID
        frm.jtIglesias.getColumnModel().getColumn(0).setMaxWidth(0);
        frm.jtIglesias.getColumnModel().getColumn(0).setMinWidth(0);
        frm.jtIglesias.getColumnModel().getColumn(0).setPreferredWidth(0);
           
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtIglesias.getRowCount();
        frm.lbtTotal_Registros.setText("Total Registros: " + fila);             
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
            mod.setNombre_iglesia(frm.txtIglesia.getText());
            mod.setPastor_iglesia(frm.txtPastor.getText());
            mod.setDireccion_iglesia(frm.txtDireccion.getText());
            mod.setCiudad_iglesia(frm.txtCiudad.getText());
            mod.setEstado_iglesia(frm.txtEstado.getText());
            mod.setPais_iglesia(frm.txtEstado.getText());
            mod.setTlf_iglesia(frm.txtTelefono.getText());
            mod.setTlf1_iglesia(frm.txtTelefono1.getText());
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
            mod.setId_iglesia(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre_iglesia(frm.txtIglesia.getText());
            mod.setPastor_iglesia(frm.txtPastor.getText());      
            mod.setDireccion_iglesia(frm.txtDireccion.getText());
            mod.setCiudad_iglesia(frm.txtCiudad.getText());
            mod.setEstado_iglesia(frm.txtEstado.getText());
            mod.setPais_iglesia(frm.txtPais.getText());
            mod.setTlf_iglesia(frm.txtTelefono.getText());
            mod.setTlf1_iglesia(frm.txtTelefono1.getText());
            if(modConsulta.modificar(mod)){ 
                 JOptionPane.showMessageDialog(null, "El Registro ha sido Modificado");
                 tabla(); 
                 limpiar();
                 inhabilitar();
             } else {
                 JOptionPane.showMessageDialog(null, "El Registro Registro no ha sido Modificado");
                 limpiar();
             }
        }        
      
        // Leer Boton Eliminar
        if(ae.getSource()== frm.btnEliminar) {
             if (JOptionPane.showConfirmDialog(null, "Se Eliminará el Registro ¿Desea continuar?", "Eliminar Registro",  
                 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    mod.setId_iglesia(Integer.parseInt(frm.txtId.getText()));
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
        
        // Leer Boton Cancelar
        if(ae.getSource()== frm.btnCancelar) {
              limpiar();
              inhabilitar();
        }   
        // Leer Boton Limpiar 
        if(ae.getSource()== frm.btnLimpiar) {
              limpiar();
        }
        // Leer Boton Salir 
        if(ae.getSource()== frm.btnSalir) {
              salir();
        }             
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getSource() == frm.jtIglesias){ 
            frm.btnModificar.setEnabled(true);
            frm.btnEliminar.setEnabled(true);
            
            habilitar();
            limpiar();
                           
            int Fila = frm.jtIglesias.getSelectedRow();
            String idIglesia          = frm.jtIglesias.getValueAt(Fila, 0).toString();
            String nombreIglesia      = frm.jtIglesias.getValueAt(Fila, 1).toString();
            String pastorIglesia      = frm.jtIglesias.getValueAt(Fila, 2).toString();
            String direccionIglesia   = frm.jtIglesias.getValueAt(Fila, 3).toString();         
            String ciudadIglesia      = frm.jtIglesias.getValueAt(Fila, 4).toString();
            String estadoIglesia      = frm.jtIglesias.getValueAt(Fila, 5).toString();
            String paisIglesia        = frm.jtIglesias.getValueAt(Fila, 6).toString();
            String tlfIglesia         = frm.jtIglesias.getValueAt(Fila, 7).toString();
            String tlf1Iglesia        = frm.jtIglesias.getValueAt(Fila, 8).toString();
                        
            this.frm.txtId.setText(idIglesia); 
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
    public void mouseClicked(MouseEvent me) {  }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) { }

    @Override
    public void mouseExited(MouseEvent me) { }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource()== frm.txtBuscar) {
            trsfiltro = new TableRowSorter(frm.jtIglesias.getModel());
            frm.jtIglesias.setRowSorter(trsfiltro);
            
            if (frm.jRIglesia.isSelected() == true) {
                trsfiltro.setRowFilter(RowFilter.regexFilter(frm.txtIglesia.getText().toUpperCase(), 1));
            } else if (frm.jRPastor.isSelected() == true){
                trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+frm.txtPastor.getText(), 2));                
            } else {
               JOptionPane.showMessageDialog(null, "Seleccione una opción para la búsqueda");   
            }
        }
        
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtIglesias.getRowCount();
        frm.lbtTotal_Registros.setText("Total Registros: " + fila);   
    }
    

    @Override
    public void keyTyped(KeyEvent ke) { }

    @Override
    public void keyPressed(KeyEvent ke) { }


}
