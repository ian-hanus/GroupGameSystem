package ECS;

/**
 * An ordered pair with a robust check for equality.
 */
public class TagPair {
    String myTag1;
    String myTag2;

    public TagPair(String tag1, String tag2) {
        myTag1 = tag1;
        myTag2 = tag2;
    }

    public String getFirstTag() {
        return myTag1;
    }

    public String getSecondTag() {
        return myTag2;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TagPair) {
            var otherPair = (TagPair) other;
            return myTag1.equals(otherPair.myTag1) && myTag2.equals(otherPair.myTag2);
        }
        return false;
    }
}
