<p align='center'>
    <img width="80%" src="./docs/images/logo-transparent.png">
</p>

<h1>
<p align='center'>
ASYPH - Online Judge
</p>
</h1>
<p align='center'>
<img src='https://circleci.com/gh/Deadcoder11u2/Asyph-OnlineJudge.svg?style=svg'>
<img src ='https://visitor-badge.laobi.icu/badge?page_id=Deadcoder11u2.Asyph-OnlineJudge'>
<!-- [![CircleCI](https://circleci.com/gh/Deadcoder11u2/Asyph-OnlineJudge.svg?style=svg)](https://github.com/Deadcoder11u2/Asyph-OnlineJudge) -->
</p>

Architecture of Asyph

## 🤖 Installation
```
make install-dev
```

![service_architecture](https://user-images.githubusercontent.com/88551109/175817787-66b97d29-8842-4e30-8ec9-b722b8c5150d.png)

## 💻 Development

To run any server cd to that server directory and run `gradle bootRun`

Or you can use the Makefile to run the server
```sh
make up-gateway
make up-user-service
make up-problem-service
make up-judge-service
```

To start the eureka server navigate to the AsyphDiscoveryServer directory and run 
```sh 
gradle bootRun
```
Now open https:localhost:8761 to see the eureka server configuration and registered services
<img width="947" alt="image" src="https://user-images.githubusercontent.com/88551109/175354017-3971557c-82bd-4407-b114-4816c124082c.png">
