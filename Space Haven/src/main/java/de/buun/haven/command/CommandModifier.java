package de.buun.haven.command;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(CommandModifiers.class)
public @interface CommandModifier {

    ModifierType value();
    String[] string() default "";
    int integer() default 0;
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface CommandModifiers{
    CommandModifier[] value();
}