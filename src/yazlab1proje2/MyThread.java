package yazlab1proje2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Oktay
 */
public class MyThread extends Thread {

    int[][] array;
    Main main = new Main();
    String hangisudoku;
    String isim;

    public MyThread(int[][] array, String hangisudoku, String isim) {
        super();
        this.array = array;
        this.hangisudoku = hangisudoku;
        this.isim = isim;
    }

    public void run() {
 /*long startTime = System.nanoTime(); */
 

        System.out.println(isim + "threadi başladı");
        try {

          
               if(hangisudoku.contains("sudoku1")){
            if (main.solveSuduko1(main.sudoku6, 0, 0))
            main.print(this.array);
               }
               else if(hangisudoku.contains("sudoku2")){
            if (main.solveSuduko2(main.sudoku6, 0, 12))
            main.print(this.array);
               }
          
               else if(hangisudoku.contains("sudoku3")){
            if (main.solveSuduko3(main.sudoku6, 6, 6))
            main.print(this.array);
               }
          
               else if(hangisudoku.contains("sudoku4")){
            if ( main.solveSuduko4(main.sudoku6, 12,0 ))
            main.print(this.array);
               }
              else   if(hangisudoku.contains("sudoku5")){
            if (main.solveSuduko5(main.sudoku6, 12, 12))
            main.print(this.array);
               }
               System.out.println("5 threadli");
      main.print(main.sudokucoz6);
        } catch (Exception e) {
            System.out.println(getName() + " is interrupted");
        }
    /* long endTime = System.nanoTime(); 
 long estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye cinsinden elde ediyoruz
 double seconds = (double)estimatedTime/1000000; // saniyeye çevirmek için milyar'a bölüyoruz.
        System.out.println(seconds+"milisaniyede cozdu");*/
    }
    

}