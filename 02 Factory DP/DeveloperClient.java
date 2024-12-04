public class DeveloperClient {
    
    public static void main(String[] args) {
        
        //To Avoid tight Coupling 
       Employee employee = EmployeeFactory.getEmployee("ANDRIOD DEVELOPER");
        System.out.println(employee);
        int salary = employee.salary();
        System.out.println("Salary:" + salary);

        Employee employee2 = EmployeeFactory.getEmployee("WEB DEVELOPER");
        System.out.println(employee2);
        int salary2 = employee2.salary();
        System.out.println("Salary:" + salary2);


        


    }

}
