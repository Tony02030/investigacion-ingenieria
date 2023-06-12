CREATE DATABASE Investigacion

-- Table to store information of the clients
CREATE TABLE Clients (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name VARCHAR(100),
  address VARCHAR(200),
  phone CHAR(8),
  email VARCHAR(100),
  birth_date DATE
);

CREATE TABLE Account_Type(
id INT PRIMARY KEY,
name VARCHAR(40)
)

-- Table to store information of the bank accounts
CREATE TABLE Accounts (
  account_number VARCHAR(20) PRIMARY KEY,
  client_id INT,
  balance DECIMAL(10, 2),
  account_type INT,
  FOREIGN KEY (client_id) REFERENCES Clients(id),
  FOREIGN KEY (account_type) REFERENCES Account_Type(id)
);

-- Table to store information of the transactions
CREATE TABLE Transactions (
  id INT PRIMARY KEY,
  source_account_number VARCHAR(20),
  destination_account_number VARCHAR(20),
  date_time DATETIME,
  amount DECIMAL(10, 2),
  FOREIGN KEY (source_account_number) REFERENCES Accounts(account_number),
  FOREIGN KEY (destination_account_number) REFERENCES Accounts(account_number)
);

-- Table to store information of the loans
CREATE TABLE Loans (
  id INT PRIMARY KEY,
  client_id INT,
  amount DECIMAL(10, 2),
  interest_rate DECIMAL(5, 2),
  term_months INT,
  FOREIGN KEY (client_id) REFERENCES Clients(id)
);

INSERT INTO Clients Values
('Jesus','Test1','88887777','tony@email.com','2002-07-18'),
('Anthony','Test1','88887777','tony@email.com','2002-07-18'),
('Sofia Fern�ndez', 'Calle 1, San Jos�', '12345678', 'sofia.fernandez@example.com', '1988-06-15'),
('Sebasti�n Rojas', 'Avenida Central, Heredia', '87654321', 'sebastian.rojas@example.com', '1990-12-03'),
('Valentina Sol�s', 'Paseo Col�n, San Jos�', '98765432', 'valentina.solis@example.com', '1982-07-22'),
('Mateo Ram�rez', 'Avenida 2, Alajuela', '23456789', 'mateo.ramirez@example.com', '1985-03-11'),
('Isabella Chaves', 'Calle 4, Cartago', '54321678', 'isabella.chaves@example.com', '1989-11-07'),
('Daniel Castro', 'Avenida 3, San Jos�', '87654321', 'daniel.castro@example.com', '1987-02-25'),
('Camila Vargas', 'Avenida 5, Heredia', '34567890', 'camila.vargas@example.com', '1980-09-18'),
('Nicol�s M�ndez', 'Calle 6, Alajuela', '56789012', 'nicolas.mendez@example.com', '1983-05-31'),
('Valeria Herrera', 'Avenida 7, San Jos�', '78901234', 'valeria.herrera@example.com', '1986-08-26'),
('Santiago Fallas', 'Calle 8, Cartago', '89012345', 'santiago.fallas@example.com', '1977-12-10'),
('Luciana Jim�nez', 'Avenida 9, Heredia', '67890123', 'luciana.jimenez@example.com', '1979-06-18'),
('Alejandro Rojas', 'Calle 10, Alajuela', '45678901', 'alejandro.rojas@example.com', '1984-03-05'),
('Gabriela Campos', 'Avenida 11, San Jos�', '23456789', 'gabriela.campos@example.com', '1981-10-13'),
('Jos� Fern�ndez', 'Calle 12, Cartago', '78901234', 'jose.fernandez@example.com', '1976-04-27'),
('Mar�a S�nchez', 'Avenida 13, Heredia', '89012345', 'maria.sanchez@example.com', '1988-01-08');

INSERT INTO Account_Type Values
(1,'D�bito'),
(2,'Cr�dito')

INSERT INTO Accounts (account_number, client_id, balance, account_type) VALUES
('CR1234567890123', 5, 1985723.47, 1),
('CR9876543210123', 7, 352891.12, 2),
('CR2345678901234', 8, 825471.59, 1),
('CR8765432101234', 4, 6571839.76, 2),
('CR3456789012345', 12, 285983.01, 1),
('CR5678901234567', 10, 6785241.39, 2),
('CR9012345678901', 16, 1286239.72, 1),
('CR6789012345678', 19, 9872635.84, 2),
('CR1234509876543', 3, 5391274.29, 1),
('CR4321098765432', 9, 215497.56, 2),
('CR7654321098765', 11, 9842153.32, 1),
('CR0987654321098', 17, 357419.08, 2),
('CR3210987654321', 6, 6482907.61, 1),
('CR5432109876543', 13, 1523948.72, 2),
('CR8765432109876', 15, 798463.15, 1),
('CR1098765432109', 14, 3658190.23, 2),
('CR9876543210987', 18, 1427810.47, 1);

INSERT INTO Transactions (id, source_account_number, destination_account_number, date_time, amount) VALUES
(1, 'CR1234567890123', 'CR9876543210123', '2010-06-23 09:18:34', 1985723.47),
(2, 'CR2345678901234', 'CR8765432101234', '2011-09-15 17:42:56', 352891.12),
(3, 'CR3456789012345', 'CR5678901234567', '2008-12-04 13:27:21', 825471.59),
(4, 'CR9012345678901', 'CR6789012345678', '2007-07-19 08:56:10', 6571839.76),
(5, 'CR1234509876543', 'CR4321098765432', '2012-03-30 21:05:43', 285983.01),
(6, 'CR7654321098765', 'CR0987654321098', '2015-11-28 12:49:16', 6785241.39),
(7, 'CR3210987654321', 'CR5432109876543', '2009-05-10 06:37:50', 1286239.72),
(8, 'CR8765432109876', 'CR1098765432109', '2006-02-18 15:22:07', 9872635.84),
(9, 'CR1234567890123', 'CR3456789012345', '2013-08-09 03:59:32', 5391274.29),
(10, 'CR5678901234567', 'CR9012345678901', '2018-07-02 19:12:45', 215497.56),
(11, 'CR7654321098765', 'CR3210987654321', '2016-04-13 10:35:28', 9842153.32),
(12, 'CR5432109876543', 'CR8765432109876', '2022-01-07 07:47:53', 357419.08),
(13, 'CR1098765432109', 'CR9876543210987', '2014-10-16 23:59:10', 6482907.61),
(14, 'CR1234509876543', 'CR7654321098765', '2019-09-22 14:30:17', 1523948.72);

INSERT INTO Loans (id, client_id, amount, interest_rate, term_months) VALUES
(1, 5, 74821.89, 1.24, 17),
(2, 18, 113829.14, 0.96, 12),
(3, 7, 87493.75, 1.06, 19),
(4, 11, 62945.72, 1.39, 14),
(5, 9, 92135.61, 1.02, 16),
(6, 15, 53894.32, 1.45, 15),
(7, 6, 102587.90, 0.78, 18),
(8, 16, 77849.42, 1.11, 13),
(9, 12, 65471.24, 1.31, 14),
(10, 3, 118312.50, 0.65, 12),
(11, 19, 50627.38, 1.50, 19);


