
CREATE DATABASE GerenciadorDeEstoque
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

USE GerenciadorDeEstoque;

CREATE TABLE Usuario (
    ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Login VARCHAR(50) NOT NULL,
    Senha VARCHAR(100) NOT NULL,
    TipoUsuario VARCHAR(50) NOT NULL
);


CREATE TABLE Cliente (
    ID_Cliente INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Telefone VARCHAR(20),
    Email VARCHAR(100),
    CPF VARCHAR(14),
    Endereco VARCHAR(200)
);


CREATE TABLE Fornecedor (
    ID_Fornecedor INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Endereco VARCHAR(200),
    CNPJ VARCHAR(18),
    CPF VARCHAR(14)
);


CREATE TABLE Mercadoria (
    ID_Mercadoria INT AUTO_INCREMENT PRIMARY KEY,
    Descricao VARCHAR(200) NOT NULL,
    Preco DECIMAL(10,2) NOT NULL,
    QtdeEstoque INT NOT NULL,
    Tamanho VARCHAR(50),
    Modelo VARCHAR(50),
    ID_Fornecedor INT,
    FOREIGN KEY (ID_Fornecedor) REFERENCES Fornecedor(ID_Fornecedor)
);


CREATE TABLE Venda (
    ID_Venda INT AUTO_INCREMENT PRIMARY KEY,
    DataVenda DATE NOT NULL,
    ValorTotal DECIMAL(10,2) NOT NULL,
    ID_Cliente INT,
    FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente)
);


CREATE TABLE ItemVenda (
    ID_ItemVenda INT AUTO_INCREMENT PRIMARY KEY,
    Quantidade INT NOT NULL,
    ID_Venda INT,
    ID_Mercadoria INT,
    Subtotal DECIMAL(10,2), 
    FOREIGN KEY (ID_Venda) REFERENCES Venda(ID_Venda),
    FOREIGN KEY (ID_Mercadoria) REFERENCES Mercadoria(ID_Mercadoria)
);

INSERT INTO Usuario (Nome, Login, Senha, TipoUsuario) VALUES
('Admin', 'admin', 'admin', 'Administrador'),
('Comum', 'comum', 'comum', 'Comum');

INSERT INTO Fornecedor (Nome, Endereco, CNPJ) VALUES
('Pequeninas Moda Kids', 'Rua das Flores, 123, São Paulo, SP', '11.111.111/0001-11'),
('Doce Encanto Confecções', 'Avenida Principal, 456, Belo Horizonte, MG', '22.222.222/0001-22'),
('Algodão Doce Atacado', 'Travessa dos Sonhos, 789, Rio de Janeiro, RJ', '33.333.333/0001-33');

INSERT INTO Cliente (Nome, Telefone, Email, CPF, Endereco) VALUES
('Ana Silva', '(38) 99999-1234', 'ana.silva@email.com', '111.111.111-11', 'Rua A, 10, Centro, Januária, MG'),
('Beatriz Costa', '(38) 98888-5678', 'beatriz.costa@email.com', '222.222.222-22', 'Rua B, 20, Sagrada Família, Januária, MG'),
('Carla Oliveira', '(38) 97777-9012', 'carla.oliveira@email.com', '333.333.333-33', 'Rua C, 30, Vila Fátima, Januária, MG');

INSERT INTO Mercadoria (Descricao, Preco, QtdeEstoque, Tamanho, Modelo, ID_Fornecedor) VALUES
('Vestido Floral com Cinto', 89.90, 15, '4', 'Rodado', 1),
('Conjunto Moletom Unicórnio', 120.50, 20, '6', 'Inverno', 2),
('Saia Jeans com Babados', 65.00, 25, '8', 'Casual', 1),
('Blusa de Lurex Manga Bufante', 55.90, 30, '4', 'Festa', 3),
('Legging Estampada Corações', 45.00, 18, '6', 'Básica', 2),
('Macacão Pantacourt Listrado', 110.00, 12, '8', 'Verão', 3),
('Vestido de Festa com Paetês', 150.00, 10, '6', 'Gala', 1);

INSERT INTO Venda (DataVenda, ValorTotal, ID_Cliente) VALUES
('2025-08-15', 255.40, 1),
('2025-08-18', 120.90, 2),
('2025-08-20', 110.00, 3);

INSERT INTO ItemVenda (Quantidade, ID_Venda, ID_Mercadoria, Subtotal) VALUES
(1, 1, 1, 89.90),
(1, 1, 2, 120.50),
(1, 1, 5, 45.00),
(1, 2, 3, 65.00),
(1, 2, 4, 55.90),
(1, 3, 6, 110.00);



