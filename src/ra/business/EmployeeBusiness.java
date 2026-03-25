package ra.business;

import ra.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeBusiness {
    private List<Employee> employeeList = new ArrayList<>();
    private static EmployeeBusiness instance;

    private EmployeeBusiness() {
    }

    public static EmployeeBusiness getInstance() {
        if (instance == null) {
            instance = new EmployeeBusiness();
        }
        return instance;
    }

    // Hiển thị danh sashc: thực hiện hiển thị ds theo định dạng bảng - nếu ds rỗng in ra lỗi
    public void getEmployeeList() {
        if (employeeList.isEmpty()) {
            System.out.println("Danh sách nhân viên đang trống");
            return;
        }
        System.out.printf("| %-10s | %-20s | %-5s | %-15s |\n", "Mã NV", "Tên NV", "Tuổi", "Lương");
        employeeList.forEach(Employee::displayData);
    }

    // thêm nhân viên: nếu trùng empId thì thực hiện in ra lỗi trùng lặp
    public void addEmployee(Scanner scanner) {
        boolean continueAdding = true;

        do {
            Employee emp = new Employee();
            while (true) {
                System.out.print("Nhập mã NV: ");
                String id = scanner.nextLine();

                boolean isDuplicate = employeeList.stream().anyMatch(e -> e.getEmpId().equalsIgnoreCase(id));

                if (!isDuplicate) {
                    emp.setEmpId(id);
                    break;
                }
                System.out.println("Mã NV đã tồn tại, vui lòng nhập lại");
            }

            emp.inputData(scanner);

            employeeList.add(emp);
            System.out.println("Đã thêm nhân viên thành công");

            while (true) {
                System.out.print("Bạn có muốn thêm nhân viên khác không (Yes/No): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("yes")) {
                    continueAdding = true;
                    break;
                } else if (choice.equals("no")) {
                    continueAdding = false;
                    break;
                } else {
                    System.out.println("Vui lòng chỉ nhập 'Yes' hoặc 'No'");
                }
            }
        } while (continueAdding);
    }

    // cập nhật thông tin  nhân viên: cho user lựa chọn thông tin nào muốn cập nhật
    public void updateEmployee(Scanner scanner) {
        System.out.print("Nhập mã NV cần cập nhật: ");
        String id = scanner.nextLine();
        Optional<Employee> opt = employeeList.stream().filter(e -> e.getEmpId().equals(id)).findFirst();

        if (opt.isPresent()) {
            Employee emp = opt.get();
            boolean exit = false;
            do {
                System.out.println("1. Sửa tên | 2. Sửa tuổi | 3. Sửa lương | 4. Xong");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Tên mới: ");
                        emp.setEmpName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Tuổi mới: ");
                        emp.setAge(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.print("Lương mới: ");
                        emp.setSalary(Double.parseDouble(scanner.nextLine()));
                        break;
                    default:
                        exit = true;
                }
            } while (!exit);
        } else {
            System.out.println("Không tìm thấy nhân viên mã: " + id);
        }
    }

    // xóa nhân viên: nếu kích thước ds k đổi sau khi thực hiên(tức là mã NV k tồn tại), in lỗi
    public void deleteEmployee(String empId) {
        int initialSize = employeeList.size();
        employeeList.removeIf(e -> e.getEmpId().equals(empId));
        if (employeeList.size() == initialSize) {
            System.out.println("Mã NV không tồn tại");
        } else {
            System.out.println("Xóa thành công");
        }
    }

    // tìm kiếm theo tên: sd tìm kiếm tg đổi (ko phân biệt hoa thường) và trả về ds đối tg đc tìm thấy
    // nếu k tìm thấy ỉn ra lỗi( hiển thị chi tiết thông tin và tổng số nhân viên tìm thấy)
    public void searchEmployeeByName(String name) {
        List<Employee> results = employeeList.stream().filter(e -> e.getEmpName().toLowerCase().contains(name.toLowerCase())).toList();
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên");
        } else {
            results.forEach(Employee::displayData);
            System.out.println("Tổng cộng: " + results.size() + " nhân viên.");
        }
    }

    // sắp xếp : sawps xếp mức lg giảm dần, trả về danh sashc kết quả và hiển thị danh sách đó lên màn hình
    public void sortAndDisplay() {
        employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(Employee::displayData);
    }

// lọc : lọc ds nhân viên có salary >= 15.triệu để đưa vào ds nv xuất sắc
    public void filterExcellent() {
        List<Employee> excellent = employeeList.stream().filter(e -> e.getSalary() >= 15000000).toList();
        if (excellent.isEmpty()) System.out.println("Không có nhân viên xuất sắc");
        else excellent.forEach(Employee::displayData);
    }

}
