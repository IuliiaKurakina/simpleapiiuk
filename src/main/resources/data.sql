INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4610 800704', 'Иванова', 'Алена', '01-01-1985', '79260001125');
INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4610 800701', 'Сидорова', 'Ирина', '02-06-1954','79260001121');
INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4614 800708', 'Михайлов', 'Петр', '12-04-1999','79260001126');
INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4614 800706', 'Петров', 'Иван', '01-06-1983','79260001124');

INSERT INTO public.deposit (closing_date, interest_rate, prolongation, account_id) VALUES ('2010-01-01', 24, true, 210);



INSERT INTO public.account (deposit_amount, opening_date, client_id) VALUES (54000,  '01-01-2020', 209);


DELETE FROM client
WHERE name = 'Иван';