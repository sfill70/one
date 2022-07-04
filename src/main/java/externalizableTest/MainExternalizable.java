package externalizableTest;

import java.io.*;

public class MainExternalizable {
    static String root = System.getProperty("user.dir");
    private static final String OUTPUT_PATH = String.join(File.separator, root, "src", "main", "out");
    private static final String INPUT_PATH = String.join(File.separator, root, "input");

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println(OUTPUT_PATH);

        FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_PATH + File.separator + "save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        UserInfo userInfoOut = new UserInfo("Ivan", "Ivanov", "Ivan Ivanov's passport data");
        objectOutputStream.writeObject(userInfoOut);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(OUTPUT_PATH + File.separator + "save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        UserInfo userInfoIn = (UserInfo) objectInputStream.readObject();
        System.out.println(userInfoIn);

        objectInputStream.close();

    }
}
