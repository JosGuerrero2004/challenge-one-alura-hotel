package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reservas;

public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}

	public Integer guardar(Reservas reserva) {
		return reservaDAO.guardar(reserva);
	}

}
