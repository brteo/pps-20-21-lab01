package lab01.tdd;

public class DefaultSelectStrategy implements SelectStrategy {
    @Override
    public boolean apply(int element) {
        return true;
    }
}
