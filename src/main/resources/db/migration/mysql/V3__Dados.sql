INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `data_Atualizacao`,`ativo`) VALUES
(DEFAULT, 'Usuario 01', 'usuario01@gmail.com', '$2a$10$FHayM6spzm5LGUa//VKYKe9iWLPlSnYpdwGEkvHMlCEZUIsr4EEIG', now(), TRUE);
 
INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `data_Atualizacao`,`ativo`) VALUES
(DEFAULT, 'Usuario 02', 'usuario02@gmail.com', '$2a$10$FHayM6spzm5LGUa//VKYKe9iWLPlSnYpdwGEkvHMlCEZUIsr4EEIG', now(),TRUE);
 
INSERT INTO `regra` (`id`, `nome`, `descricao`, `ativo`) VALUES
(DEFAULT, 'ROLE_ADM_USUARIO', 'Permite acesso aos serviços de usuário', TRUE);
 
INSERT INTO `regra` (`id`, `nome`, `descricao`, `ativo`) VALUES
(DEFAULT, 'ROLE_EXCLUSAO_REUNIAO', 'Permite exclusão de reuniões', TRUE);

INSERT INTO `regra` (`id`, `nome`, `descricao`, `ativo`) VALUES
(DEFAULT, 'ROLE_EXCLUSAO_PRESENCA', 'Permite exclusão de presença', TRUE);
 
INSERT INTO `usuario_regra` (`usuario_id`, `regra_id`) VALUES (
(SELECT `id` FROM usuario WHERE email = 'usuario01@gmail.com'),
(SELECT `id` FROM regra WHERE nome = 'ROLE_ADM_USUARIO')
);

INSERT INTO `usuario_regra` (`usuario_id`, `regra_id`) VALUES (
(SELECT `id` FROM usuario WHERE email = 'usuario01@gmail.com'),
(SELECT `id` FROM regra WHERE nome = 'ROLE_EXCLUSAO_REUNIAO')
);
 
INSERT INTO `usuario_regra` (`usuario_id`, `regra_id`) VALUES (
(SELECT `id` FROM usuario WHERE email = 'usuario01@gmail.com'),
(SELECT `id` FROM regra WHERE nome = 'ROLE_EXCLUSAO_PRESENCA')
);
