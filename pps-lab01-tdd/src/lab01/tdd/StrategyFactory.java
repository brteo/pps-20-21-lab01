package lab01.tdd;

public interface StrategyFactory {

    SelectStrategy createDefaultStrategy();

    SelectStrategy createEvenStrategy();

    SelectStrategy createMultipleOfStrategy(final int number);

    SelectStrategy createEqualsStrategy(final int number);
}
