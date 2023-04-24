package pruebas;

import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class Pruebas {

	public static void main(String[] args) {
		ComboPooledDataSource dataSource;
	
		try {
			ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
			
			pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/ALURA-HOTEL?useTimeZone=true&serverTimeZone=UTC");
			pooledDataSource.setUser("root");
			pooledDataSource.setPassword("ADMIN123");
			pooledDataSource.setMaxPoolSize(10);
			
			dataSource = pooledDataSource;
			
			dataSource.getConnection();
			
			dataSource.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
