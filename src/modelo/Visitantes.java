/**
 * AUTOR: Omar Figueroa
 * FECHA: 25/10/2019
 */

package modelo;

public class Visitantes {

    private int    id_visitantes;    
    private String nombre_visitantes; 
    private String apellido_visitantes; 
    private String tlf_visitantes; 
    private String tlf2_visitantes; 
    private String correo_visitantes; 
    private String tipo_visitantes; 
    private String observacion_visitantes; 

    public int getId_visitantes() {
        return id_visitantes;
    }

    public void setId_visitantes(int id_visitantes) {
        this.id_visitantes = id_visitantes;
    }

    public String getNombre_visitantes() {
        return nombre_visitantes;
    }

    public void setNombre_visitantes(String nombre_visitantes) {
        this.nombre_visitantes = nombre_visitantes;
    }

    public String getApellido_visitantes() {
        return apellido_visitantes;
    }

    public void setApellido_visitantes(String apellido_visitantes) {
        this.apellido_visitantes = apellido_visitantes;
    }

    public String getTlf_visitantes() {
        return tlf_visitantes;
    }

    public void setTlf_visitantes(String tlf_visitantes) {
        this.tlf_visitantes = tlf_visitantes;
    }

    public String getTlf2_visitantes() {
        return tlf2_visitantes;
    }

    public void setTlf2_visitantes(String tlf2_visitantes) {
        this.tlf2_visitantes = tlf2_visitantes;
    }
    
    public String getCorreo_visitantes() {
        return correo_visitantes;
    }

    public void setCorreo_visitantes(String correo_visitantes) {
        this.correo_visitantes = correo_visitantes;
    }

    public String getTipo_visitantes() {
        return tipo_visitantes;
    }

    public void setTipo_visitantes(String tipo_visitantes) {
        this.tipo_visitantes = tipo_visitantes;
    }

    public String getObservacion_visitantes() {
        return observacion_visitantes;
    }

    public void setObservacion_visitantes(String observacion_visitantes) {
        this.observacion_visitantes = observacion_visitantes;
    }
    
}
