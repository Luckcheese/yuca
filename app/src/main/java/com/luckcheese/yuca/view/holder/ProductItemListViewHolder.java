package com.luckcheese.yuca.view.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luckcheese.yuca.R;
import com.luckcheese.yuca.model.Product;

public class ProductItemListViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final TextView titleView;
    private final TextView currentPriceView;
    private final TextView installmentsView;

    private Product product;

    public ProductItemListViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image);
        titleView = itemView.findViewById(R.id.title);
        currentPriceView = itemView.findViewById(R.id.current_price);
        installmentsView = itemView.findViewById(R.id.installments);
    }

    public void setProduct(Product product) {
        this.product = product;

        configureView();
    }

    private void configureView() {
        titleView.setText(product.getTitle());
        currentPriceView.setText(product.getPrice().getCurrent());
        installmentsView.setText(product.getPrice().getInstallment());
    }
}
