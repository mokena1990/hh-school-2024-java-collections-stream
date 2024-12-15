package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Set<String> result = new HashSet<>();
    for (int personId : personAreaIds.keySet()) {
      Person person = persons.stream()
          .filter(p -> p.id().equals(personId))
          .findFirst()
          .orElseThrow();
      for(int areaId : personAreaIds.get(personId)) {
        result.add(String.format(
            "%s - %s",
            person.firstName(),
            areas.stream()
                .filter(ar -> ar.getId().equals(areaId))
                .map(Area::getName)
                .findFirst()
                .orElseThrow()
        ));
      }
    }
    return result;
  }
}
