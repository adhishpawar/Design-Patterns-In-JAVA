public class WebDevFactory extends EmployeeAbstarctFactory {
    @Override
    public Employee createEmployee()
    {
        return new WebDeveloper();
    }

}
