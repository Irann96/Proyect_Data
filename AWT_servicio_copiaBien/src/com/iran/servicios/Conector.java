package com.iran.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conector {
    public Connection miconector=null;
    public void abrir(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //String url = "jdbc:mysql://localhost:3306/peluqueria";  //el servidor/el puerto x el cual c comunicara/elnombrede la BD
            String url = "jdbc:mysql://localhost:3306/hospital";  //el servidor/el puerto x el cual c comunicara/elnombrede la BD
            miconector = DriverManager.getConnection(url, "root", "Polic4rpi496"); //DIRECCION/USUARIO A CONECTAD DB/CONTRASEÑA
            if (miconector != null) {
                System.out.println(" Conexión exitosa ");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cerrar(){
        try {
            miconector.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

