/**
 * AUTOR: Omar Figueroa
 * FECHA: 31/05/2019
 */


package modelo;

public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String apellido_usuario;
    private String login_usuario;
    private String contrasena_usuario;
    private String correo_usuario;
    private String last_session_usuario;
    private int id_rol_usuario;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getContrasena_usuario() {
        return contrasena_usuario;
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getLast_session_usuario() {
        return last_session_usuario;
    }

    public void setLast_session_usuario(String last_session_usuario) {
        this.last_session_usuario = last_session_usuario;
    }

    public int getId_rol_usuario() {
        return id_rol_usuario;
    }

    public void setId_rol(int id_rol_usuario) {
        this.id_rol_usuario = id_rol_usuario;
    }


       
}
