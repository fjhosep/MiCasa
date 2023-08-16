/*
* AUTOR: Omar Figueroa
* Fecha: 31 de Julio de 2019
*/

package Controlador;

import Vista.frmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ConsultaUsuarios;
import modelo.Usuario;

public class CtrlLogin implements ActionListener {
    
        public static void main(String[] args) {    
        Usuario mod = new Usuario();
        ConsultaUsuarios modConsulta = new ConsultaUsuarios();
        frmLogin frm = new frmLogin();
        
        CtrlLogin ctrl = new CtrlLogin(mod, modConsulta, frm);
        ctrl.iniciar();
 
     }   
 
    // llamamos a las clases creadas
    private final Usuario mod;
    private final ConsultaUsuarios modConsulta;
    private final frmLogin frm;
    
   
    // Constructor de la Clase
    public CtrlLogin(Usuario mod, ConsultaUsuarios modConsulta, frmLogin frm) {
       //igualamos las variables recibidas 
       this.mod = mod;
       this.modConsulta = modConsulta;
       this.frm = frm;
       
        //Declaramos los accion para cada uno de los botones
          this.frm.btnEntrar.addActionListener(this); 
        
    }
    
    // Método para crear la vista
    public void iniciar() {
        frm.setVisible(true);
        frm.setTitle("Acceso al Sistema");
        frm.setLocationRelativeTo(null);  
        frm.setResizable(false);     
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Date date = new Date();
        DateFormat fechaHora;
        fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String pass = new String(frm.txtPassword.getPassword());
        
        if (!frm.txtUsuario.getText().equals("") && !pass.equals("")) {

            String nuevoPass = Hash.sha1(pass);

            mod.setLogin_usuario(frm.txtUsuario.getText());
            mod.setContrasena_usuario(nuevoPass);
            mod.setLast_session_usuario(fechaHora.format(date));
            if (modConsulta.login(mod)) {
                //frmLogin = null;
                frm.dispose();
                
                String[] args = null;
                CtrlPrincipal.main(args);

            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
                //limpiar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
        }
        
    }
    
    
    
}
