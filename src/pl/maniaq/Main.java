package pl.maniaq;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    UserService userService = new UserService(Arrays.asList(
	            new User("admin", "admin"),
	            new User("pablo", "escabo"),
	            new User("kasia", "zyt"),
	            new User("ufo", "porno")
        ));

	    User admin = userService.getUserByLoginReturnsNull("admin");
	    User notFoundAdmin = userService.getUserByLoginReturnsNull("notFoundAdmin");

	    System.out.println("Admin: " + admin.getLogin());
	    //System.out.println("NotFoundAdmin: " + notFoundAdmin.getLogin());


		try {
			User pablo = userService.getUserByLoginThrowsException("pablo");
			System.out.println("pablo: " + pablo.getLogin());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		try {
			User notFoundPablo = userService.getUserByLoginThrowsException("notFoundPablo");
			System.out.println("notFoundPablo: " + notFoundPablo.getLogin());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}


		try {
			User kasia = userService.getUserByLoginReturnsOptional("kasia").orElseThrow(UserNotFoundException::new);
			System.out.println("kasia: " + kasia.getLogin());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}


		User defaultUser = new User("annonymous", "password");

		User notFoundKasia = userService.getUserByLoginReturnsOptional("notFoundKasia").orElse(defaultUser);
		System.out.println("notFoundKasia: " + notFoundKasia.getLogin());


		User ufo = userService.getUserByLoginReturnsOptionalUsingStreams("ufo").orElse(defaultUser);
		User notFoundUfo = userService.getUserByLoginReturnsOptionalUsingStreams("notFoundUfo").orElse(defaultUser);

		System.out.println("ufo: " + ufo.getLogin());
		System.out.println("notFoundUfo: " + notFoundUfo.getLogin());


    }
}
