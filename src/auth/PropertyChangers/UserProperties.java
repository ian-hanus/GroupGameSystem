package auth.PropertyChangers;

import java.util.Arrays;

public class UserProperties extends ObjectProperties {
    public UserProperties(String objectName){
        super(objectName);
        propertyNames.addAll(Arrays.asList(new String[]{"Height, Width, Z-Index, Health"}));
    }
}
