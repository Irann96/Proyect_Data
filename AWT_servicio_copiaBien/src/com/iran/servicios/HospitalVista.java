package com.iran.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


//http://localhost:8090/AWT_servicio/rest/nickclass/nickmethod?dato1=Juan&dato2=23&dato3=masculino
//http://localhost:8090/AWT_servicio/rest/nickclass/nickmethod?nombre=Pedro&direccion=Av.Tecnologico&telefono=5442&email=iran@gmail.com


//@QueryParam, son los datos kc allmacenan en la variable local
//nickclass, sobrenombre de la clase que se va a llamar
//@GET, corresponde al metodo para hacer la llamada HTTP
//nickmethod, sobrenombre del método, y corresponde al nombre del webservice
//@Produces, indica el formato k va a regresar
@Path("nickclass")
public class HospitalVista {
	@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
	@Path("/nickmethod")
	@Produces(MediaType.TEXT_HTML)
	public String mensaje(@QueryParam("Nombre") String Nombre,
						@QueryParam("Telefono") String Telefono, 
						@QueryParam("Direccion") String Direccion,
						@QueryParam("Localidad") String Localidad,
						@QueryParam("Estado") String Estado,
						@QueryParam("Sexo") String Sexo,
						@QueryParam("NSS") String NSS) throws Exception{
		
		Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
		c.abrir();

		PreparedStatement objetoSentSql = null;
		ResultSet generatedKeys = null;
		int id = 0;         
        
		// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
		String sql = "INSERT INTO paciente "
				+ "(Nombre, Telefono, Direccion, Localidad, Estado, Sexo, NSS) "
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			c.miconector.setAutoCommit(false);
			objetoSentSql = c.miconector.prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
			objetoSentSql.setString(1, Nombre);
			objetoSentSql.setString(2, Telefono);
			objetoSentSql.setString(3, Direccion);
			objetoSentSql.setString(4, Localidad);
			objetoSentSql.setString(5, Estado);
			objetoSentSql.setString(6, Sexo);
			objetoSentSql.setString(7, NSS);
			
			objetoSentSql.executeUpdate();
			generatedKeys = objetoSentSql.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			c.miconector.commit();

		} catch (SQLException ex) {
			try {
				c.miconector.rollback();
			} catch (SQLException ex1) {
				System.out.println("Error en recuperación de transacción");
			}
		}
		// //////////////////////            	
		c.cerrar();
		String respuesta = "<h1 \"color:dark\">" + "Datos insertados correctamente <br>" + "</h1>" 
				+ "<br> Hola amigo: " + Nombre + ", <br> Telefono: " + Telefono + "<br>Direccion: "+ Direccion 
				+"<br> Localidad: " + Localidad + ", <br> Estado: " + Estado + ", <br> Sexo: " + Sexo
				+ "<br> NSS: "+ NSS+ "<br>id: "+id;
		//return "<html><body><h1>"+respuesta +"</h1></body></html>"; #AFEEEE
		return "<html><body bgcolor=\"#AFEEEE\"> <br> "
					+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla de paciente </h1>"
					
				+ "<h1>"+respuesta +"</h1> <br> "
		+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
		+ "</body></html>";
	}
	
	//ALTA DOS
	
	@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
	@Path("/nickmethod2")
	@Produces(MediaType.TEXT_HTML)
	public String mensaje2(@QueryParam("Nombre") String Nombre,
						@QueryParam("Especialidad") String Especialidad,
						@QueryParam("Telefono") String Telefono,
						@QueryParam("Consultorio_idConsultorio") String Consultorio_idConsultorio,
						@QueryParam("Especialidades_idEspecialidades") String Especialidades_idEspecialidades) throws Exception{
		
		Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
		c.abrir();

		PreparedStatement objetoSentSql = null;
		ResultSet generatedKeys = null;
		int id = 0;         
        
		// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
		String sql = "INSERT INTO medico "
				+ "(Nombre, Especialidad, Telefono, Consultorio_idConsultorio, Especialidades_idEspecialidades) "
				+ "VALUES(?,?,?,?,?)";
		try {
			c.miconector.setAutoCommit(false);
			objetoSentSql = c.miconector.prepareStatement(sql,
			PreparedStatement.RETURN_GENERATED_KEYS);
			objetoSentSql.setString(1, Nombre);
			objetoSentSql.setString(2, Especialidad);
			objetoSentSql.setString(3, Telefono);
			objetoSentSql.setString(4, Consultorio_idConsultorio);
			objetoSentSql.setString(5, Especialidades_idEspecialidades);

			objetoSentSql.executeUpdate();
			generatedKeys = objetoSentSql.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			c.miconector.commit();

		} catch (SQLException ex) {
			try {
				c.miconector.rollback();
			} catch (SQLException ex1) {
				System.out.println("Error en recuperación de transacción");
			}
		}
		// //////////////////////            	
		c.cerrar();
		String respuesta = "<h1 \"color:dark\">" + "Datos insertados correctamente <br>" + "</h1>"
				+ "<br> Hola amigo: " + Nombre + " <br>Especialidad: " + Especialidad + "<br>Telefono personal: "+ Telefono + "<br>Consultorio_idConsultorio: " +Consultorio_idConsultorio + "<br> Especialidades_idEspecialidades" + Especialidades_idEspecialidades + "<br>id : "+id;
		//return "<html><body><h1>"+respuesta +"</h1></body></html>";
		
		return "<html><body bgcolor=\"#E8DAEF\"> <br> "
				+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla de medico </h1>"	
				+ "<h1>"+respuesta +"</h1> <br> "
		+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
		+ "</body></html>";
		
	}
	
	//ALTA TRES
	
		@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
		@Path("/nickmethod3")
		@Produces(MediaType.TEXT_HTML)
		public String mensaje3(@QueryParam("Antecedentes Familiares") String AntecedentesFamiliares,
							@QueryParam("AntecedentesMedicos") String AntecedentesMedicos,
							@QueryParam("Estudios") String Estudios,
							@QueryParam("Alergias") String Alergias,
							@QueryParam("TipoSanguineo") String TipoSanguineo,
							@QueryParam("Paciente_idPaciente") String Paciente_idPaciente) throws Exception{
			
			Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
			c.abrir();

			PreparedStatement objetoSentSql = null;
			ResultSet generatedKeys = null;
			int id = 0;         
	        
			// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
			String sql = "INSERT INTO historialclinico "
					+ "(AntecedentesFamiliares, AntecedentesMedicos, Estudios, Alergias, TipoSanguineo, Paciente_idPaciente) "
					+ "VALUES(?,?,?,?,?,?)";
			try {
				c.miconector.setAutoCommit(false);
				objetoSentSql = c.miconector.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
				objetoSentSql.setString(1, AntecedentesFamiliares);
				objetoSentSql.setString(2, AntecedentesMedicos);
				objetoSentSql.setString(3, Estudios);
				objetoSentSql.setString(4, Alergias);
				objetoSentSql.setString(5, TipoSanguineo);
				objetoSentSql.setString(6, Paciente_idPaciente);
				
				objetoSentSql.executeUpdate();
				generatedKeys = objetoSentSql.getGeneratedKeys();
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				}
				c.miconector.commit();

			} catch (SQLException ex) {
				try {
					c.miconector.rollback();
				} catch (SQLException ex1) {
					System.out.println("Error en recuperación de transacción");
				}
			}
			// //////////////////////            	
			c.cerrar();
			String respuesta = "<h1\"color:dark\">" + "Datos insertados correctamente <br>" + "</h2>"
					+ " <br> Bienvenido al Servicio "  + AntecedentesFamiliares + " <br>Antecedentes medicos: " + AntecedentesMedicos + "<br> Estudios realizados: "+ Estudios + "<br>Alergias presentadas: "+ Alergias + "<br> Tipo Sanguineo: " + TipoSanguineo +"<br> Paciente_idPaciente: " + Paciente_idPaciente + "<br>id : "+id;
			//return "<html><body><h1>"+respuesta +"</h1></body></html>";
			return "<html><body bgcolor=\"#dddddd\"> <br> "
					+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla de Historial clinico </h1>"
					+ "<h1> <br>"+respuesta +"</h1> <br> "
			+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
			+ "</body></html>";
		}	
		//ALTA CUATRO
		
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/nickmethod4")
			@Produces(MediaType.TEXT_HTML)
			public String mensaje4(@QueryParam("Hallazgos") String Hallazgos,
								@QueryParam("Numero de Laboratorio") String NumLaboratorio) throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();

				PreparedStatement objetoSentSql = null;
				ResultSet generatedKeys = null;
				int id = 0;         
		        
				// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
				String sql = "INSERT INTO estudios "
						+ "(Hallazgos, NumLaboratorio) "
						+ "VALUES(?,?)";
				try {
					c.miconector.setAutoCommit(false);
					objetoSentSql = c.miconector.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
					objetoSentSql.setString(1, Hallazgos);
					objetoSentSql.setString(2, NumLaboratorio);
					
					objetoSentSql.executeUpdate();
					generatedKeys = objetoSentSql.getGeneratedKeys();
					if (generatedKeys.next()) {
						id = generatedKeys.getInt(1);
					}
					c.miconector.commit();

				} catch (SQLException ex) {
					try {
						c.miconector.rollback();
					} catch (SQLException ex1) {
						System.out.println("Error en recuperación de transacción");
					}
				}
				// //////////////////////            	
				c.cerrar();
				String respuesta = "<h1\"color:dark\">" + "Datos insertados correctamente<br>" + "</h1>"
						+ "<br> Bienvenido<br>Hallazgos: " +Hallazgos + " <br> Numero de Laboratorio: " + NumLaboratorio + "<br>id : "+id;
				//return "<html><body><h1>"+respuesta +"</h1></body></html>";
				
	
						
				return "<html><body bgcolor=\"#FADBD8\"> <br>"
						+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla estudios </h1>"
						+ "<h1>"+respuesta +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
			//ALTA CINCO
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/nickmethod5")
			@Produces(MediaType.TEXT_HTML)
			public String mensaje5(@QueryParam("NombreDeEspecialidad") String NombreDeEspecialidad,
								@QueryParam("Descripcion") String Descripcion) throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();

				PreparedStatement objetoSentSql = null;
				ResultSet generatedKeys = null;
				int id = 0;         
		        
				// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
				String sql = "INSERT INTO especialidades "
						+ "(NombreDeEspecialidad, Descripcion) "
						+ "VALUES(?,?)";
				try {
					c.miconector.setAutoCommit(false);
					objetoSentSql = c.miconector.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
					objetoSentSql.setString(1, NombreDeEspecialidad);
					objetoSentSql.setString(2, Descripcion);
					
					objetoSentSql.executeUpdate();
					generatedKeys = objetoSentSql.getGeneratedKeys();
					if (generatedKeys.next()) {
						id = generatedKeys.getInt(1);
					}
					c.miconector.commit();

				} catch (SQLException ex) {
					try {
						c.miconector.rollback();
					} catch (SQLException ex1) {
						System.out.println("Error en recuperación de transacción");
					}
				}
				// //////////////////////            	
				c.cerrar();
				String respuesta = "<h1\"color:dark\">" + "Datos insertados correctamente<br>" + "</h1>"
						+ "<br> Bienvenido<br>Especialidad: " + NombreDeEspecialidad + " <br> Descripciono: " + Descripcion + "<br>id : "+id;
				//return "<html><body><h1>"+respuesta +"</h1></body></html>";
				
	
						
				return "<html><body bgcolor=\"#FADBD8\"> <br>"
						+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla especialidades </h1>"
						+ "<h1>"+respuesta +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
			//ALTA SEIS
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/nickmethod6")
			@Produces(MediaType.TEXT_HTML)
			public String mensaje6(@QueryParam("Piso") String Piso,
								@QueryParam("AreaDeEspecialidad") String AreaDeEspecialidad,
								@QueryParam("NumConsultorio") String NumConsultorio) throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();

				PreparedStatement objetoSentSql = null;
				ResultSet generatedKeys = null;
				int id = 0;         
		        
				// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
				String sql = "INSERT INTO consultorio "
						+ "(Piso, AreaDeEspecialidad, NumConsultorio) "
						+ "VALUES(?,?,?)";
				try {
					c.miconector.setAutoCommit(false);
					objetoSentSql = c.miconector.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
					objetoSentSql.setString(1, Piso);
					objetoSentSql.setString(2, AreaDeEspecialidad);
					objetoSentSql.setString(3, NumConsultorio);
					
					objetoSentSql.executeUpdate();
					generatedKeys = objetoSentSql.getGeneratedKeys();
					if (generatedKeys.next()) {
						id = generatedKeys.getInt(1);
					}
					c.miconector.commit();

				} catch (SQLException ex) {
					try {
						c.miconector.rollback();
					} catch (SQLException ex1) {
						System.out.println("Error en recuperación de transacción");
					}
				}
				// //////////////////////            	
				c.cerrar();
				String respuesta = "<h1\"color:dark\">" + "Datos insertados correctamente<br>" + "</h1>"
						+ "<br> Bienvenido<br> Piso: " + Piso + " <br> Area de especialidad: " + AreaDeEspecialidad + "<br> Numero de Consultorio"+ NumConsultorio +"<br>id : "+id;
				//return "<html><body><h1>"+respuesta +"</h1></body></html>";
				
	
						
				return "<html><body bgcolor=\"#FADBD8\"> <br>"
						+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla consultorio </h1>"
						+ "<h1>"+respuesta +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
			//ALTA SIETE
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/nickmethod7")
			@Produces(MediaType.TEXT_HTML)
			public String mensaje7(@QueryParam("Fecha") String Fecha,
								@QueryParam("Hora") String Hora,
								@QueryParam("Medico_idMedico") String Medico_idMedico,
								@QueryParam("Paciente_idPaciente") String Paciente_idPaciente) throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();

				PreparedStatement objetoSentSql = null;
				ResultSet generatedKeys = null;
				int id = 0;         
		        
				// DEBE TENER ESPACIO PARA QUE ENTRE LA INSTRUCCION
				String sql = "INSERT INTO citas "
						+ "(Fecha, Hora, Medico_idMedico, Paciente_idPaciente "
						+ "VALUES(?,?,?,?)";
				try {
					c.miconector.setAutoCommit(false);
					objetoSentSql = c.miconector.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
					objetoSentSql.setString(1, Fecha);
					objetoSentSql.setString(2, Hora);
					objetoSentSql.setString(3, Medico_idMedico);
					objetoSentSql.setString(4, Paciente_idPaciente);
					
					objetoSentSql.executeUpdate();
					generatedKeys = objetoSentSql.getGeneratedKeys();
					if (generatedKeys.next()) {
						id = generatedKeys.getInt(1);
					}
					c.miconector.commit();

				} catch (SQLException ex) {
					try {
						c.miconector.rollback();
					} catch (SQLException ex1) {
						System.out.println("Error en recuperación de transacción");
					}
				}
				// //////////////////////            	
				c.cerrar();
				String respuesta = "<h1\"color:dark\">" + "Datos insertados correctamente<br>" + "</h1>"
						+ "<br> Bienvenido<br> Fecha: " + Fecha + " <br> Hora: " + Hora + "<br> Medico_idMedico"+ Medico_idMedico +  "<br> Paciente_idPaciente: " + Paciente_idPaciente +"<br>id : " + id;
				//return "<html><body><h1>"+respuesta +"</h1></body></html>";
				
	
						
				return "<html><body bgcolor=\"#FADBD8\"> <br>"
						+ "<h1 align=\"center\" style=\"color:darkblue\"> Datos insertados a la tabla citas </h1>"
						+ "<h1>"+respuesta +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}				
			//Consulta Pacientes
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultapacientes")
			@Produces(MediaType.TEXT_HTML)
			public String consultaclientes () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();
				String tabla = hospital.reporte_paciente(c.miconector);
				
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_hospital/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
			
//Consulta medico
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultamedico")
			@Produces(MediaType.TEXT_HTML)
			public String consultamedico () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();
				String tabla = hospital.reporte_medico(c.miconector);
				
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body {color:red}><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
//Consulta historialclinico
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultahistorialclinico")
			@Produces(MediaType.TEXT_HTML)
			public String consultahistorialclinico () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();			
				String tabla = hospital.reporte_historialclinico(c.miconector);
			
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
				
			}	

//Consulta estudios
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultaestudios")
			@Produces(MediaType.TEXT_HTML)
			public String consultaestudios () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();
				String tabla = hospital.reporte_estudios(c.miconector);
				
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_hospital/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
//Consulta especialidades
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultaespecialidades")
			@Produces(MediaType.TEXT_HTML)
			public String consultaespecialidades () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();
				String tabla = hospital.reporte_especialidades(c.miconector);
				
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	
			
//Consulta consultorio
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultaconsultorio")
			@Produces(MediaType.TEXT_HTML)
			public String consultaconsultorio () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();
				String tabla = hospital.reporte_consultorio(c.miconector);
				
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	

//Consulta citas
			
			@GET   //el get es uno de los 4 métodos de acceso, GET, POST, DELETE, UPDATE
			@Path("/consultacitas")
			@Produces(MediaType.TEXT_HTML)
			public String consultacitas () throws Exception{
				
				Conector c =new Conector(); //CREar una instancia de la clase creada para conec BD
				c.abrir();
				HospitalGestor hospital = new HospitalGestor();
				String tabla = hospital.reporte_citas(c.miconector);
				
				// //////////////////////            	
				c.cerrar();
				//return "<html><body><h1>"+tabla +"</h1></body></html>";
				return "<html><body><h1>"+tabla +"</h1> <br> "
				+ "<a href = 'http://localhost:8090/AWT_servicio/index.html'> Menu principal </a>"  
				+ "</body></html>";
			}	

}