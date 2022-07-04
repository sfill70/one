package externalizableTest;

import java.io.*;
import java.util.Base64;


/*Сериализация с использованием Externalizable класс externalizableTest.MainExternalizable*/
public class UserInfo implements Externalizable {
    static String root = System.getProperty("user.dir");
    private static final String OUTPUT_PATH = String.join(File.separator, root, "src", "main", "out");
    private static final String INPUT_PATH = String.join(File.separator, root, "input");

    private String firstName;
    private String lastName;
    private String superSecretInformation;

    private static final long serialVersionUID = 1L;

    //Для Сериализация с использованием Externalizable обязателен конструктор по умолчанию.

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String superSecretInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superSecretInformation = superSecretInformation;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getFirstName());
        out.writeObject(this.getLastName());
        out.writeObject(this.encryptString(this.getSuperSecretInformation()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
        superSecretInformation = this.decryptString((String) in.readObject());
    }

    private String encryptString(String data) {
        String encryptedData = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(encryptedData);
        return encryptedData;
    }

    private String decryptString(String data) {
        String decrypted = new String(Base64.getDecoder().decode(data));
        System.out.println(decrypted);
        return decrypted;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSuperSecretInformation() {
        return superSecretInformation;
    }

    @Override
    public String toString() {
        return "externalizableTest.UserInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", superSecretInformation='" + superSecretInformation + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(OUTPUT_PATH);

        FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_PATH + File.separator + "save1.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        UserInfo userInfoOut = new UserInfo("Ivan", "Ivanov", "Ivan Ivanov's passport data");
        objectOutputStream.writeObject(userInfoOut);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(OUTPUT_PATH + File.separator + "save1.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        UserInfo userInfoIn = (UserInfo) objectInputStream.readObject();
        System.out.println(userInfoIn);

        objectInputStream.close();
    }
}