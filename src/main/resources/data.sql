-- Insert admin user
INSERT INTO developer (id, name, login, password) VALUES (1, 'Admin User', 'admin', '$2a$10$EixZaYVK1fsbw1ZfbX3OXe.PKbgO8H5EOu.9g6tNf6EX/f4yEphku'); -- password: password

-- Insert regular developer
INSERT INTO developer (id, name, login, password) VALUES (2, 'John Doe', 'johndoe', '$2a$10$EixZaYVK1fsbw1ZfbX3OXe.PKbgO8H5EOu.9g6tNf6EX/f4yEphku'); -- password: password

-- Assign roles
INSERT INTO developer_roles (developer_id, roles) VALUES (1, 'ADMIN');
INSERT INTO developer_roles (developer_id, roles) VALUES (1, 'USER');
INSERT INTO developer_roles (developer_id, roles) VALUES (2, 'USER');
