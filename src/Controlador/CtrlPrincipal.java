/*
* AUTOR: Omar Figueroa
* Fecha: 30 de Julio de 2019
*/

package Controlador;

import Vista.frmPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CtrlPrincipal implements ActionListener, MouseListener{
    
    public static void main(String[] args) {
        // Instanciamos el Formulario Principal
        frmPrincipal  frmPrincipal  = new frmPrincipal();
        
        CtrlPrincipal ctrl = new CtrlPrincipal(frmPrincipal);
        ctrl.iniciar();
    }
    
    //llamamos a la clase creada
    private frmPrincipal frmPrincipal;
    
     // Constructor de la Clase
    private CtrlPrincipal(frmPrincipal frm) {
        //igualamos las variables recibidas 
        this.frmPrincipal = frm;   
        
        // Declaramos los accion para cada uno de los botones
        this.frmPrincipal.jMenuPersonas.addActionListener(this);
        this.frmPrincipal.jMenuMinisterio.addActionListener(this);
        this.frmPrincipal.jMenuProfesion.addActionListener(this);
        this.frmPrincipal.jMenuItemConsultaPersonas.addActionListener(this);
        this.frmPrincipal.jMenuItemConsultaIglesias.addActionListener(this);
        this.frmPrincipal.jMenuItemIglesias.addActionListener(this);
        this.frmPrincipal.jMenuItemUsuario.addActionListener(this);
        this.frmPrincipal.jMenuSalir.addMouseListener(this);
    } 
    
       public void iniciar() {
        frmPrincipal.setVisible(true);    
        frmPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
        frmPrincipal.setTitle("Iglesia Comunidad de Amor");
        frmPrincipal.setLocationRelativeTo(null); 
        frmPrincipal.setResizable(true);    
    } 

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Persona
        if(ae.getSource()== frmPrincipal.jMenuPersonas) {
            String[] args = null;
            CtrlPersona.main(args);
        }       
        // Ministerio
        if(ae.getSource()== frmPrincipal.jMenuMinisterio) {
            String[] args = null;
            CtrlMinisterio.main(args);
        } 
        // Profesión
        if(ae.getSource()== frmPrincipal.jMenuProfesion) {
            String[] args = null;
            CtrlProfesion.main(args);
        }
        // Consulta de Personas
        if(ae.getSource()== frmPrincipal.jMenuItemConsultaPersonas) {
            String[] args = null;
            CtrlConsultaPersona.main(args);
        }
        
        // Consulta de Iglesias
        if(ae.getSource()== frmPrincipal.jMenuItemConsultaIglesias) {
            String[] args = null;
            CtrlConsultaIglesia.main(args);
        }
        
        // Consulta de Iglesias
        if(ae.getSource()== frmPrincipal.jMenuItemIglesias) {
            String[] args = null;
            CtrlIglesia.main(args);
        }
        
        // Registro de Usuarios
        if(ae.getSource()== frmPrincipal.jMenuItemUsuario) {
            String[] args = null;
            CtrlRegistro.main(args);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {       
        // Opción Salir
        if(me.getSource()== frmPrincipal.jMenuSalir) {
            frmPrincipal.dispose();
        }
        
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
}
