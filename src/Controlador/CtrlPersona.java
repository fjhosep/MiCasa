/*
* AUTOR: Omar Figueroa
* Fecha: 18 de Julio de 2019
*/

package Controlador;

import Vista.frmPersona;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ConsultaMinisterio;
import modelo.ConsultaPersona;
import modelo.ConsultaProfesion;
import modelo.Ministerio;
import modelo.Persona;
import modelo.Profesion;
import modelo.cbxministerio;
import modelo.cbxprofesion;

public class CtrlPersona implements ActionListener, MouseListener, KeyListener, ItemListener {
    
    

    // Instanciamos el formulario, el modelo, y la consulta
    public static void main(String[] args) {
        Persona mod = new Persona();
        ConsultaPersona modConsulta = new ConsultaPersona();
        frmPersona frm = new frmPersona();
        
        // Llamanos la Clase ConsultaProfesion Para llenar el comboBox
        Profesion modProfesion = new Profesion();
        ConsultaProfesion modCProfesion = new ConsultaProfesion();
        
        // Llamanos la Clase ConsultaMInisterio Para llenar el comboBox
        Ministerio modMinisterio = new Ministerio();
        ConsultaMinisterio modCMinisterio = new ConsultaMinisterio();
        
        // Llamamos a las clases par acrear los combobox
        cbxprofesion  cbxpro = new cbxprofesion();
        cbxministerio cbxmin = new cbxministerio();
                
        CtrlPersona ctrl = new CtrlPersona(mod, modConsulta, frm, modProfesion, modCProfesion, modMinisterio, modCMinisterio, cbxpro, cbxmin);
        ctrl.iniciar();  
    }     

    // llamamos a las clases creadas
    private Persona mod;
    private ConsultaPersona modConsulta;
    private frmPersona frm;
    
    // Llamamos a la clase Profesión necesario para llenarmi conbo boz
    private Profesion modProfesion;
    private ConsultaProfesion modCProfesion;
    
    // Llamamos a la clase MInisterio necesario para llenarmi conbo boz
    private Ministerio modMinisterio;
    private ConsultaMinisterio modCMinisterio;
    
    //definimos la variable tipo TableRowSorter para el filtro de datos;
    private TableRowSorter trsfiltro;
    private Component rootPane;
    
    //definimos las variables para los combos
    private cbxprofesion cbxpro;
    private cbxministerio cbxmin;
    
    public int id_pro;
    public int id_min;
    
    // Constructor de la Clase
    public CtrlPersona(Persona mod, ConsultaPersona modConsulta, frmPersona frm, 
                       Profesion  modProfesion,  ConsultaProfesion  modCProfesion,
                       Ministerio modMinisterio, ConsultaMinisterio modCMinisterio,
                       cbxprofesion cbxpro, cbxministerio cbxmin) {
       //igualamos las variables recibidas 
       this.mod = mod;
       this.modConsulta = modConsulta;
       this.frm = frm; 
       
       this.modProfesion  = modProfesion;
       this.modCProfesion = modCProfesion;
       
       this.modMinisterio  = modMinisterio;
       this.modCMinisterio = modCMinisterio;
       
       this.cbxpro = cbxpro;
       this.cbxmin = cbxmin;
       
       
       // Declaramos los accion para cada uno de los botones
       this.frm.btnNuevo.addActionListener(this);
       this.frm.btnGuardar.addActionListener(this);
       this.frm.btnModificar.addActionListener(this);
       this.frm.btnEliminar.addActionListener(this);
       this.frm.btnLimpiar.addActionListener(this);
       this.frm.btnSalir.addActionListener(this);
       this.frm.btnCancelar.addActionListener(this);
       //Declaramos el accion par el evento del Mouse 
       this.frm.jtPersonas.addMouseListener(this);
       this.frm.CboProfesion_Persona.addMouseListener(this);
       this.frm.CboMinisterio_Persona.addMouseListener(this);
       //Declaramos el action para el evento de teclado
       this.frm.txtBuscar.addKeyListener(this);   
       //Declaramoa el accion paralos combobox
       this.frm.CboProfesion_Persona.addItemListener(this);
       this.frm.CboMinisterio_Persona.addItemListener(this);   
    }

     
       public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Iglesia Comunidad de Amor (Registro de Personas)");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false); 
        frm.toFront();
        frm.grupoBusqueda.add(frm.jRCedula);
        frm.grupoBusqueda.add(frm.jRNombre);
        frm.grupoBusqueda.add(frm.jRApellido);
        
        frm.lbtId_Persona.setVisible(false);
        frm.txtId_Persona.setVisible(false);
        //frm.txtId_Ministerio.setVisible(false);
        inhabilitar();
        tabla();        
    } 
       
    // Habilitar los elementos de la tabla
    public void tabla(){        
        DefaultTableModel modelo;        
        modelo = modConsulta.mostrar(mod);  
        frm.jtPersonas.setModel(modelo) ;
        //Definir el tamaño de las columanas
        //frm.jtPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] anchos = {10, 100, 150, 150, 100, 300, 100, 100, 200, 100, 100, 100, 100, 300, 10}; 
            for(int x =0; x<15; x++) {
                frm.jtPersonas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
        //Ocultamos laColumna del ID
        frm.jtPersonas.getColumnModel().getColumn(0).setMaxWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(0).setMinWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(0).setPreferredWidth(0);
        // Ocultamos las Columna Fecha de Nacimiento
        frm.jtPersonas.getColumnModel().getColumn(4).setMaxWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(4).setMinWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(4).setPreferredWidth(0);
        // Ocultamos las Columna Ministerio
        frm.jtPersonas.getColumnModel().getColumn(14).setMaxWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(14).setMinWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(14).setPreferredWidth(0);
        
        
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtPersonas.getRowCount();
        frm.lbtTotal_Registros.setText("Total Registros: " + fila);             
    }
       
    
    // Método para inabilitar los objetos
    public void inhabilitar(){
        frm.txtCedula_Persona.setEnabled(false);
        frm.txtNombre_Persona.setEnabled(false);
        frm.txtApellido_Persona.setEnabled(false);
        frm.txtFechaNac_Persona.setEnabled(false);
        frm.txtDirHab_Persona.setEnabled(false);
        frm.txtTelFijo_Persona.setEnabled(false);
        frm.txtTelcel_Persona.setEnabled(false);
        frm.CboProfesion_Persona.setEnabled(false);
        frm.txtOcupacion_Persona.setEnabled(false);
        frm.txtCorreo_Persona.setEnabled(false);
        frm.CboBautizado_Persona.setEnabled(false);
        frm.txtIglesiaProv_Persona.setEnabled(false);
        frm.tAreaObservacion_Persona.setEnabled(false);
        frm.CboMinisterio_Persona.setEnabled(false);
        frm.btnGuardar.setEnabled(false);
        frm.btnModificar.setEnabled(false);
        //frm.btnEliminar.setEnabled(false);
        frm.btnLimpiar.setEnabled(false);  
    }
    
    // Método para habilitar los objetos
    public void habilitar(){
        frm.txtCedula_Persona.setEnabled(true);
        frm.txtNombre_Persona.setEnabled(true);
        frm.txtApellido_Persona.setEnabled(true);
        frm.txtFechaNac_Persona.setEnabled(true);
        frm.txtDirHab_Persona.setEnabled(true);
        frm.txtTelFijo_Persona.setEnabled(true);
        frm.txtTelcel_Persona.setEnabled(true);
        frm.CboProfesion_Persona.setEnabled(true);
        frm.txtOcupacion_Persona.setEnabled(true);
        frm.txtCorreo_Persona.setEnabled(true);
        frm.CboBautizado_Persona.setEnabled(true);
        frm.txtIglesiaProv_Persona.setEnabled(true);
        frm.tAreaObservacion_Persona.setEnabled(true);
        frm.CboMinisterio_Persona.setEnabled(true);
        
        frm.btnGuardar.setEnabled(true);
        frm.btnLimpiar.setEnabled(true);
    }
    
    // Habilitamos los elementos del Combo Box Profesion
    public int cbxProfesion(){
        cbxpro.listar_profesion(frm.CboProfesion_Persona);
        cbxprofesion cp = (cbxprofesion)this.frm.CboProfesion_Persona.getSelectedItem();
        int id_pro =   cp.getId_profesion();
        String deno_pro = cp.getDenominacion_profesion();
        System.out.println(id_pro);
        return id_pro;
    }
    
    // Habilitamos los elementos del Combo Box Ministerio
    public int cbxMinisterio(){     
        cbxmin.listar_ministerio(frm.CboMinisterio_Persona);
        cbxministerio cm = (cbxministerio)this.frm.CboMinisterio_Persona.getSelectedItem();
        int id_min =   cm.getId_ministerio();
        String deno_min = cm.getDenominacion_ministerio();
        return id_min;    
    }
   
    // Método para limpiar el formulario
    public void limpiar(){
        frm.txtCedula_Persona.setText(null);
        frm.txtNombre_Persona.setText(null);
        frm.txtApellido_Persona.setText(null);  
        frm.txtFechaNac_Persona.setCalendar(null); 
        frm.txtDirHab_Persona.setText(null);
        frm.txtTelFijo_Persona.setText(null);
        frm.txtTelcel_Persona.setText(null);
        frm.txtOcupacion_Persona.setText(null);
        frm.txtCorreo_Persona.setText(null);
        frm.txtIglesiaProv_Persona.setText(null);
        frm.tAreaObservacion_Persona.setText(null);
        frm.btnGuardar.setEnabled(true);        

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
            //Llamamos a los métodos para  los combos
            cbxpro.listar_profesion(frm.CboProfesion_Persona);
            cbxmin.listar_ministerio(frm.CboMinisterio_Persona);
        }  
        // Leer Boton Guardar
        if(ae.getSource()== frm.btnGuardar) {
            mod.setCedula_persona(Integer.parseInt(frm.txtCedula_Persona.getText()));
            mod.setNombre_persona(frm.txtNombre_Persona.getText());
            mod.setApellido_persona(frm.txtApellido_Persona.getText());
            
            Calendar fechaNac= frm.txtFechaNac_Persona.getCalendar();
            String dia = Integer.toString(fechaNac.get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(fechaNac.get(Calendar.MONTH));
            String ano = Integer.toString(fechaNac.get(Calendar.YEAR));
            String fecha = (ano+"-"+mes+"-"+dia);
            mod.setFechaNac_persona(Date.valueOf(fecha)); 
            
           
            mod.setDirhab_persona(frm.txtDirHab_Persona.getText());
            mod.setTelfijo_persona(frm.txtTelFijo_Persona.getText());
            mod.setTelcel_persona(frm.txtTelcel_Persona.getText());
            //mod.setId_profesion_persona(id_pro);
            mod.setOcupacion_persona(frm.txtOcupacion_Persona.getText());
            mod.setCorreo_persona(frm.txtCorreo_Persona.getText());
            mod.setBautizo_persona((String) frm.CboBautizado_Persona.getSelectedItem());
            mod.setIglesiaProv_persona(frm.txtIglesiaProv_Persona.getText());
            mod.setObservacion_persona(frm.txtOcupacion_Persona.getText());
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
            mod.setId_persona(Integer.parseInt(frm.txtId_Persona.getText()));
            mod.setCedula_persona(Integer.parseInt(frm.txtCedula_Persona.getText()));
            mod.setNombre_persona(frm.txtNombre_Persona.getText());
            mod.setApellido_persona(frm.txtApellido_Persona.getText());      
                      
            Calendar fechaNac= frm.txtFechaNac_Persona.getCalendar();
            String dia = Integer.toString(fechaNac.get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(fechaNac.get(Calendar.MONTH));
            String ano = Integer.toString(fechaNac.get(Calendar.YEAR));
            String fecha = (ano+"-"+mes+"-"+dia);
            mod.setFechaNac_persona(Date.valueOf(fecha)); 
  
            mod.setDirhab_persona(frm.txtDirHab_Persona.getText());
            mod.setTelfijo_persona(frm.txtTelFijo_Persona.getText());
            mod.setTelcel_persona(frm.txtTelcel_Persona.getText());
            mod.setId_profesion_persona(id_pro);
            mod.setOcupacion_persona(frm.txtOcupacion_Persona.getText());
            mod.setCorreo_persona(frm.txtCorreo_Persona.getText());
            mod.setBautizo_persona((String) frm.CboBautizado_Persona.getSelectedItem());
            mod.setIglesiaProv_persona(frm.txtIglesiaProv_Persona.getText());
            mod.setObservacion_persona(frm.txtOcupacion_Persona.getText());
            mod.setId_ministerio_persona(id_min);
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
                    mod.setId_persona(Integer.parseInt(frm.txtId_Persona.getText()));
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
    // Llamamos al evento itemStateChange paraseleccionar el valor del combobox
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie. getSource()== frm.CboProfesion_Persona){
            if (ie.getStateChange() == ItemEvent.SELECTED){
                cbxprofesion cp = (cbxprofesion)this.frm.CboProfesion_Persona.getSelectedItem();
                id_pro =   cp.getId_profesion();
                mod.setId_profesion_persona(id_pro);
               
            }              
        }
        if (ie.getSource()== frm.CboMinisterio_Persona){
            if (ie.getStateChange() == ItemEvent.SELECTED){
                 cbxministerio cm = (cbxministerio)this.frm.CboMinisterio_Persona.getSelectedItem();
                 id_min =   cm.getId_ministerio();
                 mod.setId_ministerio_persona(id_min);
            }  
        }          
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getSource() == frm.jtPersonas){ 
            frm.btnModificar.setEnabled(true);
            frm.btnEliminar.setEnabled(true);
            
            frm.txtCedula_Persona.setEnabled(true);
            frm.txtNombre_Persona.setEnabled(true);
            frm.txtApellido_Persona.setEnabled(true);
            frm.txtFechaNac_Persona.setEnabled(true);
            frm.txtDirHab_Persona.setEnabled(true);
            frm.txtTelFijo_Persona.setEnabled(true);
            frm.txtTelcel_Persona.setEnabled(true);
            frm.CboProfesion_Persona.setEnabled(true);
            frm.txtOcupacion_Persona.setEnabled(true);
            frm.txtCorreo_Persona.setEnabled(true);
            frm.CboBautizado_Persona.setEnabled(true);
            frm.txtIglesiaProv_Persona.setEnabled(true);
            frm.tAreaObservacion_Persona.setEnabled(true);
            frm.CboMinisterio_Persona.setEnabled(true);
                           
            int Fila = frm.jtPersonas.getSelectedRow();
            //int Fila = frm.jtPersonas.rowAtPoint(me.getPoint());
            String idPersonas         = frm.jtPersonas.getValueAt(Fila, 0).toString();
            String cedulaPersona      = frm.jtPersonas.getValueAt(Fila, 1).toString();
            String nombrePersona      = frm.jtPersonas.getValueAt(Fila, 2).toString();
            String apellidoPersona    = frm.jtPersonas.getValueAt(Fila, 3).toString();
            Date   fechaNacPersona    = Date.valueOf(frm.jtPersonas.getValueAt(Fila, 4).toString());           
            String direccionPersona   = frm.jtPersonas.getValueAt(Fila, 5).toString();
            String telfijoPersona     = frm.jtPersonas.getValueAt(Fila, 6).toString();
            String telcelPersona      = frm.jtPersonas.getValueAt(Fila, 7).toString();
            int    idProfesion        = Integer.valueOf(frm.jtPersonas.getValueAt(Fila, 8).toString());
            String ocupacionPersona   = frm.jtPersonas.getValueAt(Fila, 9).toString();
            String correoPersona      = frm.jtPersonas.getValueAt(Fila, 10).toString();
            String bautizadoPersona   = frm.jtPersonas.getValueAt(Fila, 11).toString();
            String iglesiaPersona     = frm.jtPersonas.getValueAt(Fila, 12).toString();
            String observacionPersona = frm.jtPersonas.getValueAt(Fila, 13).toString();
            int    idMinisterio       = Integer.valueOf(frm.jtPersonas.getValueAt(Fila, 14).toString());
                        
            //llamamos al combo box
            cbxpro.listar_idProfesion(idProfesion, frm.CboProfesion_Persona);
            cbxprofesion cp = (cbxprofesion)this.frm.CboProfesion_Persona.getSelectedItem();

            cbxmin.listar_idMinisterio(idMinisterio, frm.CboMinisterio_Persona);
            cbxministerio cm = (cbxministerio)this.frm.CboMinisterio_Persona.getSelectedItem();
                        
            this.frm.txtId_Persona.setText(idPersonas);
            this.frm.txtCedula_Persona.setText(cedulaPersona); 
            this.frm.txtNombre_Persona.setText(nombrePersona); 
            this.frm.txtApellido_Persona.setText(apellidoPersona); 
            this.frm.txtFechaNac_Persona.setDate(fechaNacPersona); 
            this.frm.txtDirHab_Persona.setText(direccionPersona);
            this.frm.txtTelFijo_Persona.setText(telfijoPersona);
            this.frm.txtTelcel_Persona.setText(telcelPersona);
            this.frm.txtTelFijo_Persona.setText(telfijoPersona);
            this.frm.txtTelcel_Persona.setText(telcelPersona);
            this.frm.txtOcupacion_Persona.setText(ocupacionPersona);
            this.frm.txtCorreo_Persona.setText(correoPersona);
            this.frm.CboBautizado_Persona.setSelectedItem(bautizadoPersona);
            this.frm.txtIglesiaProv_Persona.setText(iglesiaPersona);
            this.frm.tAreaObservacion_Persona.setText(observacionPersona);         
        }  

    }
     
    @Override
    public void mouseClicked(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) {  }

    @Override
    public void mouseExited(MouseEvent me) { }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource()== frm.txtBuscar) {
            trsfiltro = new TableRowSorter(frm.jtPersonas.getModel());
            frm.jtPersonas.setRowSorter(trsfiltro);
            
            if (frm.jRCedula.isSelected() == true) {
                trsfiltro.setRowFilter(RowFilter.regexFilter(frm.txtBuscar.getText().toUpperCase(), 1));
            } else if (frm.jRNombre.isSelected() == true){
                trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+frm.txtBuscar.getText(), 2));                
            } else if (frm.jRApellido.isSelected() == true){
                trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+frm.txtBuscar.getText(), 3));                
            } else {
               JOptionPane.showMessageDialog(null, "Seleccione una opción para la búsqueda");   
            }
        }
        
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtPersonas.getRowCount();
        frm.lbtTotal_Registros.setText("Total Registros: " + fila); 
    }  

    @Override
    public void keyTyped(KeyEvent ke) { }

    @Override
    public void keyPressed(KeyEvent ke) { }


  
}
