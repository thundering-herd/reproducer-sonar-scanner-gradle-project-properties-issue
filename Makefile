sonar:
	touch .$(ENV).env
	echo 'FOO=$(ENV)' >>.$(ENV).env
	./gradlew sonar --stacktrace -Pdotenv.filename=./.$(ENV).env
	rm .$(ENV).env
