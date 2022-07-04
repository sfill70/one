package annotationTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        readMyClass(MyClass1.class);
        readMyClass(MyClass2.class);
        readMyClass(MyClass3.class);
    }

    static void readMyClass(Class<?> myClassObj) throws NoSuchMethodException {
        System.out.println("\nКласс " + myClassObj.getName());
        readAnnotation(myClassObj);
        Method method = myClassObj.getMethod("myClassMethod");
        readAnnotation(method);
    }

    static void readAnnotation(AnnotatedElement element) {
        try {
            System.out.println("Поиск аннотаций в " + element.getClass().getName());
            Annotation[] annotations = element.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Info) {
                    final Info fileInfo = (Info) annotation;
                    System.out.println("Автор: " + fileInfo.author());
                    System.out.println("Версия: " + fileInfo.version());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
