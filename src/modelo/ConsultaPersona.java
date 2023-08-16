/*
* AUTOR: Omar Figueroa
* Fecha: 18 de Julio de 2019
*/
package modelo;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsultaPersona extends Conexion  {
    
     //************************  AGREGAR REGISTRO **********************************   
    public boolean registrar(Persona per) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "INSERT INTO persona "
                   + "(cedula_persona, nombre_persona, apellido_persona,"
                   + " fechaNac_persona, dirhab_persona,telfijo_persona,"
                   + " telcel_persona, id_profesion_persona, ocupacion_persona,"
                   + " correo_persona, bautizado_persona, iglesiaProv_persona,"
                   + " observacion_persona, id_ministerio_persona)" 
                   + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt   (1,  per.getCedula_persona()); 
            ps.setString(2,  per.getNombre_persona());
            ps.setString(3,  per.getApellido_persona());
            ps.setDate  (4, (Date) per.getFechaNac_persona()); 
            ps.setString(5,  per.getDirhab_persona());
            ps.setString(6,  per.getTelfijo_persona()); 
            ps.setString(7,  per.getTelcel_persona());
            ps.setInt   (8,  per.getId_profesion_persona()); 
            ps.setString(9,  per.getOcupacion_persona());
            ps.setString(10, per.getCorreo_persona()); 
            ps.setString(11, per.getBautizo_persona());
            ps.setString(12, per.getIglesiaProv_persona()); 
            ps.setString(13, per.getObservacion_persona());
            ps.setInt   (14, per.getId_ministerio_persona());    
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
    public boolean modificar(Persona per) {
        PreparedStatement ps = null;
        Connection con = getConexion(); 
        // creamos una variable
        String sql  = "UPDATE persona "
                    + "SET cedula_persona = ?, nombre_persona = ?, apellido_persona = ?,"
                    + "fechaNac_persona = ?, dirhab_persona = ?, telfijo_persona = ?,"
                    + "telcel_persona = ?, id_profesion_persona = ?, ocupacion_persona = ?,"
                    + "correo_persona = ?, bautizado_persona = ?, iglesiaProv_persona = ?,"
                    + "observacion_persona = ?, id_ministerio_persona = ? "                 
                    + "WHERE  id_persona = ?"; 
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt   (1,  per.getCedula_persona()); 
            ps.setString(2,  per.getNombre_persona());
            ps.setString(3,  per.getApellido_persona());
            ps.setDate  (4, (Date) per.getFechaNac_persona()); 
            ps.setString(5,  per.getDirhab_persona());
            ps.setString(6,  per.getTelfijo_persona()); 
            ps.setString(7,  per.getTelcel_persona());
            ps.setInt   (8,  per.getId_profesion_persona()); 
            ps.setString(9,  per.getOcupacion_persona());
            ps.setString(10, per.getCorreo_persona()); 
            ps.setString(11, per.getBautizo_persona());
            ps.setString(12, per.getIglesiaProv_persona()); 
            ps.setString(13, per.getObservacion_persona());
            ps.setInt   (14, per.getId_ministerio_persona()); 
            ps.setInt   (15, per.getId_persona());
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
    public boolean eliminar(Persona per) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "DELETE FROM persona WHERE id_persona=?";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt(1, per.getId_persona());
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
    public DefaultTableModel mostrar(Persona per) {
        DefaultTableModel modelo;
        modelo = new DefaultTableModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        String sql = "SELECT Id_persona, cedula_persona, nombre_persona, apellido_persona,"
                   + " fechaNac_persona, dirhab_persona, telfijo_persona, telcel_persona,"
                   + " id_profesion_persona, ocupacion_persona,"
                   + " correo_persona, bautizado_persona, iglesiaProv_persona,"
                   + " observacion_persona, id_ministerio_persona" 
                   + " FROM persona ORDER BY Id_persona";
         
        
        try {                            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // le pasamos el resultado de la consulta a la jtTable
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("Id");
            modelo.addColumn("Cédula");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Fecha Nac");
            modelo.addColumn("Dirección");
            modelo.addColumn("Telefono");
            modelo.addColumn("Celular");
            modelo.addColumn("Profesion");
            modelo.addColumn("Ocupación");
            modelo.addColumn("Correo");
            modelo.addColumn("Bautizado");
            modelo.addColumn("Iglesia");
            modelo.addColumn("Observacion");
            modelo.addColumn("Ministerio");
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
