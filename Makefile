.PHONY: build up-gateway up-user up-problems

build:
	@ cd OnlineJudge && gradle bootJar && docker build . -t deadcoder11u2/judge-alpine
	@ docker stop judge-alpine
	@ docker rm judge-alpine
	@ docker run -d -p8081:8081 --name=judge-alpine deadcoder11u2/judge-alpine

up-gateway:
	@ cd ApiGateway && gradle bootRun

up-user:
	@ cd UserMicroservice && gradle bootRun

up-problems:
	@ cd ProblemsContestService && gradle bootRun
