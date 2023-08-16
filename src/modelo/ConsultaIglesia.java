/**
 * AUTOR: Omar Figueroa
 * FECHA: 08/08/2019
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsultaIglesia extends Conexion {

     //************************  AGREGAR REGISTRO **********************************   
    public boolean registrar(Iglesia dat) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "INSERT INTO iglesia "
                   + "(nombre_iglesia, pastor_iglesia, direccion_iglesia,"
                   + " ciudad_iglesia, estado_iglesia, pais_iglesia,"
                   + " tlf_iglesia, tlf1_iglesia) VALUES (?,?,?,?,?,?,?,?)";  
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1,  dat.getNombre_iglesia()); 
            ps.setString(2,  dat.getPastor_iglesia());
            ps.setString(3,  dat.getDireccion_iglesia());
            ps.setString(4,  dat.getCiudad_iglesia()); 
            ps.setString(5,  dat.getEstado_iglesia());
            ps.setString(6,  dat.getPais_iglesia()); 
            ps.setString(7,  dat.getTlf_iglesia());
            ps.setString(8,  dat.getTlf1_iglesia());     
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
    public boolean modificar(Iglesia dat) {
        PreparedStatement ps = null;
        Connection con = getConexion(); 
        // creamos una variable
        String sql  = "UPDATE iglesia "
                    + "SET nombre_iglesia = ?, pastor_iglesia = ?, direccion_iglesia = ?,"
                    + "ciudad_iglesia = ?, estado_iglesia = ?, pais_iglesia = ?,"
                    + "tlf_iglesia = ?, tlf1_iglesia = ? WHERE  id_iglesia = ?"; 
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1,  dat.getNombre_iglesia()); 
            ps.setString(2,  dat.getPastor_iglesia());
            ps.setString(3,  dat.getDireccion_iglesia());
            ps.setString(4,  dat.getCiudad_iglesia()); 
            ps.setString(5,  dat.getEstado_iglesia());
            ps.setString(6,  dat.getPais_iglesia()); 
            ps.setString(7,  dat.getTlf_iglesia());
            ps.setString(8,  dat.getTlf1_iglesia());   
            ps.setInt   (9,  dat.getId_iglesia());  
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
    public boolean eliminar(Iglesia dat) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "DELETE FROM iglesia WHERE id_iglesia=?";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt(1, dat.getId_iglesia());
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
    public DefaultTableModel mostrar(Iglesia dat) {
        DefaultTableModel modelo;
        modelo = new DefaultTableModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        String sql = "SELECT Id_iglesia, nombre_iglesia, pastor_iglesia, direccion_iglesia,"
                   + " ciudad_iglesia, estado_iglesia, pais_iglesia,"
                   + " tlf_iglesia, tlf1_iglesia FROM iglesia ORDER BY Id_iglesia";   
        try {                            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // le pasamos el resultado de la consulta a la jtTable
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Iglesia");
            modelo.addColumn("Pastor");
            modelo.addColumn("Dirección");
            modelo.addColumn("Ciudad");
            modelo.addColumn("Estado");
            modelo.addColumn("País");
            modelo.addColumn("Telefono");
            modelo.addColumn("Telefono");
            
            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for(int i = 0; i< cantidadColumnas; i++) {  
                    filas[i] = rs.getObject(i+1);
                }
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
