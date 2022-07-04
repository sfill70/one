package annotationGamePerson;

@GamePerson(live=1000, strength=150, magic=250, attack=99, defense=99)
public class GameEvilMaster {


    public static void main(String[] args) {

        System.out.println(fight(GameEvilMaster.class,GameElf.class));

    }
/*Метод вравнения силы двух игровых персонажей*/
    public static boolean fight(Class first, Class second)
    {
        if (!first.isAnnotationPresent(GamePerson.class))
            throw new RuntimeException("first param is not game person");
        if (!second.isAnnotationPresent(GamePerson.class))
            throw new RuntimeException("second param is not game person");

        /*Получение экземпляра класса через getAnnotation(Class<?>.class)*/
        GamePerson firstPerson = (GamePerson) first.getAnnotation(GamePerson.class);
        GamePerson secondPerson = (GamePerson) second.getAnnotation(GamePerson.class);

        System.out.println(firstPerson);

        int firstAttack = firstPerson.attack() * firstPerson.strength() + firstPerson.magic();
        int firstPower = firstPerson.live() * firstPerson.defense() * firstAttack;

        int secondAttack = secondPerson.attack() * secondPerson.strength() + secondPerson.magic();
        int secondPower = secondPerson.live() * secondPerson.defense() * secondAttack;
        System.out.println(firstPower +" - "+ secondPower);
        return firstPower > secondPower;
    }
}


