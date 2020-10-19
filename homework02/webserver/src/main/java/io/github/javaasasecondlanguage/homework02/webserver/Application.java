package io.github.javaasasecondlanguage.homework02.webserver;

import io.github.javaasasecondlanguage.homework02.di.Context;
import io.github.javaasasecondlanguage.homework02.di.Injector;

import java.util.Map;
import java.util.concurrent.Executors;

public class Application {
    public static void initDI() {
        new Context()
                .register((Logger) System.out::println)
                .register("Hello dear ", "welcomeText")
                .register(8080, "port")
                .register("localhost", "host")
                .register(Executors.newFixedThreadPool(1))
                .register(Map.of("/test", new MyHttpHandler()))
                .register(new MyWebServer());


    }

    public static void main(String[] args) {
        initDI();
        var server = Injector.inject(WebServer.class);
        server.start();
    }
}
