package eu.nexanet.chattify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setup();
    }

    private void init() {
        profileImage = findViewById(R.id.profileImage);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void setup() {
        profileImage.setOnClickListener(this::profileImageAction);
        btnLogin.setOnClickListener(this::btnLoginAction);
    }


    private void profileImageAction(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
    }

    private void btnLoginAction(View v) {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (data != null) {
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        String dest = UUID.randomUUID().toString() + ".jpg";
                        Uri dest_uri = Uri.fromFile(new File(getCacheDir(), dest));

                        UCrop.of(selectedImageUri, dest_uri)
                                .start(this);
                    }
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                if (data != null) {
                    Uri selectedImageUri = UCrop.getOutput(data);
                    if (selectedImageUri != null) {
                        profileImage.setImageURI(selectedImageUri);
                    }
                }
            }
        }
    }
}