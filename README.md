# fasm-Ford Sales Agent Manager

A simple spring boot project for sales agent register and sign-in management.

## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the Repository**

```
git clone https://github.com/itzhangyang/fasm.git
```

**2. Create Mysql database**

```
source scripts/database.sql
```

**3. Change mysql username and password as per your installation**

- open `src/main/resources/application.properties`
- change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

##### 4.Run your application via Idea or Maven command

## Rest APIs

```
post /sales/register

POST /sales/{mobilePhone}/sign-in

GET /sales/rank
```

See more details on the swagger page:

```
http://localhost:8080/swagger-ui.html
```

## Authentication

In order to access the api, you should set the token in your cookie, for example, you can set your cook as following(In postman):

```
token=token2; Path=/; Domain=localhost; Expires=Sun, 20 Jun 2021 09:46:47 GMT;
```

 Note Now we have only two tokens for test(token1 and token2, hard-coded in the code for test)

## Meodels and Concepts

![https://github.com/itzhangyang/fasm/blob/master/doc/images/models.png]()

