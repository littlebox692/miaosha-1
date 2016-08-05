package com.yan.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Yan on 2016/7/25.
 */
public class TreeSolution {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TreeSolution() {
    }

    public static void main(String[] args) {
        int[] array = new int[]{0};
        TreeSolution solution = new TreeSolution();
        solution.solution();
    }

    public String solution() {
        String result = "";
        int[][] array;
        String midLine;
        String lastLine;
        String firstLine;
        String[] parseLine;
        int[] leafs;
        try {
            File directory = new File("D:\\intellij_workspace\\helloTest\\src\\main\\java\\com\\yan\\hello\\tree.txt");
            Reader in = new FileReader(directory);
            BufferedReader bin = new BufferedReader(in);
            firstLine = bin.readLine();
            if (firstLine.length() != 1) {
                return "invalid first line...";
            }
            int num = Integer.parseInt(firstLine);
            array = new int[num + 1][num + 1];
            //初始化邻接矩阵，先全部设为-1
            for (int i = 0; i < num + 1; i++) {
                for (int j = 0; j < num + 1; j++) {
                    array[i][j] = -1;
                    if (i == 0) {
                        array[i][j] = j;
                    }
                    if (j == 0) {
                        array[i][j] = i;
                    }
                }
            }
            //读取边，并修改邻接矩阵的值为0
            while ((midLine = bin.readLine()).length() == 3) {
                parseLine = midLine.split(" ");
                array[Integer.parseInt(parseLine[0])][Integer.parseInt(parseLine[1])] = 0;
            }
            //若边中存在不是两个数字的，说明定义有错误，返回
            if (bin.read() != -1) {
                return "the last line is invalid...";
            }
            lastLine = midLine;
            //解析最后一行，该行为叶子节点，初始化叶子节点数组
            parseLine = lastLine.split(" ");
            leafs = new int[parseLine.length];
            for (int i = 0; i < parseLine.length; i++) {
                leafs[i] = Integer.parseInt(parseLine[i]);
            }
            for (int[] x : array) {
                System.out.println(Arrays.toString(x));
            }
            System.out.println(Arrays.toString(leafs));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    public void initialMatrix(int[][] matrix, int[] leafs) {

    }
}
