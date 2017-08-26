# Basic Algorithms Projects

### Information about the class

This repository contains all the projects I worked on for my Basic Algorithms class, UA-CSCI 301 with Professor Shoup in Spring 2017. We had the liberty of choosing the language in which we wanted to complete them and I chose Java. In each folder, titled after the algorithm implemented, you will find:
* a "sample_io" folder which contains test inputs and their desired outputs
* a pdf file which contains the provided instructions
* a single .java file named after the assignment

### Running the projects

To run these projects, you will have to download the .java file and run:
$ javac name_of_file.java
$ java  name_of_file /path/to/input/file

You can come up with your own test cases that follow the constraints stated in the instruction .txt, or you can use the provided IO to test out the code.

### Breakdown of each project

Each project's objective was to implement the well-known algorithm in the context of solving a given problem.

#### DFS

This program identifies loops of connecting rooms in a house.

##### Input format:

It takes in a list of numbers. The first line has n, m - the number of rooms in a house and the number of passages which connect two rooms. The next m lines after that have two numbers a, b which tell that there is a passage from a to b.

##### Output format:

It outputs "0" if there is no loop in the house. Otherwise, it prints "1" and then the series of rooms which have passages leading back to a previous room.

#### Dijkstra

This program identifies a path from a starting point to an endpoint that includes at least one wet road and avoids as many dry roads as possible.

##### Input format:

It takes in a list of numbers. The first line contains two numbers n, m - the number of crossroads and the number of roads connecting them. The next m lines contain three numbers a, b, c which tell that there is a road from a to b with c indicating if the road is wet (c = 1) or dry (c = 2).

##### Output format:

It outputs "-1" if there is no path with at least one wet road. Otherwise, it prints the minimum number of dry roads on the way from the starting point to the endpoint.

#### Karatsuba Multiplication

This program multiplies two polynomials by using the Karatsuba method and recursively computing the product of smaller polynomials.

##### Input format:

It takes in three lines of input. The first line contains a number n which is the degree of the two following polynomials. The next two lines consist of n+1 space seperated integers a_0, ..., a_n where a_i is the coefficient of x^i.

##### Output format:

It outputs the coefficients of the product of the two polynomials, space separated in a single line, starting with the coefficient of x^0.

#### Sequence Allignment

This program takes in two sequences and finds the optimal alignment between them. The StringGenerator file generates random sequences for testing.

##### Input format:

It takes in two sequences a and b.

##### Output format:

It outputs the optimal score of aligning the two sequences and teh optimal alignment of the two sequences.

#### Topological Sort

This program performs topological sort on a directed graph.

##### Input format:

It takes in a list of numbers. The first line contains two numbers n, m - the number of vertices and the number of edges between them. The next m lines contain two numbers i, j denoting an edge from vertex i to vertex j.

##### Output format:

It outputs the topological sort of the graph's vertices.
