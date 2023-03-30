 package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

 public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("usernameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double Price = receivedOrderIntent.getDoubleExtra("priceForIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0);

        TextView orderTextView = findViewById(R.id.orderTextView);

        orderTextView.setText("Имя пользователя: " + userName + "\n"
                + "Наименование товара: " + goodsName + "\n"
                + "Количество товаров: "  + quantity + "\n"
                + "Цена за единицу товара: " + Price + "\n"
                + "Цена заказа: "  + orderPrice);
    }
}