package org.example;
import java.util.*;

class FibNumb{
    public int ordNum;
    public int num;

    FibNumb(){
        ordNum = 0;
        num = 0;
    }
    /*FibNumb(int oN, int n){
        oN = ordNum;
        n = num;
    }
    public int getOrdNum(){
        return ordNum;
    }*/
    public int getNum(){
        return num;
    }
}

class Main{
    public static void main(String[] args) {
        int N, f1 = 1, f2 = 1, tmp;
        Scanner reader = new Scanner(System.in);
        System.out.print("Введіть кількість чисел Фібоначчі: ");
        N = reader.nextInt();
        System.out.println("Введене число: "+ N);
        int newN = N+2;
        FibNumb[] fib = new FibNumb[newN];
        for (int i = 0; i < newN; i++) {
            fib[i] = new FibNumb();
        }
        for(int i = 0; i < fib.length; i++){
            fib[i].ordNum = i + 1;
            tmp = f1 + f2;
            fib[i].num = f1;
            f1 = f2;
            f2 = tmp;
        }
        //Вивід самого ряду Фібоначчі
        /*for(int i = 0; i < N; i++){
            System.out.println(fib[i].getNum());
        }*/
        int sum1 = 0, sum2 = 0;

        //Сума N чисел ряду за формулою: S(N) = Num(N+2) - 1
        sum1 = fib[newN - 1].getNum() - 1;
        System.out.println("Сума "+ N + " чисел ряду за формулою: S(N) = Num(N+2) - 1 = " + sum1);

        //Сума N чисел ряду за допомогою додавання
        for(int i = 0; i < N; i++){
            sum2 += fib[i].getNum();
        }
        System.out.println("Сума "+ N + " чисел ряду за допомогою додавання: " + sum2);

        if(sum1 == sum2) {
            System.out.println("Перевірка: " + sum1 + " = " + sum2);
            System.out.println("Сума перших " + N + " чисел Фібоначчі: " + sum1);
        }
        else {
            System.out.println("Сума, обчислена за формулою є неправильною");
            System.out.println("Сума перших " + N + " чисел Фібоначчі: " + sum2);
        }
         /*for (int i = 1; i < N; i++) {
            tmp = f1 + f2; // Наступне число Фібоначчі
            sum += f2; // Додаємо число до суми
            f1 = f2; // Зміщуємо значення вперед
            f2 = tmp;  // Зміщуємо значення вперед
        }*/
    }
}