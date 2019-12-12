package com.luckcheese.yuca.network;

import com.luckcheese.yuca.model.Product;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

import static org.junit.Assert.*;

public class ServerTest {

    private Server server = new Server();

    @Test
    public void getProducts() throws IOException {
        Response<List<Product>> response = server.getRequests().getProducts().execute();

        assertTrue("request failed", response.isSuccessful());
        assertTrue("response is empty", response.body() != null && !response.body().isEmpty());
    }
}
