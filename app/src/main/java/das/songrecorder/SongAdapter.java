package das.songrecorder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Toni on 26.11.2016.
 */

public class SongAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private Context context;

    public SongAdapter(Context c){
        super();
        context=c;
        songs=new ArrayList<Song>();
    }

    public void addSong(Song s){
        songs.add(s);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_item_layout,parent,false);
        }
        TextView title=(TextView)convertView.findViewById(R.id.name_artist);
        TextView author=(TextView)convertView.findViewById(R.id.author);
        TextView duration=(TextView)convertView.findViewById(R.id.duration);
        ImageView image=(ImageView)convertView.findViewById(R.id.imageView4);
        Song s=songs.get(position);
        String t1=context.getResources().getString(R.string.nameAndArtist);
        String t2=TextUtils.htmlEncode(t1);
        String t3=String.format(t2, s.getName(), s.getArtist());
        title.setText(t3);
        t1=context.getResources().getString(R.string.writtenBy);
        t2=TextUtils.htmlEncode(t1);
        t3=String.format(t2, s.getAuthor());
        author.setText(t3);
        t1=context.getResources().getString(R.string.duration);
        t2=TextUtils.htmlEncode(t1);
        t3=String.format(t2, s.getDuration());
        duration.setText(t3);
        image.setImageResource(R.drawable.play);
        return convertView;
    }
}
