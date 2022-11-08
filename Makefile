.PHONY: build up-gateway up-user-service up-problem-service install-dev

MAKE = make
## installation
install-dev:
	@ cd ApiGateway && gradle dependencies
	@ cd ProblemsContestService && gradle dependencies
	@ cd OnlineJudge && gradle dependencies
	@ cd UserMicroservice && gradle dependencies
	@ curl -fLSs https://raw.githubusercontent.com/CircleCI-Public/circleci-cli/master/install.sh | bash
	@ circleci update

init-env:
	@ cp .env ./UserMicroservice/.env
	@ cp .env ./ProblemsAndContestService/.env

build:
	@ gradle OnlineJudge:bootJar
	@ gradle ApiGateway:bootJar
	@ gradle UserMicroservice:bootJar
	@ gradle ProblemsAndContestService:bootJar

clean:
	@ gradle OnlineJudge:clean
	@ gradle ApiGateway:clean
	@ gradle UserMicroservice:clean
	@ gradle ProblemsAndContestService:clean

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
	@ docker build -f Dockerfile.dev -t deadcoder11u2/asyph .
	@ cd ./UserMicroservice && docker build . -t deadcoder11u2/asyph-user && cd ..
	@ cd ./ProblemsAndContestService && docker build . -t deadcoder11u2/asyph-problems && cd ..
	@ docker push deadcoder11u2/asyph
	@ docker push deadcoder11u2/asyph-user
	@ docker push deadcoder11u2/asyph-problems

ci:
	@ circleci config validate

docker-build-dev: 
	@ echo "\033[1m============CLEANING THE PROJECT============\033[0m"
	$(MAKE) clean
	@ echo "\033[1m============BUILDING THE PROJECT============\033[0m"
	$(MAKE) build 
	@ echo "\033[1m============DOCKER IMAGE BUILD INITIATED============\033[0m"
	$(MAKE) docker-dev
