package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Usuario;
import com.fitness.repositorios.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
	    return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional
	public Usuario guardar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void eliminar(Integer idUsuario) {
		usuarioRepository.deleteById(idUsuario);	
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario encontrarUsuario(Integer idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(idUsuario).orElse(null);
	}

}
