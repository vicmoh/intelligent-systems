program: compile run

example: compile run_example

compile:
	javac -Xlint:unchecked ./src/*.java -d ./bin/

run:
	java -cp ./bin EightPuzzleSearchAgent $(arg)

run_example:
	java -cp ./bin EightPuzzleSearchAgent ./assets/dog.txt

git:
	git add -A
	git commit -m "[AUTO]"
	git push

clean:
	rm ./bin/*.class
