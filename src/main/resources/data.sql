INSERT INTO roles(name)
VALUES
('EMPLOYEE'),
('HR'),
('CEO');

INSERT INTO employees(id, email, first_name, last_name, password, username, role_id)
VALUES
(1, 'asdasd@asd.bg', 'atanas', 'daskalov', '123', 'asd', 1),
(2, 'ppppp@asd.bg', 'petar', 'petrov', 'pesheca','321', 1),
(3, 'hrhrhr@asd.bg','hris','hristova','hihi','4545', 2),
(4, 'mm@asd.bg','mitko','dimitrov','dd','5151', 3);