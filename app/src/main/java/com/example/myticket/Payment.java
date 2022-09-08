package com.example.myticket;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Payment extends AppCompatActivity implements PaymentResultListener {
    int [] seats={45,67};//getIntent().getIntArrayExtra("seats");
    List<Integer> list = Arrays.stream(seats).boxed().collect(Collectors.toList());
    float price=list.size()*220;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_not_found);
        Checkout.preload(getApplicationContext());
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_YvUmzQYElkCdy7");
        checkout.setImage(R.drawable.splash);

        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Movie Ticket");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", ""+price*100);//pass amount in currency subunits
            options.put("prefill.email", "vadherravi672@gmail.com");
            options.put("prefill.contact","9327346944");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("Ravi:","Complete",e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        FirebaseDatabase db= FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference();
        DatabaseReference ref1 = db.getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user=mAuth.getCurrentUser().getUid();
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String date=df.format(c);
        SeatAdapter sa =new SeatAdapter(null,getApplicationContext(),null);
        String title=getIntent().getStringExtra("m_title");
        UserTicketModel model=new UserTicketModel(user,title,price,list,date+"");
        ref.child(user).child(title).setValue(model);
        TicketModel ticketModel = new TicketModel(title,list,date);
        ref1.child(title).setValue(ticketModel);
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"Success: "+s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Failed: "+s,Toast.LENGTH_LONG).show();
    }
}