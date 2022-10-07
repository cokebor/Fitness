package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.modelo.Usuario;
import com.fitness.repositorios.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;
	@Override
	public List<Usuario> listaUsuarios() {
	    return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void guardar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public void eliminar(Usuario usuario) {
		usuarioRepository.delete(usuario);	
	}

	@Override
	public Usuario encontrarUsuario(Usuario uruario) {
		// TODO Auto-generated method stub
		return null;
	}

}
