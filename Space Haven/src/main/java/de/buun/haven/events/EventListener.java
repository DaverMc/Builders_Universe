package de.buun.haven.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface EventListener {

    default List<Method> getEventMethods(){
        Event dummy = new Event();
        List<Method> list = new ArrayList<>();

        for(Method method : getClass().getMethods()){
            if(method.getParameterCount() != 1) continue;
            if(method.getParameterTypes()[0].isInstance(dummy)) list.add(method);
        }

        return list;
    }

}
