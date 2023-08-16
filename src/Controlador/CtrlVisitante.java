/**
 * AUTOR: Omar Figueroa
 * FECHA: 25/10/2019
 */

package Controlador;

import Vista.frmVisitantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ConsultaVisitante;
import modelo.Visitantes;

public class CtrlVisitante implements ActionListener, MouseListener, KeyListener {

    // Instanciamos el formulario, el modelo, y la consulta
    public static void main(String[] args) {
        Visitantes mod = new Visitantes();
        ConsultaVisitante modConsulta = new ConsultaVisitante();
        frmVisitantes frm = new frmVisitantes();
        
        CtrlVisitante ctrl = new CtrlVisitante(mod, modConsulta, frm);
        ctrl.iniciar();   
    } 

    // llamamos a las clases creadas
    private Visitantes mod;
    private ConsultaVisitante modConsulta;
    private frmVisitantes frm;
    
    //definimos la variable tipo TableRowSorter para el filtro de datos;
    private TableRowSorter trsfiltro;   
    
    // Constructor de la Clase
    public CtrlVisitante(Visitantes mod, ConsultaVisitante modConsulta, frmVisitantes frm) {
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
       this.frm.jtListado_Visitantes.addMouseListener(this);
       //Declaramos el action para el evento de teclado
       this.frm.txtBuscar.addKeyListener(this);    
    }
    
    public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Iglesia Comunidad de Amor (Directorio de Visitantes)");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false); 
        frm.toFront();
        frm.grupoBusqueda.add(frm.jRNombre);
        frm.grupoBusqueda.add(frm.jRApellido);
        
        frm.lbtId_Visitantes.setVisible(false);
        frm.txtId_Visitantes.setVisible(false);
        inhabilitar();
        tabla();        
    }

        // Método para inabilitar los objetos
    public void habilitar(){
        frm.txtNombre_Visitante.setEnabled(true);
        frm.txtApellido_Visitante.setEnabled(true);
        frm.txtCorreo_Visitante.setEnabled(true);
        frm.cboTipo_Visitante.setEnabled(true);
        frm.btnGuardar.setEnabled(true);
        frm.btnModificar.setEnabled(true);
        //frm.btnEliminar.setEnabled(false);
        frm.btnLimpiar.setEnabled(true);  
    }   
       
    // Método para inabilitar los objetos
    public void inhabilitar(){
        frm.txtNombre_Visitante.setEnabled(false);
        frm.txtApellido_Visitante.setEnabled(false);
        frm.txtCorreo_Visitante.setEnabled(false);
        frm.cboTipo_Visitante.setEnabled(false);        
        frm.btnGuardar.setEnabled(false);
        frm.btnModificar.setEnabled(false);
        //frm.btnEliminar.setEnabled(false);
        frm.btnLimpiar.setEnabled(false);  
    }
    
    // Método para limpiar el formulario
    public void limpiar(){
        frm.txtNombre_Visitante.setText(null);
        frm.txtApellido_Visitante.setText(null);
        frm.txtCorreo_Visitante.setText(null);
        frm.cboTipo_Visitante.setSelectedItem(null);  
        frm.btnGuardar.setEnabled(true);        
    }
    
        // Habilitar los elementos de la tabla
    public void tabla(){        
        DefaultTableModel modelo;        
        modelo = modConsulta.mostrar(mod);  
        frm.jtListado_Visitantes.setModel(modelo) ;
        //Definir el tamaño de las columanas
        //frm.jtIglesias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] anchos = {10, 200, 200, 100, 100}; 
            for(int x =0; x<5; x++) {
                frm.jtListado_Visitantes.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
        //Ocultamos laColumna del ID
        frm.jtListado_Visitantes.getColumnModel().getColumn(0).setMaxWidth(0);
        frm.jtListado_Visitantes.getColumnModel().getColumn(0).setMinWidth(0);
        frm.jtListado_Visitantes.getColumnModel().getColumn(0).setPreferredWidth(0);
           
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtListado_Visitantes.getRowCount();
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
            mod.setNombre_visitantes(frm.txtNombre_Visitante.getText());
            mod.setApellido_visitantes(frm.txtApellido_Visitante.getText());
            mod.setTlf_visitantes(frm.txtTlf_Visitante.getText());
            mod.setTlf2_visitantes(frm.txtTlf2_Visitante.getText());
            mod.setCorreo_visitantes(frm.txtCorreo_Visitante.getText());
            mod.setTipo_visitantes((String) frm.cboTipo_Visitante.getSelectedItem());       
            mod.setObservacion_visitantes((String) frm.txtObservacion_Visitante.getText());     
                    
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
            mod.setId_visitantes(Integer.parseInt(frm.txtId_Visitantes.getText()));
            mod.setNombre_visitantes(frm.txtNombre_Visitante.getText());
            mod.setApellido_visitantes(frm.txtApellido_Visitante.getText());
            mod.setTlf_visitantes(frm.txtTlf_Visitante.getText());
            mod.setTlf2_visitantes(frm.txtTlf2_Visitante.getText());
            mod.setCorreo_visitantes(frm.txtCorreo_Visitante.getText());
            mod.setTipo_visitantes((String) frm.cboTipo_Visitante.getSelectedItem());       
            mod.setObservacion_visitantes((String) frm.txtObservacion_Visitante.getText()); 
            mod.setTipo_visitantes((String) frm.cboTipo_Visitante.getSelectedItem());
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
                    mod.setId_visitantes(Integer.parseInt(frm.txtId_Visitantes.getText()));
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
        if(me.getSource() == frm.jtListado_Ministerio){ 
            frm.btnModificar.setEnabled(true);
            frm.btnEliminar.setEnabled(true);
            
            habilitar();
            limpiar();
            
            int Fila = frm.jtListado_Visitantes.getSelectedRow();
            String id_visitante          = frm.jtListado_Visitantes.getValueAt(Fila, 0).toString();
            String nombre_visitante      = frm.jtListado_Visitantes.getValueAt(Fila, 1).toString();
            String apellido_visitante    = frm.jtListado_Visitantes.getValueAt(Fila, 2).toString();
            String correo_visitante      = frm.jtListado_Visitantes.getValueAt(Fila, 3).toString();         
            String tipo_visitante        = frm.jtListado_Visitantes.getValueAt(Fila, 4).toString();
            
            this.frm.txtId_Visitantes.setText(id_visitante); 
            this.frm.txtNombre_Visitante.setText(nombre_visitante); 
            this.frm.txtApellido_Visitante.setText(apellido_visitante); 
            this.frm.txtCorreo_Visitante.setText(correo_visitante); 
            this.frm.cboTipo_Visitante.setSelectedItem(tipo_visitante);
           
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
    public void keyTyped(KeyEvent ke) { }

    @Override
    public void keyPressed(KeyEvent ke) { }

    @Override
    public void keyReleased(KeyEvent ke) { }

}
