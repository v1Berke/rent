import java.io.Serializable;

public class User_44 implements Serializable {
    private int userId;
    private static int count = UserDatabase_44.loadCount(); 
    private boolean isAdmin;
    private String name;
    private String surname;
    private String password;

    public User_44(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.userId = count++;
        this.isAdmin = false;
        UserDatabase_44.saveCount(count);
    }

    public int setUserId(int newID) { return userId = newID; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSurname() { return surname; }
    public boolean isAdmin() { return isAdmin; }
    public String getName() { return name; }
    public int getUserId() { return userId; }
    public void setAdmin() { isAdmin = true; }
    public void outAdmin() { isAdmin = false; }
}
