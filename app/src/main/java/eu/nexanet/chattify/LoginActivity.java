package eu.nexanet.chattify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    TextView btnRegister, btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setup();
    }

    private void init() {
        btnRegister = findViewById(R.id.btnRegister);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
    }

    private void setup() {
        btnRegister.setOnClickListener(this::btnRegisterAction);
        btnForgotPassword.setOnClickListener(this::btnForgotPasswordAction);
    }

    void btnRegisterAction(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    void btnForgotPasswordAction(View v) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.show(getSupportFragmentManager(), "ForgotPassword");
    }
}