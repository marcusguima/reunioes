package com.reunioes.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reunioes.api.entities.Reuniao;
import com.reunioes.api.repositories.ReuniaoRepository;
import com.reunioes.api.repositories.UsuarioRepository;
import com.reunioes.api.utils.ConsistenciaException;

@Service
public class ReuniaoService {

	private static final Logger log = LoggerFactory.getLogger(ReuniaoService.class);

	@Autowired
	private ReuniaoRepository reuniaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Reuniao> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando as reuniões de id: {}", id);

		Optional<Reuniao> reuniao = reuniaoRepository.findById(id);

		if (!reuniao.isPresent()) {

			log.info("Service: Nenhuma reunião com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhuma reunião com id: {} foi encontrado", id);

		}

		return reuniao;

	}

	public Optional<List<Reuniao>> buscarPorUsuarioId(int usuarioId) throws ConsistenciaException {

		log.info("Service: buscando as reuniões do usuario de id: {}", usuarioId);

		Optional<List<Reuniao>> reunioes = Optional.ofNullable(reuniaoRepository.findByUsuarioId(usuarioId));

		if (!reunioes.isPresent() || reunioes.get().size() < 1) {

			log.info("Service: Nenhuma reunião encontrada para o usuário de id: {}", usuarioId);
			throw new ConsistenciaException("Nenhuma reunião encontrada para o usuário de id: {}", usuarioId);

		}

		return reunioes;

	}

	public Reuniao salvar(Reuniao reuniao) throws ConsistenciaException {

		log.info("Service: salvando a reunião: {}", reuniao);

		if (!usuarioRepository.findById(reuniao.getUsuario().getId()).isPresent()) {

			log.info("Service: Nenhum usuário com id: {} foi encontrado", reuniao.getUsuario().getId());
			throw new ConsistenciaException("Nenhum usuário com id: {} foi encontrado", reuniao.getUsuario().getId());

		}

		if (reuniao.getId() > 0)
			buscarPorId(reuniao.getId());

		try {

			return reuniaoRepository.save(reuniao);

		} catch (DataIntegrityViolationException e) {

			log.info("Service: Já existe uma reunião de número {} cadastrado", reuniao.getId());
			throw new ConsistenciaException("Já existe uma reunião de número {} cadastrado", reuniao.getId());

		}

	}

	public void excluirPorId(int id) throws ConsistenciaException {

		log.info("Service: excluíndo a reunião de id: {}", id);

		buscarPorId(id);

		reuniaoRepository.deleteById(id);

	}

}
