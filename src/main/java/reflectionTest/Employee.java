package reflectionTest;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {
    private String name;
    private String surname;
    private int age;

    {
        age = -1;
        name = "Ivan";
        surname = "Ivanov";
    }

    public Employee() {
    }

    public Employee(String name, String surname) throws Exception {
        this.name = name;
        this.surname = surname;
    }

    private Employee(String name, String surname, List<String> list) {
        this.name = name;
        this.surname = surname;
    }

    public Employee(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "reflectionTest.Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee = Employee.class.newInstance();
        Class<?> clazz = Employee.class;
        var v = (Employee) clazz.newInstance();
        var emp = clazz.newInstance();

        System.out.println("age is - " + employee.getAge());


        System.out.println("age is - " + emp + " ");


        Class employeeClass = Employee.class;
        System.out.println("getConstructors:");
        printAllConstructors(employeeClass);
        System.out.println("\n" + "getDeclaredConstructors:");
        printDeclaredConstructors(employeeClass);

        var constructors = getAllConstructors(employeeClass);
        var constructorNames = getConstructorNames(constructors);
        var constructorModifiers = getConstructorModifiers(constructors);

        System.out.println("Класс reflectionTest.Employee:");
        System.out.println("Конструкторы :");
        System.out.println(constructorNames);
        System.out.println("Модификаторы :");
        System.out.println(constructorModifiers);

        var constructorExceptionTypes = getConstructorExceptionTypes(constructors.get(2));
        System.out.println("Типы исключений :");
        System.out.println(constructorExceptionTypes);

//        Для конструктора -  private reflectionTest.Employee(String name, String surname, List<String> list)
        var constructorParameterTypes = getConstructorParameterTypes(constructors.get(1));
        var constructorParameters = getConstructorParameters(constructors.get(1));
        var constructorParametersGenerics = getConstructorParametersGenerics(constructors.get(1));

        System.out.println("Параметры конструкторов :");
        System.out.println(constructorParameters);

        System.out.println("Типы параметров :");
        System.out.println(constructorParameterTypes);

        System.out.println("Типы параметров конструкторов :");
        System.out.println(constructorParametersGenerics);

//        Создания объекта через Constructor.newInstance()
        System.out.println("Создание объекта через конструктор");
        Constructor<?> employeeConstructor = employeeClass.getConstructor();
        System.out.println(employeeConstructor);
/*А если мы хотим получить определенный конструктор, нам нужно передавать
в этот метод типы параметров, которые будут в конструкторе.*/
        Constructor<?> employeeConstructor2 = employeeClass.getDeclaredConstructor(String.class, String.class, List.class);
        System.out.println(employeeConstructor2);
//        Создание через публичный конструктор
        Constructor<?> employeeConstructor3 = employeeClass.getConstructor(String.class, String.class, int.class);
        System.out.println(employeeConstructor3);

        Employee newInstance = (Employee) employeeConstructor3.newInstance("NeIvan", "NeIvanov", 10);
        System.out.println(newInstance);
//        Создание через приватный конструктор

        Constructor<?> declaredConstructor = employeeClass.getDeclaredConstructor(String.class, String.class, List.class);
        declaredConstructor.setAccessible(true);
        System.out.println(declaredConstructor);

        Employee newInstance2 = (Employee) declaredConstructor.newInstance("NeIvan", "NeIvanov", new ArrayList<>());
        System.out.printf(newInstance2.toString());

    }


    static void printAllConstructors(Class<?> c) {
        for (Constructor<?> constructor : c.getConstructors()) {
            System.out.println(constructor);
        }
    }

    static void printDeclaredConstructors(Class<?> c) {
        for (Constructor<?> constructor : c.getDeclaredConstructors()) {
            System.out.println(constructor);
        }
    }

    static List<Constructor<?>> getAllConstructors(Class<?> c) {
        return new ArrayList<>(Arrays.asList(c.getDeclaredConstructors()));
    }

    static List<String> getConstructorNames(List<Constructor<?>> constructors) {
        List<String> result = new ArrayList<>();
        for (Constructor<?> constructor : constructors) {
            result.add(constructor.toString());
        }
        return result;
    }

    static List<String> getConstructorModifiers(List<Constructor<?>> constructors) {
        List<String> result = new ArrayList<>();
        for (Constructor<?> constructor : constructors) {
            result.add(Modifier.toString(constructor.getModifiers()));
        }
        return result;
    }

    static List<Class<?>> getConstructorExceptionTypes(Constructor<?> c) {
        return new ArrayList<>(Arrays.asList(c.getExceptionTypes()));
    }

    static List<Parameter> getConstructorParameters(Constructor<?> c) {
        return new ArrayList<>(Arrays.asList(c.getParameters()));
    }

    static List<Class<?>> getConstructorParameterTypes(Constructor<?> c) {
        return new ArrayList<>(Arrays.asList(c.getParameterTypes()));
    }

    static List<Type> getConstructorParametersGenerics(Constructor<?> c) {
        return new ArrayList<>(Arrays.asList(c.getGenericParameterTypes()));
    }


}