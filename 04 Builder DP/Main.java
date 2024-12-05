public class Main {

    public static void main(String[] args) {
      User user = new User.UserBuilder()
                            .setEmailId("adhishpawar@gmail.com")
                            .setUserId("Adhish9008")
                            .setUserName("Adhish")
                            .build();

        System.out.println(user);

        User user2 = User.UserBuilder.builder()
        .setUserId("XYZ123")
        .setUserName("XYZ")
        .build();

        System.out.println(user2);
    }
}
    
   
