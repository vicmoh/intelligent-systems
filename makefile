program: compile run

compile:
	javac -Xlint:unchecked ./src/*.java -d ./bin/

run:
	java -cp ./bin Main

git:
	git add -A
	git commit -m "[AUTO]"
	git push