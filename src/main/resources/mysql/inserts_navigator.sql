use navigator;

INSERT INTO city (id, name)
VALUES (1, 'Kiev'),
       (2, 'Cherkasy'),
       (3, 'Chernihiv'),
       (4, 'Poltava'),
       (5, 'Sumy'),
       (6, 'Vinnytsya'),
       (7, 'Zhytomyr'),
       (8, 'Kropyvnytskyi'),
       (9, 'Odesa'),
       (10, 'Dnipro'),
       (11, 'Donetsk'),
       (12, 'Zaporizhzhia'),
       (13, 'Lviv'),
       (14, 'Mykolaiv'),
       (15, 'Rivne'),
       (16, 'Ivano-Frankivsk'),
       (17, 'Ternopil'),
       (18, 'Luhansk'),
       (19, 'Chernivtsi'),
       (20, 'Kharkiv'),
       (21, 'Kherson'),
       (22, 'Khmelnytskyi'),
       (23, 'Lutsk'),
       (24, 'Uzhhorod'),
       (25, 'Uman');

INSERT INTO distance (id, distance, city1_id, city2_id)
VALUES (1, 140, 1, 7),
       (2, 147, 1, 3),
       (3, 188, 1, 2),
       (4, 333, 1, 5),
       (5, 346, 1, 4),
       (6, 142, 4, 20),
       (7, 188, 15, 7),
       (8, 237, 7, 25),
       (9, 165, 25, 8),
       (10, 267, 25, 9),
       (11, 186, 8, 14),
       (12, 67, 14, 21),
       (13, 145, 9, 14),
       (14, 243, 8, 10),
       (15, 84, 10, 12),
       (16, 249, 10, 11),
       (17, 217, 10, 20),
       (18, 229, 11, 12),
       (19, 148, 11, 18),
       (20, 335, 18, 20),
       (21, 321, 10, 14),
       (22, 415, 10, 25),
       (23, 164, 6, 25),
       (24, 118, 6, 22),
       (25, 287, 6, 19),
       (26, 135, 16, 19),
       (27, 174, 17, 19),
       (28, 111, 17, 22),
       (29, 184, 7, 22),
       (30, 193, 15, 22),
       (31, 73, 15, 23),
       (32, 150, 15, 13),
       (33, 269, 13, 24),
       (34, 133, 13, 16),
       (35, 271, 16, 24),
       (36, 167, 17, 23),
       (37, 150, 13, 23),
       (38, 134, 13, 17),
       (39, 186, 19, 22),
       (40, 378, 4, 11);

select dis.id, cit.name as city_1, ci.name as city_2, dis.distance
from distance as dis
         inner join city as cit
                    on
                        cit.id = dis.city1_id
         inner join city as ci
                    on
                        ci.id = dis.city2_id
order by dis.distance;