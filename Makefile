.PHONY: build up-gateway up-user-service up-problem-service install-dev

## installation
install-dev:
	@ cd ApiGateway && gradle dependencies
	@ cd ProblemsContestService && gradle dependencies
	@ cd OnlineJudge && gradle dependencies
	@ cd UserMicroservice && gradle dependencies

build:
	@ gradle OnlineJudge:bootJar
	@ gradle ApiGateway:bootJar
	@ gradle UserMicroservice:bootJar
	@ gradle ProblemsContestService:bootJar

clean:
	@ gradle OnlineJudge:clean
	@ gradle ApiGateway:clean
	@ gradle UserMicroservice:clean
	@ gradle ProblemsContestService:clean

test:
	@ gradle ApiGateway:test
	@ gradle UserMicroservice:test
	@ gradle ProblemsContestService:test

up-gateway:
	@ cd ApiGateway && gradle bootRun

up-user-service:
	@ cd UserMicroservice && gradle bootRun

up-problem-service:
	@ cd ProblemsContestService && gradle bootRun

up-judge-service:
	@ cd OnlineJudge && gradle bootRun

docker-dev:
		@ docker build -f Dockerfile.dev -t srikanth/asyph .

docker-build-dev: clean build docker-dev
