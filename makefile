program: compile run

compile:
	javac -Xlint:unchecked ./src/*.java -d ./bin/

run:
	java -cp ./bin EightPuzzleSearchAgent $(arg)

git:
	git add -A
	git commit -m "[AUTO]"
	git push

clean:
	rm ./bin/*.class