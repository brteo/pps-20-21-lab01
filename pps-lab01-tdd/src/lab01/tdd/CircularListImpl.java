package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList{
    private List<Integer> list = new ArrayList<>();
    private Integer current = -1;
    private SelectStrategy strategy;

    public CircularListImpl(){
        this(new DefaultSelectStrategy());
    }

    public CircularListImpl(final SelectStrategy strategy){
        this.strategy = strategy;
    }

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
        return next(strategy);
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
        Optional<Integer> item=Optional.empty();

        if(list.isEmpty())
            return item;

        for(int i=0; i< list.size(); i++)
        {
            current = current >= list.size() -1 ? 0 : current+1;

            int element = list.get(current);
            if(strategy.apply(element))
            {
                item = Optional.of(element);
                break;
            }
        }

        return item;
    }
}
