package android.example.workoutlog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class MemberInitActivity extends AppCompatActivity {

    private static final String TAG = "MemberInitActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);
        findViewById(R.id.okButton).setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.okButton:
                    memberInit();
                    break;
             }
        }
    };

    private void memberInit() {
        String name = ((EditText) findViewById(R.id.editName)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.editPhone)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.editBirthday)).getText().toString();


        if (name.length() > 0 && phoneNumber.length()>9 && birthday.length()>5 ) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            MemberInfo memberInfo = new MemberInfo(name, phoneNumber, birthday);
            if(user != null){

                db.collection("users").document(user.getUid()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                startToast("회원정보가 등록되었습니다.");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                                startToast("회원정보 등록에 실패하였습니다.");
                            }
                        });
            }
        } else {
            startToast("빈 칸을 모두 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
