package com.iran.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class HospitalGestor {
public String reporte_paciente(Connection conector){

    PreparedStatement consulta = null;
    ResultSet resultSet = null;

    String tabla="";
    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
    String consultaSQL = "SELECT"
    + " idPaciente, Nombre, Telefono, Direccion, Localidad, Estado, Sexo, NSS"
    + " from paciente";

    try {
        consulta = conector.prepareStatement(consultaSQL);
        resultSet = consulta.executeQuery();

         tabla ="<title>Datos de una tabla </title>"
        		+ "<body bgcolor=\"#AFEEEE\"> <br>"
          		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla paciente </h1>"
          		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "  		
       		//+ "<table style=\"width:100%\"> "
        		+ "<tr> <th>idPaciente</th> "
        		+ "<th>Nombre</th> "
        		+ "<th>Tel&eacutefono</th> "
        		+ "<th>Direccion</th> "
        		+ "<th>Localidad</th> "
        		+ "<th>Estado</th> "
        		+ "<th>Sexo</th> "
        		+ "<th>NSS</th> </tr>";
     
                
        while ((resultSet.next()) != false) {

            //ESTRUCTURA TEMPORAL DE 7 DATOS
       
        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  //datos de id
             tabla += "<td>"+ resultSet.getString(2) + "</td>"; // datos de nombre
             tabla += "<td>"+ resultSet.getString(3) + "</td>"; //datos de telefono
             tabla += "<td>"+ resultSet.getString(4) + "</td>"; //datos del direccion
             tabla += "<td>"+ resultSet.getString(5) + "</td>";  //datos de localidad
             tabla += "<td>"+ resultSet.getString(6) + "</td>";   //datos de edo.
             tabla += "<td>"+ resultSet.getString(7) + "</td>";   //datos de sexo
             tabla += "<td>"+ resultSet.getString(8) + "</td>";  //datos de NSS
             
              tabla += "</tr>";
        }
       
        tabla+="</table>";
        
    } catch (SQLException ex) {
        System.out.println("Error al grabar en la DB");
    }
	
	return tabla;	
}

public String reporte_medico(Connection conector){

    PreparedStatement consulta = null;
    ResultSet resultSet = null;

    String tabla="";
    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
    String consultaSQL = "SELECT"
    + " idMedico, Nombre, Especialidad, Telefono, Consultorio_idConsultorio, Especialidades_idEspecialidades"
    + " from medico";

    try {
        consulta = conector.prepareStatement(consultaSQL);
        resultSet = consulta.executeQuery();

         tabla ="<title>Consulta de la tabla médico </title>"
         		+ "<body bgcolor=\"#FADBD8\"> <br>"
         		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla médico </h1>"
         		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "
        		+ "<tr><th>idMedico</th> "
        		+ "<th>Nombre</th> "
        		+ "<th>Especialidad</th> "
        		+ "<th>Telefono</th> "
        		+ "<th>Consultorio_idConsultorio</th> "
        		+ "<th>Especialidades_idEspecialidades</th> </tr> ";
     
                
        while ((resultSet.next()) != false) {

            //ESTRUCTURA TEMPORAL DE 7 DATOS
       
        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  
             tabla += "<td>"+ resultSet.getString(2) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(3) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(4) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(5) + "</td>";  
             tabla += "<td>"+ resultSet.getString(6) + "</td>";  
             
              tabla += "</tr>";
        }
       
        tabla+="</table>";
        
    } catch (SQLException ex) {
        System.out.println("Error al grabar en la DB");
    }
	
	return tabla;	
}

public String reporte_historialclinico(Connection conector){
//http://localhost:8090/AWT_servicio/rest/nickclass/consultaspeluqueros
    PreparedStatement consulta = null;
    ResultSet resultSet = null;

    String tabla="";
    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
    String consultaSQL = "SELECT"
    + " idHC, AntecedentesFamiliares, AntecedentesMedicos, Estudios, Alergias, Paciente_idPaciente"
    + " from historialclinico";

    try {
        consulta = conector.prepareStatement(consultaSQL);
        resultSet = consulta.executeQuery();

         tabla ="<title>Datos de una tabla </title>"
        		+ "<body bgcolor=\"#E8DAEF\"> <br>"
          		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla historial clinico </h1>"
          		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "  		
       	
         		//+ "<table style=\"width:100%\"> "
        		+ "<tr> <th>id HC</th> "
        		+ "<th>Antecedentes Familiares</th> "
        		+ "<th>Antecedentes Medicos</th> "
        		+ "<th>Estudios</th> "
        		+ "<th>Alergias</th> "
        		+ "<th>Paciente_idPaciente</th> ";
        		
        while ((resultSet.next()) != false) {

            //ESTRUCTURA TEMPORAL DE 7 DATOS
       
        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  
             tabla += "<td>"+ resultSet.getString(2) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(3) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(4) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(5) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(6) + "</td>"; 
             
              tabla += "</tr>";
        }
       
        tabla+="</table>";
        
    } catch (SQLException ex) {
        System.out.println("Error al grabar en la DB");
    }
	
	return tabla;	
}

public String reporte_estudios(Connection conector){

    PreparedStatement consulta = null;
    ResultSet resultSet = null;

    String tabla="";
    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
    String consultaSQL = "SELECT"
    + " idEstudio, Hallazgos, NumLaboratorio"
    + " from estudios";

    try {
        consulta = conector.prepareStatement(consultaSQL);
        resultSet = consulta.executeQuery();

         tabla ="<title>Datos de una tabla </title>"
        		+ "<body bgcolor=\"#dddddd\"> <br>"
          		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla estudios </h1>"
          		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "  		
         		//+ "<table style=\"width:100%\"> "
        		+ "<tr> <th>idEstudio</th> "
        		+ "<th>Hallazgos</th> "
        		+ "<th>Numero de Laboratorio</th> ";
        		
        		
        while ((resultSet.next()) != false) {

            //ESTRUCTURA TEMPORAL DE 7 DATOS
       
        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  
             tabla += "<td>"+ resultSet.getString(2) + "</td>"; 
             tabla += "<td>"+ resultSet.getString(3) + "</td>"; 
             
              tabla += "</tr>";
        }
       
        tabla+="</table>";
        
    } catch (SQLException ex) {
        System.out.println("Error al grabar en la DB");
    }
	
	return tabla;	
}


public String reporte_especialidades(Connection conector){
	//http://localhost:8090/AWT_servicio/rest/nickclass/consultaspeluqueros
	    PreparedStatement consulta = null;
	    ResultSet resultSet = null;

	    String tabla="";
	    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
	    String consultaSQL = "SELECT"
	    + " idEspecialidades, NombreDeEspecialidad, Descripcion"
	    + " from especialidades";

	    try {
	        consulta = conector.prepareStatement(consultaSQL);
	        resultSet = consulta.executeQuery();

	         tabla ="<title>Datos de una tabla </title>"
	        		+ "<body bgcolor=\"#E8DAEF\"> <br>"
	          		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla especialidades </h1>"
	          		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "  		
	       	
	         		//+ "<table style=\"width:100%\"> "
	        		+ "<tr> <th>idEspecialidades</th> "
	        		+ "<th>NombreDeEspecialidad</th> "
	        		+ "<th>Descripcion</th> ";
	        		
	        while ((resultSet.next()) != false) {

	            //ESTRUCTURA TEMPORAL DE 7 DATOS
	       
	        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
	             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  
	             tabla += "<td>"+ resultSet.getString(2) + "</td>"; 
	             tabla += "<td>"+ resultSet.getString(3) + "</td>"; 
	             
	              tabla += "</tr>";
	        }
	       
	        tabla+="</table>";
	        
	    } catch (SQLException ex) {
	        System.out.println("Error al grabar en la DB");
	    }
		
		return tabla;	
	}

public String reporte_consultorio(Connection conector){
	//http://localhost:8090/AWT_servicio/rest/nickclass/consultaspeluqueros
	    PreparedStatement consulta = null;
	    ResultSet resultSet = null;

	    String tabla="";
	    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
	    String consultaSQL = "SELECT"
	    + " idConsultorio, Piso, AreaDeEspecialidad, NumConsultorio"
	    + " from consultorio";

	    try {
	        consulta = conector.prepareStatement(consultaSQL);
	        resultSet = consulta.executeQuery();

	         tabla ="<title>Datos de una tabla </title>"
	        		+ "<body bgcolor=\"#E8DAEF\"> <br>"
	          		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla consultorio </h1>"
	          		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "  		
	       	
	         		//+ "<table style=\"width:100%\"> "
	        		+ "<tr> <th>idConsultorio</th> "
	        		+ "<th>Piso</th> "
	        		+ "<th>AreaDeEspecialidad</th> "
	        		+ "<th>NumConsultorio</th> ";
	        		
	        while ((resultSet.next()) != false) {

	            //ESTRUCTURA TEMPORAL DE 7 DATOS
	       
	        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
	             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  
	             tabla += "<td>"+ resultSet.getString(2) + "</td>"; 
	             tabla += "<td>"+ resultSet.getString(3) + "</td>"; 
	             tabla += "<td>"+ resultSet.getString(4) + "</td>";
	             
	              tabla += "</tr>";
	        }
	       
	        tabla+="</table>";
	        
	    } catch (SQLException ex) {
	        System.out.println("Error al grabar en la DB");
	    }
		
		return tabla;	
	}

public String reporte_citas(Connection conector){
	//http://localhost:8090/AWT_servicio/rest/nickclass/consultaspeluqueros
	    PreparedStatement consulta = null;
	    ResultSet resultSet = null;

	    String tabla="";
	    //DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
	    String consultaSQL = "SELECT"
	    + " idCita, Fecha, Hora, Medico_idMedico, Paciente_idPaciente"
	    + " from citas";

	    try {
	        consulta = conector.prepareStatement(consultaSQL);
	        resultSet = consulta.executeQuery();

	         tabla ="<title>Datos de una tabla </title>"
	        		+ "<body bgcolor=\"#E8DAEF\"> <br>"
	          		+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos de la tabla citas </h1>"
	          		+ "<table style=\"text-align:center\" border=\"1px solid black\" bgcolor=\" #ffcccc\"  align=\"center\"> "  		
	       	
	         		//+ "<table style=\"width:100%\"> "
	        		+ "<tr> <th>idCita</th> "
	        		+ "<th>Fecha</th> "
	        		+ "<th>Hora</th> "
	        		+ "<th>Medico_idMedico</th> "
	        		+ "<th>Paciente_idPaciente</th> ";
	        		
	        while ((resultSet.next()) != false) {

	            //ESTRUCTURA TEMPORAL DE 7 DATOS
	       
	        	 tabla += "<tr>";  //no se pierda la cadea de la tabla
	             tabla += "<td>"+ resultSet.getInt(1) + "</td>";  
	             tabla += "<td>"+ resultSet.getString(2) + "</td>"; 
	             tabla += "<td>"+ resultSet.getString(3) + "</td>"; 
	             tabla += "<td>"+ resultSet.getString(4) + "</td>"; 
	             tabla += "<td>"+ resultSet.getString(5) + "</td>";
	             
	              tabla += "</tr>";
	        }
	       
	        tabla+="</table>";
	        
	    } catch (SQLException ex) {
	        System.out.println("Error al grabar en la DB");
	    }
		
		return tabla;	
	}

}
