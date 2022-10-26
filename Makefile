.PHONY: build up-gateway up-user-service up-problem-service install-dev

## installation

install-dev:
	@ cd ApiGateway && gradle dependencies
	@ cd ProblemsContestService && gradle dependencies
	@ cd OnlineJudge && gradle dependencies
	@ cd UserMicroservice && gradle dependencies

build:
	@ cd OnlineJudge && gradle bootJar
	@ cd ApiGateway && gradle bootJar
	@ cd UserMicroservice && gradle bootJar
	@ cd ProblemsContestService && gradle bootJar

test:
	@ cd ApiGateway && gradle test
	@ cd UserMicroservice && gradle test
	@ cd ProblemsContestService && gradle test

up-gateway:
	@ cd ApiGateway && gradle bootRun

up-user-service:
	@ cd UserMicroservice && gradle bootRun

up-problem-service:
	@ cd ProblemsContestService && gradle bootRun

up-judge-service:
	@ cd OnlineJudge && gradle bootRun
