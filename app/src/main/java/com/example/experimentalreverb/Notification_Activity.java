package com.example.experimentalreverb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Notification_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String token = Accesstoken.getInstance().getToken();
        new GetNotificationsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, token);
    }


    class GetNotificationsTask extends AsyncTask<String, Void, List<GitHubNotification>> {

        @Override
        protected List<GitHubNotification> doInBackground(String... strings) {
            GitHubApiClient client = new GitHubApiClient(strings[0]);
            try {
                Log.d("P", "doInBackground: " + " " + "Getting notifications");
                return client.getNotifications();
            } catch (IOException e) {
                Log.d("P", "onCreate: " + " " + "Error in getting notifications" + " " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<GitHubNotification> notifications) {
            String[] subjects = new String[0];
            String[] lastUpdatedDates = new String[0];
            String[] times = new String[0];
            if (notifications != null) {
                subjects = new String[notifications.size()];
                lastUpdatedDates = new String[notifications.size()];
                times = new String[notifications.size()];
                for (int i = 0; i < notifications.size(); i++) {
                    GitHubNotification notification = notifications.get(i);
                    subjects[i] = notification.getSubject().getTitle();
                    String date = notification.getUpdated_at();
                    String[] dateParts = date.split("T");
                    lastUpdatedDates[i] = dateParts[0];
                    times[i] = dateParts[1].split("Z")[0];
                }
                Log.d("Profile", "Subjects: " + Arrays.toString(subjects));
                Log.d("Profile", "Last updated dates: " + Arrays.toString(lastUpdatedDates));
                Log.d("Profile", "Times: " + Arrays.toString(times));
            }
            // Load the text views from the XML layout
            TextView notification1SubjectTextView = findViewById(R.id.notification1_subject);
            TextView notification1DateTextView = findViewById(R.id.notification1_date);
            TextView notification1TimeTextView = findViewById(R.id.notification1_time);
            TextView notification2SubjectTextView = findViewById(R.id.notification2_subject);
            TextView notification2DateTextView = findViewById(R.id.notification2_date);
            TextView notification2TimeTextView = findViewById(R.id.notification2_time);
            TextView notification3SubjectTextView = findViewById(R.id.notification3_subject);
            TextView notification3DateTextView = findViewById(R.id.notification3_date);
            TextView notification3TimeTextView = findViewById(R.id.notification3_time);
            TextView notification4SubjectTextView = findViewById(R.id.notification4_subject);
            TextView notification4DateTextView = findViewById(R.id.notification4_date);
            TextView notification4TimeTextView = findViewById(R.id.notification4_time);
            TextView notification5SubjectTextView = findViewById(R.id.notification5_subject);
            TextView notification5DateTextView = findViewById(R.id.notification5_date);
            TextView notification5TimeTextView = findViewById(R.id.notification5_time);

            // Set the values of the text views
            if (subjects.length > 0) {
                notification1SubjectTextView.setText(subjects[0]);
                notification1DateTextView.setText(lastUpdatedDates[0]);
                notification1TimeTextView.setText(times[0]);
            }
            if (subjects.length > 1) {
                notification2SubjectTextView.setText(subjects[1]);
                notification2DateTextView.setText(lastUpdatedDates[1]);
                notification2TimeTextView.setText(times[1]);
            }
            if (subjects.length > 2) {
                notification3SubjectTextView.setText(subjects[2]);
                notification3DateTextView.setText(lastUpdatedDates[2]);
                notification3TimeTextView.setText(times[2]);
            }
            if (subjects.length > 3) {
                notification4SubjectTextView.setText(subjects[3]);
                notification4DateTextView.setText(lastUpdatedDates[3]);
                notification4TimeTextView.setText(times[3]);
            }
            if (subjects.length > 4) {
                notification5SubjectTextView.setText(subjects[4]);
                notification5DateTextView.setText(lastUpdatedDates[4]);
                notification5TimeTextView.setText(times[4]);
            }

        }
    }
}