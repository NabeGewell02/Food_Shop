 package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

 public class OrderActivity extends AppCompatActivity {

     String[] addresses = {"talyshevmihail06@gmail.com"};
     String subject = "Order from food shop";
     String emailText;
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
        emailText = "Имя пользователя: " + userName + "\n"
                + "Наименование товара: " + goodsName + "\n"
                + "Количество товаров: "  + quantity + "\n"
                + "Цена за единицу товара: " + Price + "\n"
                + "Цена заказа: "  + orderPrice;
        orderTextView.setText(emailText);

    }

     public void otpravit_zakaz(View view)
     {
         Intent intent = new Intent(Intent.ACTION_SENDTO);
         intent.setData(Uri.parse("mailto:"));
         intent.putExtra(Intent.EXTRA_EMAIL, addresses);
         intent.putExtra(Intent.EXTRA_SUBJECT, subject);
         intent.putExtra(Intent.EXTRA_TEXT, emailText);
         if (intent.resolveActivity(getPackageManager()) != null){
             startActivity(intent);
         }
     }

 }