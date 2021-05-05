package de.buun.haven.sql;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public abstract class Database {

    private final Map<String, Table<?>> cache;

    protected Database(){
        this.cache = new HashMap<>();
    }

    public boolean createTable(String name, ColumnData...columns){
        boolean created = SQLCommands.createTable(this,name, columns);
        if(!created) return false;
        Table<?> table = new Table<>(SQLCommands.selectAll(this, name, columns.length));
        this.cache.put(name, table);
        return true;
    }

    public Table<?> getTable(String tableName){
        return this.cache.get(tableName);
    }

    public boolean insert(String tableName, Object...values){
        Table<?> table = getTable(tableName);
        ValueSet set = new ValueSet(values.length);
        return table.insertValue(set);
    }

    public boolean update(String tableName, Object...values){
        Table<?> table = getTable(tableName);
        ValueSet set = new ValueSet(values.length);
        return table.updateValue(set);
    }

    public void postValuesToDatabase(){
        cache.forEach((tableName, table) -> table.getUpdateableValues().forEach(set -> {
                boolean deleted = set.post(this, tableName);
                if(deleted) table.getValues().remove(set.getValue(0));
            }));
    }

    public abstract Connection connect();
}
