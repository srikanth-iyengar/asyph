.PHONY: build up-gateway up-user-service up-problem-service install-dev

## installation

install-dev:
	@ cd ApiGateway && gradle dependencies
	@ cd ProblemsContestService && gradle dependencies
	@ cd OnlineJudge && gradle dependencies
	@ cd UserMicroservice && gradle dependencies

build:
	@ cd OnlineJudge && gradle bootJar && docker build . -t deadcoder11u2/judge-alpine
	@ docker stop judge-alpine
	@ docker rm judge-alpine
	@ docker run -d -p8081:8081 --name=judge-alpine deadcoder11u2/judge-alpine

up-gateway:
	@ cd ApiGateway && gradle bootRun

up-user-service:
	@ cd UserMicroservice && gradle bootRun

up-problem-service:
	@ cd ProblemsContestService && gradle bootRun

up-judge-service:
	@ cd OnlineJudge && gradle bootRun