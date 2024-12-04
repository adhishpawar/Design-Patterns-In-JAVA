public class Cake 
{

    private static Cake cake;
    //constructor 
    private Cake(){

        //Solution for Breaking using Reflection API
        if(cake != null)
        { 
            throw new RuntimeException("You are Trying to break a Singleton pattern");
        }
    }

    //Lazy Way o f Creating Singleton Object --> When Its Req 
    // synchronized for multiple threads Env 
    public static Cake getCake()
    {
            //Creation of Object of This class
            if(cake == null)
            {
                synchronized(Cake.class)
                {    
                    cake = new Cake();
                }
            }
            return cake;
    }
}