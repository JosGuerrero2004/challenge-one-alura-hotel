package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;

public class HuespedDAO {
	final private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huespedes huesped) {
		try (con){
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes"
					+ "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
					+ "VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
			);
			
			try (statement){
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, huesped.getFechaDeNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try (resultSet){
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue insertado el huesped %s", huesped.toString()));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Huespedes> listar() {
		List<Huespedes> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva"
					+ " FROM huespedes");
			try (statement) {
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet) {
					resultToList(resultado, resultSet);
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	private void resultToList(List<Huespedes> resultado, final ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Huespedes fila = new Huespedes(
					resultSet.getInt("id"),
					resultSet.getString("nombre"),
					resultSet.getString("apellido"),
					resultSet.getDate("fecha_nacimiento"),
					resultSet.getString("nacionalidad"),
					resultSet.getString("telefono"),
					resultSet.getInt("id_reserva"));
			
			resultado.add(fila);
		}
	}
}
