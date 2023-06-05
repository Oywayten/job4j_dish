CREATE TABLE dish (
    id          BIGSERIAL PRIMARY KEY,
    name        varchar(50)           NOT NULL UNIQUE,
    description varchar(256)
);

INSERT INTO dish (name, description)
VALUES ('Caesar salad with chicken', 'Gorgeous fresh herb and chicken salad'),
       ('Crab Louie', 'The main ingredient in Crab Louie, as the name suggests, is crab meat.'),
       ('Broccoli slaw', 'Salad, which is a variation of the traditional coleslaw, ' ||
                         'made using chopped raw broccoli stalks as a substitute for cabbage');