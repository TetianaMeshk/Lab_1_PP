package org.example;
import java.util.*;

class FibNumb{
    int ordNum;
    int num;
    FibNumb(int oN, int n){
        oN = ordNum;
        n = num;
    }
    public int getOrdNum(){
        return ordNum;
    }
    public int getNum(){
        return num;
    }
}

class Main{
    public static void main(String[] args) {
        int N = 0, f1 = 1, f2 = 0, sum = 0;
        if (args.length > 0)
            System.out.println("Кількість чисел Фібоначчі: " + args[0]);
        else {
            Scanner reader = new Scanner(System.in);
            System.out.print("Введіть кількість чисел Фібоначчі: ");
            N = reader.nextInt();
        }
        FibNumb[] fib = new FibNumb[N];
        for(int i = 0; i < fib.length; i++){
            fib[i].ordNum = ++i;
            fib[i].num = f1;
            sum += f1;
            i++;

        }
    }
}