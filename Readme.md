## About this project
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

## Requirements
1. Java - 1.8.x
2. Maven - 3.x.x
3. H2DB (Inbuilt)
4. GIT
5. IDE 

## Steps to Setup
**1. Clone the application**

```
git clone https://github.com/raghuveera22/Reward-service.git
```
**2. H2DB database**
```
DB will be created when spring application context created.
It's executes the data.sql in the resorces folder and creates the tables and inserts the values
```

## Explore Rest APIs
The app defines following CRUD APIs.
```    
    POST /api/notes/{userId}   

```
## Run Project
```
Run as spring boot application in any IDE or we can deploy application jar into any of the web servers and start the webserver.

```

## Test Rest APIs

You can test them using swagger document or cURL commands.

Swagger documentation is available at <http://localhost:9090/swagger-ui.html>.

System inserted one default users into the database on start.

Once application is up user can perform below operation add new note using
 
Swagger do provide the example value and try button to test api

