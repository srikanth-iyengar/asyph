<p align='center'>
    <img width="45%" src="./docs/images/logo.png">
</p>

<p align='center'>
<img alt="CircleCI" src="https://img.shields.io/circleci/build/github/srikanth-iyengar/Asyph-OnlineJudge">
<img alt="Website" src="https://img.shields.io/website?down_color=lightgrey&down_message=offline&up_color=green&url=https%3A%2F%2Fgateway-srikanth-iyengar.cloud.okteto.net%2Fapi%2Fv1%2Fuser%2Fswagger-ui%2Findex.html">
<img alt="GitHub" src="https://img.shields.io/github/license/srikanth-iyengar/Asyph-OnlineJudge">
<img alt="GitHub pull requests" src="https://img.shields.io/github/issues-pr/srikanth-iyengar/Asyph-OnlineJudge">
<img alt="GitHub closed pull requests" src="https://img.shields.io/github/issues-pr-closed-raw/srikanth-iyengar/Asyph-OnlineJudge">
<img src ='https://visitor-badge.laobi.icu/badge?page_id=srikanth-iyengar.Asyph-OnlineJudge'>
<img src ='https://img.shields.io/badge/version-0.0.1-blue'>
<img src ='https://img.shields.io/badge/coverage-60%25-blue'>
<img src="https://img.shields.io/github/commit-activity/m/srikanth-iyengar/Asyph-OnlineJudge" />
<a href="https://github.com/srikanth-iyengar/Asyph-OnlineJudge/graphs/contributors" alt="Contributors">
<img src="https://img.shields.io/github/contributors/srikanth-iyengar/Asyph-OnlineJudge" />
<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/srikanth-iyengar/asyph-onlinejudge">
</a>

</p>

## Architecture of Asyph
![service_architecture](https://user-images.githubusercontent.com/88551109/175817787-66b97d29-8842-4e30-8ec9-b722b8c5150d.png)

## 🤖 Installation
```
make install-dev
```


## 💻 Development

To run any server cd to that server directory and run 
``````sh
gradle bootRun
``````

Or you can use the Makefile to run the server
```sh
make up-gateway
make up-user-service
make up-problem-service
make up-judge-service
```

If you are too lazy, start all the services by running 

```sh
docker-compose up
`````````

#### Make sure that you have all the variables present in your environment

Here is the sample .env file
```bash 
GMAIL_PASSWORD=${GMAIL_PASSWORD}
ASTRA_API_APPLICATION-TOKEN=${ASTRA_TOKEN}
ASTRA_API_DATABASE-ID=${ASTRA_DB_ID}
ASTRA_API_DATABASE-REGION=${ASTRA_API_DB_REGION}
ASTRA_CQL_DRIVER-CONFIG_BASIC_SESSION-KEYSPACE=${ASTRA_SESSION_KEYSPACE}
GMAIL_ACCOUNT=${GMAIL_ID}
```


## 📄 API Docs
User Service Api Doc: https://gateway-srikanth-iyengar.cloud.okteto.net/api/v1/user/swagger-ui/index.html <br>
Problems Contest Service Api Doc: https://gateway-srikanth-iyengar.cloud.okteto.net/api/v1/Problems-Contest-Service/swagger-ui/index.html



## ✏️  Todo
- **Currently using in memory jobstore(which is not persistent) for quartz scheduler migrate to cockroachdb as a job store**
- **Judge doesn't update the execution status of submission**
- **Sending contest reminder for the active users if pending**
- **Elo rating system not implemented yet**
