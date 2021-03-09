package lab01.tdd;

public class MultipleOfSelectStrategy implements SelectStrategy {
    private int multiplier;

    public MultipleOfSelectStrategy(final int multiplier){
        this.setValue(multiplier);
    }

    public void setValue(final int multiplier) {
        this.multiplier=multiplier;
    }

    @Override
    public boolean apply(final int element) {
        return element % multiplier == 0;
    }
}
