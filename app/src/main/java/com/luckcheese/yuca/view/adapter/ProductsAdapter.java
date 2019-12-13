package com.luckcheese.yuca.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luckcheese.yuca.R;
import com.luckcheese.yuca.model.Product;
import com.luckcheese.yuca.view.holder.ProductItemListViewHolder;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final LayoutInflater layoutInflater;

    private List<Product> products;

    public ProductsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = layoutInflater.inflate(R.layout.item_product, parent, false);
        layout.setOnClickListener(this);

        ProductItemListViewHolder holder = new ProductItemListViewHolder(layout);
        layout.setTag(R.id.item_list_holder, holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product p = products.get(position);
        ((ProductItemListViewHolder) holder).setProduct(p);
    }

    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public void onClick(View view) {
        Object tag = view.getTag(R.id.item_list_holder);
        if (tag instanceof ProductItemListViewHolder) {
            Product p = ((ProductItemListViewHolder) tag).getProduct();

        }
    }
}
