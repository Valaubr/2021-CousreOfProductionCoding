insert into serviceuser values(1, 'nmail', 'pass', 'Ilya', '$2a$10$dHTP8FRUcnZ9d.T5PaSbxOHjsmupKUezcGto3omlnsfze/IBwdaUy', 'ADMINISTRATOR'); --pass = pa pa pa
insert into datastorage (parentid, name, DATEOFCREATION , PATHONDISK , AUTHOR_ID , ISFOLDER) values(null, 'First catalog', '2021-04-22', '~/', 1, true);
insert into datastorage (parentid, name, DATEOFCREATION , PATHONDISK , AUTHOR_ID , ISFOLDER) values(null, 'nmae', '2021-04-13', '~/', 1, true);
insert into datastorage (parentid, name, DATEOFCREATION , PATHONDISK , AUTHOR_ID , ISFOLDER) values(1, 'First document', '2021-04-13', '~/First catalog/', 1, false);
insert into datastorage (parentid, name, DATEOFCREATION , PATHONDISK, AUTHOR_ID , ISFOLDER) values(1, 'nmae', '2021-04-22', '~/First catalog/', 1, false);
insert into document values('first document. it`s cool but why?', 1, 'LOW', 1, '1' ,null, 3);
insert into document values('it`s nmae document, why i did it? id dont understend...', 1, 'HIGH', 1, 2, null, 4);
insert into catalogwhitelist values(1, 'WRITE', 1, 1);
insert into SERVICEUSER values(2, 'aminMail', 'adminio', 'enought', '$2a$10$dHTP8FRUcnZ9d.T5PaSbxOHjsmupKUezcGto3omlnsfze/IBwdaUy', 'ROLE_ADMINISTRATOR');