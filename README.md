# OnlineJudge

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

To start the eureka server navigate to the AsyphDiscoveryServer directory and run ```BASH mvn spring-boot:run``` 
Now open https:localhost:8761 to see the eureka server configuration and registered server

<img width="947" alt="image" src="https://user-images.githubusercontent.com/88551109/175354017-3971557c-82bd-4407-b114-4816c124082c.png">
