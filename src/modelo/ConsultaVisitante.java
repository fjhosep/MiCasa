/*
* AUTOR: Omar Figueroa
* Fecha: 30 de Octubre de 2019
*/

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsultaVisitante extends Conexion  {

 //************************  AGREGAR REGISTRO **********************************   
    public boolean registrar(Visitantes vis) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "INSERT INTO visitantes (nombre_visitantes,"
                + "                           apellido_visitantes,"
                + "                           tlf_visitantes,"
                + "                           tlf2_visitantes,"
                + "                           correo_visitantes,"
                + "                           tipo_visitantes"
                + "                           observacion_visitantes)"
                + "                           VALUES (?,?,?,?,?,?,?)";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, vis.getNombre_visitantes()); 
            ps.setString(2, vis.getApellido_visitantes());
            ps.setString(3, vis.getTlf_visitantes());
            ps.setString(4, vis.getTlf2_visitantes());
            ps.setString(5, vis.getCorreo_visitantes()); 
            ps.setString(6, vis.getTipo_visitantes());
            ps.setString(7, vis.getObservacion_visitantes());
            ps.executeUpdate();
            return true;   
        } catch(SQLException e) {
                System.err.println(e);    
                return false;
        } finally {
             try {
                 con.close();
             } catch(SQLException e) {
                 System.err.println(e);     
             }           
        }    
    }
//************************** MODIFICAR REGISRO *********************************
    public boolean modificar(Visitantes vis) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // creamos una variable
        String sql  = "UPDATE visitantes"
                    + "SET nombre_visitantes = ?, apellido_visitantes = ?, "
                    + "SET tlf_visitantes = ?, tlf2_visitantes = ?, "
                    + "correo_visitantes = ?, tipo_visitantes = ?, "
                    + "observacion_visitantes = ? "                
                    + "WHERE  id_visitantes = ?"; 
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, vis.getNombre_visitantes()); 
            ps.setString(2, vis.getApellido_visitantes());
            ps.setString(3, vis.getTlf_visitantes());
            ps.setString(4, vis.getTlf2_visitantes());
            ps.setString(5, vis.getCorreo_visitantes()); 
            ps.setString(6, vis.getTipo_visitantes());
            ps.setString(7, vis.getObservacion_visitantes());
            ps.setInt(8, vis.getId_visitantes());
            ps.executeUpdate();
            return true;   
        } catch(SQLException e) {
                System.err.println(e);    
                return false;
        } finally {
             try {
                 con.close();
             } catch(SQLException e) {
                 System.err.println(e);     
             }           
        }    
    }
//****************************   ELIMINAR  *************************************
    public boolean eliminar(Visitantes vis) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "DELETE FROM visitantes WHERE id_visitantes=?";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt(1, vis.getId_visitantes());
            ps.executeUpdate();
            return true;   
        } catch(SQLException e) {
                System.err.println(e);    
                return false;
        } finally {
             try {
                 con.close();
             } catch(SQLException e) {
                 System.err.println(e);     
             }           
        }      
    } 
//********************************* MOSTRAR *************************************
    public DefaultTableModel mostrar(Visitantes vis) {
        int totalRegistro = 0;
        
        DefaultTableModel modelo;
        modelo = new DefaultTableModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        //int totalRegistro = 0;
        String sql = "SELECT * FROM visitantes";
        
        try {                            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // le pasamos el resultado de la consulta a la jtTable
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd. getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Correo");
            modelo.addColumn("Tipo");
                        
            // Si encuentra registro
            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for(int i = 0; i< cantidadColumnas; i++) {  
                    filas[i] = rs.getObject(i+1);
                    
                }
                totalRegistro = totalRegistro + 1;
                modelo.addRow(filas);
            }       
            return modelo;   
        } catch(SQLException e) {
                System.err.println(e);    
                return null;
        } finally {
             try {
                 con.close();
             } catch(SQLException e) {
                 System.err.println(e);     
             }           
        }
        
    }
//******************************************************************************  
}
