package optionals;

import java.util.Optional;

public class PhoneBookCrawler {

    private PhoneBook phoneBook;

    public PhoneBookCrawler(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public Optional<String> findPhoneNumberByNameAndPunishIfNothingFound(String name) {

        if (!phoneBook.findPhoneNumberByName(name).isPresent()) {
            throw new IllegalArgumentException("No phone number found");
        }
        return phoneBook.findPhoneNumberByName(name);
    }

    public String findPhoneNumberByNameAndPrintPhoneBookIfNothingFound(String name) {
        if (!phoneBook.findPhoneNumberByName(name).isPresent()) {
            return phoneBook.toString();
        }
        return phoneBook.findPhoneNumberByName(name).get();
    }

    public String findPhoneNumberByNameOrNameByPhoneNumber(String name, String phoneNumber) {
        if (!phoneBook.findPhoneNumberByName(name).isPresent()) {
            if (!phoneBook.findNameByPhoneNumber(phoneNumber).isPresent()) {
                if (phoneBook.findPhoneNumberByName("Jos de Vos").isPresent()) {
                    return phoneBook.findPhoneNumberByName("Jos de Vos").get();
                } else {
                    return phoneBook.toString();
                }
            } else {
                return phoneBook.findNameByPhoneNumber(phoneNumber).get();
            }
        }

        return phoneBook.findPhoneNumberByName(name).get();
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

}
