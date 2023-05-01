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
		this.huespedDAO.guardar(huesped);
	}

	public List<Huespedes> listar() {
		return this.huespedDAO.listar();
	}

	public List<Huespedes> buscarId(int id) {
		return this.huespedDAO.buscarId(id);
	}

	public List<Huespedes> buscarApellido(String apellido) {
		return this.huespedDAO.buscarApellido(apellido);
	}

	public int editar(Huespedes huespedModificado) {
		return this.huespedDAO.editar(huespedModificado);
	}
}
