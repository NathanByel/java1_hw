package ru.geekbrains.java1.lesson5;

/*
    ФИО должно быть в формате "xxx xxx xxx"
    Email должен быть в формате "aaa@bbb.ccc"
    Номер телефона должен быть в формате "XXXXXXXXXXX" или "+XXXXXXXXXXX"
*/

public class Worker {
    private String  name;
    private String  position;
    private String  email;
    private String  phoneNumber;
    private int     salary;
    private int     age;

    // Private methods *************************************************************************************************
    // Проверка строки на null и пустоту
    private void checkStringForEmpty(String str, String field) {
        if(str == null) {
            throw new IllegalArgumentException( field + " can't be null pointer." );
        } else if(str.length() == 0) {
            throw new IllegalArgumentException( field + " can't be empty." );
        }
    }

    // Public methods *************************************************************************************************
    public void printInfo() {
        System.out.println(
                "ФИО:       " + this.name        + "\n" +
                "Должность: " + this.position    + "\n" +
                "Email:     " + this.email       + "\n" +
                "Телефон:   " + this.phoneNumber + "\n" +
                "Зарплата:  " + this.salary      + "\n" +
                "Возраст:   " + this.age );
    }

    // Constructors ****************************************************************************************************
    public Worker() {
        this.name        = "Empty";
        this.position    = "Empty";
        this.email       = "Empty";
        this.phoneNumber = "Empty";
        this.salary      = 0;
        this.age         = 0;
    }

    public Worker(String name, String position, String email, String phoneNumber, int salary, int age) {
        this.setName(name);
        this.setPosition(position);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setSalary(salary);
        this.setAge(age);
    }

    // Getters & Setters ***********************************************************************************************
    // Name ********************************************
    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkStringForEmpty(name,"Name");
        if( !name.matches("^([A-zА-я]+\\s){2}[A-zА-я]+$") ){
            throw new IllegalArgumentException( name + " - Wrong name format. Expected \"xxx xxx xxx\"" );
        }
        this.name = name;
    }

    // Position ****************************************
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        checkStringForEmpty( position, "Position" );
        this.position = position;
    }

    // Email *******************************************
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        checkStringForEmpty(email,"Email");
        if( !email.matches("^\\w+([\\w\\.\\-]\\w+)*\\@\\w+([\\w\\.\\-]\\w+)*\\.\\w+$") ){
            throw new IllegalArgumentException( email + " - Wrong email format. Expected \"aaa@bbb.ccc\"" );
        }
        this.email = email;
    }

    // PhoneNumber *************************************
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        checkStringForEmpty(phoneNumber,"Phone number");
        if( !phoneNumber.matches("^((\\+7)|8)\\d{10}$") ) {
            throw new IllegalArgumentException( phoneNumber + " - Wrong phone number format. Expected \"XXXXXXXXXXX\" or \"+XXXXXXXXXXX\"" );
        }
        this.phoneNumber = phoneNumber;
    }

    // Salary ******************************************
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary <= 0) {
            throw new IllegalArgumentException( "Salary can't be 0 or less." );
        }
        this.salary = salary;
    }

    // Age *********************************************
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 16 || age > 75) {
            throw new IllegalArgumentException( "Age can't be < 16 or > 75" );
        }
        this.age = age;
    }
}
