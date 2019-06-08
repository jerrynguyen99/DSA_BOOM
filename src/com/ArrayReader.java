package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ArrayReader {

    private int [][] myArray = null;

    public void read() {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("D:/DSA_BOOM/src/asset/map_files/singleplayer/desert.txt"))); // Absolute path again
            int rows = 10;
            int columns = 16;
            myArray = new int[rows][columns];
            while(sc.hasNextLine()) {
                for (int i=0; i<myArray.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j=0; j<line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++)
                System.out.print(myArray[i][j]+" ");
            System.out.println();
        }
        return "\n STRING DONE!";
    }
}