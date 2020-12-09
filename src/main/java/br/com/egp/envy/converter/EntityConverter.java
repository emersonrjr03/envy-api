package br.com.egp.envy.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EntityConverter<T,K> {

    public List<T> unmarshall(List<K> modelList);

    public T unmarshall(K model);

    public List<K> marshall(List<T> entityList) throws Exception;
    public K marshall(T entity);
}
