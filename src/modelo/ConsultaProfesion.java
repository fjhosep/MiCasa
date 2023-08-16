/*
* AUTOR: Omar Figueroa
* Fecha: 17 de junio de 2019
*/

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;


import javax.swing.table.DefaultTableModel;

public class ConsultaProfesion extends Conexion {

public int totalRegistro;
    private int cantidadColumnas;
 //************************  AGREGAR REGISTRO **********************************   
    public boolean registrar(Profesion pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        
        String sql = "INSERT INTO profesion (denominacion_profesion) VALUES (?)";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getDenominacion_profesion());      
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
    public boolean modificar(Profesion pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        // creamos una variable
        String sql  = "UPDATE profesion SET denominacion_profesion = ? WHERE denominacion_profesion=?"; 
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getDenominacion_profesion());
            ps.setString(2, pro.getDenominacion_profesion());
            //ps.setInt(2, pro.getId_profesion()); 
            System.out.println(ps);
            ps.executeUpdate();
            System.out.println(sql);
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
    public boolean eliminar(Profesion pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();  
        
        String sql = "DELETE FROM profesion WHERE denominacion_profesion=?";
        
        try {                            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getDenominacion_profesion());
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
    public boolean buscar(Profesion pro) {
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        String sql = "SELECT id_profesion, denominacion_profesion FROM profesion WHERE Id_profesion = ?";
        try {                            
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId_profesion()); 
            rs = ps.executeQuery();
            //System.out.println(ps);
            if (rs.next()){
                //pro.setId_profesion(Integer.parseInt(rs.getString("id_profesion")));
                pro.setDenominacion_profesion(rs.getString("denominacion_profesion"));
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
    public DefaultTableModel mostrar(Profesion pro) {
        DefaultTableModel modelo;
        modelo = new DefaultTableModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();  
        
        //int totalRegistro = 0;
        String sql = "SELECT denominacion_profesion FROM profesion";
        
        try {                            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // le pasamos el resultado de la consulta a la jtTable
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("Profesión");
           
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
   public DefaultComboBoxModel cbxprofesion(Profesion pro) {  
        
        DefaultComboBoxModel modelo;
        modelo = new DefaultComboBoxModel();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection con = getConexion();  
               
        String sql = "SELECT id_profesion, denominacion_profesion FROM profesion ";
       
        try {
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();   
            
           //Lenamos el comboboxde
            while(rs.next()){        
   
                //System.out.println(rs.getInt("id_profesion"));
                //System.out.println(rs.getString("denominacion_profesion"));
                
                modelo.addElement(new Object[] {rs.getString(2), rs.getInt(1)});

            }

            return modelo;       
        } catch(SQLException e) {
            System.err.println(e);    
            return null;  
        }  
        
    }
//*******************************************************************
        /*public Vector<Profesion> cbxprofesion(Profesion pro) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();   
        
        // Creamos un objeto de tipo Vector
        Vector<Profesion> datos = new Vector<Profesion>();
        Profesion dat = null;
        
        // llenamos el combo box
        try {

            String sql = "SELECT id_profesion, denominacion_profesion FROM profesion ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

           
            while (rs.next()) {
                dat = new Profesion();
                dat.setId_profesion(Integer.parseInt(rs.getString("id_profesion")));
                datos.add(dat); 
                System.out.println(datos);
                //pro.setId_profesion(Integer.parseInt(rs.getString("id_profesion")));
               // pro.setDenominacion_profesion(rs.getString("denominacion_profesion"));

            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error consulta :" + ex.getMessage());
        }
        return datos;
    }*/
    
    //******************************************************************************    
   /*public DefaultComboBoxModel cbxprofesion(Profesion pro) {  
        
        DefaultComboBoxModel modelo;
        modelo = new DefaultComboBoxModel();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();               
        String sql = "SELECT id_profesion, denominacion_profesion FROM profesion ";
       
        try {
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();   
            
           //Lenamos el comboboxde
            while(rs.next()){        
                modelo.addElement(new Object[] {rs.getInt("id_profesion"), rs.getString("denominacion_profesion")});     
            }

            return modelo;       
        } catch(SQLException e) {
            System.err.println(e);    
            return null;  
        }  
        
    }*/

}