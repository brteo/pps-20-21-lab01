package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList{
    private List<Integer> list= new ArrayList<>();
    private Integer current = -1;

    @Override
    public void add(final int element){
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() <= 0;
    }

    @Override
    public Optional<Integer> next() {
        if(list.isEmpty())
            return Optional.empty();

        current = current >= list.size() -1 ? 0 : current+1;
        return Optional.of(list.get(current));
    }

    @Override
    public Optional<Integer> previous() {
        if(list.isEmpty())
            return Optional.empty();

        current = current <= 0 ? list.size()-1 : current-1;
        return Optional.of(list.get(current));
    }

    @Override
    public void reset() {
        current = -1;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
