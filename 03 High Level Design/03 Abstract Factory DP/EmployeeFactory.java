public class EmployeeFactory {
    
    //Get Employee

    public static Employee getEmployee(EmployeeAbstarctFactory factory) 
    {
        return factory.createEmployee();
    }

}
