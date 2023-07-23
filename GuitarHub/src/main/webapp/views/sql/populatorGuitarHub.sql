SET SQL_SAFE_UPDATES = 0;
use mysql;
USE guitarhub;

DELETE FROM Utente;
INSERT INTO Utente (username, passwd, nome, cognome, dataNascita, sesso, email, ruolo,cardNumber,cardExpDate,cvv,billingAddress)
VALUES ('matteo99', '$2a$12$QyZuXSaXiRdZpqimkV2WxO8UQGMS1a7Agh/XRBS4c6zhUzCn0ivBa', 'Matteo', 'Galli', '1990-05-15', 'maschio', 'matteo@gmail.com', 'utente','1111-2222-3333-4444','2024-08-02','1234','Via saturno 22 Verona MI 38100');

INSERT INTO Utente (username, passwd, nome, cognome, dataNascita, sesso, email, ruolo,billingAddress)
VALUES ('amministratore', '$2a$12$exVXM2s/dYF3brNKbLQNzuBseH0UxFWO5frfANxyEb8NSdMagSXia', NULL, NULL, NULL, NULL, 'admin@gmail.com', 'admin',NULL);

INSERT INTO Utente (username, passwd, nome, cognome, dataNascita, sesso, email, ruolo,cardNumber,cardExpDate,cvv,billingAddress)
VALUES ('elena', '$2a$12$r5XRs28Cv8GdUYUD.REh1uc/EehIyr/tldcPQZEly.aQ4zzVTf19m', 'Elena', 'Billi', '1985-08-22', 'femmina', 'elena@gmail.com', 'utente','1111-2222-3333-4444','2024-08-02','1234','Via saturno 24 Verona MI 38100');

INSERT INTO Indirizzo (idUser, nomeInd, cognomeInd, indirizzo, codPostale, numeroCivico, city, provincia, telefono)
VALUES (1, 'Nora', 'nelli', 'Via delle rampe', '12345', '22', 'Verona', 'MI', '123 4567890');

INSERT INTO Indirizzo (idUser, nomeInd, cognomeInd, indirizzo, codPostale, numeroCivico, city, provincia, telefono)
VALUES (3, 'Mel', 'Ferri', 'Via delle rastre', '12345', '24', 'Verona', 'MI', '123 0567890');

DELETE FROM Prodotto;
INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Harley Benton slim star', 'Una telecaster con corpo di frassino americano e manico bolt-on con acero canadese tostato con striature Roseacer Skunk. Tastiera di acero tostato con 21 tasti e pickup con 2 single coil Roswell TEA Alnico-5 TE. Inoltre 1 controllo di volume e 1 di tono con interruttore a 3 vie. Infine colore naturale lucido.', '/GuitarHub/images/productimg/HarleyBentonslimstar/', 125.00, 'Harley Benton', 8, 1, 0, 25, 22, 'Harley Benton telecaster con corpo di frassino');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Solar Guitars V 1.7', 'Una chitarra a 7 corde con corpo Mahogany, collo di acero e fretboard di ebano con 24 tasti. Con 2 pickup di tipo Solar humbuckers. Infine colore Flame Black Burst.','/GuitarHub/images/productimg/SolarGuitarsV1.7/', 1250.00, 'Solar', 6, 1, 15, 2, 22, 'Una chitarra a 7 corde con corpo Mahogany');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Ibanez RGMS8-BK', 'Una chitarra ad 8 corde con corpo di Nyatoh, collo di acero e fretboard di ciliegio brasiliano con 24 tasti. Inoltre 2 pickup Array-8 MS humbuckers. Infine colore nero.', '/GuitarHub/images/productimg/IbanezRGMS8-BK/', 680.00, 'Ibanez', 8, 1, 10, 40, 22, 'Una chitarra ad 8 corde con corpo di Nyatoh');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Harley Benton RB-612CS', 'Una chitarra di 12 corde con corpo di sapele, manico di acero monostrato e tastiera di amaranto con 22 tasti. Inoltre 2 pickupt Artec Classic Mini Humbucker. Infine colore Cherry Sunburst High Gloss.','/GuitarHub/images/productimg/HarleyBentonRB-612CS/', 280.00, 'Harley Benton', 8, 1, 0, 15, 22, 'Una chitarra di 12 corde con corpo di sapele');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Fender Player Series Jaguar PF 3TS', 'Una chitarra 3 quarti con corpo di legno di ontano, collo di acero e fretaboard di Pau Ferro con 22 tasti. Inoltre 2 pickup New Player AlNiCo III humbucker e New Player AlNiCo II Jaguar single coil. Infine colore 3-Tone Sunburst.', '/GuitarHub/images/productimg/FenderPlayerSeriesJaguarPF3TS/', 820.00, 'Fender', 10, 1, 20, 15, 22, 'Una chitarra 3 quarti con corpo di legno di ontano');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Gibson 1959 ES-335 Reissue VN VOS', 'Una chitarra semiacustica con corpo di acero, collo di mogano e fretboard di palissandro con 22 tasti. Inoltre 2 pickupt Custombucker Alnico III humbucker. Infine colore legno naturale.', '/GuitarHub/images/productimg/Gibson1959ES-335ReissueVNVOS/', 4525.00, 'Gibson', 12, 1, 0, 25, 22, 'Una chitarra semiacustica con corpo di acero');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Fender Jim Root Jazzmaster', 'Una chitarra Jim Root signature con corpo di mogano, collo di acero e fretboard di ebano con 22 tasti. Inoltre 2 pickup EMG 81 e EMG 60. Infine colore nero.', '/GuitarHub/images/productimg/FenderJimRootJazzmaster/', 1250.00, 'Fender', 8, 1, 10, 12, 22, 'Una chitarra Jim Root signature con corpo di mogano');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Gibson Les Paul Classic TC LH', 'Una chitarra a 6 corde con corpo di acero e manico bolt-on con ebano. Tastiera di acero tostato con 21 tasti e pickup con 2 single coil Roswell TEA Alnico-5 TE. Inoltre 1 controllo di volume e 1 di tono con interruttore a 3 vie. Infine colore rosso naturale lucido.', '/GuitarHub/images/productimg/GibsonLesPaulClassicTCLH/', 2825.00, 'Gibson', 8, 1, 0, 0, 22, 'Una chitarra a 6 corde con corpo di acero');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Fender Yngwie Malmsteen MN VW', 'Una stratocaster Yngwie Malmsteen signature con corpo di legno d\'ontano americano e manico con ebano e tastiera di acero tostato con 21 tasti e pickup con 2 singlecoil humbucker. Inoltre 1 controllo di volume e 1 di tono con interruttore a 3 vie. Infine colore legno naturale lucido.', '/GuitarHub/images/productimg/FenderYngwieMalmsteenMNVW/', 1250.00, 'Fender', 6, 1, 0, 2, 22, 'Una stratocaster Yngwie Malmsteen signature con corpo di legno d\'ontano americano');

INSERT INTO Prodotto (nome, descrizione, immagine, prezzo, marca, peso, disponibile, sconto, quantity, iva, shortDescription)
VALUES ('Ibanez RG421EX-TCM B-Stock', 'Una stratocaster con corpo di frassino americano e manico di acero e tastiera di ciliegio brasiliano con 24 tasti e con 2 pickup Ibanez Quantum humbuckers. Infine rosso trasparente.', '/GuitarHub/images/productimg/IbanezRG421EX-TCMB-Stock/', 385.00, 'Ibanez', 8, 1, 0, 25, 22, 'Una stratocaster con corpo di frassino americano');





INSERT INTO Ordine (idUser, destinazione, qntProdotti, totale, numeroSpedizione, dataOrdine)
VALUES (1, 'Address 1', 3, 30.50, 'XYZ123', '2022-01-15 10:30:00');

INSERT INTO Ordine (idUser, destinazione, qntProdotti, totale, numeroSpedizione, dataOrdine)
VALUES (1, 'Address 2', 2, 15.75, 'ABC789', '2022-03-20 15:45:00');

INSERT INTO Ordine (idUser, destinazione, qntProdotti, totale, numeroSpedizione, dataOrdine)
VALUES (1, 'Address 3', 1, 8.99, 'DEF456', '2022-06-05 09:00:00');

INSERT INTO Ordine (idUser, destinazione, qntProdotti, totale, numeroSpedizione, dataOrdine)
VALUES (3, 'Address 4', 4, 40.20, 'GHI987', '2022-08-12 12:20:00');

INSERT INTO Ordine (idUser, destinazione, qntProdotti, totale, numeroSpedizione, dataOrdine)
VALUES (3, 'Address 5', 2, 18.75, 'JKL321', '2022-11-30 18:10:00');

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (1, 'Harley benton slim stardom', 'Description 1', 2, 5.99, 0.3, 22);

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (1, 'Fender squier', 'Description 2', 1, 8.99, 0.4, 22);

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (2, 'Gisbon les paul', 'Description 3', 1, 7.75, 0.2, 22);

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (2, 'Fender stratocaster', 'Description 4', 1, 8.00, 0.3, 22);

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (3, 'Ibanez tident', 'Description 1', 1, 5.99, 0.3, 22);

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (4, 'Fender telecaster', 'Description 2', 3, 8.99, 0.4, 22);

INSERT INTO dettagliOrdine (idOrdine, nomeProd, shortDescription, qntProd, prezzoProd, pesoProd, iva)
VALUES (5, 'Gibson les paul Slash signature', 'Description 3', 2, 7.75, 0.2, 22);



