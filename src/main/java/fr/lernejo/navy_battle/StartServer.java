package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;




public class StartServer {

    final HttpServer Serv;
    StartServer(int port) throws IOException {
        InetSocketAddress soc = new InetSocketAddress(port);
        this.Serv = HttpServer.create(soc, 0);

        this.Serv.setExecutor(Executors.newSingleThreadExecutor());
        this.Serv.createContext("/api/game/start", new CustomStartHandler(port));
        this.Serv.createContext("/api/game/fire" , new CustomFireHandler());
        this.Serv.createContext("/ping", new Hand2());

        this.Serv.start();
    }

    public final void Stop() {
        this.Serv.stop(0);
    }
}
