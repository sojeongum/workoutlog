package android.example.workoutlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class sinupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "SignupActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.register).setOnClickListener(onClickListener);
        findViewById(R.id.backtoLogin).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.register:
                    signUp();
                    break;
                case R.id.backtoLogin:
                    mystartActivity(LoginActivity.class);
                    break;
            }
        }
    };

    private void signUp(){
        String email = ((EditText)findViewById(R.id.emailtext)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordcheck)).getText().toString();

        if(email.length()>0 && password.length()>0 && passwordCheck.length()>0){
            if(password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("가입 완료!");
                                    finish();
                                } else {
                                    if(task.getException() != null) {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        startToast(task.getException().toString());
                                    }
                                }
                            }
                        });
            }else{
                startToast("비밀번호가 일치하지 않습니다.");
            }
        }else{
            startToast("빈 칸을 모두 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void mystartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
