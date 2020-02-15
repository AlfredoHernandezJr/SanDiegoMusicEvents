package edu.miracosta.cs134.sandiegomusicevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.JSONLoader;
import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class MainActivity extends AppCompatActivity {

    private List<MusicEvent> eventsList;
    private MusicEventListAdapter mMusicEventListAdapter ;
    private ListView mMusicEventsListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pull data from JSON
        try {
            eventsList = JSONLoader.loadJSONFromAsset(this) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: Wire up the musicEventsListView
        mMusicEventsListView = findViewById(R.id.musicEventListView) ;

        // TODO: Instantiate a new ListAdapter (MusicEventListAdapter)
        mMusicEventListAdapter = new MusicEventListAdapter(this, R.layout.event_list_item, eventsList) ;

        // TODO: Set the adapter of the list view to the newly instantiated adapter
        mMusicEventsListView.setAdapter(mMusicEventListAdapter) ;

    }

    public void openEventDetails(View v)
    {
        // Extract the "tag"
        MusicEvent clickedEvent = (MusicEvent) v.getTag() ;

        // Set up an intent.
        Intent intent = new Intent(this, EventDetailsActivity.class) ;

        // Fill the intent with the details about the clicked event.
        /** Added the image name to the intent. */
        intent.putExtra("ImageName", clickedEvent.getImageName()) ;

        intent.putExtra("Artist", clickedEvent.getArtist()) ;
        intent.putExtra("Date", clickedEvent.getDate()) ;

        intent.putExtra("Day", clickedEvent.getDay()) ;
        intent.putExtra("Time", clickedEvent.getTime()) ;

        intent.putExtra("Venue", clickedEvent.getVenue()) ;
        intent.putExtra("City", clickedEvent.getCity()) ;

        intent.putExtra("State", clickedEvent.getState()) ;

        // Go to (startActivity) event details.
        startActivity(intent) ;
    }
}
