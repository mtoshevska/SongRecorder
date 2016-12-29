package das.songrecorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Toni on 26.11.2016.
 */

public class SongAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private Context context;
    private Player player;
    private Saver saver;

    public SongAdapter(Context c){
        super();
        context=c;
        songs=new ArrayList<Song>();
        player=Player.getInstance();
        saver=Saver.getInstance();
    }

    public void addSongs(ArrayList<Song>s){
        songs.addAll(s);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_item_layout,parent,false);
        }
        TextView title=(TextView)convertView.findViewById(R.id.name_artist);
        TextView author=(TextView)convertView.findViewById(R.id.author);
        TextView duration=(TextView)convertView.findViewById(R.id.duration);
        ImageButton playButton=(ImageButton)convertView.findViewById(R.id.playButton);
        ImageButton deleteButton=(ImageButton)convertView.findViewById(R.id.deleteButton);
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
        playButton.setImageResource(R.drawable.play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Play(new File(songs.get(position).getLocation()));
            }
        });
        deleteButton.setImageResource(R.drawable.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song f=songs.get(position);
                songs.remove(position);
                Delete(f);
            }
        });
        return convertView;
    }

    public void Play(File f){
        player.playSong(f,context);
    }

    public void Delete(Song f){
        saver.discard(f,context);
        this.notifyDataSetChanged();
    }
}
