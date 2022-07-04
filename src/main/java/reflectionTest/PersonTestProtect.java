package reflectionTest;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class PersonTestProtect {

    private int age;
    public String nickname;

    public PersonTestProtect(int age, String nickname) {
        this.age = age;
        this.nickname = nickname;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = PersonTestProtect.class.getDeclaredFields();
        List<String> actualFieldNames = getFieldNames(fields);
        actualFieldNames.forEach(System.out::println);

        PersonTestProtect person = new PersonTestProtect(10, "javaRush");

//        Доступ к публичному полю
        Field field = PersonTestProtect.class.getDeclaredField("nickname");
        String nickname = (String) field.get(person);
        System.out.println(nickname);
        System.out.println(person.nickname);

        //        Доступ к приватному полю
        Field fieldAge = PersonTestProtect.class.getDeclaredField("age");
        fieldAge.setAccessible(true);
        int age = (int) fieldAge.get(person);
        System.out.println("Текущее значение - " + age);

//        Изменение приватного поля
        fieldAge.set(person, 19);
        int ageNew = (int) fieldAge.get(person);
        System.out.println("Текущее значение - " + ageNew);
    }

    static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(Modifier.toString(field.getModifiers()) + " " + field.getName());
        return fieldNames;
    }
}
