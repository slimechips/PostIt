package com.example.postit.eventlisting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.postit.R;
import com.example.postit.createevent.EventDetailRowView;
import com.squareup.picasso.Picasso;


public class EventDetailsActivity extends AppCompatActivity {

    // strings for logcat,for Log.i(TAG, msg)
    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = EventDetailsActivity.class.getSimpleName();
    private static final String TAG = COMMON_TAG;

    // initialise Views
    TextView tvEventDetailsTitle;
    ImageView ivEventDetailsImage;
    TextView tvEventDetailsCategory, tvEventDetailsDate, tvEventDetailsTime, tvEventDetailsLocation, tvEventDetailsDescription;
//    EventDetailRowView rvEventDetailsDate;
    EventDetailRowView rvEventDetailsTime;
//    EventDetailRowView rvEventDetailsLocation;
//    EventDetailRowView rvEventDetailsDescription;
    TextView tvJoinTelegramGroupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // bind the xml widget which this activity
        tvEventDetailsTitle = findViewById(R.id.tvEventDetailsTitle);
        ivEventDetailsImage = findViewById(R.id.ivEventDetailsImage);
        tvEventDetailsCategory = findViewById(R.id.tvEventDetailsCategory);
        tvEventDetailsDate = findViewById(R.id.tvEventDetailsDate);
        tvEventDetailsTime = findViewById(R.id.tvEventDetailsTime);
        tvEventDetailsLocation = findViewById(R.id.tvEventDetailsLocation);
        tvEventDetailsDescription = findViewById(R.id.tvEventDetailsDescription);


        // TODO fetch from the somewhere
        String indiviaulEventUrl = "https://api.myjson.com/bins/sglu4";

        new EventDetailsFetcher(this, indiviaulEventUrl, new EventDetailsFetcher.jsonParseListener() {
            @Override
            public void onResponse(String eventDate, String eventCategory, String eventDescription,
                                   String eventLocation, String eventTime, String eventImage) {
                tvEventDetailsDate.setText(eventDate);
                tvEventDetailsCategory.setText(eventCategory);
                Picasso.get().load(eventImage).into(ivEventDetailsImage);
                tvEventDetailsTime.setText(eventTime);
                tvEventDetailsLocation.setText(eventLocation);
                tvEventDetailsDescription.setText(eventDescription);
                Log.i("image", String.valueOf(eventImage));
            }
        });


        // open the telegram group to join that particular group
        tvJoinTelegramGroupButton = findViewById(R.id.tvJoinTelegramGroupButton);
        tvJoinTelegramGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hard code to one group TODO fetch from the database (string formatting the URL)
                Intent openTelegramIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/joinchat/GE6VXBfUL8cw0U5E8hOFag"));
                startActivity(openTelegramIntent);
            }
        });
        Log.i(TAG, ACTIVITY_NAME+" onCreate");

    }


}
