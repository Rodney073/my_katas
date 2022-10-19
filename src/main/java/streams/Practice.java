package streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Practice {

    /**
     * give me the total number of letters in all the names with more than 5 letters
     */
    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        return Arrays.stream(names).filter(name -> name.length() > 5).mapToInt(String::length).sum();
    }

    /**
     * Flatten this multidimensional collection
     */
    public static List<String> transform(List<List<String>> collection) {
        return collection.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Get the oldest person from the collection
     */
    public static Person getOldestPerson(List<Person> people) {
        return people.stream().max(Comparator.comparing(Person::getAge)).get();
    }

    /**
     * Sum all elements of a collection, try to use the reduce operator with identity parameter instead of an IntStream
     */
    public static int calculate(List<Integer> numbers) {
        //return numbers.stream().reduce(Integer::sum).get();
        return numbers.stream().mapToInt(x -> x).sum();
    }

    /**
     * Get the names of all kids under the age of 18
     */
    public static Set<String> getKidNames(List<Person> people) {
        return people.stream().filter(p -> p.getAge() < 18).map(Person::getName).collect(Collectors.toSet());
    }


    /**
     * Partition these people into adults and kids, you'll need a special collector for this one
     */
    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people.stream() // Convert collection to Stream
                .collect(partitioningBy(p -> p.getAge() >= 18)); // Partition stream of people into adults (age => 18) and kids
    }

    /**
     * Group these people by nationality, same kind as the previous exercise
     */
    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
        return people.stream().collect(groupingBy(Person::getNationality));
    }

    /**
     * Return a comma-separated string of all these people's names
     */
    public static String namesToString(List<Person> people) {
        return people.stream().map(Person::getName).collect(Collectors.joining(", ", "Names: ", "."));
    }

    /**
     * Return a comma-separated string of all these people's names
     */
    public static String getString(List<Integer> list) {
        return list.stream().map(i -> i % 2 == 0 ? "e" + i : "o" + i).collect(joining(","));
    }


}
