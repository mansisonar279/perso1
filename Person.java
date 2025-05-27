import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void displayName() {
        System.out.println("Name: " + name);
    }

    public void displayAge(String dob) {
        LocalDate birthDate = parseDate(dob);
        if (birthDate == null) {
            System.out.println("Invalid date format.");
            return;
        }
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();
        System.out.println("Age: " + age);
    }

    private LocalDate parseDate(String dob) {
        LocalDate date = null;
        try {
            // Try YYYY-MM-DD
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dob, formatter1);
        } catch (Exception e1) {
            try {
                // Try DD-MM-YYYY
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(dob, formatter2);
            } catch (Exception e2) {
                // Parsing failed for both formats
                return null;
            }
        }
        return date;
    }
}

class Employee extends Person {
    private int empId;
    private double salary;

    public Employee(String name, int empId, double salary) {
        super(name);
        this.empId = empId;
        this.salary = salary;
    }

    public void displayEmployeeDetails() {
        displayName();
        System.out.println("Employee ID: " + empId);
        System.out.println("Salary: " + salary);
    }
}

public class Main {
    public static void main(String[] args) {
        // Test Person
        Person p = new Person("Alice");
        p.displayName();
        p.displayAge("1990-05-27");  // YYYY-MM-DD
        p.displayAge("27-05-1990");  // DD-MM-YYYY
        
        System.out.println();

        // Test Employee
        Employee e = new Employee("Bob", 101, 75000);
        e.displayEmployeeDetails();
        e.displayAge("1985-12-15");
    }
}
