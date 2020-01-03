package LambdaExpressions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Person {
    public enum Sex {
        MALE, FEMALE
    }
    String name;
    Sex gender;
    LocalDate birthDay;
    String emailAddress;
    String hometown;

    //birthDay is in the format "yyyy-MM-dd"
    public Person(String name, Sex gender, String birthDay, String emailAddress, String hometown){
        this.name = name;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.hometown = hometown;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthDay = LocalDate.parse(birthDay, formatter);
    }

    public int getAge() {
        Calendar now = Calendar.getInstance();
        //Get difference between years
        return now.get(Calendar.YEAR) - birthDay.getYear();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString(){
        return "Name : " + name + "\n"
                + "Gender : " + gender + "\n"
                + "Birth date : " + birthDay + "\n"
                + "Email Address : " + emailAddress;
    }

    public void printPerson() {
        System.out.println(this.toString());
    }

    interface CheckPerson {
        boolean test(Person p);
    }

    /*public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }*/

    /**
     * 1. "source" of objects
     * 2. "tester" predicate object/lambda expression to filter out eligible objects according to our criteria
     * 3. "mapper" lambda expression to pick corresponding value to selected object
     * 4. "block" lambda expression to take specific action on selected object
     * */
    public static <X,Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X,Y> mapper,
            Consumer<Y> block){
        for (X e:source){
            if (tester.test(e)){
                Y data = mapper.apply(e);
                block.accept(data);
            }
        }
    }

    //"yyyy-MM-dd"
    public static void main(String[] args){
        Collection<Person> roster = new ArrayList<>();
        roster.add(new Person("Jack",Sex.MALE,"2000-05-06","jack@example.com","Kansas City"));
        roster.add(new Person("Carlos",Sex.MALE,"2006-09-17","carl_68@example.com","Boston"));
        roster.add(new Person("Linda",Sex.FEMALE,"1990-07-24","lin_angel@example.com","Miami"));
        roster.add(new Person("Stephen",Sex.MALE,"1999-07-11","firestorms@example.com","New York"));
        roster.add(new Person("Lea",Sex.FEMALE,"2011-03-08","lea_89@example.com","Denver"));
        roster.add(new Person("Naomi",Sex.FEMALE,"2005-10-10","naomi_14@example.com","San Francisco"));
        roster.add(new Person("Valdez",Sex.MALE,"1995-11-20","valdez@example.com","Charlotte"));
        roster.add(new Person("Tom",Sex.MALE,"1997-05-15","tom538@example.com","Orlando"));
        roster.add(new Person("Mike",Sex.MALE,"1992-04-24","hi72@example.com","Saint Louis"));
        roster.add(new Person("Myriam",Sex.FEMALE,"2008-08-22","youme@example.com","Chicago"));

        System.out.println("email addresses of guy above 18");
        processElements(
                roster,
                p->p.getAge()>18 && p.gender == Sex.MALE,
                p->p.getEmailAddress(),
                email->System.out.println(email)
        );

        System.out.println("\nHometown of all females");
        processElements(
                roster,
                p->p.gender == Sex.FEMALE,
                p->p.hometown,
                hometown->System.out.println(hometown)
        );
    }
}