package ra.entity;
import java.util.Scanner;

public class Employee {
    private String empId;
    private String empName;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(String empId, String empName, int age, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // phương thức inputData(Scanner scanner) cho phép nhập đầy đủ thông tin của nhân viên từ bàn phím
    // và validate( Nhập sai yêu cầu nhập lại)
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên NV: ");
        this.empName = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Nhập tuổi (>=18): ");
                this.age = Integer.parseInt(scanner.nextLine());
                if (this.age >= 18){
                    break;
                }
                System.out.println("Tuổi phải lớn hơn hoặc bằng 18");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập mức lương (>0): ");
                this.salary = Double.parseDouble(scanner.nextLine());
                if (this.salary > 0) {
                    break;
                }
                System.out.println("Lương phải lớn hơn 0");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực");
            }
        }
    }

    // phướng thức displayData() hiển thị thng tin nhân viên ra màn hình vs định dạng rõ ràng
    public void displayData() {
        System.out.printf("| %-10s | %-20s | %-5d | %-15.2f |\n", empId, empName, age, salary);
    }
}