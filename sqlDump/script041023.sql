SELECT * FROM bovinoapp.users;
SELECT * FROM bovinoapp.animals;
SELECT * FROM bovinoapp.evolution;

use bovinoapp;


alter table evolution
drop column registry_date;

alter table animals
drop column evolution_historic;


ALTER TABLE evolution
CHANGE registry registry_date date;

ALTER TABLE animals
DROP FOREIGN KEY FKj3kcd9djwfl547f9fvu6my1en;


alter table animals
add column sexo ENUM('M','F');

alter table animals
drop column genero;

select * from evolution;

create or replace view wbois_ano AS
select count(*) AS quantidade, month(birth) AS mes
from animals
where year(birth) = 2022
group by month(birth);


SELECT
  MONTH(registry_date) AS month,
  AVG(weight) AS average_weight
FROM
  evolution
GROUP BY
  MONTH(registry_date);

select avg(weight)
from evolution

SELECT *
FROM animals
ORDER BY id DESC
LIMIT 5;

-- alter table animals
-- add column dtcreated timestamp not null default current_timestamp;

select * from wbois_ano;

use bovinoapp;

