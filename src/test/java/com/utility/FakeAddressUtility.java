package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.Address;

import java.util.Locale;

public class FakeAddressUtility {

    public static Address getFakeAddress() {
        Faker faker = new Faker(Locale.US);

        return new Address(
                faker.company().name(),
                faker.address().buildingNumber(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.numerify("#####"),
                faker.phoneNumber().cellPhone(),
                faker.phoneNumber().cellPhone(),
                "other",
                "Office Address"
        );
    }
}
