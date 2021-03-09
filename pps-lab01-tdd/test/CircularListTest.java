import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private final Integer ITEMS_NUMBERS = 5;
    private CircularListImpl list;
    private final StrategyFactory strategyFactory = new StrategyFactoryImpl();

    private void addMultipleItems(Integer n){
        for (int i=1; i<=n; i++)
            list.add(i);
    }

    @BeforeEach
    void beforeEach(){
        list = new CircularListImpl();
    }

    @Test
    void checkEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void checkSizeOneItem() {
        list.add(5);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void checkSizeMoreItems() {
        addMultipleItems(ITEMS_NUMBERS);

        assertEquals(ITEMS_NUMBERS, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void nextOneItem() {
        list.add(5);
        assertEquals(Optional.of(5), list.next());
    }

    @Test
    void nextOneItemIfEmpty() {
        assertEquals(Optional.empty(), list.next());
    }

    @Test
    void nextMoreItems() {
        addMultipleItems(ITEMS_NUMBERS);

        for (int i=1; i<=ITEMS_NUMBERS; i++)
            assertEquals(Optional.of(i), list.next());
    }

    @Test
    void nextCircularItems() {
        addMultipleItems(ITEMS_NUMBERS);

        for (int i=0; i<ITEMS_NUMBERS*2; i++)
            assertEquals(Optional.of(i%ITEMS_NUMBERS+1), list.next());
    }

    @Test
    void previousOneItem() {
        list.add(5);
        assertEquals(Optional.of(5), list.previous());
    }

    @Test
    void previousOneItemIfEmpty() {
        assertEquals(Optional.empty(), list.previous());
    }

    @Test
    void previousMoreItems() {
        addMultipleItems(ITEMS_NUMBERS);

        for (int i=ITEMS_NUMBERS; i>0; i--)
            assertEquals(Optional.of(i), list.previous());
    }

    @Test
    void previousCircularItems() {
        addMultipleItems(ITEMS_NUMBERS);

        for (int j=0; j<2; j++){
            for (int i=ITEMS_NUMBERS; i>0; i--)
                assertEquals(Optional.of(i), list.previous());
        }
    }

    @Test
    void nextAndPreviousOneItem() {
        list.add(5);
        assertEquals(Optional.of(5), list.next());
        assertEquals(Optional.of(5), list.previous());
    }

    @Test
    void nextAndPreviousMoreItems() {
        addMultipleItems(ITEMS_NUMBERS);

        assertEquals(Optional.of(1), list.next());
        assertEquals(Optional.of(2), list.next());
        assertEquals(Optional.of(1), list.previous());
        assertEquals(Optional.of(ITEMS_NUMBERS), list.previous());
        assertEquals(Optional.of(1), list.next());
    }

    @Test
    void checkReset() {
        addMultipleItems(ITEMS_NUMBERS);
        Random rand = new Random();

        for(int i = 0; i< rand.nextInt(ITEMS_NUMBERS); i++)
            list.next();

        list.reset();
        assertEquals(Optional.of(1), list.next());

        for(int i = 0; i< rand.nextInt(ITEMS_NUMBERS); i++)
            list.previous();

        list.reset();
        assertEquals(Optional.of(1), list.next());
    }

    @Test
    void nextEvenStrategy() {
        list = new CircularListImpl(strategyFactory.createEvenStrategy());

        list.add(5);
        list.add(7);
        list.add(2);

        assertEquals(Optional.of(2), list.next());
        assertEquals(Optional.of(2), list.next());
    }

    @Test
    void nextEvenStrategyEmpty() {
        list = new CircularListImpl(strategyFactory.createEvenStrategy());

        list.add(5);
        list.add(7);

        assertEquals(Optional.empty(), list.next());
    }

    @Test
    void nextMultipleStrategy() {
        list = new CircularListImpl(strategyFactory.createMultipleOfStrategy(2));

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(10);

        assertEquals(Optional.of(2), list.next());
        assertEquals(Optional.of(10), list.next());
        assertEquals(Optional.of(2), list.next());
    }

    @Test
    void nextMultipleStrategyEmpty() {
        list = new CircularListImpl(strategyFactory.createMultipleOfStrategy(20));

        list.add(1);
        list.add(3);
        list.add(5);
        list.add(10);

        assertEquals(Optional.empty(), list.next());
    }

    @Test
    void nextEqualsStrategy() {
        list = new CircularListImpl(strategyFactory.createEqualsStrategy(2));

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(Optional.of(2), list.next());
        assertEquals(Optional.of(2), list.next());
    }

    @Test
    void nextEqualsStrategyEmpty() {
        list = new CircularListImpl(strategyFactory.createEqualsStrategy(3));

        list.add(1);
        list.add(2);

        assertEquals(Optional.empty(), list.next());
    }

    @Test
    void nextMixedStrategy() {
        list = new CircularListImpl();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertEquals(Optional.of(1), list.next());
        assertEquals(Optional.of(2), list.next());

        assertEquals(Optional.of(1), list.next(strategyFactory.createEqualsStrategy(1)));
        assertEquals(Optional.of(4), list.next(strategyFactory.createEqualsStrategy(4)));

        assertEquals(Optional.of(2), list.next(strategyFactory.createEvenStrategy()));

        assertEquals(Optional.of(4), list.next(strategyFactory.createMultipleOfStrategy(4)));
    }
}
