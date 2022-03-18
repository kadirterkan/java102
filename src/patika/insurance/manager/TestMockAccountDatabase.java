package patika.insurance.manager;

import patika.insurance.entities.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.TreeSet;

public class TestMockAccountDatabase {

    public static TreeSet<AbstractAccount> readyTheSet() {
        TreeSet<AbstractAccount> accounts = new TreeSet<>();

        User normalUser = new User("Ben", "Kenobi", "ob1@tatooine", "obiwankenobi", "Stranger", 47, Date.valueOf(LocalDate.now()));
        User.addAddress(normalUser, getTheHomeAddress());
        IndividualAccount individualAccount = new IndividualAccount(normalUser);
        accounts.add(individualAccount);

        User enterpriseUser = new User("Darth", "Vader", "sithno2@mustafar", "padme", "Dictator's right hand", 31, Date.valueOf(LocalDate.now()));
        User.addAddress(enterpriseUser, getTheBusinessAddress());
        EnterpriseAccount enterpriseAccount = new EnterpriseAccount(enterpriseUser);
        accounts.add(enterpriseAccount);

        return accounts;
    }

    public static HomeAddress getTheHomeAddress() {
        return new HomeAddress("My Home Address", "Manhattan, NY 10036", "New York City", "New York");
    }

    public static BusinessAddress getTheBusinessAddress() {
        return new BusinessAddress("My Bussss Address", "1600 Pennsylvania Avenue NW", "Washington", "District of Columbia");
    }
}
