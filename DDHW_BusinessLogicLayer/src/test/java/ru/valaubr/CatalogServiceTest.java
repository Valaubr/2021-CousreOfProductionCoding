package ru.valaubr.services;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.Jetty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogServiceTest {
    @BeforeEach
    void serverStart() {
        int port = 8080;
        Server server = new Server(port);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void doGet() {
    }

    @Test
    void doPost() {
    }

    @Test
    void doPut() {
    }
}