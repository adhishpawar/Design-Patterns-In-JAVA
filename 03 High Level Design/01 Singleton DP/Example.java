
import java.lang.reflect.Constructor;

public class Example {
 
    public static void main(String[] args) throws Exception{
        // new Cake();  --> Error

        // Cake cake1 = Cake.getCake();
        // System.out.println(cake1.hashCode());

        // Cake cake2 = Cake.getCake();
        // System.out.println(cake2.hashCode());

        // Jalebi jalebi1 = Jalebi.getJalebi();
        // System.out.println(jalebi1.hashCode());

        // Jalebi jalebi2 = Jalebi.getJalebi();
        // System.out.println(jalebi2.hashCode());


        //Breaking Using Reflection API
        Cake c1 = Cake.getCake();
        System.out.println(c1.hashCode());   //103536485

        Constructor<Cake> constructor = Cake.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Cake c2 = constructor.newInstance();
        System.out.println(c2.hashCode());   //37380050

            //For this Solution in implemented --> Exception Handling in private contructor 


    }
}
