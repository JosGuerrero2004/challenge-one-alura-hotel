package controller;

import java.util.List;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import modelo.Usuarios;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.usuarioDAO = new UsuarioDAO(factory.recuperaConexion());
	}
	
	public List<Usuarios> listar(){
		return usuarioDAO.listar();
	}
	
}
