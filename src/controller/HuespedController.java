package controller;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huespedes;

public class HuespedController {
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huespedes huesped) {
		huespedDAO.guardar(huesped);
	}

	public List<Huespedes> listar() {
		return huespedDAO.listar();
	}
}
