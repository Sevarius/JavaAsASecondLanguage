package io.github.javaasasecondlanguage.homework02.webserver;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import static io.github.javaasasecondlanguage.homework02.di.Injector.inject;

/**
 * https://dzone.com/articles/simple-http-server-in-java
 */
public class MyWebServer implements WebServer {
    private static final Logger log = inject(Logger.class);
    private String host = inject(String.class, "host");
    private int port = inject(Integer.class, "port");
    private Executor executor = inject(ThreadPoolExecutor.class);
    private Map<String, HttpHandler> handlers = inject(Map.class);

    private HttpServer server;

    public MyWebServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(host, port), 0);
        } catch (IOException e) {
            log.info("Oops " + e.getMessage());
        }
        server.setExecutor(executor);
        handlers.forEach(server::createContext);
    }

    @Override
    public void start() {
        log.info("Starting server");
        server.start();
    }

    @Override
    public void stop() {
        log.info("Stopping server");
        server.stop(0);
    }

}
