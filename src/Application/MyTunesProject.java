/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import UI.MainMenu;

/**
 *
 * @author bhp
 */
public class MyTunesProject
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MainMenu employeeMenu = new MainMenu();
        employeeMenu.run();
    }

//    private static void printAllEmployees() throws Exception
//    {
//        EmployeeManager empManager = new EmployeeManager();
//        ArrayList<Employee> employees = empManager.getAll();
//
//        System.out.println("All Employees:");
//        for (Employee e : employees)
//        {
//            System.out.println(e);
//        }
//    }
//
//    private static void printEmployeeById() throws Exception
//    {
//        System.out.print("Enter employee id: ");
//        int id = new Scanner(System.in).nextInt();
//
//        EmployeeManager empManager = new EmployeeManager();
//        Employee emp = empManager.getById(id);
//
//        if (emp != null)
//        {
//            System.out.println("Found employee: ");
//            System.out.println("Id         = " + emp.getId());
//            System.out.println("Name       = " + emp.getName());
//            System.out.println("Address    = " + emp.getAddress());
//            System.out.println("Department = " + emp.getDepNum());
//        }
//        else
//        {
//            System.out.println("Employee with id = " + id + " not found.");
//        }
//    }
//
//    private static void printEmployeeByName() throws Exception
//    {
//        System.out.print("Enter search name: ");
//        String name = new Scanner(System.in).nextLine();
//
//        EmployeeManager empManager = new EmployeeManager();
//        ArrayList<Employee> emps = empManager.getByName(name);
//
//        System.out.println("Mathing employee names:");
//        for (Employee e : emps)
//        {
//            System.out.println(e);
//        }
//    }
//
//    private static void pause()
//    {
//        System.out.print("Press ENTER to continue...");
//        new Scanner(System.in).nextLine();
//    }
}
