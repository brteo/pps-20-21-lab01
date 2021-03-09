package lab01.tdd;

public class StrategyFactoryImpl implements StrategyFactory {

    @Override
    public SelectStrategy createDefaultStrategy() {
        return new DefaultSelectStrategy();
    }

    @Override
    public SelectStrategy createEvenStrategy() {
        return new EvenSelectStrategy();
    }

    @Override
    public SelectStrategy createMultipleOfStrategy(final int number) {
        return new MultipleOfSelectStrategy(number);
    }

    @Override
    public SelectStrategy createEqualsStrategy(final int number) {
        return new EqualsSelectStrategy(number);
    }
}