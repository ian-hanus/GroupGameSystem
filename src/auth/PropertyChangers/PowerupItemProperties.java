package auth.PropertyChangers;

import java.util.Arrays;

public class PowerupItemProperties extends ObjectProperties {
    public PowerupItemProperties(String objectName){
        super(objectName);
        propertyNames.addAll(Arrays.asList(new String[]{"Height", "Width", "Z-Index", "Health", "Powerup"}));
    }
}
