
CREATE TABLE persons (
                         nom VARCHAR(250),
                         prenom VARCHAR(250),
                         dateN DATE,
                         numeroTel VARCHAR(50),
                         adress VARCHAR(250),
                         emailAdresse VARCHAR(250),
                         createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Employes (
                          matricule VARCHAR(255) PRIMARY KEY,
                          dateRecrutement DATE
)INHERITS(persons);

CREATE TABLE Clients (
    code VARCHAR(255) PRIMARY KEY
)INHERITS(persons);

CREATE TABLE Missions (
                          code VARCHAR(255) PRIMARY KEY,
                          nom VARCHAR(255),
                          description VARCHAR(255)
);

CREATE TABLE Affectations (
                              employe_matricule VARCHAR(255),
                              mission_code VARCHAR(255),
                              datedebut DATE,
                              datefin DATE,
                              PRIMARY KEY (employe_matricule, mission_code),
                              FOREIGN KEY (employe_matricule) REFERENCES Employes(matricule),
                              FOREIGN KEY (mission_code) REFERENCES Missions(code)
);
CREATE TABLE Comptes (
                         numero VARCHAR(255) PRIMARY KEY,
                         sold DECIMAL(10, 2),
                         dateCreation DATE,
                         etat VARCHAR(10),
                         client_code VARCHAR(255),
                         employe_matricule VARCHAR(255),
                         FOREIGN KEY (client_code) REFERENCES Clients(code),
                         FOREIGN KEY (employe_matricule) REFERENCES Employes(matricule),
                         CHECK (etat IN ('active', 'inactive'))
);

CREATE TABLE ComptesCourants (
                                 numeroCompte VARCHAR(255) PRIMARY KEY,
                                 decouvert FLOAT,
                                 FOREIGN KEY (numeroCompte) REFERENCES Comptes(numero)
);

CREATE TABLE ComptesEpargnes (
                                 numeroCompte VARCHAR(255) PRIMARY KEY,
                                 tauxInteret FLOAT,
                                 FOREIGN KEY (numeroCompte) REFERENCES Comptes(numero)
);



CREATE TABLE Agences (
                         code  VARCHAR(255) PRIMARY KEY,
                         nom VARCHAR(255),
                         adresse VARCHAR(255),
                         tel VARCHAR(255)
);
CREATE TABLE EmployesAgency (
                                employe_matricule VARCHAR(255),
                                agence_code VARCHAR(255),
                                datedebut DATE,
                                datefin DATE,
                                PRIMARY KEY (employe_matricule, agence_code),
                                FOREIGN KEY (employe_matricule) REFERENCES Employes(matricule),
                                FOREIGN KEY (agence_code) REFERENCES Agences(code)
);

CREATE TABLE DemendeCredits (
                                numero VARCHAR(255) PRIMARY KEY,
                                date DATE,
                                montant DECIMAL(10, 2),
                                duree VARCHAR(255),
                                remarque VARCHAR(255),
                                status VARCHAR(10) DEFAULT 'EnAttante',
                                agence_code VARCHAR(255),
                                FOREIGN KEY (agence_code) REFERENCES Agences(code),
                                employe_matricule VARCHAR(255),
                                FOREIGN KEY (employe_matricule) REFERENCES Employes(matricule),
                                client_code VARCHAR(255),
                                FOREIGN KEY (client_code) REFERENCES Clients(code)
                                
);

CREATE TABLE Operations (
                            numero VARCHAR(255) PRIMARY KEY,
                            dateCreation DATE,
                            montant DECIMAL(10, 2)

);



CREATE TABLE OperationsSimple (
                                  type VARCHAR(10),
                                  employe_matricule VARCHAR(255),
                                  FOREIGN KEY (employe_matricule) REFERENCES Employes(matricule),
                                  numeroCompte VARCHAR(255) PRIMARY KEY,
                                  FOREIGN KEY (numeroCompte) REFERENCES Comptes(numero),
                                  CHECK (type IN ('versement', 'retrait'))


)INHERITS (Operations);

CREATE TABLE verements (
                           expediteur  VARCHAR(255),
                           FOREIGN KEY (expediteur) REFERENCES Comptes(numero),
                           Beneficiaire  VARCHAR(255),
                           FOREIGN KEY (Beneficiaire) REFERENCES Comptes(numero)

)INHERITS (Operations);


ALTER TABLE Comptes
    ADD COLUMN agence_code VARCHAR(255) REFERENCES Agences(code);



CREATE OR REPLACE FUNCTION insert_versement_trigger()
RETURNS TRIGGER AS $$
DECLARE
current_balance NUMERIC;
BEGIN
    IF NEW.type = 'versement' THEN
UPDATE Comptes
SET sold = sold + NEW.montant
WHERE numero = NEW.numeroCompte;
ELSIF NEW.type = 'retrait' THEN
SELECT sold INTO current_balance
FROM Comptes
WHERE numero = NEW.numeroCompte;

IF current_balance >= NEW.montant THEN
UPDATE Comptes
SET sold = sold - NEW.montant
WHERE numero = NEW.numeroCompte;
ELSE
            RAISE EXCEPTION 'Insufficient funds for retrait.';
DELETE FROM OperationsSimple WHERE numero = NEW.numero;
END IF;
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


