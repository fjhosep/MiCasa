/*
* AUTOR: Omar Figueroa
* Fecha: 24 de Julio de 2019
*/
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class cbxministerio {
    
    private int id_ministerio;
    private String denominacion_ministerio;

    public cbxministerio() {
    }
    
    public cbxministerio(int id_ministerio, String denominacion_ministerio) {
        this.id_ministerio = id_ministerio;
        this.denominacion_ministerio = denominacion_ministerio;
    }

    public int getId_ministerio() {
        return id_ministerio;
    }

    public void setId_ministerio(int id_ministerio) {
        this.id_ministerio = id_ministerio;
    }

    public String getDenominacion_ministerio() {
        return denominacion_ministerio;
    }

    public void setDenominacion_ministerio(String denominacion_ministerio) {
        this.denominacion_ministerio = denominacion_ministerio;
    }


    @Override
    public String toString() {
        return this.denominacion_ministerio;
    }
    
        public void listar_ministerio(JComboBox box){
        DefaultComboBoxModel value;
        PreparedStatement ps = null;
        Conexion conect = new Conexion();
        Connection con = null;
        con = conect.getConexion(); 
        ResultSet rs = null;
        try{ 
            String sql = "SELECT id_ministerio, denominacion_ministerio FROM ministerio ";
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();   
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while(rs.next()){
                value.addElement(new cbxministerio(rs.getInt(1),rs.getString(2)));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                rs.close();
            }catch(Exception ex){
            }
        }
        
    }
          
    public void listar_idMinisterio(int idMinisterio, JComboBox box) {
        DefaultComboBoxModel value;
        PreparedStatement ps = null;
        Conexion conect = new Conexion();
        Connection con = null;
        con = conect.getConexion(); 
        ResultSet rs = null;
        try{ 
            String sql = "SELECT id_ministerio, denominacion_ministerio FROM ministerio WHERE id_ministerio = ?";
            ps = con.prepareStatement(sql); 
            ps.setInt(1, idMinisterio);
            rs = ps.executeQuery();   
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while(rs.next()){
                value.addElement(new cbxministerio(rs.getInt(1),rs.getString(2)));
            }
            
            String sqlAll = "SELECT id_ministerio, denominacion_ministerio FROM ministerio";
            ps = con.prepareStatement(sqlAll); 
            rs = ps.executeQuery();   
            while(rs.next()){
                value.addElement(new cbxministerio(rs.getInt(1),rs.getString(2)));
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                rs.close();
            }catch(Exception ex){
            }
        }
    }
}
