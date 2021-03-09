package lab01.tdd;

public class EqualsSelectStrategy implements SelectStrategy {
    private int equalsTo;

    public EqualsSelectStrategy(final int equalsTo){
        this.setValue(equalsTo);
    }

    public void setValue(final int equalsTo) {
        this.equalsTo=equalsTo;
    }

    @Override
    public boolean apply(int element) {
        return element == equalsTo;
    }
}
