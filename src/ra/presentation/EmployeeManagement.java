package ra.presentation;

import ra.business.EmployeeBusiness;
import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeBusiness business = EmployeeBusiness.getInstance();
        while (true) {
            try {
                System.out.println("\n**************** QUẢN LÝ NHÂN VIÊN ****************");
                System.out.println("1. Hiển thị danh sách toàn bộ nhân viên");
                System.out.println("2. Thêm mới nhân viên");
                System.out.println("3. Cập nhật thông tin theo mã nhân viên");
                System.out.println("4. Xóa nhân viên theo mã");
                System.out.println("5. Tìm kiếm nhân viên theo tên");
                System.out.println("6. Lọc danh sách nhân viên xuất sắc (Lương >= 15tr)");
                System.out.println("7. Sắp xếp danh sách giảm dần theo lương");
                System.out.println("8. Thoát");
                System.out.print("Lựa chọn của bạn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        business.getEmployeeList();
                        break;
                    case 2:
                        business.addEmployee(scanner);
                        break;
                    case 3:
                        business.updateEmployee(scanner);
                        break;
                    case 4:
                        System.out.print("Nhập mã muốn xóa: ");
                        business.deleteEmployee(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Nhập tên muốn tìm: ");
                        business.searchEmployeeByName(scanner.nextLine());
                        break;
                    case 6:
                        business.filterExcellent();
                        break;
                    case 7:
                        business.sortAndDisplay();
                        break;
                    case 8:
                        System.out.println("Thoát hệ thống");
                        return;
                    default:
                        System.out.println("Vui lòng chọn từ 1-8");
                }
            } catch (Exception e) {
                System.out.println("Xảy ra lỗi, hãy thử lại");
            }
        }
    }
}