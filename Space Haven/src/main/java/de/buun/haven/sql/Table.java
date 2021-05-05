package de.buun.haven.sql;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Table<K> {

    @Getter
    private final Map<K, ValueSet> values;
    private final ColumnData[] columns;

    public Table(Map<K, ValueSet> values, ColumnData...columns){
        this.columns = columns;
        this.values = values;
    }

    public boolean insertValue(ValueSet valueSet){
        K key = valueSet.getValue(0);
        if(this.values.containsKey(key)) return false;
        this.values.put(key, valueSet);
        return true;
    }

    public boolean updateValue(ValueSet valueSet){
        K key = valueSet.getValue(0);
        valueSet.setState((byte) 2);
        if(this.values.containsKey(key)){
            this.values.remove(key);
            this.values.put(key, valueSet);
            return true;
        }
        return false;
    }

    public List<ValueSet> getUpdateableValues(){
        return this.values.values().stream()
                .filter(valueSet -> valueSet.getState() != 0)
                .collect(Collectors.toList());
    }

    public ColumnData[] getColumn(){
        return this.columns;
    }

}
