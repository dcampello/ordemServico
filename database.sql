CREATE DATABASE `ordem_servico` /*!40100 DEFAULT CHARACTER SET utf8 */;

use ordem_servico;

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `data_registro` datetime NOT NULL,
  `login` varchar(64) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `senha` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g1orfqvgih1w8s3vyg15fq2b8` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `ordemservico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_registro` datetime NOT NULL,
  `defeito` varchar(200) NOT NULL,
  `email` varchar(45) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `equipamentoNome` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(10) NOT NULL,
  `telefone` varchar(10) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl1xwyhytme5wb5o5eqay1p7mk` (`usuario_id`),
  CONSTRAINT `FKl1xwyhytme5wb5o5eqay1p7mk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;



