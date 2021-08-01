# Exchange Trading
``Exchange-Trading`` was made as part of the BSc Thesis. Application is simulator of real stock exchange. Main futures for users:
- list of noticed companies
- view on price statistics of noticed companies
- price and volumes charts
- sell and buy company stocks
- create sell and buy limit offers (offers create orderbook)
- preview of the company's stock orders (orderbook)
- manage own investors wallet
- simple authorization

[Exchange-Trading-Tester](https://github.com/pkwasek08/Exchange-Trading-Tester) is additional module responsible for simulating behaviour of the real stock excgange and generating price statistics of noticed company. 
It has special endpoint that activate special algorithm. Thanks for that system can generate artificial stock exchange data.

Both of api applications have own ``Postgresql`` database and common frontend application [Exchange-Trading-App](https://github.com/pkwasek08/Exchange-Trading-App). Only admin can run simulation from the UI test.

System registers time which is need to process stock exchange operations. ``Exchange-Trading`` is monitored by ``Prometheus`` - special tool to monitor system behaviour and resources usage.  

Data can be display on charts using ``Grafana`` (tool to draw charts in real time). 

Docker image has been created for all modules. Entire ``Exchange-Trading`` can be run by ``docker-compose`` in [Exchange-Trading-DevOps](https://github.com/pkwasek08/Exchange-Trading-DevOps) repository.

This project is main api application.  
Application contains all mechanisms that are responsible for the proper functioning of the stock exchange.
 
## Technologies
- Java 8
- Spring Boot 2.2.6
- Hibernate
- Maven
- Docker
- Swagger
- Prometheus

## Endpoints
#### Main endpoints:
- ``/company`` - service and operations on listed companies
- ``/companyStatistics``  service and operations on stocks of company price statistics
- ``/offerSellBuy`` - service and operations on sell/buy quick transaction
- ``/offerSellBuyLimit`` - service and operations on sell/buy limit offer
- ``/stock`` - service and operations on stock transactions
- ``/user`` - service and operations on users
    
    All endpoints are presented on **swagger-ui** (http://localhost:8080/swagger-ui.html)
 
## Data model
<p align="center"><img src="src/main/resources/data_model.png" width="75%"/></p>

## Setup

App starts on port 8080. You can starts app using maven, IDE or Docker.
To use Docker, first you have to build project:
```
mvn install
```
Then, you can create custom Docker image which including Exchange-Trading:
```
docker build -t <image_name>:<tag> .
```
To run application on Docker you must start container:
```
docker run -d -p 8080:8080 <image_name>
```

## Linked repositoris
* [Exchange-Trading-Tester](https://github.com/pkwasek08/Exchange-Trading-Tester)    
* [Exchange-Trading-App](https://github.com/pkwasek08/Exchange-Trading-App)  
* [Exchange-Trading-DevOps](https://github.com/pkwasek08/Exchange-Trading-DevOps)