package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuarios;

public class UsuarioDAO {

	final private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public List<Usuarios> listar(){
		List<Usuarios> resultado = new ArrayList<>();
		
		try(con){
			final PreparedStatement statement = con.prepareStatement(
					"SELECT usuario, contraseña, tipo "
					+ "FROM usuarios");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						Usuarios usuario = new Usuarios(resultSet.getString("usuario"), 
								resultSet.getString("contraseña"), 
								resultSet.getString("tipo"));
						resultado.add(usuario);
					}
				}
			}
		} catch(SQLException e) {
			throw new RuntimeException();
		}
		return resultado;
	}
}
