package org.example.main;
import org.example.customer.Customer;
import java.util.Scanner;

public class Main {
    /**
     * Метод для створення масиву об'єктів
     * @param n кількість елементів масиву
     * @return arr - масив об'єктів класу Customer
     */
    public static Customer[] createArr(int n) {
        Customer[] arr = new Customer[n];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("\nВведіть інформацію для клієнта №" + (i + 1));

            int id;
            while (true){
                System.out.print("ID: ");
                if(sc.hasNextInt()){
                    id = sc.nextInt();
                    if(id > 0)
                        break;
                    else{
                        System.out.println("Помилка: id має бути ненульовим і додатним");
                    }
                }
                else{
                    System.out.println("Помилка: введіть ціле число");
                    sc.next(); // Очищуємо буфер
                }
            }

            sc.nextLine(); // Очищуємо буфер після введення числа

            String lastName;
            while (true){
                System.out.print("Прізвище: ");
                lastName = sc.nextLine();
                if(lastName.matches("[a-zA-Zа-яА-ЯіІїЇєЄЙй']+")){
                    break;
                }
                else{
                    System.out.println("Помилка: прізвище повинне містити тільки букви");
                }
            }

            String firstName;
            while (true){
                System.out.print("Ім'я: ");
                firstName = sc.nextLine();
                if(firstName.matches("[a-zA-Zа-яА-ЯіІїЇєЄЙй']+")){
                    break;
                }
                else{
                    System.out.println("Помилка: ім'я повинне містити тільки букви");
                }
            }

            String middleName;
            while (true){
                System.out.print("По-батькові: ");
                middleName = sc.nextLine();
                if(middleName.matches("[a-zA-Zа-яА-ЯіІїЇєЄЙй']+")){
                    break;
                }
                else{
                    System.out.println("Помилка: по-батькові повинне містити тільки букви");
                }
            }

            System.out.print("Адреса: ");
            String address = sc.nextLine();

            long cardNum;
            while (true){
                System.out.print("Номер картки: ");
                if(sc.hasNextLong()){
                    cardNum = sc.nextLong();
                    if(cardNum > 0){
                        break;
                    }
                    else{
                        System.out.println("Помилка: номер картки має бути додатним");
                    }
                }
                else{
                    System.out.println("Помилка: введіть ціле число");
                    sc.next(); // Очищуємо буфер
                }
            }

            sc.nextLine(); // Очищуємо буфер після введення числа

            double cardBalance;
            while (true){
                System.out.print("Баланс картки: ");
                if(sc.hasNextDouble()){
                    cardBalance = sc.nextDouble();
                    break;
                }
                else{
                    System.out.println("Помилка: введіть числове значення");
                    sc.next(); // Очищуємо буфер
                }
            }

            arr[i] = new Customer(id, lastName, firstName, middleName, address, cardNum, cardBalance);
            sc.nextLine(); // Очищуємо буфер після введення значень
        }
        return arr;
    }

    /**
     * Метод для пошуку і виведення клієнтів з заданим іменем
     * @param arr масив об'єктів класу Customer
     * @param fName ім'я - критерій пошуку і виведення об'єктів
     */
    public static void findCustByFName(Customer[] arr, String fName) {
        int j = 0;
        for (Customer customer : arr) {
            if (customer.getFirstName().equalsIgnoreCase(fName))
                j++;
        }

        if (j > 0) {
            System.out.println("Клієнти з іменем " + fName + ":");
            j = 1;
            for (Customer customer : arr) {
                if (customer.getFirstName().equalsIgnoreCase(fName)) {
                    System.out.println(j + ". " + customer);
                    j++;
                }
            }
        }
        else {
            System.out.println("Немає клієнтів з іменем " + fName);
        }
    }

    /**
     * Метод для виведення клієнтів з № картки в заданих межах
     * @param arr масив об'єктів класу Customer
     * @param k1 нижня межа діапазону
     * @param k2 верхня межа діапазону
     */
    public static void findCustByCard(Customer[] arr, long k1, long k2) {
        int j = 0;
        for (Customer customer : arr) {
            if (customer.getCardNum() > k1 && customer.getCardNum() < k2)
                j++;
        }

        if(j > 0) {
            System.out.println("\nКлієнти з картками в заданих межах: ");
            j = 1;
            for (Customer customer : arr) {
                if (customer.getCardNum() > k1 && customer.getCardNum() < k2) {
                    System.out.println(j + ". " + customer);
                    j++;
                }
            }
        }
        else {
            System.out.println("Немає клієнтів з карткою в заданому інтервалі");
        }
    }

    /**
     * Метод для виведення кількості клієнтів з заборгованостями
     * і їхньої інформації
     * @param arr масив об'єктів класу Customer
     */
    public static void printCustWithDebt(Customer[] arr) {
        int count = 0;
        int j = 1;
        for (Customer customer : arr) {
            if (customer.getCardBalance() < 0) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println("\nКількість клієнтів з заборгованістю: " + count);
            System.out.println("Клієнти з заборгованістю:");
            for (Customer customer : arr) {
                if (customer.getCardBalance() < 0) {
                    System.out.println(j + ". " + customer);
                    j++;
                }
            }
        } else {
            System.out.println("\nНемає клієнтів з заборгованістю");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true){
            System.out.print("Введіть кількість клієнтів банку: ");
            if(sc.hasNextInt()){
                n = sc.nextInt();
                if(n > 0)
                    break;
                else{
                    System.out.println("Помилка: кількість має бути ненульовою");
                }
            }
            else{
                System.out.println("Помилка: введіть ціле число");
                sc.next(); // Очищуємо буфер
            }
        }

        sc.nextLine(); // Очищуємо буфер після введення числа
        Customer[] customers = createArr(n);


        String fName;
        while (true){
            System.out.print("\nВведіть ім'я клієнта для пошуку: ");
            fName = sc.nextLine();
            if(fName.matches("[a-zA-Zа-яА-ЯіІїЇєЄЙй']+")){
                break;
            }
            else{
                System.out.println("Помилка: ім'я повинне містити тільки букви");
            }
        }
        findCustByFName(customers, fName);

        System.out.println("\nВведіть межі для номера картки клієнта: ");
        System.out.print("Введіть 1 межу: ");
        long k1 = sc.nextLong();
        System.out.print("Введіть 2 межу: ");
        long k2 = sc.nextLong();
        findCustByCard(customers, k1, k2);

        printCustWithDebt(customers);

        sc.close();
    }
}
