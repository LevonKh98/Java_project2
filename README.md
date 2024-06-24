Stable Marriage Problem: There are n men and n women, where each person has ranked all members 
of the opposite sex in order of preference. You want to pair them up to create a stable matching that 
there are no two people of opposite sex who would both rather have each other than their current 
partners.  
Write a Java program using Gale–Shapley algorithm which will receive an instance of the marriage 
problem and return a stable matching when: 
a) men propose to women   
b) women propose to men  
Input Format: Create input1.txt file in the same directory as the java and class files. 
“input1.txt” will be entered as a command line argument. The first line will be n(the number of 
women or men). The next n lines will be the preference lists of the men and the fallowing n 
lines will be the preference lists of the women with a whitespace separated list from best to 
worst. 
Output Format: Your program will output a stable matching for when men propose to women and 
another stable matching for when women propose to men. 
Examples:  
If input1.txt contains: 
2 
1 2 
2 1 
2 1 
1 2 
Then output will be: 
Output when men propose: 
{(1,1), (2,2)}              
→ It means {(m1, w1), (m2, w2)} 
Output when women propose: 
{(1,2), (2,1)}              
→ It means ((w1, m2), (w2, m1)} 
If Input1.txt contains: 
3 
1 2 3 
2 1 3 
1 2 3 
2 1 3 
1 2 3 
1 2 3 
Then output will be: 
Output when men propose: 
{(1,1), (2,2), (3,3}              
→ It means {(m1, w1), (m2, w2), (m3, w3)} 
Output when women propose: 
{(1,2), (2,1), (3,3)}              
Note:  
→ It means ((w1, m2), (w2, m1), (w3, m3)} 
Your Java program should be commented, indented, and structured.  Output should be sent to 
System.out. The program should be named project1. Please place all your files (.java, .class, .txt) in a 
directory named after you, zip them and submit them to canvas. Don’t include any extra files and 
directories from IDE environment. The program must compile with the command javac *.java and run 
with the command java project1 input1.txt. Remember input1.txt is the command line argument.  
Don’t place the classes in a package (use default package).  
