package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Usuario;


public interface UsuarioService {
	public List<Usuario> listaUsuarios();
	
	public void guardar(Usuario usuario);
	
	public void eliminar(Usuario usuario);
	public Usuario encontrarUsuario(Usuario uruario);
}
