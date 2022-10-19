package optionals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneBookCrawlerTest {

    private final PhoneBookCrawler phoneBookCrawler = new PhoneBookCrawler(new PhoneBook());

    @Test
    void findPhoneNumberByNameAndPunishIfNothingFound() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> phoneBookCrawler.findPhoneNumberByNameAndPunishIfNothingFound("Raf de Giraf"),
                "Expected to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("No phone number found"));
    }

    @Test
    public void findPhoneNumberByNameAndReturnEntirePhoneBookIfNothingFound() {
        String phoneBook = phoneBookCrawler.findPhoneNumberByNameAndPrintPhoneBookIfNothingFound("Raf de Giraf");
        assertEquals("PhoneBook{phoneBookEntries={An de Toekan=016/161617, Jos de Vos=016/161616, Kris de Vis=016/161618}}", phoneBook);
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_foundByName() {
        assertEquals("016/161618",
                phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Kris de Vis", "016/161619"));
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_foundByPhoneNumber() {
        assertEquals("An de Toekan",
                phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Raf de Giraf", "016/161617"));
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_nothingFoundReturnsJosDeVosPhoneNumber() {
        assertEquals("016/161616",
                phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Raf de Giraf", "016/161619"));
    }

}