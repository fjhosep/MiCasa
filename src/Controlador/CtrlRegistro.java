/*
* AUTOR: Omar Figueroa
* Fecha: 18 de junio de 2019
*/
package Controlador;

import Vista.frmRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ConsultaUsuarios;
import modelo.Usuario;

public class CtrlRegistro implements ActionListener{
    
    public static void main(String[] args) {    
        Usuario mod = new Usuario();
        ConsultaUsuarios modConsulta = new ConsultaUsuarios();
        frmRegistro frm = new frmRegistro();
        
        CtrlRegistro ctrl = new CtrlRegistro(mod, modConsulta, frm);
        ctrl.iniciar();
 
     }   
 
        // llamamos a las clases creadas
    private Usuario mod;
    private ConsultaUsuarios modConsulta;
    private frmRegistro frm;
    
   
    // Constructor de la Clase
    public CtrlRegistro(Usuario mod, ConsultaUsuarios modConsulta, frmRegistro frm) {
       //igualamos las variables recibidas 
       this.mod = mod;
       this.modConsulta = modConsulta;
       this.frm = frm;
       
       // Declaramos los accion para cada uno de los botones
        this.frm.btnRegistrar.addActionListener(this); 
        this.frm.btnSalir.addActionListener(this);
    }
    
    // Método para crear la vista
    public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Registro de Usuarios");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false);     
    }
    
        // Método para limpiar el formulario
    public void limpiar(){
        frm.txtNombre.setText(null);
        frm.txtApellido.setText(null);
        frm.txtPassword.setText(null);
        frm.txtConfirmaPassword.setText(null);
        frm.txtEmail.setText(null);
        frm.txtUsuario.setText(null);
    }  
    // Método para salir del formulario
    public void salir(){
        frm.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Leer Boton Registro 
        if(e.getSource()== frm.btnRegistrar) {
            
            String pass =  new String(frm.txtPassword.getPassword());
            String passCon = new String(frm.txtConfirmaPassword.getPassword());
                    
            if (frm.txtUsuario.getText().equals("") || pass.equals("") || passCon.equals("") || 
                frm.txtNombre.getText().equals("") || frm.txtApellido.getText().equals("") ||frm.txtEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los datos");
            }else{
                if (pass.equals(passCon)) {
                    if(modConsulta.existeUsuario(frm.txtUsuario.getText())== 0){
                      if (modConsulta.esEmail(frm.txtEmail.getText())) {    
                            String nuevoPass = Hash.sha1(pass);
                            mod.setNombre_usuario(frm.txtNombre.getText());
                            mod.setApellido_usuario(frm.txtApellido.getText());
                            mod.setLogin_usuario(frm.txtUsuario.getText());
                            mod.setContrasena_usuario(nuevoPass);               
                            mod.setCorreo_usuario(frm.txtEmail.getText());
                            mod.setId_rol(1);
                            if(modConsulta.registrar(mod)){
                                JOptionPane.showMessageDialog(null, "El Registro ha sigo Guardado");
                                limpiar();
                            }else{ 
                                JOptionPane.showMessageDialog(null, "El Registro no ha sido Guardado. Verifique");
                                limpiar();
                            }
                       }else{ 
                            JOptionPane.showMessageDialog(null, "El Correo Electrónico no es válido");  
                       }
                    }else{
                        JOptionPane.showMessageDialog(null, "El Usuario ya existe");    
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                }  
            } 
        }
        // Leer Boton Salir 
        if(e.getSource()== frm.btnSalir) {
              salir();
        }  
    }   
}
