CREATE DATABASE webdyn;

USE webdyn;

CREATE TABLE utilisateur(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(200),
    mdp VARCHAR(200)
);

CREATE TABLE prevision(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100),
    montant DECIMAL(10,2)
);

CREATE TABLE depense(
    prevision INTEGER,
    montant DECIMAL(10,2),
    FOREIGN KEY (prevision) REFERENCES prevision(id)
);