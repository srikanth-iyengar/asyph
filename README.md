# OnlineJudge

Microservice Architecture of Online Judge

![service_architecture](https://user-images.githubusercontent.com/88551109/175817787-66b97d29-8842-4e30-8ec9-b722b8c5150d.png)


Following is the database configuration steps </br>


Cassandra docker compose file

```YAML
version: "2.1"
services:
  cassandra:
    image: cassandra
    expose:
    - 7000
    - 7001
    - 7199
    - 9042
    - 9160
    ports:
    - 9042:9042
    networks:
      vpcbr:
        ipv4_address: 10.5.0.5

  cassandra-web:
    image: markusgulden/cassandra-web
    depends_on:
    - cassandra
    environment:
      CASSANDRA_HOST_IPS: 10.5.0.5
      CASSANDRA_PORT: 9042
      CASSANDRA_USER: cassandra
      CASSANDRA_PASSWORD: cassandra
    ports:
    - 3000:3000
    networks:
      vpcbr:
        ipv4_address: 10.5.0.6
networks:
  vpcbr:
    driver: bridge
    ipam:
     config:
       - subnet: 10.5.0.0/16
         gateway: 10.5.0.1

```
Now open https://localhost:3000 to open the cassandra web client

<img width="960" alt="image" src="https://user-images.githubusercontent.com/88551109/175507845-9c210802-24bb-4754-8c7f-c9ef0ebcb7ee.png">


Redis Configuration

```YAML
services:
  redis:
    container_name: redis-my
    hostname: redis
    image: redis
    ports:
    - "6379:6379"

  redis-commander:
    container_name: redis-commander
    hostname: redis-commander
    image: rediscommander/redis-commander:latest
    environment:
    - REDIS_HOSTS=local:redis:6379
    ports:
    - "3001:8081"

```

<img width="960" alt="image" src="https://user-images.githubusercontent.com/88551109/175353609-de536193-4144-42ec-9b52-19ea3f92d211.png">





To start the eureka server navigate to the AsyphDiscoveryServer directory and run ```mvn spring-boot:run``` 
Now open https:localhost:8761 to see the eureka server configuration and registered server

<img width="947" alt="image" src="https://user-images.githubusercontent.com/88551109/175354017-3971557c-82bd-4407-b114-4816c124082c.png">
