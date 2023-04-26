package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Reservas;

public class ReservaDAO {
	final private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public Integer guardar(Reservas reserva) {
		try(con){
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
}
