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

INSERT INTO Clients
Values('Anthony','Test1','88887777','tony@email.com','2002-07-18')