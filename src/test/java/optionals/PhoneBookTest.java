package optionals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    private final PhoneBook phoneBook = new PhoneBook();

    @Test
    public void findPhoneNumberByName() {
        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("Jos de Vos");
        assertEquals("016/161616", phoneNumber.get());
    }

    @Test
    public void findPhoneNumberByName_NotFound() {
        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("Jos de Voss");
        Assertions.assertThrows(NoSuchElementException.class, phoneNumber::get);
    }

    @Test
    public void findNameByPhoneNumber() {
        Optional<String> name = phoneBook.findNameByPhoneNumber("016/161616");
        assertEquals("Jos de Vos", name.get());
    }

    @Test
    public void findNameByPhoneNumber_NotFound() {
        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("016/161619");
        Assertions.assertThrows(NoSuchElementException.class, phoneNumber::get);
    }
}