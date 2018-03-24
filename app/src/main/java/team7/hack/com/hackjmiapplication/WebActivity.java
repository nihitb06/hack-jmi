package team7.hack.com.hackjmiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        int pos = getIntent().getIntExtra("url", 0);

        String s1 = "https://www.eventshigh.com/delhi/blood+donation+camp";
        String s2 = "https://www.disabled-world.com/calculators-charts/blood-chart.php";
        String s3 = "https://www.organicfacts.net/health-benefits/other/blood-donation.html";
        String s4 = "https://www.justdial.com/Delhi-NCR/Blood-Donation-Centres/nct-10049094";

        WebView wb1 = findViewById(R.id.wb1);
        String arr[] = new String[4];
        arr[0] = s1;
        arr[1] = s2;
        arr[2] = s3;
        arr[3] = s4;


        wb1.loadUrl(arr[pos]);
    }
}
