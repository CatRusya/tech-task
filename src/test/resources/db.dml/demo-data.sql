insert into users (id, name, username, password)
values (1,'John Doe', 'johndoe@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       (2,'Mike Smith', 'mikesmith@yahoo.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m');

insert into country (id, country_name, logo)
values (104,'Japan', null),
       (2,'Monaco', null);

insert into city (id, city_name, country_id)
values (1,'Tokyo', 104),
       (2,'Ōsaka', 104);


insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');
