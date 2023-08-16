
package modelo;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public class cbxprofesion {
    
    private int id_profesion;
    private String denominacion_profesion;
    
    
    public cbxprofesion() {
    }

    public cbxprofesion(int id_profesion, String denominacion_profesion) {
        this.id_profesion = id_profesion;
        this.denominacion_profesion = denominacion_profesion;
    }


    public int getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
    }

    public String getDenominacion_profesion() {
        return denominacion_profesion;
    }

    public void setDenominacion_profesion(String denominacion_profesion) {
        this.denominacion_profesion = denominacion_profesion;
    }

    
    @Override
    public String toString() {
        return this.denominacion_profesion;
    }
       
    public void listar_profesion(JComboBox box){
        DefaultComboBoxModel value;
        Conexion conect = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{ 
            con = conect.getConexion(); 
            String sql = "SELECT id_profesion, denominacion_profesion FROM profesion ";
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();   
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while(rs.next()){
                value.addElement(new cbxprofesion(rs.getInt(1),rs.getString(2)));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                //ps.close();
                //con.close();
                rs.close();
            }catch(Exception ex){
            }
        }
        
    }
    
    public void listar_idProfesion(int idProfesion, JComboBox box) {
        DefaultComboBoxModel value;
        Conexion conect = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{ 
            con = conect.getConexion(); 
            String sql = "SELECT id_profesion, denominacion_profesion FROM profesion WHERE id_profesion = ?";
            ps = con.prepareStatement(sql); 
            ps.setInt(1, idProfesion);
            rs = ps.executeQuery();   
            value = new DefaultComboBoxModel();
            box.setModel(value);
            while(rs.next()){
                value.addElement(new cbxprofesion(rs.getInt(1),rs.getString(2)));
            }
            
            String sqlAll = "SELECT id_profesion, denominacion_profesion FROM profesion";
            ps = con.prepareStatement(sqlAll); 
            rs = ps.executeQuery();   
            while(rs.next()){
                value.addElement(new cbxprofesion(rs.getInt(1),rs.getString(2)));
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                //ps.close();
                //con.close();
                rs.close();
            }catch(Exception ex){
            }
        }
    }

}
