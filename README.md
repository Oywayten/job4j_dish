# Hungry wolf
`RestFull API architecture application for the delivery of ready meals`

### Technology stack:
+ Java 17,
+ Maven 4.0,
+ Hibernate 5,
+ Spring boot 2,
+ PostgreSql 14,
+ Liquibase,
+ SLF4J,
+ Lombok,

## Environment requirements:
+ Java 17,
+ Maven 3.8,
+ PostgreSQL 14

### Application launch

1. Install PostgreSQL: login - postgres, password - password;
2. Create dish database;
    ```postgres-sql
    CREATE DATABASE dish;
    ```
3. Build the project and run the Spring Boot application;
    ```shell
    mvn clean package spring-boot:run
   ```

### Use application

1. Dish registration.

   Request `POST /api/v1/dishes/registration`  
   With the body of a JSON object
   ```json
   {
    "name":"good salad",
    "description":"very good salad"
   }
   ```

   Response from the server with id
   ```json
   {
    "id": 8,
    "name": "good salad",
    "description": "very good salad"
   }
   ``` 

2. Dish description patch.  

   Request `PATCH /api/v1/dishes`  
   With the body of a JSON object (id and description)
   ```json
   {
    "id": 8,
    "description": "it's beautiful salad!"
   }
   ```

   Response from the server
   ```json
   {
    "id": 8,
    "name": "good salad",
    "description": "it's beautiful salad!"
   }
   ``` 
   
3. Dish name and description update. 

   Request `PUT /api/v1/dishes`  
   With the body of a JSON object (id, name and description)
   ```json
   {
    "id": 8,
    "name": "wonderful salad",
    "description": "it's wonderful salad!"
   }
   ```

   Response from the server
   ```json
   {
    "id": 8,
    "name": "wonderful salad",
    "description": "it's wonderful salad!"
   }
   ``` 

4. Get all dishes.

   Request `GET /api/v1/dishes` without body
   
   Response from the server
   ```json
   [
    {
        "id": 1,
        "name": "Caesar salad with chicken",
        "description": "Gorgeous fresh herb and chicken salad"
    },
    {
        "id": 2,
        "name": "Crab Louie",
        "description": "The main ingredient in Crab Louie, as the name suggests, is crab meat."
    }
   ]
   ```
5. Find dish by id.

   Request `GET /api/v1/dishes/2` without body

   Response from the server
   ```json
   {
    "id": 2,
    "name": "Crab Louie",
    "description": "The main ingredient in Crab Louie, as the name suggests, is crab meat."
   }
   ```

6. Delete dish by id.

   Request `DELETE /api/v1/dishes/2` without body  

   Response from the server `Status: 200 OK` and header `Title: delete`

### Contacts
+ email: [oywayten+git@gmail.com](mailto:oywayten+git@gmail.com)
+ telegram: [@VitaliyJVM](https://t.me/VitaliyJVM/ "go to t.me/VitaliyJVM")
