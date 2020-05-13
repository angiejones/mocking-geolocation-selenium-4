package geolocation;

import base.BaseTests;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationTests extends BaseTests {

    @Test
    public void goShoppingInAustin(){

        Map coordinates = Map.of(
                "latitude", 30.3079823,
                "longitude", -97.893803,
                "accuracy", 1
        );

        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        driver.navigate().to("https://oldnavy.gap.com/stores");

        var addresses = driver.findElementsByClassName("address");
        assertTrue(addresses.size() > 0, "No addresses found");
        assertTrue(addresses.
                stream()
                .allMatch(a -> a.getText().contains(", TX ")),
                "Some addresses listed are not in Texas");
    }
}
