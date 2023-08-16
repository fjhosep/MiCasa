/*
* AUTOR: Omar Figueroa
* Fecha: 31 de Julio de 2019
*/
package Controlador;

import Vista.frmConsultaPersona;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ConsultaMinisterio;
import modelo.ConsultaPersona;
import modelo.ConsultaProfesion;
import modelo.Ministerio;
import modelo.Persona;
import modelo.Profesion;

public class CtrlConsultaPersona implements ActionListener, MouseListener, KeyListener, ChangeListener{
    
    // Instanciamos el formulario, el modelo, y la consulta
    public static void main(String[] args) {
        Persona mod = new Persona();
        ConsultaPersona modConsulta = new ConsultaPersona();
        frmConsultaPersona frm = new frmConsultaPersona();
        
        Profesion modP = new Profesion();
        ConsultaProfesion  mcp = new ConsultaProfesion();
        
        Ministerio modM = new Ministerio();
        ConsultaMinisterio mcm = new ConsultaMinisterio();
                             
        CtrlConsultaPersona ctrl = new CtrlConsultaPersona(mod, modConsulta, frm, modP, mcp, modM, mcm);
        ctrl.iniciar();  
    }     
   
    // llamamos a las clases creadas
    private Persona mod;
    private ConsultaPersona modConsulta;
    private frmConsultaPersona frm;
    
    private Profesion modP;      
    private ConsultaProfesion  mcp;
    
    private Ministerio modM;
    private ConsultaMinisterio mcm; 
    
    //definimos la variable tipo TableRowSorter para el filtro de datos;
    private TableRowSorter trsfiltro;
    private Component rootPane;
    
    private String  idPersona = null;
    private String  cedulaPersona = null;
    private String  nombrePersona = null;
    private String  apellidoPersona = null;
    private String  fechaNacPersona = null;
    private String  direccionPersona = null;
    private String  telfijoPersona = null;
    private String  telcelPersona = null;
    public  Integer idProfesion;
    private String  denominacionProfesion;
    private String  ocupacionPersona = null;
    private String  correoPersona = null;
    private String  bautizadoPersona = null;
    private String  iglesiaPersona = null;
    private String  observacionPersona = null;
    private String  denominacionMinisterio = null;
    public  Integer idMinisterio;
  
    
    // Constructor de la Clase
    public CtrlConsultaPersona(Persona mod, ConsultaPersona modConsulta, frmConsultaPersona frm, 
                               Profesion modP, ConsultaProfesion  mcp, Ministerio modM,ConsultaMinisterio mcm) {
       
        //igualamos las variables recibidas 
       this.mod = mod;
       this.modConsulta = modConsulta;
       this.frm = frm; 
       
       this.mcp = mcp;
       this.modP = modP;
       
       this.mcm = mcm;
       this.modM = modM;
        
       // Declaramos el action del boton salir
       this.frm.btnSalir.addActionListener(this);
       //Declaranmos el action para el evento de teclado
       this.frm.txtBuscar.addKeyListener(this);
       this.frm.jtPersonas.addMouseListener(this);
       //javax.swing.event.ChangeListener l = null;
       this.frm.jTabbedRegistro.addChangeListener(this);
    } 
    
    
    public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Iglesia Comunidad de Amor (Consulta de Personas)");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false); 
        frm.toFront();
        frm.grupoBusqueda.add(frm.jRCedula);
        frm.grupoBusqueda.add(frm.jRNombre);
        frm.grupoBusqueda.add(frm.jRApellido);
        tabla();        
    } 
    
        // Habilitar los elementos de la tabla
    public void tabla(){        
        DefaultTableModel modelo;        
        modelo = modConsulta.mostrar(mod);  
        frm.jtPersonas.setModel(modelo) ;
        //Definir el tamaño de las columanas
        //frm.jtPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] anchos = {10, 100, 150, 150, 100, 300, 100, 100, 20, 100, 100, 100, 100, 300, 10}; 
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
        // Ocultr la Columna Profesión
        frm.jtPersonas.getColumnModel().getColumn(8).setMaxWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(8).setMinWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(8).setPreferredWidth(0);
        // Ocultar la Columna Ocupación
        frm.jtPersonas.getColumnModel().getColumn(9).setMaxWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(9).setMinWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(9).setPreferredWidth(0);
        // Ocultamos las Columna MInisterio
        frm.jtPersonas.getColumnModel().getColumn(14).setMaxWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(14).setMinWidth(0);
        frm.jtPersonas.getColumnModel().getColumn(14).setPreferredWidth(0);
        
        
        // Tomamos el numero de registro de la tabla
        int fila = frm.jtPersonas.getRowCount();
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
    public void keyReleased(KeyEvent ke) { 
            if (ke.getSource()== frm.txtBuscar) {
            trsfiltro = new TableRowSorter(frm.jtPersonas.getModel());
            frm.jtPersonas.setRowSorter(trsfiltro);
            
            if (frm.jRCedula.isSelected() == true) {
                trsfiltro.setRowFilter(RowFilter.regexFilter(frm.txtBuscar.getText(), 1));
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
        frm.lbtTotal_Registro.setText("Total Registros: " + fila); 
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {  }

    @Override
    public void keyPressed(KeyEvent ke) {  }
    
  
    @Override
    public void mouseClicked(MouseEvent me) {
                    
            int Fila = frm.jtPersonas.getSelectedRow();  
            idPersona          = frm.jtPersonas.getValueAt(Fila, 0).toString();
            cedulaPersona      = frm.jtPersonas.getValueAt(Fila, 1).toString();
            nombrePersona      = frm.jtPersonas.getValueAt(Fila, 2).toString();
            apellidoPersona    = frm.jtPersonas.getValueAt(Fila, 3).toString();
            fechaNacPersona    = frm.jtPersonas.getValueAt(Fila, 4).toString();           
            direccionPersona   = frm.jtPersonas.getValueAt(Fila, 5).toString();
            telfijoPersona     = frm.jtPersonas.getValueAt(Fila, 6).toString();
            telcelPersona      = frm.jtPersonas.getValueAt(Fila, 7).toString();
            idProfesion        = Integer.valueOf(frm.jtPersonas.getValueAt(Fila, 8).toString());
            ocupacionPersona   = frm.jtPersonas.getValueAt(Fila, 9).toString();
            correoPersona      = frm.jtPersonas.getValueAt(Fila, 10).toString();
            bautizadoPersona   = frm.jtPersonas.getValueAt(Fila, 11).toString();
            iglesiaPersona     = frm.jtPersonas.getValueAt(Fila, 12).toString();
            observacionPersona = frm.jtPersonas.getValueAt(Fila, 13).toString();
            idMinisterio       = Integer.valueOf(frm.jtPersonas.getValueAt(Fila, 14).toString());
            

        modP.setId_profesion(idProfesion);         
        if(mcp.buscar(modP)){
            denominacionProfesion  = modP.getDenominacion_profesion();
        }
        
        modM.setId_ministerio(idMinisterio);         
        if(mcm.buscar(modM)){
            denominacionMinisterio  = modM.getDenominacion_ministerio();
        }
            
    }

    @Override
    public void mousePressed(MouseEvent me) {    }

    @Override
    public void mouseReleased(MouseEvent e) { }
        
    @Override
    public void mouseEntered(MouseEvent e) {  }

    @Override
    public void mouseExited(MouseEvent e) {  }  

    @Override
    public void stateChanged(ChangeEvent e) {
               
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        int selectedIndex = frm.jTabbedRegistro.getSelectedIndex();
        if (selectedIndex == 1){       
            this.frm.txtCedula_Persona.setText(cedulaPersona);
            this.frm.txtNombre_Persona.setText(nombrePersona);
            this.frm.txtApellido_Persona.setText(apellidoPersona); 
            this.frm.txtFechaNac_Persona.setText(fechaNacPersona);
            this.frm.txtDirHab_Persona.setText(direccionPersona);
            this.frm.txtTelFijo_Persona.setText(telfijoPersona);
            this.frm.txtTelcel_Persona.setText(telcelPersona);
            this.frm.txtTelFijo_Persona.setText(telfijoPersona);
            this.frm.txtTelcel_Persona.setText(telcelPersona);
            this.frm.txtProfesion_Persona.setText(denominacionProfesion);
            this.frm.txtOcupacion_Persona.setText(ocupacionPersona);
            this.frm.txtCorreo_Persona.setText(correoPersona);
            this.frm.txtBautizado_Persona.setText(bautizadoPersona);
            this.frm.txtIglesiaProv_Persona.setText(iglesiaPersona);
            this.frm.tAreaObservacion_Persona.setText(observacionPersona);  
            this.frm.txtMinisterio_Persona.setText(denominacionMinisterio);
        } 
    }
}

