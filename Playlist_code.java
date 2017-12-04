//Playlist Shuffed

public void shuffle (){
    newList = new ArrayList<Mp3> ();
    while (songs.size()>0){
        Mp3 song = songs.get(nextInt(songs.size()));
        songs.remove(song); // the forgotten step
        newList.add(song);
        System.out.println("new list" + newList);
   }
 }
 
 
 import java.util.Random;

public class SuffleSongs {

    public static void main(String[] args) {

        List<String> playList = new ArrayList<String>();
        playList.add("Song1");
        playList.add("Song2");
        playList.add("Song3");
        playList.add("Song4");
        playList.add("Song5");
        playList.add("Song6");
        playList.add("Song7");
        playList.add("Song8");
        playList.add("Song9");
        playList.add("Song10");
        playList.add("Song11");
        playList.add("Song12");
        playList.add("Song13");
        playList.add("Song14");
        playList.add("Song15");
        playList.add("Song16");
        playList.add("Song17");
        playList.add("Song18");
        playList.add("Song19");
        playList.add("Song20");
        playList.add("Song21");

        // shuffle the playlist
        for (int i=0; i<playList.size(); ++i) {
            Random rand = new Random();
            int temp = rand.nextInt(playList.size() -i) + i;
            Collections.swap(playList, i, temp);
        }

        // print the shuffled playlist
        for(int j = 0; j < playList.size(); ++j) {
            System.out.println(playList.get(j));
        }

    }

}


//Code for mp3 playlist 

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    Timer timer;
    MediaPlayer mp;
    ArrayList<Integer> playlist;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playlist = new ArrayList<>();
        playlist.add(R.raw.a1);
        playlist.add(R.raw.a2);
        mp = MediaPlayer.create(this,playlist.get(0));
        mp.start();
        timer = new Timer();
        if (playlist.size()>1) playNext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playNext() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mp.reset();
                mp = MediaPlayer.create(MainActivity.this,playlist.get(++i));
                mp.start();
                if (playlist.size() > i+1) {
                    playNext();
                }
            }
        },mp.getDuration()+100);
    }

    @Override
    public void onDestroy() {
        if (mp.isPlaying())
            mp.stop();
        timer.cancel();
        super.onDestroy();
    }
}