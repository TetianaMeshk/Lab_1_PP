package org.example.customer;

public class Customer {
    private int id;
    private String lastName; //прізвище
    private String firstName; //ім'я
    private String middleName; //по-батькові
    private String address;
    private long cardNum;
    private double cardBalance;
    public Customer(int id, String lastName, String firstName, String middleName, String address, long cardNum, double cardBalance) {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
        setAddress(address);
        setCardNum(cardNum);
        setCardBalance(cardBalance);
    }
    public void setId(int id){
        this.id = id;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setCardNum(long cardNum){
        this.cardNum = cardNum;
    }
    public void setCardBalance(double cardBalance){
        this.cardBalance = cardBalance;
    }
    public int getId(){
        return id;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getMiddleName(){
        return middleName;
    }
    public String getAddress(){
        return address;
    }
    public long getCardNum(){
        return cardNum;
    }
    public double getCardBalance(){
        return cardBalance;
    }

    public String toString(){
        return "Клієнт:\n" + "ID: " + id + "\nПІБ: "
                + lastName + " " + firstName + " " + middleName
                + "\nАдреса: " + address
                + "\nНомер картки: " + cardNum
                + "\nБаланс: " + cardBalance;
    }
}
