public class ManagerFactory extends EmployeeAbstarctFactory{

    @Override
    public Employee createEmployee() {
        return new Manager();
    }
    
    
}
