package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input3.txt"));

        String[] str = in.lines().collect(Collectors.joining("\n")).split("\n");
        int row = 140, column=140;
        char[][]engineSchematic = new char[140][140];

        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++)
            engineSchematic[i][j] = str[i].charAt(j);
        }
        int result = sumOfPartNumbers(engineSchematic);
        System.out.println(result);
    }
    private static int sumOfPartNumbers(char[][] engineSchematic) {
        int totalSum = 0;
        int rows = engineSchematic.length;
        int cols = engineSchematic[0].length;

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int i = 0; i < rows * cols; i++) {
            int x = i / cols;
            int y = i % cols;

            char currentChar = engineSchematic[x][y];
            if (currentChar >= '0' && currentChar <= '9') {
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && engineSchematic[newX][newY] >= '0' && engineSchematic[newX][newY] <= '9') {
                        totalSum += engineSchematic[newX][newY] - '0';
                    }
                }
            }
        }

        return totalSum;
    }
}

