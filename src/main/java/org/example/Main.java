package org.example;
import java.util.Scanner;

/**
 * Клас для зберігання даних про число Фібоначчі
  */
class FibNum {
    /**
     *  Номер числа Фібоначчі
     */
    public int ordNum;
    /**
     * Значення числа Фібоначчі
     */
    public long num;

    /**
     * Конструктор ініціалізації
     * @param ordNum номер числа
     * @param num значення числа
     */
    public FibNum(int ordNum, long num) {
        this.ordNum = ordNum;
        this.num = num;
    }

    /**
     * метод для отримання номера числа
     * @return номер числа
     */
    public int getOrdNum() {
        return ordNum;
    }

    /**
     * метод для значення числа
     * @return значення числа
     */
    public long getNum() {
        return num;
    }

    /*public void setNum(long num) {
        this.num = num;
    }*/
}

/**
 * Клас для виконання основних обрахунків сум
 */
class Main {

    /**
     * Метод для обчислення числа Фібоначчі за номером
     * @param n номер елемента масиву об'єктів класу
     * @return значення числа Фібоначчі
     */
    public static long calcFib(int n) {
        if (n == 0) return 1;
        long f1 = 1, f2 = 1;
        for (int i = 1; i <= n; i++) {
            long tmp = f1 + f2;
            f1 = f2;
            f2 = tmp;
        }
        return f1;
    }

    /**
     * Метод для обчислення суми перших N чисел Фібоначчі за формулою
     * @param fib масив о'єктів класу
     * @param N номер елемента масиву
     * @return суму, обчислену за формулою
     */
    public static long calcFibSum(FibNum[] fib, int N) {
        long sum;
        sum = fib[N].getNum()-1;
        return sum;
    }

    /**
     * Метод для обчислення суми перших N чисел Фібоначчі
     * @param N кількість N чисел Фібоначчі
     * @param fib масив о'єктів класу
     * @return суму, обчислену додаванням чисел Фібоначчі
     */
    public static long calcFibSum(int N, FibNum[] fib) {
        long sum = 0;
        for (int i=0; i<N; i++) {
            sum += fib[i].getNum();
        }
        return sum;
    }

    /**
     * функція main в якій виконується програма
     * @param args args масив типу string
     */
    public static void main(String[] args) {
        // Введення кількості чисел Фібоначчі
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість чисел Фібоначчі (N): ");
        int N = scanner.nextInt();

        // Створення масиву об'єктів для зберігання чисел Фібоначчі
        int newN = N+2;
        FibNum[] fib = new FibNum[newN];

        // Обчислення чисел Фібоначчі та заповнення масиву
        for (int i = 0; i < newN; i++) {
            long num = calcFib(i);
            fib[i] = new FibNum(i, num);
        }

        // Виведення всіх чисел Фібоначчі
        /*System.out.println("Числа Фібоначчі:");
        for (FibNum number : fib) {
            System.out.println("F(" + number.getOrdNum() + ") = " + number.getNum());
        }*/

        // Обчислення та виведення суми
        long sum1 = calcFibSum(N, fib);
        //Сума N чисел ряду за формулою: S(N) = Num(N+2) - 1
        long sum2 = calcFibSum(fib, newN-1);
        System.out.println("Сума "+ N + " чисел ряду за формулою: S(N) = Num(N+2) - 1");
        System.out.println("S(N) = Num(" + N + "+2) - 1");
        System.out.println("S(N) = Num(" + newN + ") - 1");
        System.out.println("S(N) = " + fib[N+1].getNum() + " - 1 = " + sum2);

        if(sum1 == sum2) {
            System.out.println("Перевірка: " + sum2 + " = " + sum1);
            System.out.println("Сума перших " + N + " чисел Фібоначчі: " + sum2);
        }
        else {
            System.out.println("Сума, обчислена за формулою є неправильною");
            System.out.println("Сума перших " + N + " чисел Фібоначчі: " + sum1);
        }
    }
}
