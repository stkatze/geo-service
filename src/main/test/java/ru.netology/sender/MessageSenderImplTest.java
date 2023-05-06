package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @ParameterizedTest(name = "ip - адрес:  {0}; cтрана: {1}; сообщение \"{2}\"")
    @CsvSource({"1, RUSSIA, Добро пожаловать", "1, USA, Welcome",})
    void send(String ipAddress, Country countryGetName, String text) {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(new Location(null, countryGetName, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(countryGetName)).thenReturn(text);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        String exepected = messageSender.send(headers);
        System.out.println();
        assertEquals(exepected, text);
    }
}