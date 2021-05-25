/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.function.Consumer;

public class Mediator {
    private static Mediator instance;

    private Consumer<String> consumer;

    public static Mediator getInstance() {
        if(instance == null) {
            instance = new Mediator();
        }
        return instance;
    }

    private Mediator() {
    }

    public void register(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    public void fireEvent(String string) {
        if(consumer != null) {
            consumer.accept(string);
        }
    }
}
