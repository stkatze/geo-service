package ru.netology.i18n;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    LocalizationServiceImpl sut;

    @BeforeEach
    public void init() {
        sut = new LocalizationServiceImpl();
    }

    @ParameterizedTest(name = "Текст для {0}  - \"{1}\"")
    @CsvSource({"RUSSIA, Добро пожаловать", "USA, Welcome"})
    void locale(Country country, String text) {
        String exepted = sut.locale(country);
        assertEquals(exepted, text);
    }

}