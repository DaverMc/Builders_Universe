package de.buun.haven.sql;

import lombok.Getter;

class ColumnData {

    public enum ColumnType{
        INTEGER,
        TEXT,
        DATE;
    }

    @Getter
    private final String name;
    private final ColumnType type;
    private final boolean notNull;
    private final boolean unique;

    public ColumnData(String name, ColumnType type){
        this.name = name;
        this.type = type;
        this.notNull = false;
        this.unique = false;
    }

    public ColumnData(String name, ColumnType type, boolean notNull, boolean unique){
        this.name = name;
        this.type = type;
        this.notNull = notNull;
        this.unique = unique;
    }

    public String getModifiers(){
        String modifiers = "";
        if(notNull){
            modifiers += " " + "NOTNULL";
        }
        if(unique){
            modifiers += " " + "UNIQUE";
        }
        return modifiers;
    }

    public String getSQLCode(){
        return name + " " + type.name()  + getModifiers();
    }

}
