package edu.miracosta.cs134.sandiegomusicevents;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class MusicEventListAdapter extends ArrayAdapter<MusicEvent> {

    // Instance variables for each of the parameters.
    private Context mContext ; // context = activity(MainActivity)
    private int mResource ; // resource id = R.layout.event_list_item
    private List<MusicEvent> mMusicEventList ; // List = hardcode music events.

    public MusicEventListAdapter(@NonNull Context context, int resource, @NonNull List<MusicEvent> objects) {
        super(context, resource, objects);

        mContext = context ;
        mResource = resource ;
        mMusicEventList = objects ;
    }

    // Override a method called getView
    // Ctrl + o => override

    @NonNull
    @Override //        position in list
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(mResource, null) ;

        MusicEvent selectedEvent = mMusicEventList.get(position) ;

        // Wire up the linear layout


        LinearLayout musicEventLinearLayout = view.findViewById(R.id.musicEventListLinearLayout) ;


        // "Inflate" the information about the artist name and date.
        TextView musicEventListTextView = view.findViewById(R.id.musicEventListTextView) ;
        musicEventListTextView.setText(selectedEvent.getArtist()) ;

        TextView musicEventListDateTextView = view.findViewById(R.id.musicEventListDateTextView) ;
        musicEventListDateTextView.setText(selectedEvent.getDate()) ;

        // Set its tag (hidden locker) to be the selected music event.
        musicEventLinearLayout.setTag(selectedEvent) ;


        ImageView musicEventimageView = view.findViewById(R.id.musicEventListImageView) ;
        AssetManager am = mContext.getAssets() ;

        try
        {
            InputStream stream = am.open(selectedEvent.getImageName()) ;
            Drawable image = Drawable.createFromStream(stream, selectedEvent.getArtist()) ;
            musicEventimageView.setImageDrawable(image) ;
        } catch (IOException e)
        {
            Log.e("SD Music Events", "Error loading " + selectedEvent.getArtist(), e) ;
        }

        return view ;
    }
}
