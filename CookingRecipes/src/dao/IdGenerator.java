package dao;

@FunctionalInterface
public interface IdGenerator<K> {
	K getNextId();
}
