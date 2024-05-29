all: part1.cpp part2.cpp
	g++ -O2 part1.cpp -o part1 -lpapi
	g++ -O2 part2.cpp -o part2 -lpapi -fopenmp

part1: part1.cpp
	g++ -O2 part1.cpp -o part1 -lpapi

part2: part2.cpp
	g++ -O2 part2.cpp -o part2 -lpapi -fopenmp

clean:
	rm -f part1 part2