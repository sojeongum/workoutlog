package android.example.workoutlog.fragment;

import android.content.Context;
import android.content.Intent;
import android.example.workoutlog.WorkoutAddActivity;
import android.example.workoutlog.sinupActivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.workoutlog.R;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragmentActivity";
    CalendarView calendarView;
    int selectYear, selectMonth, selectDay;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.clickToChange).setOnClickListener(onClickListener);


        calendarView = (CalendarView) view.findViewById(R.id.calenderView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth= month+1;
                selectDay = dayOfMonth;
                String msg;
                msg = Integer.toString(selectYear) + "년 " + Integer.toString(selectMonth)+"월 "+Integer.toString(selectDay)+"일";
                Toast.makeText(getActivity(), msg , Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.clickToChange:
                    myStartActivity(WorkoutAddActivity.class);///class 추가
                    break;
            }
        }
    };




    private void myStartActivity(Class c){
        Intent intent = new Intent(getActivity(), c);
        startActivity(intent);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}