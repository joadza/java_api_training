package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;


public class CustomFireHandler implements HttpHandler{
    final Cell map;


    CustomFireHandler() { this.map = new Cell(); }



    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int SB;
        if (!exchange.getRequestMethod().equals("GET")) {
            NotFoundMethod(exchange);
        }

        var JsonProp = Parser(exchange);
        if (JsonProp != null) {

            SB = CheckBoat(JsonProp);
            if (SB == 0) {
                SendResponse(exchange, "{\n\t\"consequence\": \"miss\",\n\t\"shipLeft\": true\n}", 200);
            } else if (SB == 2) {
                SendResponse(exchange, "{\n\t\"consequence\": \"hit\",\n\t\"shipLeft\": true\n}", 200);
            } else { B_restant(exchange); }

        }


    }

    private void B_restant(HttpExchange exchange) throws IOException {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (map.Sea[i][j] != 0) {
                    SendResponse(exchange, "{\n\t\"consequence\": \"sunk\",\n\t\"shipLeft\": true\n}", 200);
                    return;
                }
            }
        }
        SendResponse(exchange, "{\n\t\"consequence\": \"sunk\",\n\t\"shipLeft\": false\n}", 200);
    }

    private int CheckBoat(JsonFireHandlerProp JsonProp) {
        if (map.Sea[JsonProp.row][JsonProp.col] == 0) {
            return 0;
        }
        else {
            var sb = new Bateaux();
            int statusBoat = sb.B_check(map, JsonProp);
            map.Sea[JsonProp.row][JsonProp.col] = 0;
            if (statusBoat == 0)
                return 1;
            else
                return 2;
        }
    }

    private void SendResponse(HttpExchange exchange, String s, int rcode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(rcode, s.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(s.getBytes());
        }
    }

    private JsonFireHandlerProp Parser(HttpExchange exchange) throws IOException {
        String param = exchange.getRequestURI().getQuery();
        final int length = param.substring(param.indexOf("=") + 1).length();
        if (length < 2) {
            SendResponse(exchange, "Wrong Param", 404);
            return null;
        }
        final int number = param.substring(param.indexOf("=") + 1).charAt(1) - '0';
        if (length > 3 || (length == 3 && !param.substring(param.indexOf("=") + 2).equals("10")) || param.substring(param.indexOf("=") + 1).charAt(0) > 'J' || number == 0 || number > 10) {
            SendResponse(exchange, "Wrong Param", 404);
            return null;
        }
        else
            return new JsonFireHandlerProp(param.substring(param.indexOf("=") + 1));
    }




    private void NotFoundMethod(HttpExchange exchange) throws IOException {
        String error = "TNull";


        exchange.sendResponseHeaders(404, error.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(error.getBytes());
        }
    }
}
