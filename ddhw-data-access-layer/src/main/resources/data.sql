insert into serviceuser values(1, 'nmail', 'pass', 'Ilya', '$2a$10$dHTP8FRUcnZ9d.T5PaSbxOHjsmupKUezcGto3omlnsfze/IBwdaUy', 'ROLE_USER'); --pass = pa pa pa
insert into SERVICEUSER values(2, 'aminMail', 'adminio', 'enought', '$2a$10$dHTP8FRUcnZ9d.T5PaSbxOHjsmupKUezcGto3omlnsfze/IBwdaUy', 'ROLE_ADMINISTRATOR');

insert into datastorage (parent_id, DISCRIMINATOR, name, DATEOFCREATION , PATHONDISK , AUTHOR_ID ) values(null, 'CATALOG','First catalog', '2021-04-22', '~/', 1);
insert into datastorage (parent_id, DISCRIMINATOR, name, DATEOFCREATION , PATHONDISK , AUTHOR_ID ) values(null, 'CATALOG','nmae', '2021-04-13', '~/1', 1);

insert into datastorage (parent_id, DISCRIMINATOR,name, DATEOFCREATION , PATHONDISK , AUTHOR_ID ) values(1, 'DOCUMENT', 'First document', '2021-04-13', '~/First catalog/', 1);
insert into datastorage (parent_id, DISCRIMINATOR,name, DATEOFCREATION , PATHONDISK, AUTHOR_ID ) values(1, 'DOCUMENT','nmae', '2021-04-22', '~/First catalog/1', 1);insert into document values('first document. it`s cool but why?', 1, 'LOW', 1, '1' ,null, 3);

insert into document values('it`s nmae document, why i did it? id dont understend...', 1, 'HIGH', false, 1, 5, null);
insert into document values('it`s a TIPA modified doc', 1, 'NORMAL', true, 2, 8, 5);

insert into catalogwhitelist values(1, 'WRITE', 1, 1);
