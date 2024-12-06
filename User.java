public class User {
    private final String username;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String phoneNumber;
    private final String password;
    private final int age;

    public User(String username, String lastName, String firstName, String middleName, int age, String phoneNumber, String password) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Creates a new User instance with an updated last name.
     */
    public User withLastName(String newLastName) {
        return new User(username, newLastName, firstName, middleName, age, phoneNumber, password);
    }

    /**
     * Creates a new User instance with an updated first name.
     */
    public User withFirstName(String newFirstName) {
        return new User(username, lastName, newFirstName, middleName, age, phoneNumber, password);
    }

    /**
     * Creates a new User instance with an updated middle name.
     */
    public User withMiddleName(String newMiddleName) {
        return new User(username, lastName, firstName, newMiddleName, age, phoneNumber, password);
    }

    /**
     * Creates a new User instance with an updated age.
     */
    public User withAge(int newAge) {
        return new User(username, lastName, firstName, middleName, newAge, phoneNumber, password);
    }

    /**
     * Creates a new User instance with an updated phone number.
     */
    public User withPhoneNumber(String newPhoneNumber) {
        return new User(username, lastName, firstName, middleName, age, newPhoneNumber, password);
    }
}
