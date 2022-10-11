clean:
	./gradlew clean

installDist:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app

check:
	gradle checkstyleMain

checkTest:
	gradle checkstyleTest

.PHONY: build

build:
	./gradlew build

jacoco:
	app/gradle jacocoTestReport
