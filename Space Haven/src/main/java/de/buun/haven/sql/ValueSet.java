package de.buun.haven.sql;

import lombok.Getter;
import lombok.Setter;

public class ValueSet {

    private ValueNode<?>[] nodes;
    @Getter
    @Setter
    private byte state; //0 Nothing changed, 1 created, 2 updated, 3 deleted

    public ValueSet(int size){
        this.nodes = new ValueNode[size];
        this.state = (byte) 1;
    }

    public<T> T getValue(int index){
        if(this.nodes[index] == null) return null;
        return (T) this.nodes[index].getValue();
    }

    public <T> void setValue(int index, T value){
        ValueNode<?> node = this.nodes[index];
        if(node == null){
            this.nodes[index] = new ValueNode<>(value);
            return;
        }
        this.nodes[index] = new ValueNode<>(value);
        this.nodes[index].setEdited(true);
        this.state = (byte) 1;
    }

    //Returns if it was deleted
    public boolean post(Database database, String tableName){
        if(state == (byte) 1){
            SQLCommands.insertInto(database, tableName, this);
            setState((byte) 0);
            return false;
        }else if(state == (byte) 2){
            SQLCommands.update(database, tableName, this);
            setState((byte) 0);
            return false;
        }else if(state == (byte) 3){
            SQLCommands.delete(database, tableName, this.getValue(0));
            setState((byte) 0);
            return true;
        }
        return true;
    }

}
