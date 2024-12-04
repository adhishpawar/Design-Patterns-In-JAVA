public class Client {
    public static void main(String[] args) {

        //Want to get Andriod Developer
        Employee e1 =  EmployeeFactory.getEmployee(new AndroidDevFactory());
        e1.name();

        
        //Want to get Web Developer
        Employee e2 =  EmployeeFactory.getEmployee(new WebDevFactory());
        e2.name();

        //Want to get Manager
        Employee e3 =  EmployeeFactory.getEmployee(new ManagerFactory());
        e3.name();
    }
}
