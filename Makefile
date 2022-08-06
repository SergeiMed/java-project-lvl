clean:
	./gradlew clean

installDist:
	./gradlew installDist

run-dist:
	./build/install/java-project-lvl2/bin/java-project-lvl2

check:
	gradle checkstyleMain
