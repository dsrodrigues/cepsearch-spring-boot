insert into Address(zip_code, street, neighborhood, city, state) values ('04675010', 'Av. Dr. Silva Melo', 'Jd. Taquaral', 'Sao Paulo', 'Sao Paulo');
insert into Address(zip_code, street, neighborhood, city, state) values ('79002240', 'Rua Dr. Ferreira', 'Centro', 'Campo Grande', 'Mato Grosso do Sul');
insert into Address(zip_code, street, neighborhood, city, state) values ('22333900', 'Av. Mouse Teclado', 'Centro', 'Sao Paulo', 'Sao Paulo');
-- Massa 12345678 return 12345670
insert into Address(zip_code, street, neighborhood, city, state) values ('12345670', 'Endereco Substituicao Primeiro Zero', 'Centro', 'Sao Paulo', 'Sao Paulo');
-- Massa 22345678 return 22345600
insert into Address(zip_code, street, neighborhood, city, state) values ('22345600', 'Endereco Substituicao Segundo Zero', 'Centro', 'Sao Paulo', 'Sao Paulo');
-- Massa 32345678 return 32345000
insert into Address(zip_code, street, neighborhood, city, state) values ('32345000', 'Endereco Substituicao Terceiro Zero', 'Centro', 'Sao Paulo', 'Sao Paulo');
-- Massa 42345678 return NotFound
insert into Address(zip_code, street, neighborhood, city, state) values ('32340000', 'Endereco Substituicao Quarto Zero NotFound', 'Centro', 'Sao Paulo', 'Sao Paulo');