package repositories.interfaces;

public interface Repository<T> {

    public void add(T model);
    public void remove(T model);
    public T find(T data);
}
