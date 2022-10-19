package streams;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static streams.Practice.*;

class PracticeTest {

    @Test
    public void test() {
        System.out.println("Testing if [william, jones, aaron, seppe, frank, gilliam] returns 14");
        assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam"), 14);

        System.out.println("Testing if [aaron] returns 0");
        assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("aaron"), 0);
    }

    @Test
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");

        assertEquals(transform(collection), expected);

    }

    @Test
    public void getOldestPersonShouldReturnOldestPerson() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);

        assertEquals(getOldestPerson(collection), eva);
    }

    @Test
    public void calculateShouldSumAllNumbers() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);

        assertEquals(calculate(numbers), 1 + 2 + 3 + 4 + 5);
    }

    @Test
    public void getKidNameShouldReturnNamesOfAllKidsUnder18() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);

        assertTrue(getKidNames(collection).contains("Sara"));
        assertTrue(getKidNames(collection).contains("Anna"));
        assertFalse(getKidNames(collection).contains("Viktor"));
        assertFalse(getKidNames(collection).contains("Eva"));

    }

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = partitionAdults(collection);

        assertTrue(result.get(true).contains(viktor));
        assertTrue(result.get(true).contains(eva));
        assertTrue(result.get(false).contains(sara));

    }

    @Test
    public void groupByNationalityTest() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = groupByNationality(collection);

        assertTrue(result.get("Norwegian").contains(sara));
        assertTrue(result.get("Norwegian").contains(eva));
        assertTrue(result.get("Serbian").contains(viktor));
    }

    @Test
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);

        assertEquals("Names: Sara, Viktor, Eva.", namesToString(collection));

    }


    @Test
    public void getStringShouldOutputEvenUnevenString() {

        assertEquals("o3,e44", getString(asList(3, 44)));
        assertEquals("o3", getString(Collections.singletonList(3)));
    }

}