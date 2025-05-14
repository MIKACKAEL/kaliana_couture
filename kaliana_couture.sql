/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  TUF
 * Created: 11 mai 2025
 */

CREATE DATABASE kaliana_couture;

USE  kaliana_couture;

-- Table admin
CREATE TABLE admin (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(50) NOT NULL,       
    password VARCHAR(255) NOT NULL   
); 

-- Table client
CREATE TABLE client (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    surnom VARCHAR(100),
    tel VARCHAR(20) NOT NULL
);

-- Table mesure_robe
CREATE TABLE mesure_robe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT,
    date_time_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    t_enc DECIMAL(5,2),     -- Tour d'encolure
    ep DECIMAL(5,2),        -- Épaule à épaule
    cdvt DECIMAL(5,2),      -- Carrure devant
    tp DECIMAL(5,2),        -- Tour de poitrine
    tt DECIMAL(5,2),        -- Tour de taille
    tb DECIMAL(5,2),        -- Tour de bassin (ou hanches)
    ltdvt DECIMAL(5,2),     -- Longueur taille-devant
    lj DECIMAL(5,2),        -- Longueur jupe ou robe
    hh DECIMAL(5,2),        -- Hauteur hanches
    es DECIMAL(5,2),        -- Écartement des seins (distance entre les deux mamelons)
    hs DECIMAL(5,2),        -- Hauteur sein
    gb DECIMAL(5,2),        -- Grosseur bras (tour du bras supérieur)

    FOREIGN KEY (id_client) REFERENCES client(id_client) ON DELETE CASCADE
);

-- Table mesure_pantalon_femme
CREATE TABLE mesure_pantalon_femme (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT,
    date_time_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    tt DECIMAL(5,2),    -- Tour de taille
    tb DECIMAL(5,2),    -- Tour de bassin (ou hanches)
    lg DECIMAL(5,2),    -- Longueur genoux
    enf DECIMAL(5,2),   -- Enfourchure (hauteur entrejambe)
    lp DECIMAL(5,2),    -- Longueur pantalon (côté extérieur)
    gg DECIMAL(5,2),    -- Grosseur genou
    gb DECIMAL(5,2),    -- Grosseur bas (tour bas de jambe)

    FOREIGN KEY (id_client) REFERENCES client(id_client) ON DELETE CASCADE
);

