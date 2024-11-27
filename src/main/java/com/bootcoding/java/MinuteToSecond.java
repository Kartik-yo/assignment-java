package com.bootcoding.java;
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
public class MinuteToSecond {
    public static int convertToSeconds(int minutes) {
        return minutes * 60;
    }
    public static void main(String[] args) {
        try {
            // Create HTTP server listening on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            // Create context for the root path
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    // Default conversion for 10 minutes
                    int minutes = 10;
                    int seconds = convertToSeconds(minutes);
                    // Prepare response
                    String response = minutes + " minutes are " + seconds + " seconds";
                    // Send HTTP response
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }
            });
            // Create context for conversion with query parameter
            server.createContext("/convert", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    // Parse query parameters
                    String query = exchange.getRequestURI().getQuery();
                    int minutes = 10; // default
                    if (query != null && query.startsWith("minutes=")) {
                        try {
                            minutes = Integer.parseInt(query.split("=")[1]);
                        } catch (NumberFormatException e) {
                            // Keep default if parsing fails
                        }
                    }
                    int seconds = convertToSeconds(minutes);
                    // Prepare response
                    String response = minutes + " minutes are " + seconds + " seconds";
                    // Send HTTP response
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }
            });
            // Set executor to null for default
            server.setExecutor(null);
            // Start the server
            server.start();
            System.out.println("Server is running on port 8080");
            System.out.println("Open http://localhost:8080 in your browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




