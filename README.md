# Log correlation



Example of log correlation using Spring Cloud Sleuth and ELK stack.


This project has 4 services, have the flow:
```
                       -> Service-C
Service-A -> Service-B 
                       -> Service-D
```

- Service-A - is Spring MVC, call Service-B
- Service-B - is Spring Webflux, call Service-C and Service-D simultaneously, both Spring MVC


Witch service has one endpoint to generate log:
- http://localhost:8080/servicea/message
- http://localhost:8180/serviceb/message
- http://localhost:8280/servicec/message
- http://localhost:8380/serviced/message

Docker compose file have all services and ELK stack configured


Use the `start.sh` to run the project, they will generate build of jar files, execute docker-compose, await kibana stay health, create index pattern and make some requests to generate logs.


After star is complete acess: http://localhost:5601/app/discover#/

### Log flow:
![image](https://user-images.githubusercontent.com/7308344/142554397-590820b5-1bf7-45b4-8ee1-bc6b181c598f.png)


### Log correlation in Kibana
![image](https://user-images.githubusercontent.com/7308344/142554435-3652d1e3-fd43-4eb6-a328-0a831526f032.png)

