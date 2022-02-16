package yazlab1proje2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.timer.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    public static List<int[][]> cozumYollari = new ArrayList<>();
    static ArrayList<String> satir = new ArrayList<String>();
    static ArrayList<String> sutun = new ArrayList<String>();
    static ArrayList<String> sayi = new ArrayList<String>();
    static ArrayList<String> satirt10 = new ArrayList<String>();
    static ArrayList<String> sutunt10 = new ArrayList<String>();
    static ArrayList<String> sayit10 = new ArrayList<String>();
    static ArrayList<Double> sure1 = new ArrayList<Double>();
    static ArrayList<Double> sure2 = new ArrayList<Double>();
    static int suretoplam1=0;
    static int suretoplam2=0;
     static int sure1konum=1295;
    static int sure2konum=1345;
// N is the size of the 2D matrix   N*N
    static int N = 9;

    public Main() {

    }
    /* Kısmen doldurulmuş bir grid alır ve dener
    içindeki tüm atanmamış konumlara değer atamak için
    gereksinimleri karşılamak için böyle bir yol
    Sudoku çözümü (satırlar arasında tekrarlamama,
    sütunlar ve kutular) */

    static int adim = 1;

    static boolean solveSuduko1(int grid[][], int row, int col) {
        // print(grid);

        /*8'e ulaştıysak
           satır ve 9. sütun (0
           indekslenmiş matris),
           daha fazla önlemek için true dönüyoruz
           geri izleme */
        long startTime = System.nanoTime();
        if (row == N - 1 && col == N) {
            sudokucoz6 = grid;
            return true;
        }

        // Sütun değerinin 9 olup olmadığını kontrol edin,
        // sonraki satıra geçiyoruz
        // ve sütun 0'dan başlar
        if (col == N) {
            row++;
            col = 0;

        }

        // Geçerli konumun olup olmadığını kontrol edin
        // ızgaranın zaten
        // >0 değerini içeriyor, yineliyoruz
        // sonraki sütun için
        if (grid[row][col] != 0) {
            sudokucoz6 = grid;
            return solveSuduko1(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

            // Yerleştirmenin güvenli olup olmadığını kontrol edin
            // içindeki sayı (1-9)
            // verilen satır ,col ->bir sonraki sütuna geçiyoruz
            if (isSafe1(grid, row, col, num)) {

                /* geçerli sayıyı atama
                (satır, sütun) ızgaranın konumu ve
                pozisyonda atanan numaramızı varsayarak
                doğru */
                grid[row][col] = num;
                sudokucoz6 = grid;
             
                System.out.println(" ");

                if (solveSuduko1(grid, row, col + 1)) {

                    if (solveSuduko3(sudokucoz6, 6, 6)) {
                        print(sudokucoz6);
                        satir.add(Integer.toString(row));
                        sutun.add(Integer.toString(col));
                        sayi.add(Integer.toString(num));
                        long endTime = System.nanoTime();
                        long estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye cinsinden elde ediyoruz
                        double seconds = (double) estimatedTime / 1000000; // saniyeye çevirmek için milyar'a bölüyoruz.
                        sure1.add(seconds);
                        return true;
                    }

                }

            }
            /* atanan sayıyı kaldırıyoruz, çünkü
               varsayım yanlıştı ve bir sonraki adıma geçiyoruz
               diff num değerine sahip varsayım */
            grid[row][col] = 0;

        }

        return false;
    }

    static boolean solveSuduko2(int grid[][], int row, int col) {

        if (row == N - 1 && col == 21) {
            sudokucoz6 = grid;
            return true;
        }

        if (col == 21) {
            row++;
            col = 12;
        }


        if (grid[row][col] != 0) {
            sudokucoz6 = grid;
            return solveSuduko2(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {


            if (isSafe2(grid, row, col, num)) {

                grid[row][col] = num;

                if (solveSuduko2(grid, row, col + 1)) {
                    sudokucoz6 = grid;
                    if (solveSuduko3(sudokucoz6, 6, 6)) {

                        sudokucoz6 = grid;
                        satir.add(Integer.toString(row));
                        sutun.add(Integer.toString(col));
                        sayi.add(Integer.toString(num));

                        return true;
                    }
                }
            }

            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko3(int grid[][], int row, int col) {

        if (row == 15 - 1 && col == 15) {
            sudokucoz6 = grid;
            return true;
        }

      
        if (col == 15) {
            row++;
            col = 6;
        }

    
        if (grid[row][col] != 0) {
            sudokucoz6 = grid;
            return solveSuduko3(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

            if (isSafe3(grid, row, col, num)) {

  
                grid[row][col] = num;

                if (solveSuduko3(grid, row, col + 1)) {
                    sudokucoz6 = grid;
                    if (solveSuduko1(sudokucoz6, 0, 0) && solveSuduko4(sudokucoz6, 12, 0) && solveSuduko5(sudokucoz6, 12, 12) && solveSuduko2(sudokucoz6, 0, 12)) {
                        satir.add(Integer.toString(row));
                        sutun.add(Integer.toString(col));
                        sayi.add(Integer.toString(num));
                        return true;

                    }
                }
            }
       
            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko4(int grid[][], int row, int col) {

        if (row == 21 - 1 && col == N) {
            sudokucoz6 = grid;
            return true;
        }

 
        if (col == N) {
            row++;
            col = 0;
        }


        if (grid[row][col] != 0) {
            sudokucoz6 = grid;
            return solveSuduko4(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

 
            if (isSafe4(grid, row, col, num)) {

                grid[row][col] = num;
                if (solveSuduko4(grid, row, col + 1)) {
                    sudokucoz6 = grid;
                    if (solveSuduko3(sudokucoz6, 6, 6)) {

                        sudokucoz6 = grid;
                        satir.add(Integer.toString(row));
                        sutun.add(Integer.toString(col));
                        sayi.add(Integer.toString(num));
                        return true;
                    }
                }
            }

            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko5(int grid[][], int row, int col) {

    
        if (row == 21 - 1 && col == 21) {
            sudokucoz6 = grid;
            return true;
        }

 
        if (col == 21) {
            row++;
            col = 12;
        }


        if (grid[row][col] != 0) {
            sudokucoz6 = grid;
            return solveSuduko5(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

        
            if (isSafe5(grid, row, col, num)) {

          
                grid[row][col] = num;
        
                if (solveSuduko5(grid, row, col + 1)) {
                    sudokucoz6 = grid;
                    if (solveSuduko3(sudokucoz6, 6, 6) && solveSuduko1(sudokucoz6, 0, 0)) {

                        sudokucoz6 = grid;
                        satir.add(Integer.toString(row));
                        sutun.add(Integer.toString(col));
                        sayi.add(Integer.toString(num));
                        return true;
                    }
                }
            }
         
            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko1t10(int grid[][], int row, int col) {
       

       
        long startTime = System.nanoTime();
        if (row == N - 1 && col == N) {
            sudokucoz6t10 = grid;
            return true;
        }

        if (col == N) {
            row++;
            col = 0;

        }


        if (grid[row][col] != 0) {
            sudokucoz6t10 = grid;
            return solveSuduko1t10(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {


            if (isSafe1t10(grid, row, col, num)) {


                grid[row][col] = num;
                sudokucoz6t10 = grid;

                System.out.println(" ");

                if (solveSuduko1t10(grid, row, col + 1)) {

                    if (solveSuduko3t10(sudokucoz6t10, 6, 6)) {
                        print(sudokucoz6t10);
                        satirt10.add(Integer.toString(row));
                        sutunt10.add(Integer.toString(col));
                        sayit10.add(Integer.toString(num));
                        long endTime = System.nanoTime();
                        long estimatedTime = endTime - startTime; 
                        double seconds = (double) estimatedTime / 1000000; 
                        sure2.add(seconds);

                        return true;
                    }

                }

            }
           
            grid[row][col] = 0;

        }

        return false;
    }

    static boolean solveSuduko2t10(int grid[][], int row, int col) {

      
        if (row == N - 1 && col == 21) {
            sudokucoz6t10 = grid;
            return true;
        }

 
        if (col == 21) {
            row++;
            col = 12;
        }

      
        if (grid[row][col] != 0) {
            sudokucoz6t10 = grid;
            return solveSuduko2t10(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

            if (isSafe2t10(grid, row, col, num)) {

                grid[row][col] = num;

                if (solveSuduko2t10(grid, row, col + 1)) {
                    sudokucoz6t10 = grid;
                    if (solveSuduko3t10(sudokucoz6t10, 6, 6)) {

                        sudokucoz6t10 = grid;
                        satirt10.add(Integer.toString(row));
                        sutunt10.add(Integer.toString(col));
                        sayit10.add(Integer.toString(num));

                        return true;
                    }
                }
            }

            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko3t10(int grid[][], int row, int col) {

   
        if (row == 15 - 1 && col == 15) {
            sudokucoz6t10 = grid;
            return true;
        }

 
        if (col == 15) {
            row++;
            col = 6;
        }


        if (grid[row][col] != 0) {
            sudokucoz6t10 = grid;
            return solveSuduko3t10(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {


            if (isSafe3t10(grid, row, col, num)) {

            
                grid[row][col] = num;

    
                if (solveSuduko3t10(grid, row, col + 1)) {
                    sudokucoz6t10 = grid;
                    if (solveSuduko1t10(sudokucoz6t10, 0, 0) && solveSuduko4t10(sudokucoz6t10, 12, 0) && solveSuduko5t10(sudokucoz6t10, 12, 12) && solveSuduko2t10(sudokucoz6t10, 0, 12)) {
                        satirt10.add(Integer.toString(row));
                        sutunt10.add(Integer.toString(col));
                        sayit10.add(Integer.toString(num));
                        return true;

                    }
                }
            }
           
            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko4t10(int grid[][], int row, int col) {

   
        if (row == 21 - 1 && col == N) {
            sudokucoz6t10 = grid;
            return true;
        }

       
        if (col == N) {
            row++;
            col = 0;
        }

      
        if (grid[row][col] != 0) {
            sudokucoz6t10 = grid;
            return solveSuduko4t10(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

           
            if (isSafe4t10(grid, row, col, num)) {

             
                grid[row][col] = num;
                if (solveSuduko4t10(grid, row, col + 1)) {
                    sudokucoz6t10 = grid;
                    if (solveSuduko3t10(sudokucoz6t10, 6, 6)) {

                        sudokucoz6t10 = grid;
                        satirt10.add(Integer.toString(row));
                        sutunt10.add(Integer.toString(col));
                        sayit10.add(Integer.toString(num));
                        return true;
                    }
                }
            }
           
            grid[row][col] = 0;

        }
        return false;
    }

    static boolean solveSuduko5t10(int grid[][], int row, int col) {

       
        if (row == 21 - 1 && col == 21) {
            sudokucoz6t10 = grid;
            return true;
        }

      
        if (col == 21) {
            row++;
            col = 12;
        }

   
        if (grid[row][col] != 0) {
            sudokucoz6t10 = grid;
            return solveSuduko5t10(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {


            if (isSafe5t10(grid, row, col, num)) {

  
                grid[row][col] = num;
         
                if (solveSuduko5t10(grid, row, col + 1)) {
                    sudokucoz6t10 = grid;
                    if (solveSuduko3t10(sudokucoz6t10, 6, 6) && solveSuduko1t10(sudokucoz6t10, 0, 0)) {

                        sudokucoz6t10 = grid;
                        satirt10.add(Integer.toString(row));
                        sutunt10.add(Integer.toString(col));
                        sayit10.add(Integer.toString(num));
                        return true;
                    }
                }
            }
           
            grid[row][col] = 0;

        }
        return false;
    }

   
    static void print(int[][] grid) {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }


    static boolean isSafe1(int[][] grid, int row, int col, int num) {

        if (col == 6 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }

        }
        if (col == 6 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür

        for (int x = 0; x <= 8; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 0; x <= 8; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe2(int[][] grid, int row, int col,
            int num) {

        if (col == 12 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 13 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }

        if (col == 14 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür
        for (int x = 12; x < 21; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 0; x <= 8; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = (col - col % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe3(int[][] grid, int row, int col,
            int num) {
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür

        if (col == 6 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num) || (x != 6 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }

        }
        if (col == 6 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num) || (x != 7 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num) || (x != 8 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num) || (x != 6 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num) || (x != 7 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num) || (x != 8 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num) || (x != 6 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num) || (x != 7 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num) || (x != 8 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }

        if (col == 13 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[6][x] != 0 && sudokucoz6[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[7][x] != 0 && sudokucoz6[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }

        if (col == 14 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[8][x] != 0 && sudokucoz6[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 8 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }

        if (col == 6 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }

        for (int x = 6; x < 15; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 6; x < 15; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe4(int[][] grid, int row, int col, int num) {

        if (col == 6 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][6] != 0 && sudokucoz6[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][7] != 0 && sudokucoz6[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[x][8] != 0 && sudokucoz6[x][8] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür

        for (int x = 0; x <= 8; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 12; x < 21; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe5(int[][] grid, int row, int col, int num) {

        if (col == 12 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num) || (x != 12 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num) || (x != 13 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num) || (x != 14 && sudokucoz6[x][12] != 0 && sudokucoz6[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 13 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num) || (x != 12 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num) || (x != 13 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num) || (x != 14 && sudokucoz6[x][13] != 0 && sudokucoz6[x][13] == num)) {
                    return false;
                }
            }
        }

        if (col == 14 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[12][x] != 0 && sudokucoz6[12][x] == num) || (x != 12 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num) || (x != 13 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6[14][x] != 0 && sudokucoz6[14][x] == num) || (x != 14 && sudokucoz6[x][14] != 0 && sudokucoz6[x][14] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür
        for (int x = 12; x < 21; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 12; x < 21; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe1t10(int[][] grid, int row, int col, int num) {

        if (col == 6 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }

        }
        if (col == 6 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür

        for (int x = 0; x <= 8; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 0; x <= 8; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe2t10(int[][] grid, int row, int col,
            int num) {

        if (col == 12 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 13 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }

        if (col == 14 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür
        for (int x = 12; x < 21; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 0; x <= 8; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = (col - col % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe3t10(int[][] grid, int row, int col,
            int num) {
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür

        if (col == 6 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num) || (x != 6 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }

        }
        if (col == 6 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num) || (x != 7 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num) || (x != 8 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num) || (x != 6 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num) || (x != 7 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num) || (x != 8 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 6) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num) || (x != 6 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 7) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num) || (x != 7 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 8) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num) || (x != 8 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }

        if (col == 13 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 6) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[6][x] != 0 && sudokucoz6t10[6][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 7) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[7][x] != 0 && sudokucoz6t10[7][x] == num)) {
                    return false;
                }
            }
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }

        if (col == 14 && row == 8) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[8][x] != 0 && sudokucoz6t10[8][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 8 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }

        if (col == 12 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }

        if (col == 6 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }

        for (int x = 6; x < 15; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 6; x < 15; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe4t10(int[][] grid, int row, int col, int num) {

        if (col == 6 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }
        if (col == 6 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 6 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][6] != 0 && sudokucoz6t10[x][6] == num)) {
                    return false;
                }
            }
        }

        if (col == 7 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }
        if (col == 7 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 7 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][7] != 0 && sudokucoz6t10[x][7] == num)) {
                    return false;
                }
            }
        }

        if (col == 8 && row == 12) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 13) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        if (col == 8 && row == 14) {
            for (int x = 0; x < 15; x++) {
                if ((x != 8 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num)) {
                    return false;
                }
            }
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[x][8] != 0 && sudokucoz6t10[x][8] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür

        for (int x = 0; x <= 8; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 12; x < 21; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe5t10(int[][] grid, int row, int col, int num) {

        if (col == 12 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num) || (x != 12 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6[13][x] != 0 && sudokucoz6[13][x] == num) || (x != 13 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }
        if (col == 12 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 12 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num) || (x != 14 && sudokucoz6t10[x][12] != 0 && sudokucoz6t10[x][12] == num)) {
                    return false;
                }
            }
        }

        if (col == 13 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num) || (x != 12 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num) || (x != 13 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }
        if (col == 13 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 13 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num) || (x != 14 && sudokucoz6t10[x][13] != 0 && sudokucoz6t10[x][13] == num)) {
                    return false;
                }
            }
        }

        if (col == 14 && row == 12) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[12][x] != 0 && sudokucoz6t10[12][x] == num) || (x != 12 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 13) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[13][x] != 0 && sudokucoz6t10[13][x] == num) || (x != 13 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        if (col == 14 && row == 14) {
            for (int x = 6; x < 21; x++) {
                if ((x != 14 && sudokucoz6t10[14][x] != 0 && sudokucoz6t10[14][x] == num) || (x != 14 && sudokucoz6t10[x][14] != 0 && sudokucoz6t10[x][14] == num)) {
                    return false;
                }
            }
        }
        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer satırda, biz
        // yanlış döndür
        for (int x = 12; x < 21; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // benzer sütunda,
        // false döndürürüz
        for (int x = 12; x < 21; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Aynı sayıyı bulup bulmadığımızı kontrol et
        // özellikle 3*3
        // matris, false döndürürüz
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }
    static int[][] sudoku1 = new int[9][9];
    static int[][] sudoku2 = new int[9][9];
    static int[][] sudoku3 = new int[9][9];
    static int[][] sudoku4 = new int[9][9];
    static int[][] sudoku5 = new int[9][9];
    static int[][] sudoku6 = new int[21][21];
    static int[][] sudoku6kopya = new int[21][21];
    static int[][] sudokucoz6 = new int[21][21];
    static int[][] sudoku6t10 = new int[21][21];
    static int[][] sudokucoz6t10 = new int[21][21];
static String[] sureDegerleri = {"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000","100000"};
 static int[] surekonumlari = {895, 945, 995, 1045,1095, 1145, 1195, 1245, 1295, 1345};
    public static void Threads() throws IOException, InterruptedException {
        FileReader inputStream = null;
        int[] dizi = new int[441];

        ArrayList<Integer> sayilar = new ArrayList<Integer>();
        int i = 0;
        try {
            inputStream = new FileReader("C:\\Users\\Oktay\\Documents\\NetBeansProjects\\YazLab1Proje2\\src\\yazlab1proje2\\SAMURAISUDOKU.txt");
            int con = 0;
            sayilar.clear();
            while ((con = inputStream.read()) != -1) {
                switch (con) {
                    case '1':
                        dizi[i] = 1;
                        break;
                    case '2':
                        dizi[i] = 2;
                        break;
                    case '3':
                        dizi[i] = 3;
                        break;
                    case '4':
                        dizi[i] = 4;
                        break;
                    case '5':
                        dizi[i] = 5;
                        break;
                    case '6':
                        dizi[i] = 6;
                        break;
                    case '7':
                        dizi[i] = 7;
                        break;
                    case '8':
                        dizi[i] = 8;
                        break;
                    case '9':
                        dizi[i] = 9;
                        break;
                    default:
                        dizi[i] = 0;
                        break;
                }
                i++;
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

        }
        for (int j = 0; j < dizi.length; j++) {
            //  System.out.print(dizi[j]);
        }
        System.out.println(" ");
        int satiratla = 0;
        for (int k = 0; k < 9; k++) {

            for (int l = 0; l < 9; l++) {
                sudoku1[k][l] = dizi[satiratla];
                sudoku6[k][l] = dizi[satiratla];
                sudoku6kopya[k][l] = dizi[satiratla];
                sudokucoz6[k][l] = dizi[satiratla];
                sudoku6t10[k][l] = dizi[satiratla];
                sudokucoz6t10[k][l] = dizi[satiratla];
                satiratla++;
            }
            if (k > 5) {
                satiratla = satiratla + 14;
            } else {
                satiratla = satiratla + 11;
            }
        }
        int satiratla2 = 9;
        for (int k = 0; k < 9; k++) {

            for (int l = 0; l < 9; l++) {
                sudoku2[k][l] = dizi[satiratla2];
                sudoku6[k][l + 12] = dizi[satiratla2];
                sudoku6kopya[k][l + 12] = dizi[satiratla2];
                sudokucoz6[k][l + 12] = dizi[satiratla2];
                sudokucoz6t10[k][l + 12] = dizi[satiratla2];
                sudoku6t10[k][l + 12] = dizi[satiratla2];
                satiratla2++;
            }
            if (k > 4) {
                satiratla2 = satiratla2 + 14;
            } else {
                satiratla2 = satiratla2 + 11;
            }
        }

        int satiratla3 = 126;
        for (int k = 0; k < 9; k++) {

            for (int l = 0; l < 9; l++) {
                sudoku3[k][l] = dizi[satiratla3];
                sudoku6[k + 6][l + 6] = dizi[satiratla3];
                sudoku6kopya[k + 6][l + 6] = dizi[satiratla3];
                sudokucoz6[k + 6][l + 6] = dizi[satiratla3];
                sudokucoz6t10[k + 6][l + 6] = dizi[satiratla3];
                sudoku6t10[k + 6][l + 6] = dizi[satiratla3];
                satiratla3++;
            }
            if (k < 2 || k > 5) {
                satiratla3 = satiratla3 + 14;
            } else if (k == 2 || k == 5) {
                satiratla3 = satiratla3 + 8;
            } else {
                satiratla3 = satiratla3 + 2;
            }

        }
        int satiratla4 = 222;
        for (int k = 0; k < 9; k++) {

            for (int l = 0; l < 9; l++) {
                sudoku4[k][l] = dizi[satiratla4];
                sudoku6[k + 12][l] = dizi[satiratla4];
                sudoku6kopya[k + 12][l] = dizi[satiratla4];
                sudokucoz6[k + 12][l] = dizi[satiratla4];
                sudokucoz6t10[k + 12][l] = dizi[satiratla4];
                sudoku6t10[k + 12][l] = dizi[satiratla4];
                satiratla4++;
            }
            if (k < 3) {
                satiratla4 = satiratla4 + 14;
            } else {
                satiratla4 = satiratla4 + 11;
            }
        }
        int satiratla5 = 234;
        for (int k = 0; k < 9; k++) {

            for (int l = 0; l < 9; l++) {
                sudoku5[k][l] = dizi[satiratla5];
                sudoku6[k + 12][l + 12] = dizi[satiratla5];
                sudoku6kopya[k + 12][l + 12] = dizi[satiratla5];
                sudokucoz6[k + 12][l + 12] = dizi[satiratla5];
                sudokucoz6t10[k + 12][l + 12] = dizi[satiratla5];
                sudoku6t10[k + 12][l + 12] = dizi[satiratla5];
                satiratla5++;
            }
            if (k < 2) {
                satiratla5 = satiratla5 + 14;
            } else {
                satiratla5 = satiratla5 + 11;
            }
        }
        MyThread myThread = new MyThread(sudoku6, "sudoku1", "thread1");
        MyThread myThread2 = new MyThread(sudoku6, "sudoku2", "thread2");
        MyThread myThread3 = new MyThread(sudoku6, "sudoku3", "thread3");
        MyThread myThread4 = new MyThread(sudoku6, "sudoku4", "thread4");
        MyThread myThread5 = new MyThread(sudoku6, "sudoku5", "thread5");

        myThread.start();
        myThread.sleep(1000l);
        myThread2.start();
        myThread2.sleep(1000l);
        myThread3.start();
        myThread3.sleep(1000l);
        myThread4.start();
        myThread4.sleep(1000l);
        myThread5.start();
        myThread5.sleep(1000l);

        MyThread2 mythreadt10 = new MyThread2(sudoku6, "sudoku1", "thread1");
        MyThread2 mythread2t10 = new MyThread2(sudoku6, "sudoku2", "thread2");
        MyThread2 mythread3t10 = new MyThread2(sudoku6, "sudoku3", "thread3");
        MyThread2 mythread4t10 = new MyThread2(sudoku6, "sudoku4", "thread4");
        MyThread2 mythread5t10 = new MyThread2(sudoku6, "sudoku5", "thread5");
        MyThread2 mythread6t10 = new MyThread2(sudoku6, "sudoku6", "thread6");
        MyThread2 mythread7t10 = new MyThread2(sudoku6, "sudoku7", "thread7");
        MyThread2 mythread8t10 = new MyThread2(sudoku6, "sudoku8", "thread8");
        MyThread2 mythread9t10 = new MyThread2(sudoku6, "sudoku9", "thread9");
        MyThread2 mythread10t10 = new MyThread2(sudoku6, "sudoku10", "thread10");

        mythreadt10.start();
        mythreadt10.sleep(1000l);
        mythread2t10.start();
        mythread2t10.sleep(1000l);
        mythread3t10.start();
        mythread3t10.sleep(1000l);
        mythread4t10.start();
        mythread4t10.sleep(1000l);
        mythread5t10.start();
        mythread5t10.sleep(1000l);
        mythread6t10.start();
        mythread6t10.sleep(1000l);
        mythread7t10.start();
        mythread7t10.sleep(1000l);
        mythread8t10.start();
        mythread8t10.sleep(1000l);
        mythread9t10.start();
        mythread9t10.sleep(1000l);
        mythread10t10.start();
        mythread10t10.sleep(1000l);
        
      for(int k=0;k<sure1.size();k++)
        {
            suretoplam1+=sure1.get(k);
        }
        for(int k=0;k<sure2.size();k++)
        {
            suretoplam2+=sure2.get(k);
        }
        System.out.println("5 threadli cozumun suresi"+suretoplam1);
        System.out.println("10 threadli cozumun suresi"+suretoplam2);
       
        for( i=0;i<11;i++)
       {
           if(i==10){
                if(suretoplam1>suretoplam2){
           if(suretoplam1>Double.valueOf(sureDegerleri[9]))
           {
              
                  
                       sure1konum=surekonumlari[9]+35;
                   
                
           }
           if(suretoplam2>Double.valueOf(sureDegerleri[9]))
           {
              
                  
                       sure2konum=surekonumlari[9]+25;
                   
                
           }
       }
       else
       {
               if(suretoplam1>Double.valueOf(sureDegerleri[9]))
           {
             
                       sure1konum=surekonumlari[9]+25;
                   
                
           }
           
           if(suretoplam2>Double.valueOf(sureDegerleri[9]))
           {
               
                   
                       sure2konum=surekonumlari[9]+35;
                   
                
           }
       }
           }
           else{
               if(suretoplam1>suretoplam2){
           if(suretoplam1>Double.valueOf(sureDegerleri[i]))
           {
               for(int j=i;j<10;j++)
               {
                   if(suretoplam1<Double.valueOf(sureDegerleri[j]))
                   {
                       sure1konum=surekonumlari[i]+35;
                   }
                }
           }
           if(suretoplam2>Double.valueOf(sureDegerleri[i]))
           {
               for(int j=i;j<10;j++)
               {
                   if(suretoplam2<Double.valueOf(sureDegerleri[j]))
                   {
                       sure2konum=surekonumlari[i]+25;
                   }
                }
           }
       }
       else
       {
               if(suretoplam1>Double.valueOf(sureDegerleri[i]))
           {
               for(int j=i;j<10;j++)
               {
                   if(suretoplam1<Double.valueOf(sureDegerleri[j]))
                   {
                       sure1konum=surekonumlari[i]+25;
                   }
                }
           }
           
           if(suretoplam2>Double.valueOf(sureDegerleri[i]))
           {
               for(int j=i;j<10;j++)
               {
                   if(suretoplam2<Double.valueOf(sureDegerleri[j]))
                   {
                       sure2konum=surekonumlari[i]+35;
                   }
                }
           }
       } 
           }
          
       }
        
    
        
     

        // mythread10.sleep(1000l);
        // solveSuduko1(sudokucoz6, 0, 0);
        //  solveSuduko2(sudokucoz6, 0,12);
        // solveSuduko3(sudokucoz6, 6, 6);
        //solveSuduko4(sudokucoz6, 12, 0);
        //solveSuduko5(sudokucoz6, 12, 12);
        //print(sudokucoz6);
        sonucuDosyayaYaz();
        sonucuDosyayaYazt10();
    }

    public void paintComponent(Graphics g) {

        g.setFont(new Font("default", Font.BOLD, 15));
        super.paintComponent(g);
        this.setBackground(Color.white);

        g.setColor(Color.blue);
        for (int i = 0; i <= 630; i += 90)// mavi dikey çizgiler
        {
            g.drawLine(i, 0, i, 270);
            g.drawLine(i, 360, i, 630);
        }

        for (int i = 30; i < 630; i += 30)// gri  dkey çizgiler
        {
            g.setColor(Color.gray);
            if (i != 300 && i != 330 && i % 90 != 0) {

                g.drawLine(i, 0, i, 270);
                g.drawLine(i, 360, i, 630);
            }
            if (i > 180 && i < 450 && i % 90 != 0) {
                g.drawLine(i, 180, i, 450);
            }

        }
        g.setColor(Color.blue);
        g.drawLine(180, 270, 180, 360);
        g.drawLine(450, 270, 450, 360);
        g.drawLine(270, 270, 270, 360);
        g.drawLine(360, 270, 360, 360);
        g.drawLine(270, 181, 360, 181);
        g.drawLine(270, 451, 360, 451);
        g.drawLine(270, 271, 360, 271);
        g.drawLine(270, 361, 360, 361);

        for (int i = 1; i <= 631; i += 90)// mavi yatay çizgiler
        {

            int j = 1;
            int h = 271;
            g.drawLine(j, i, h, i);
            g.drawLine(361, i, 631, i);

        }

        for (int i = 30; i < 630; i += 30)// gri yatay çizgiler
        {
            g.setColor(Color.gray);
            if (i != 300 && i != 330 && i % 90 != 0) {
                int j = 0;
                int h = 270;
                g.drawLine(j, i, h, i);
                g.drawLine(360, i, 630, i);
            }
            if (i > 180 && i < 450 && i % 90 != 0) {
                g.drawLine(180, i, 450, i);
            }

        }
        g.setColor(Color.black);
        g.drawString("Kare Sayısı", 755, 90);
        g.drawLine(800, 100, 800, 600);
        g.drawLine(800, 600, 1400, 600);
        //grafikte y ekseni ölçekleri

        g.drawLine(785, 150, 815, 150);
        g.drawString("369", 755, 155);
        g.drawLine(785, 210, 815, 210);
        g.drawString("300", 755, 215);
        g.drawLine(785, 270, 815, 270);
        g.drawString("250", 755, 275);
        g.drawLine(785, 330, 815, 330);
        g.drawString("200", 755, 335);
        g.drawLine(785, 390, 815, 390);
        g.drawString("150", 755, 395);
        g.drawLine(785, 450, 815, 450);
        g.drawString("100", 755, 455);
        g.drawLine(785, 510, 815, 510);
        g.drawString("50", 755, 515);
       
        //grafikte x ekseni ölçekleri
        
        g.drawString("Süre", 1420, 600);
       
       
       // g.drawLine(1100, 585, 1100, 615);
       // g.drawString(sureDegerleri[0], 1095, 635);
     
        //g.drawLine(1350, 585, 1350, 615);
        //g.drawString(sureDegerleri[1], 1345, 635);
        //grafikte x ekseni ölçekleri
      
         g.drawString("Süre",1420, 600);
        g.drawLine(900, 585, 900, 615);
        g.drawString(sureDegerleri[0],surekonumlari[0], 635);
        g.drawLine(950, 585, 950, 615);
          g.drawString(sureDegerleri[1],surekonumlari[1], 635);
        g.drawLine(1000, 585, 1000, 615);
          g.drawString(sureDegerleri[2],surekonumlari[2], 635);
        g.drawLine(1050, 585, 1050, 615);
          g.drawString(sureDegerleri[3],surekonumlari[3], 635);
        g.drawLine(1100, 585, 1100, 615);
          g.drawString(sureDegerleri[4],surekonumlari[4], 635);
        g.drawLine(1150, 585, 1150, 615);
          g.drawString(sureDegerleri[5],surekonumlari[5], 635);
        g.drawLine(1200, 585, 1200, 615);
          g.drawString(sureDegerleri[6],surekonumlari[6], 635);
        g.drawLine(1250, 585, 1250, 615);
          g.drawString(sureDegerleri[7],surekonumlari[7], 635);
        g.drawLine(1300, 585, 1300, 615);
          g.drawString(sureDegerleri[8],surekonumlari[8], 635);
        g.drawLine(1350, 585, 1350, 615);
          g.drawString(sureDegerleri[9],surekonumlari[9], 635);
          g.setColor(Color.blue);
         
        g.drawLine(sure1konum, 585, sure1konum, 615);
          g.drawString(Double.toString(suretoplam1),sure1konum, 660);
         g.drawLine(800,600, sure1konum,150);
         
        g.setColor(Color.red);
        g.drawLine(sure2konum, 585, sure2konum, 615);
          g.drawString(Double.toString(suretoplam2),sure2konum, 680);
         g.drawLine(800,600, sure2konum,150);
        /*try {
            Threads();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        int i = 0;
        int x = 0, y = 0;
        for (i = 15; i < 270; i += 30) {
            for (int j = 15; j < 270; j += 30) {
                String yaz = Integer.toString(sudoku6kopya[x][y]);
                if (yaz.contains("0")) {

                    g.setColor(Color.DARK_GRAY);
                    g.drawString(Integer.toString(sudokucoz6[x][y]), j, i);

                    y++;
                    continue;

                } else {
                    g.setColor(Color.red);
                    g.drawString(yaz, j, i);

                    y++;
                }

            }
            y = 0;
            x++;

        }
        y = 0;
        x = 0;
        int x2 = 0, y2 = 12;
        for (i = 15; i < 270; i += 30) {
            for (int j = 375; j < 645; j += 30) {
                String yaz2 = Integer.toString(sudoku6kopya[x2][y2]);

                if (yaz2.contains("0")) {

                    g.setColor(Color.DARK_GRAY);
                    g.drawString(Integer.toString(sudokucoz6[x2][y2]), j, i);

                    y2++;

                    continue;

                } else {
                    g.setColor(Color.red);
                    g.drawString(yaz2, j, i);
                    y2++;

                }

            }
            y2 = 12;
            x2++;

        }
        y2 = 0;
        x2 = 0;
        int x3 = 6, y3 = 6;

        for (i = 195; i < 465; i += 30) {
            for (int j = 195; j < 465; j += 30) {
                String yaz3 = Integer.toString(sudoku6kopya[x3][y3]);
                if (yaz3.contains("0")) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawString(Integer.toString(sudokucoz6[x3][y3]), j, i);
                    y3++;
                } else {
                    g.setColor(Color.red);
                    g.drawString(yaz3, j, i);

                    y3++;
                }
            }
            y3 = 6;
            x3++;

        }
        x3 = 0;
        y3 = 0;
        int x4 = 12, y4 = 0;
        for (i = 375; i < 645; i += 30) {
            for (int j = 15; j < 270; j += 30) {
                String yaz4 = Integer.toString(sudoku6kopya[x4][y4]);

                if (yaz4.contains("0")) {

                    g.setColor(Color.DARK_GRAY);
                    g.drawString(Integer.toString(sudokucoz6[x4][y4]), j, i);

                    y4++;

                    continue;

                } else {
                    g.setColor(Color.red);
                    g.drawString(yaz4, j, i);
                    y4++;

                }

            }
            y4 = 0;
            x4++;

        }
        y4 = 0;
        x4 = 0;
        int x5 = 12, y5 = 12;
        for (i = 375; i < 645; i += 30) {
            for (int j = 375; j < 645; j += 30) {
                String yaz5 = Integer.toString(sudoku6kopya[x5][y5]);

                if (yaz5.contains("0")) {

                    g.setColor(Color.DARK_GRAY);
                    g.drawString(Integer.toString(sudokucoz6[x5][y5]), j, i);
                    y5++;

                    continue;

                } else {
                    g.setColor(Color.red);

                    g.drawString(yaz5, j, i);
                    y5++;

                }

            }
            y5 = 12;
            x5++;

        }
        x5 = 0;
        y5 = 0;

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        sonucuDosyayaYaz();
        sonucuDosyayaYazt10();
        //solveSuduko1(sudokucoz6, 0, 0);
        //solveSuduko2(sudokucoz6, 0,12);
        //solveSuduko3(sudokucoz6, 6, 6);
        //solveSuduko4(sudokucoz6, 12, 0);
        //solveSuduko5(sudokucoz6, 12, 12); 
        Threads();

        Main bs = new Main();
        bs.setBackground(Color.white);

        JFrame jf = new JFrame("Java grafik kütüphanesi");
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(300, 300);
        jf.setVisible(true);
        jf.add(bs);
        System.out.println("");

    }

    static void sonucuDosyayaYaz() throws IOException, InterruptedException {
        Path path = Paths.get("C:\\Users\\Oktay\\Documents\\NetBeansProjects\\YazLab1Proje2\\src\\yazlab1proje2\\Thread5.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            int i, j;
            for (i = 0; i < satir.size(); i++) {
                writer.write(satir.get(i) + ".satır " + sutun.get(i) + ".sutun " + sayi.get(i) + " sayısı atandı.");

                writer.write(" -----------------------\n");
            }

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sonucuDosyayaYazt10() throws IOException, InterruptedException {
        Path path = Paths.get("C:\\Users\\Oktay\\Documents\\NetBeansProjects\\YazLab1Proje2\\src\\yazlab1proje2\\Thread10.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            int i, j;

            for (i = 0; i < satirt10.size(); i++) {
                writer.write(satirt10.get(i) + ".satır " + sutunt10.get(i) + ".sutun " + sayit10.get(i) + " sayısı atandı.");

                writer.write(" -----------------------\n");
            }

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
