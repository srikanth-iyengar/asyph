gateway:
	@ mvn -f ./AsyphAuthService spring-boot:run

userservice:
	@ mvn -f ./UserMicroservice spring-boot:run

contestservice:
	@ mvn -f ./ProblemsAndContestService spring-boot:run

judge:
	@ mvn -f ./OnlineJudge spring-boot:run

update:
	@	mvn -f ./AsyphAuthService clean install -U
	@	mvn -f ./UserMicroservice clean install -U
	@	mvn -f ./ProblemsAndContestService clean install -U
	@	mvn -f ./OnlineJudge clean install -U
