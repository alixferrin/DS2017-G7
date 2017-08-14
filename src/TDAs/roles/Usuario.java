/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs.roles;

/**
 *
 * @author HOME
 */
public class Usuario {
    private String cedula, nombre, apellido, userName, password, acceso;

    public Usuario(String cedula, String nombre, String apellido, String userName, String password, String acceso) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.userName = userName;
        this.password = password;
        this.acceso = acceso;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLevel() {
        return acceso;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", userName=" + userName + ", password=" + password + ", acceso=" + acceso + '}';
    }
    
}
