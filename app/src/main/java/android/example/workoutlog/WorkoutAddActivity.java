package android.example.workoutlog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_add);
    }

    //체크박스에서 체크하면 그게 회원정보에 들어갈 수 있도록 설정//
    //한계점 : 운동을 추가할 수 없는거로 쓰기 //

}