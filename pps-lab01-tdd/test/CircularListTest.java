import lab01.tdd.CircularListImpl;
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
}
