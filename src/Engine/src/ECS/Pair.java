package ECS;

/**
 * An ordered pair with a robust check for equality.
 */
public class Pair<T> {
    T myItem1;
    T myItem2;

    public Pair(T item1, T item2) {
        myItem1 = item1;
        myItem2 = item2;
    }

    public T getFirstTag() {
        return myItem1;
    }

    public T getSecondTag() {
        return myItem2;
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass().isInstance(other)) {
            var otherPair = (Pair<T>) other;
            return myItem1.equals(otherPair.myItem1) && myItem2.equals(otherPair.myItem2);
        }
        return false;
    }
}
