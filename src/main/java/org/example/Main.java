package org.example;
import java.util.*;

class FibNumb{
    int ordNum;
    int num;
    FibNumb(){
        ordNum = 0;
        num = 0;
    }
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
        int N, f1 = 1, f2 = 1, sum = 0, tmp;
        Scanner reader = new Scanner(System.in);
        System.out.print("Введіть кількість чисел Фібоначчі: ");
        N = reader.nextInt();
        System.out.println("Введене число: "+ N);
        FibNumb[] fib = new FibNumb[N];
        for (int i = 0; i < N; i++) {
            fib[i] = new FibNumb();
        }
        for(int i = 0; i < fib.length; i++){
            fib[i].ordNum = i + 1;
            tmp = f1 + f2;
            fib[i].num = f1;
            f1 = f2;
            f2 = tmp;
        }

        for(int i = 0; i < fib.length; i++){
            System.out.println(fib[i].getNum());
        }

        for(int i = 0; i < fib.length; i++){
            sum += fib[i].getNum();
        }
        System.out.println("Сума перших "+ N + " чисел Фібоначчі: " + sum);

    }
}