public class Jalebi {
    
    //Eager way of creating singleton Object 
    
    private static Jalebi jalebi  = new Jalebi();

    public static Jalebi getJalebi()
    {
        return jalebi;
    }

}
