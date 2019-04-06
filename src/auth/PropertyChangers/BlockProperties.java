package auth.PropertyChangers;

import java.util.*;

public class BlockProperties extends ObjectProperties {
    public BlockProperties(String objectName){
        super(objectName);
        propertyNames.addAll(Arrays.asList(new String[]{"Height, Width, Z-Index, Image File Name"}));
    }
}
