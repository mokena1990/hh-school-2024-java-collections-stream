package tasks;

import common.Person;
import common.PersonService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> personSet = personService.findPersons(personIds);
    List<Person> personList = new ArrayList<>();
    Map<Integer, Person> personMap = personSet.stream().collect(Collectors.toMap(Person::id, Function.identity()));
    for (int i = 0; i < personMap.size(); i++) {
      personList.add(personMap.get(personIds.get(i)));
    }
    return personList;
  }
}
