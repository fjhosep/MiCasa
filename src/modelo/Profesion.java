/*
* AUTOR: Omar Figueroa
* Fecha: 17 de junio de 2019
*/

package modelo;


public class Profesion {

    private int id_profesion;
    private String denominacion_profesion;


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

    public void setId_profesion(String idProfesion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
