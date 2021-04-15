package com.alexsobiek.SpaceRace;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matrix {

    private final Scanner input;

    /**
     * Constructor:
     * Parses and returns matrix stored in file
     * @param fileName Name of file to parse
     * @return int[][] Returns parsed matrix
     * @throws FileNotFoundException Throws error if the provided file does not exist
     */
    public Matrix(String fileName) throws FileNotFoundException {
        input = new Scanner(new File(fileName));
    }

    /**
     * Returns 2 dimensional int array (matrix)
     * parsed from provided file
     * @return int[][]
     */
    @Getter
    public int[][] get() {
        return parse(input);
    }

    /**
     * Parses the scanner to a 2D int array
     * @param input Scanner we should use for file operations
     * @return int[][] int array parsed from the provided scanner
     */
    private int[][] parse(Scanner input) {
        int length = 0;
        List<int[]> lineList = new ArrayList<>();
        while (input.hasNextLine()) {
            if (input.nextLine().startsWith("//")) continue;
            String[] stringInts = input.nextLine().split(",");
            int[] line = new int[stringInts.length];
            length = line.length;
            for (int i = 0; i < line.length; i++) line[i] = Integer.parseInt(stringInts[i]);
            lineList.add(line);
        }
        int[][] output = new int[length][lineList.size()];
        for(int i = 0; i < lineList.size(); i++) {
            int[] line = lineList.get(i);
            System.arraycopy(line, 0, output[i], 0, line.length);
        }
        return output;
    }

}
