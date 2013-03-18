GraphTheoryProject
==================
This project was done as a part of assignment the Graph Theory and Combinatorics.

Problem Statement:

A triangle-free graph is an undirected graph in which no three vertices form a triangle of
edges [1]. In order to determine a Ramsey graph based on r colourings, it often suffices to
investigate maximal triangle-free graphs of any colour any colour among the r colours [2].
These are triangle-free graphs for which the insertion of any further edges would create a
triangle [3]. Develop a program that counts the number of maximal triangle free graphs
that can be constructed from n vertices, where n > 1. 






Bonus Question:

If the graph is a complete graph and if the program identifies whether or not it is a Ramsey
graph based on the number of colourings r . You may assume that the value of k and l in
R(r,k,l) is 2 and 3 respectively. Thus only Ramsey graphs with R(r,2,3) = n need to be
identified.



Usage Instructions:

Requirements: 

JDK 1.6
USING ECLIPSE:

	To Build:
	You can import this project in eclipse and build.

	To Package:
	To make a jar file you can just use the export option and choose export using jar file.
	And then run the jar file using command line with proper arguments.

	You can even run directly from eclipse by configuring the run arguments as described by usage.

USING COMMAND LINE:
	Browse to source folder.
	
	cd path_to_Project_Folder/src/
	
	
	Run the following commands
	
	
	mkdir bin
	javac -d "bin" "gtc/*.java"
	java -cp "bin" gtc.Main <options> <n>
	
	<options>
		-a : To compute both Ramsey Graphs and Maximal-Triangle-Free Graphs
		-r : To compute Ramsey Graphs
		-t : to compute Maximal-Triangle-Free Graphs

	<n>	   : No. of vertices
	
	Example:
	java -cp "bin" gtc.Main -a 5