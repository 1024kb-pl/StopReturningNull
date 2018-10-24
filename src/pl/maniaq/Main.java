package pl.maniaq;

import java.util.Arrays;
import java.util.Optional;

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
	    // Uncomment line below to get NullPointException
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


		User secondPablo = userService.getUserByLoginReturnsDefaultObject("pablo-2");
		System.out.println("Second pablo: " + secondPablo.getPassword());


		Optional<User> foundKasia = userService.getUserByLoginReturnsOptional("kasia");
		if (foundKasia.isPresent()) {
			System.out.println("Found kasia: " + foundKasia.get());
		}

		User notFoundKasia = userService.getUserByLoginReturnsOptional("notFoundKasia").orElse(User.DEFAULT_USER);
		System.out.println("notFoundKasia: " + notFoundKasia.getPassword());

		try {
			User kasia = userService.getUserByLoginReturnsOptional("kasia")
					.orElseThrow(() -> new UserNotFoundException("User with login kasia not found."));
			System.out.println("kasia: " + kasia.getPassword());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}


		User ufo = userService.getUserByLoginReturnsOptionalUsingStreams("ufo").orElse(User.DEFAULT_USER);
		User notFoundUfo = userService.getUserByLoginReturnsOptionalUsingStreams("notFoundUfo").orElse(User.DEFAULT_USER);

		System.out.println("ufo: " + ufo.getLogin());
		System.out.println("notFoundUfo: " + notFoundUfo.getLogin());


    }
}
