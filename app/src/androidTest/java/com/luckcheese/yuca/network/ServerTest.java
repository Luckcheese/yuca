package com.luckcheese.yuca.network;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.luckcheese.yuca.model.Price;
import com.luckcheese.yuca.model.Product;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ServerTest {

    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private Server server = new Server(context);

    @Test
    public void getProducts() throws IOException {
        Response<List<Product>> response = server.getRequests().getProducts().execute();

        assertTrue("request failed", response.isSuccessful());

        List<Product> result = response.body();
        assertTrue("response is empty", result != null && !result.isEmpty());

        result.forEach(new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                assertNotNull("product is null", product);
            }
        });

        Product firstProduct = result.get(0);
        assertNotNull("price is null", firstProduct.getPrice());
        assertNotNull("picture is null", firstProduct.getPicture());
        assertNotNull("title is null", firstProduct.getTitle());
        assertNotNull("id is null", firstProduct.getId());
        assertNotNull("_link is null", firstProduct.getLink());

        Price firstProductPrice = firstProduct.getPrice();
        assertNotNull("current price is null", firstProductPrice.getCurrent());
        assertNotNull("price installments is null", firstProductPrice.getInstallment());
    }
}
