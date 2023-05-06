package ru.netology.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    GeoServiceImpl sut;

    @BeforeEach
    public void init() {
        this.sut = new GeoServiceImpl();
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({"127.0.0.1,", "172.0.32.11, RUSSIA", "96.44.183.149, USA", "172.11.32.11, RUSSIA", "96.00.000.000, USA"})
    void byIpЕ(String ip, Country a) {
        Country exepted = sut.byIp(ip).getCountry();
        assertEquals(exepted, a);
    }

    @Test
    void byCoordinates() {
        double alatitude = 1.1, longitude = 1.2;
        var exepted = Exception.class;
        assertThrows(exepted,
                () -> sut.byCoordinates(alatitude, longitude));
    }

}