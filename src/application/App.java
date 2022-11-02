package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Employee;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.print("Quantos funcionários serão registrados? ");
        Integer employeeRegister = Integer.parseInt(scan.nextLine());
        List<Employee> employee = new ArrayList<>();
        System.out.println();

        for (int i = 0; i < employeeRegister; i++) {
            System.out.println("Funcionário #" + (i + 1) + ":");
            System.out.print("Id: ");
            Integer id = Integer.parseInt(scan.nextLine());
            System.out.print("Name: ");
            String name = scan.nextLine();
            System.out.print("Salary: ");
            Double salary = Double.parseDouble(scan.nextLine());
            System.out.println();

            employee.add(new Employee(id, name, salary));
        }

        System.out.println();
        for (Employee obj : employee) {
            System.out.println(obj);
        }

        System.out.print("Qual o ID do funcionário que vai ter aumento de salário? ");
        Integer employeeIncrease = Integer.parseInt(scan.nextLine());
        Integer numberId = posId(employee, employeeIncrease);
        Employee result = employee.stream().filter(x -> x.getId() == employeeIncrease)
                .findFirst().orElse(null);

        System.out.print("Qual é a porcentagem? ");
        result.setSalary(Math.rint((((Double.parseDouble(scan.nextLine()) / 100) + 1) * result.getSalary())));

        employee.removeIf(x -> x.getId() == employeeIncrease);
        employee.add(numberId, result);

        for (Employee obj : employee) {
            System.out.println(obj);
        }

        scan.close();
    }

    public static Integer posId(List<Employee> list, Integer id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id)
                return i;
        }
        return null;
    }
}
