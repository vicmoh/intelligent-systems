program: compile run

compile:
	javac -Xlint:unchecked ./src/*.java -d ./bin/

run:
	java -cp ./bin LogicAgent $(arg1) $(arg2)

arg:
	javac -Xlint:unchecked ./src/*.java -d ./bin/
	java -cp ./bin LogicAgent ./assets/HornKB.txt ./assets/Query1.txt

git:
	git add -A
	git commit -m "[AUTO]"
	git push

clean:
	rm ./bin/*.class