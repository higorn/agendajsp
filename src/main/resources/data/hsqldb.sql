CREATE TABLE IF NOT EXISTS usuario (
	login varchar(255) NOT NULL,
	email varchar(255),
	nome varchar(255),
	senha varchar(255),
	CONSTRAINT usuario_pkey PRIMARY KEY (login)
);

CREATE TABLE IF NOT EXISTS AGENDAMENTO (
   DIA INTEGER,
   MES INTEGER,
   ANO INTEGER,
   HORA_INICIO INTEGER,
   MINUTO_INICIO INTEGER,
   TIPO_AGENDAMENTO CHARACTER(1),
   NOME VARCHAR(100),
   TELEFONE VARCHAR(100),
   CONSTRAINT AGENDAMENTO_PK PRIMARY KEY (DIA,MES,ANO,HORA_INICIO,MINUTO_INICIO)
) ;