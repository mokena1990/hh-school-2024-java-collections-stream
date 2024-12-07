package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    Collection<Person> personsAll = new ArrayList<>();
    personsAll.addAll(persons1);
    personsAll.addAll(persons2);
    return personsAll.stream()
        .sorted(Comparator.comparing(Person::createdAt))
        .limit(limit)
        .toList();
  }
}
