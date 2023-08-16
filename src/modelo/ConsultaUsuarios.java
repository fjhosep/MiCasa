/**
 * AUTOR: Omar Figueroa
 * FECHA: 31/05/2019
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ConsultaUsuarios extends Conexion {
    
    // Registrar Usuario
    public boolean registrar(Usuario usr) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO usuario (nombre_usuario, apellido_usuario, login_usuario, contrasena_usuario, correo_usuario, id_rol_usuario) "
                   + "VALUES(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getNombre_usuario());
            ps.setString(2, usr.getApellido_usuario());
            ps.setString(3, usr.getLogin_usuario());
            ps.setString(4, usr.getContrasena_usuario());
            ps.setString(5, usr.getCorreo_usuario());
            ps.setInt(6, usr.getId_rol_usuario());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    // Método para hacer login
    public boolean login(Usuario usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT id_usuario, nombre_usuario, contrasena_usuario, nombre_usuario, apellido_usuario, id_rol_usuario FROM usuario WHERE login_usuario = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getLogin_usuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getContrasena_usuario().equals(rs.getString(3))) {
                    
                    String sqlUpdate = "UPDATE usuario SET last_session_usuario = ? WHERE id_usuario = ?";
                    ps = con.prepareStatement(sqlUpdate);
                    ps.setString(1, usr.getLast_session_usuario());
                    ps.setInt(2, rs.getInt(1));
                    ps.executeUpdate();
                    
                    usr.setId_usuario(rs.getInt(1));
                    usr.setNombre_usuario(rs.getString(4));
                    usr.setApellido_usuario(rs.getString(5));
                    usr.setId_rol(rs.getInt(6));
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    // Verificar si el usuario existe
    public int existeUsuario(String usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT count(id_usuario) FROM usuario WHERE login_usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    // Código para validar el E-mail
    public boolean esEmail(String correo) {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        return mather.find();
    }

}
