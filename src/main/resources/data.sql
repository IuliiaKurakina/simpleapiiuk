INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4610 800704', 'Иванова', 'Алена', '01-01-1985', '79260001125');
INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4610 800701', 'Сидорова', 'Ирина', '02-06-1954','79260001121');
INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4614 800708', 'Михайлов', 'Петр', '12-04-1999','79260001126');
INSERT INTO public.client (passport, surname, name, date_of_birth, phone) VALUES ('4614 800706', 'Петров', 'Иван', '01-06-1983','79260001124');

INSERT INTO public.deposit (interest_rate, term_of_deposit, prolongation) VALUES (4, 24, true);
INSERT INTO public.deposit (interest_rate, term_of_deposit, prolongation) VALUES (8, 12, false);
INSERT INTO public.deposit (interest_rate, term_of_deposit, prolongation) VALUES (12, 36, true);
INSERT INTO public.deposit (interest_rate, term_of_deposit, prolongation) VALUES (8, 6, true);


INSERT INTO public.account (client_id, deposit_id, date_closing, amount) VALUES (54, 40, '01-01-2020', 15000);
INSERT INTO public.account (client_id, deposit_id, date_closing, amount) VALUES (55, 41, '01-01-2020', 25000);
INSERT INTO public.account (client_id, deposit_id, date_closing, amount) VALUES (56, 43, '01-01-2020', 1000);
INSERT INTO public.account (client_id, deposit_id, date_closing, amount) VALUES (57, 42, '01-01-2020', 95000);

DELETE FROM client
WHERE name = 'Иван';