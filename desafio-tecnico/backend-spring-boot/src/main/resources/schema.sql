drop table if exists Comercio;

drop table if exists Cidade;

create table Cidade(
  id int not null AUTO_INCREMENT,
  nome varchar(100) not null,
  uf varchar(2) not null,
  capital boolean not null,  
  PRIMARY KEY ( ID )
);

CREATE TABLE Comercio (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome_comercio VARCHAR(100) NOT NULL,
  nome_responsavel VARCHAR(100) NOT NULL,
  tipo_comercio VARCHAR(100) NOT NULL,
  cidade_id BIGINT NOT NULL,
  FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);
