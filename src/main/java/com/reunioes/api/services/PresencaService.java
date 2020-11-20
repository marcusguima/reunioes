package com.reunioes.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reunioes.api.entities.Presenca;
import com.reunioes.api.repositories.PresencaRepository;
import com.reunioes.api.repositories.UsuarioRepository;
import com.reunioes.api.repositories.ReuniaoRepository;
import com.reunioes.api.utils.ConsistenciaException;

@Service
public class PresencaService {

	private static final Logger log = LoggerFactory.getLogger(PresencaService.class);

	@Autowired
	private PresencaRepository presencaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ReuniaoRepository reuniaoRepository;
	
	public Optional<Presenca> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando a presença de id: {}", id);

		Optional<Presenca> presenca = presencaRepository.findById(id);

		if (!presenca.isPresent()) {

			log.info("Service: Nenhuma presença com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhuma presença com id: {} foi encontrado", id);

		}

		return presenca;

	}

	@Cacheable("cachePresencaPorUsuario")
	public Optional<List<Presenca>> buscarPorUsuarioId(int usuarioId) throws ConsistenciaException {

		log.info("Service: buscando as presenças do usuário de id: {}", usuarioId);

		Optional<List<Presenca>> presenca = Optional.ofNullable(presencaRepository.findByUsuarioId(usuarioId));

		if (!presenca.isPresent() || presenca.get().size() < 1) {

			log.info("Service: Nenhuma presença encontrada para o usuário de id: {}", usuarioId);
			throw new ConsistenciaException("Nenhuma presença encontrada para o usuário de id: {}", usuarioId);

		}

		return presenca;

	}
	
	@Cacheable("cachePresencaPorUsuario")
	public Optional<List<Presenca>> buscarPorReuniaoId(int reuniaoId) throws ConsistenciaException {

		log.info("Service: buscando as presenças da reunião de id: {}", reuniaoId);

		Optional<List<Presenca>> presenca = Optional.ofNullable(presencaRepository.findByReuniaoId(reuniaoId));

		if (!presenca.isPresent() || presenca.get().size() < 1) {

			log.info("Service: Nenhuma presença encontrada para a reunião de id: {}", reuniaoId);
			throw new ConsistenciaException("Nenhuma presença encontrada para a reunião de id: {}", reuniaoId);

		}

		return presenca;

	}

	@CachePut("cachePresencaPorUsuario")
	public Presenca salvar(Presenca presenca) throws ConsistenciaException {

		log.info("Service: salvando a presença: {}", presenca);

		if (!usuarioRepository.findById(presenca.getUsuario().getId()).isPresent()) {

			log.info("Service: Nenhum usuário com id: {} foi encontrado", presenca.getUsuario().getId());
			throw new ConsistenciaException("Nenhum usuário com id: {} foi encontrado", presenca.getUsuario().getId());

		} 
		
		if (!reuniaoRepository.findById(presenca.getReuniao().getId()).isPresent()) {

			log.info("Service: Nenhuma reunião com id: {} foi encontrado", presenca.getReuniao().getId());
			throw new ConsistenciaException("Nenhuma reunião com id: {} foi encontrado", presenca.getReuniao().getId());
		}

		if (presenca.getId() > 0)
			buscarPorId(presenca.getId());

		try {

			return presencaRepository.save(presenca);

		} catch (DataIntegrityViolationException e) {

			log.info("Service: Já existe uma presença de número {} cadastrado", presenca.getId());
			throw new ConsistenciaException("Já existe uma presença de número {} cadastrado", presenca.getId());

		}
	}

	@CachePut("cachePresencaPorUsuario")
	public void excluirPorId(int id) throws ConsistenciaException {

		log.info("Service: excluíndo a presença de id: {}", id);

		buscarPorId(id);

		presencaRepository.deleteById(id);

	}

}
