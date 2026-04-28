import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UserDatabase_44 {
    private static final String FILE_NAME = "users.dat";
    private static final String COUNT_FILE = "user_count.dat";

    public static void saveUsers(List<User_44> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<User_44> loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User_44>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveCount(int count) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(COUNT_FILE))) {
            dos.writeInt(count);
        } catch (IOException e) {
            System.out.println("Could not save user count.");
        }
    }

    public static int loadCount() {
        File file = new File(COUNT_FILE);
        if (!file.exists()) {
            return 1923;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(COUNT_FILE))) {
            return dis.readInt();
        } catch (IOException e) {
            System.out.println("Could not load user count. Defaulting to 1923.");
            return 1923;
        }
    }
}
