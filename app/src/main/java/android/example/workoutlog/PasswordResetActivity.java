package android.example.workoutlog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "PasswordResetActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordreset);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.reset).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.reset:
                    reset();
                    break;
            }
        }
    };

    private void reset() {
        String email = ((EditText) findViewById(R.id.emailtext)).getText().toString();

        if (email.length() > 0) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                                startToast("임시 비밀번호 이메일을 보냈습니다.");
                            }
                        }
                    });
        } else {
            startToast("빈 칸을 모두 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
