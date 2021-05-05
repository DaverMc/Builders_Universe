package de.buun.haven.sql;

import lombok.Getter;
import lombok.Setter;

public class ValueNode<K> {

    @Getter
    @Setter
    private K value;
    @Getter
    @Setter
    private boolean edited;

    public ValueNode(K value){
        this.value = value;
        this.edited = true;
    }
}
