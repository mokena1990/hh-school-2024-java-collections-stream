package tasks;

import common.Person;
import common.PersonService;
import common.PersonWithResumes;
import common.Resume;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
  Еще один вариант задачи обогащения
  На вход имеем коллекцию персон
  Сервис умеет по personId искать их резюме (у каждой персоны может быть несколько резюме)
  На выходе хотим получить объекты с персоной и ее списком резюме
 */
public class Task8 {
  private final PersonService personService;

  public Task8(PersonService personService) {
    this.personService = personService;
  }

  public Set<PersonWithResumes> enrichPersonsWithResumes(Collection<Person> persons) {
    Map<Integer, Person> personMap = new HashMap<>(persons.stream().collect(Collectors.toMap(Person::id, Function.identity(), (x, y) -> x)));
    Set<Resume> resumes = personService.findResumes(personMap.keySet());
    Map<Integer, Set<Resume>> personIdWithResume = resumes.stream()
        .collect(
            Collectors.groupingBy(Resume::personId, Collectors.mapping(Function.identity(), Collectors.toSet()))
        );

    return personMap.entrySet()
        .stream()
        .map(entry -> new PersonWithResumes(entry.getValue(), personIdWithResume.get(entry.getKey())))
        .collect(Collectors.toSet());
  }
}
