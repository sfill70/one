package reflectionTest;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public final class Person implements Serializable, Cloneable {
    private int age;
    private String name;
    public boolean isMale;
    protected String address;
    public static final int MAX_AGE = 120;
    public static final int MIN_AGE = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "reflectionTest.Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (isMale != person.isMale) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return address != null ? address.equals(person.address) : person.address == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isMale ? 1 : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public List<String> someMethod(List<String> list, String s) {
        //очень полезный и важный метод
        return null;
    }

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("reflectionTest.Person");
            System.out.println(aClass.getClass());
            Class aClass1 = Person.class;
            System.out.println(aClass1.getClass());
            Person person = new Person();
            Class aClass2 = person.getClass();
            System.out.println(aClass2.getClass());
            System.out.println(Arrays.toString(aClass.getDeclaredMethods()));
            System.out.println(Arrays.toString(aClass.getMethods()));
            System.out.println(Arrays.toString(aClass.getDeclaredFields()));
            System.out.println(Arrays.toString(aClass.getFields()));
            int classModifiers = aClass.getModifiers();
            System.out.println(classModifiers);
            System.out.println(classModifiers & 2);

            boolean isPublic = Modifier.isPublic(classModifiers);
            boolean isStatic = Modifier.isStatic(classModifiers);
            boolean isFinal = Modifier.isFinal(classModifiers);
            boolean isAbstract = Modifier.isAbstract(classModifiers);
            boolean isInterface = Modifier.isInterface(classModifiers);
            System.out.printf("Class modifiers: %d%n", classModifiers);
            System.out.printf("Is public: %b%n", isPublic);
            System.out.printf("Is static: %b%n", isStatic);
            System.out.printf("Is final: %b%n", isFinal);
            System.out.printf("Is abstract: %b%n", isAbstract);
            System.out.printf("Is interface: %b%n", isInterface);

            Class<Person> personClass = Person.class;
            final Class<? super Person> superclass = personClass.getSuperclass();
            System.out.println(superclass);

            final Class<?>[] interfaces = personClass.getInterfaces();
            System.out.println(Arrays.toString(interfaces));

            Field[] fields = Person.class.getDeclaredFields();
            List<Field> actualFields = getFieldNames(fields);
            System.out.println(actualFields);
            printTypes(actualFields);
            printFieldInfo(actualFields);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    static List<Field> getFieldNames(Field[] fields) {
        return List.of(fields);
    }

    static void printTypes(List<Field> fields) {
        fields.forEach(e -> System.out.print(e.getType() + "; "));
    }

    static void printFieldInfo(List<Field> fields) {
        fields.forEach(e -> System.out.printf("Field type - %s\nField name - %s\nModifiers - %s\n\n",
                e.getType(), e.getName(), Modifier.toString(e.getModifiers())));
    }

}
