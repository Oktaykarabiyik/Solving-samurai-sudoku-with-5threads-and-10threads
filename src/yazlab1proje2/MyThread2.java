package yazlab1proje2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Oktay
 */
public class MyThread2 extends Thread {

    int[][] array;
    Main main = new Main();
    String hangisudoku;
    String isim;

    public MyThread2(int[][] array, String hangisudoku, String isim) {
        super();
        this.array = array;
        this.hangisudoku = hangisudoku;
        this.isim = isim;
    }

    public void run() {
         long startTime = System.nanoTime(); 
        System.out.println(isim + "threadi başladı");
        try {
                   Thread.sleep(1000l);
          
               if(hangisudoku.contains("sudoku6")){
            if (main.solveSuduko1t10(main.sudoku6t10, 4, 4))
                Thread.sleep(1000l);
            main.print(this.array);
               }
               else if(hangisudoku.contains("sudoku7")){
            if (main.solveSuduko2t10(main.sudoku6t10, 4, 16))
                Thread.sleep(1000l);
            main.print(this.array);
               }
          
               else if(hangisudoku.contains("sudoku8")){
            if (main.solveSuduko3t10(main.sudoku6t10, 10, 10))
                Thread.sleep(1000l);
            main.print(this.array);
               }
          
               else if(hangisudoku.contains("sudoku9")){
            if ( main.solveSuduko4t10(main.sudoku6t10, 16,4 ))
                Thread.sleep(1000l);
            main.print(this.array);
               }
              else   if(hangisudoku.contains("sudoku10")){
            if (main.solveSuduko5t10(main.sudoku6t10, 16, 16))
                Thread.sleep(1000l);
            main.print(this.array);
 
               }
                else   if(hangisudoku.contains("sudoku1")){
            if (main.solveSuduko1t10(main.sudoku6t10, 0, 0))
                Thread.sleep(1000l);
            main.print(this.array);
               }
                else   if(hangisudoku.contains("sudoku2")){
            if (main.solveSuduko2t10(main.sudoku6t10, 0, 12))
                Thread.sleep(1000l);
            main.print(this.array);
               }
                else   if(hangisudoku.contains("sudoku3")){
            if (main.solveSuduko3t10(main.sudoku6t10, 6, 6))
                Thread.sleep(1000l);
            main.print(this.array);
               }
                else   if(hangisudoku.contains("sudoku4")){
            if (main.solveSuduko4t10(main.sudoku6t10, 12, 0))
                Thread.sleep(1000l);
            main.print(this.array);
               }
                else   if(hangisudoku.contains("sudoku5")){
            if (main.solveSuduko5t10(main.sudoku6t10, 12, 12))
                Thread.sleep(1000l);
            main.print(this.array);
               }
            System.out.println("10 threadli");
      main.print(main.sudokucoz6t10);
        } catch (Exception e) {
            System.out.println(getName() + " is interrupted");
        }
         long endTime = System.nanoTime(); 
 long estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye cinsinden elde ediyoruz
 double seconds = (double)estimatedTime/1000000; // saniyeye çevirmek için milyar'a bölüyoruz.
        System.out.println(seconds+"milisaniyede cozdu");   
    }
    

}