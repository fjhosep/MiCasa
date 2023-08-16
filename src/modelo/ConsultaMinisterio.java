/*
* AUTOR: Omar Figueroa
* Fecha: 16 de Julio de 2019
*/

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class ConsultaMinisterio extends Conexion  {
    
public int totalRegistro; 
 //************************  AGREGAR REGISTRO **********************************   
    public boolean registrar(Ministerio pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "INSERT INTO ministerio (denominacion_ministerio, pastor_ministerio) VALUES (?,?)";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getDenominacion_ministerio()); 
            ps.setString(2, pro.getPastor_ministerio());
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
    public boolean modificar(Ministerio pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // creamos una variable
        String sql  = "UPDATE ministerio "
                    + "SET denominacion_ministerio = ?, pastor_ministerio = ? "
                    + "WHERE  id_ministerio = ?"; 
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getDenominacion_ministerio());
            ps.setString(2, pro.getPastor_ministerio());
            ps.setInt(3, pro.getId_ministerio());
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
    public boolean eliminar(Ministerio pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // Creamos una variable con la consulta sql
        String sql = "DELETE FROM ministerio WHERE id_ministerio=?";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId_ministerio());
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
//********************************* BUSCAR *************************************
    public boolean buscar(Ministerio pro) {              
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        String sql = "SELECT * FROM ministerio WHERE Id_ministerio = ?";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId_ministerio());  
            rs = ps.executeQuery();
            //System.out.println(ps);
            if (rs.next()){
                pro.setDenominacion_ministerio(rs.getString("denominacion_ministerio"));
                return true;
            }        
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
    public DefaultTableModel mostrar(Ministerio pro) {
        totalRegistro = 0;
        
        DefaultTableModel modelo;
        modelo = new DefaultTableModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        //int totalRegistro = 0;
        String sql = "SELECT * FROM ministerio";
        
        try {                            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // le pasamos el resultado de la consulta a la jtTable
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd. getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("Denominación");
            modelo.addColumn("Pastor Asignado");
                        
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
    public DefaultComboBoxModel cbxministerio(Ministerio pro) {  
             
        DefaultComboBoxModel modelo;
        modelo = new DefaultComboBoxModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        modelo.addElement("Selecciones una Opción");
               
        String sql = "SELECT denominacion_ministerio FROM ministerio ";
       
        try {
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();   
            //Lenamos el combobox
            while(rs.next()){             
                modelo.addElement(rs.getString("denominacion_ministerio"));
            }
            return modelo;       
        } catch(SQLException e) {
            System.err.println(e);    
            return null;  
        }  
        
    }
//*******************************************************************   
}
