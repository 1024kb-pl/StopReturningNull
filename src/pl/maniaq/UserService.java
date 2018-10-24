package pl.maniaq;

import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users;

    public UserService(List<User> users) {
        this.users=users;
    }

    public User getUserByLoginReturnsNull(String login) {
        for(User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    public User getUserByLoginThrowsException(String login) throws UserNotFoundException {
        for(User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        throw new UserNotFoundException("User with login: " + login + " not found.");
    }

    public User getUserByLoginReturnsDefaultObject (String login) {
        for(User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return User.DEFAULT_USER;
    }

    public Optional<User> getUserByLoginReturnsOptional(String login) {
        for(User user : users) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<User> getUserByLoginReturnsOptionalUsingStreams(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
