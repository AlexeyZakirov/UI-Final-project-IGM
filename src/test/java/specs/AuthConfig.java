package specs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/auth.properties"
})
public interface AuthConfig extends Config {

    String email();

    String password();

    String selenoidUser();

    String selenoidPassword();
}
