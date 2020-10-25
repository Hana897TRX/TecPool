Create Database TecPool;

USE TecPool;

Create table Persons(
	id INT AUTO_INCREMENT PRIMARY KEY,
	personName VARCHAR(50),
	username VARCHAR(15),
	fechaNacimiento DATE,
	matricula VARCHAR(15),
	carrera VARCHAR(5),
	ecoPuntos INT,
	passToken VARCHAR(256),
    coreToken VARCHAR(64)
);

SELECT * FROM Persons;