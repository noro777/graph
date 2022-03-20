package com.company;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
System.out.println("Input number of one connections.");
int numberOfOneConnections = scanner.nextInt();
System.out.println("Input connections");
int[][] initialMatrixOfOneConnections = new
int[numberOfOneConnections][2];
for (int i = 0; i < numberOfOneConnections; i++) {
for (int j = 0; j < 2; j++) {
initialMatrixOfOneConnections[i][j] = scanner.nextInt();
}
}
int numberOfOneConnectionsWithoutZero = 0;
for (int i = 0; i < numberOfOneConnections; i++) {
if (initialMatrixOfOneConnections[i][1] != 0) {
numberOfOneConnectionsWithoutZero++;
}
}
//mkm matrixic hanum enq elqayinnery
int[][] matrixOfOneConnectionsWithoutZero = new
int[numberOfOneConnectionsWithoutZero][2];
for (int i = 0; i < numberOfOneConnections; i++) {
if (initialMatrixOfOneConnections[i][1] != 0) {
matrixOfOneConnectionsWithoutZero[i][1] =
initialMatrixOfOneConnections[i][1];
matrixOfOneConnectionsWithoutZero[i][0] =
initialMatrixOfOneConnections[i][0];
}
if (i == numberOfOneConnectionsWithoutZero) {
break;
}
}
int y;
ArrayList<Integer> t1 = new ArrayList<>();
ArrayList<Integer> t2 = new ArrayList<>();
ArrayList<Integer> t3 = new ArrayList<>();
for (int i = 0; i < numberOfOneConnections; i++) {
y = 0;
for (int j = 0; j < numberOfOneConnections; j++) {
if (initialMatrixOfOneConnections[i][0] ==
initialMatrixOfOneConnections[j][1]) {
y++;
if (!t2.contains(initialMatrixOfOneConnections[i][0]) &&
initialMatrixOfOneConnections[i][1] != 0) {
t2.add(initialMatrixOfOneConnections[i][0]);
}
}
}
if (initialMatrixOfOneConnections[i][1] == 0) {
t3.add(initialMatrixOfOneConnections[i][0]);
}
if (y == 0) {
if (!t1.contains(initialMatrixOfOneConnections[i][0])) {
t1.add(initialMatrixOfOneConnections[i][0]);
}
}
}
int t5 = 0;
int t6 = 0;
for (int i = 0; i < numberOfOneConnections; i++) {
if (t2.contains(initialMatrixOfOneConnections[i][0])) {
if (t2.contains(initialMatrixOfOneConnections[i][1]))
t5++;
}
if (t3.contains(initialMatrixOfOneConnections[i][0])) {
if (t3.contains(initialMatrixOfOneConnections[i][1]))
t6++;
}
}
System.out.println("t1: " + t1.toString());
System.out.println("t2: " + t2.toString());
System.out.println("t3: " + t3.toString());
System.out.println("t5: " + t5);
System.out.println("t6: " + t6);
int maxValueOfGraph = getMaxValueOfGraph(numberOfOneConnections,
initialMatrixOfOneConnections);
System.out.println("Km: " + ((float) t2.size() / (float)
maxValueOfGraph));
System.out.println("Knk: " + ((float) t5 / (float)
numberOfOneConnections));
System.out.println("K: " + ((float) 2 * t6 / (float) (t3.size() *
(t3.size() - 1))));
System.out.println();
int[][] matrixOfOneConnections = getMatrixOfOneConnections(
numberOfOneConnectionsWithoutZero,
matrixOfOneConnectionsWithoutZero,
maxValueOfGraph);
System.out.println("Matrix of one connections");
printMatrixOfOneConnections(maxValueOfGraph, matrixOfOneConnections);
getDynamicMatrix(maxValueOfGraph, matrixOfOneConnections,
matrixOfOneConnections);
}
static int rangOfGraph = 1;
private static int[][] getDynamicMatrix(int maxValueOfGraph,
int[][] matrixOfOneConnections,
int[][] dynamicMatrix) {
int[][] kkk = new int[maxValueOfGraph][maxValueOfGraph];
for (int i = 0; i < maxValueOfGraph; i++) {
for (int j = 0; j < maxValueOfGraph; j++) {
kkk[i][j] = dynamicMatrix[i][0] *
matrixOfOneConnections[0][j];
for (int k = 1; k < maxValueOfGraph; k++) {
kkk[i][j] = kkk[i][j] + dynamicMatrix[i][k] *
matrixOfOneConnections[k][j];
}
}
}
getT4T7(maxValueOfGraph, dynamicMatrix, rangOfGraph);
System.out.println("A" + rangOfGraph);
rangOfGraph++;
System.out.println();
printMatrixOfOneConnections(maxValueOfGraph, kkk);
int sumOfDynamicMatrix = 0;
for (int i = 0; i < maxValueOfGraph; i++) {
for (int j = 0; j < maxValueOfGraph; j++) {
sumOfDynamicMatrix += kkk[i][j];
}
}
if (sumOfDynamicMatrix == 0) {
System.out.println("rang of graph: " + (rangOfGraph - 1));
return kkk;
}
return getDynamicMatrix(maxValueOfGraph, matrixOfOneConnections,
kkk);
}
private static int[][] getMatrixOfOneConnections(int
numberOfOneConnectionsWithoutZero,
int[][]
matrixOfOneConnectionsWithoutZero,
int maxValueOfGraph) {
int[][] A = new int[maxValueOfGraph][maxValueOfGraph];
for (int i = 0; i < maxValueOfGraph; i++) {
for (int j = 0; j < maxValueOfGraph; j++) {
for (int k = 0; k < numberOfOneConnectionsWithoutZero; k++) {
if (matrixOfOneConnectionsWithoutZero[k][0] == (i + 1)) {
A[i][matrixOfOneConnectionsWithoutZero[k][1] - 1] = 1;
}
}
if (A[i][j] != 1) {
A[i][j] = 0;
}
}
}
return A;
}
private static void printMatrixOfOneConnections(int maxValueOfGraph,
int[][] A) {
for (int i = 0; i < maxValueOfGraph; i++) {
for (int j = 0; j < maxValueOfGraph; j++) {
System.out.print(A[i][j] + " ");
}
System.out.println();
}
}
private static int getMaxValueOfGraph(int numberOfOneConnections,
int[][] matrixOfOneConnections) {
int maxValueOfGraph = matrixOfOneConnections[0][0];
for (int i = 0; i < numberOfOneConnections; i++) {
for (int j = 0; j < 2; j++) {
if (matrixOfOneConnections[i][j] > maxValueOfGraph) {
maxValueOfGraph = matrixOfOneConnections[i][j];
}
}
}
return maxValueOfGraph;
}
private static void getT4T7(int maxValueOfGraph, int[][] dynamicMatrix,
int n) {
int t4 = 0;
int sum = 0;
for (int i = 0; i < maxValueOfGraph; i++) {
for (int j = 0; j < maxValueOfGraph; j++) {
sum += dynamicMatrix[j][i];
}
if (sum == 0) {
t4++;
}
}
System.out.println("t4: " + t4);
System.out.println("t7: " + (t4 - n));
}
}