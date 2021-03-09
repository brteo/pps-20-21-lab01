package lab01.tdd;

public class EqualsSelectStrategy implements SelectStrategy {
    private final int equalsTo;

    public EqualsSelectStrategy(final int equalsTo) {
        this.equalsTo=equalsTo;
    }

    @Override
    public boolean apply(int element) {
        return element == equalsTo;
    }
}
