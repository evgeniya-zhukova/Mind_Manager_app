package ca.gbc.comp3074.mind_manager_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });

        //button for sharing link on Facebook
        ShareButton shareButton = (ShareButton)findViewById(R.id.btnFBLink);
        shareButton.setShareContent(shareLinkContent);
    }

    //share link on Facebook
    ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
            .setContentUrl(Uri.parse("https://www.georgebrown.ca/programs/computer-programming-and-analysis-program-t177?year=2021"))
            .build();

    private void backToLogin(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }
}