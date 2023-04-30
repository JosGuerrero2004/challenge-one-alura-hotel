package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reservas;

public class ReservaDAO {
	final private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public Integer guardar(Reservas reserva) {
		try{
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas"
					+ "(check_in, check_out, valor, forma_pago)"
					+ "VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				statement.setDate(1, reserva.getCheckIn());
				statement.setDate(2, reserva.getCheckOut());
				statement.setBigDecimal(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				
				statement.execute();
				
				final ResultSet resulSet = statement.getGeneratedKeys();
				
				try (resulSet){
					while (resulSet.next()) {
						reserva.setId(resulSet.getInt(1));
						System.out.println(String.format("Fue insertada la reserva %s ", reserva));
					}
					return reserva.getId();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Reservas> listar(){
		List<Reservas> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id, check_in, check_out, valor, forma_pago"
					+ " FROM reservas");
			try (statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					resultToList(resultado, resultSet);
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reservas> buscarId(Integer id) {
		List<Reservas> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id, check_in, check_out, valor, forma_pago"
					+ " FROM reservas "
					+ "WHERE id = ?");
			try (statement) {
				statement.setInt(1, id);
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
	
	
	
	private void resultToList(List<Reservas> resultado, final ResultSet resultSet) throws SQLException {
		while(resultSet.next()) {
			Reservas fila = new Reservas(resultSet.getInt(1),
					resultSet.getDate(2), 
					resultSet.getDate(3),
					resultSet.getBigDecimal(4),
					resultSet.getString(5));
			
			resultado.add(fila);
		}
	}
	
}
