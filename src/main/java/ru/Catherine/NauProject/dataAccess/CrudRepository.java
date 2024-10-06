package ru.Catherine.NauProject.dataAccess;

public interface CrudRepository <T, ID>{
    void create(T entity);
    T read(ID id);
    void update(ID id, T entity);
    void delete(ID id);     //returns deleting object
}
