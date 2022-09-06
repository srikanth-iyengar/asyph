gateway:
	mvn -f ./AsyphAuthService spring-boot:run

userservice:
	mvn -f ./UserMicroservice spring-boot:run

contestservice:
	mvn -f ./ProblemsAndContestService spring-boot:run

judge:
	mvn -f ./OnlineJudge spring-boot:run


