//Play/Pause button

//Question:

In my project, I am playing music file in android media player by using the following code

MediaPlayer mPlayer = MediaPlayer.create(MyActivity.this, R.raw.myfile);
mPlayer.start();
//The above is coded in the onclick of the play button. I want to pause the playback by clicking
//the same button again.ie) single button for play/pause. How shall i do this.

//Answer 1
//You could use simple if-check to handle the pausing. Try this:

if(mPlayer.isPlaying()){
    mPlayer.pause();
} else {
    mPlayer.start();
}



//Answer 2:	Please try this 

final Button bPlay = (Button) findViewById(R.id.bPlay);
MediaPlayer song1 = MediaPlayer.create(tutorialFour.this, R.raw.fluet);
Button bStop = (Button) findViewById(R.id.bStop);
bPlay.setWidth(10);

song1.setOnCompletionListener(new OnCompletionListener() {

        public void onCompletion(MediaPlayer mp) {

            bPlay.setText("Play");


        }
    });

bPlay.setOnClickListener(new View.OnClickListener() {

	public void onClick(View v) {
		// TODO Auto-generated method stub
		b = true;

		if (bPlay.getText().equals("Play") && b == true) {

			song1.start();

			bPlay.setText("Pause");
			b = false;
		} else if (bPlay.getText().equals("Pause")) {
			x = song1.getCurrentPosition();
			song1.pause();
			bPlay.setText("Resume");
			Log.v("log", "" + x);
			b = false;
		} else if (bPlay.getText().equals("Resume") && b == true) {
			song1.seekTo(x);
			song1.start();
			bPlay.setText("Pause");
			b = false;
		}


	}

});
	

//Inside the button click check for mediaPlayer.isPlaying(). This will return true if the media player is 
//playing else false.

//So now with this, flag value you can make a if statement and switch to play or pause like this,
button.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {

            if (mediaplayer.isPlaying()) {
                mediaplayer.pause();
            } else {
                mediaplayer.start();
            }
        }
    });
	

//Below code takes care of your play/pause button click event along with forward and backward buttons 
//for forward and backward seek on the seekbar provided (which is synchronized with the media track). 
//Currently it plays just ONE song. However, you can add to that. This is my first media player using 
//mediaplayer class, so you might find it a bit primitive. However if you need you can also check out the 
//VideoView examples. It's apparently easier with VideoView as the standard media console is already present 
//in the form of pre-defined widgets. so that makes designing the player much easier.

package in.org.Test;

import android.app.Activity;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;

import android.widget.ProgressBar;
import android.widget.SeekBar;

import android.widget.Toast;

public class Test12Activity extends Activity implements OnClickListener,Runnable {

    private static final String isPlaying = "Media is Playing"; 
    private static final String notPlaying = "Media has stopped Playing"; 

    private SeekBar seek;

    MediaPlayer player = new MediaPlayer();
     private ImageButton plus,minus;

    ImageButton im;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        plus = (ImageButton) findViewById(R.id.imageButton2);
        minus = (ImageButton) findViewById(R.id.imageButton3);


        player = MediaPlayer.create(this, R.raw.sound2);


        player.setLooping(false); 
        im = (ImageButton) this.findViewById(R.id.imageButton1);


        seek = (SeekBar) findViewById(R.id.seekBar1);
        seek.setVisibility(ProgressBar.VISIBLE);
        seek.setProgress(0);
        seek.setMax(player.getDuration());
        new Thread(this).start();


        im.setOnClickListener(this);
        player.start(); 

        Toast.makeText(this, isPlaying, Toast.LENGTH_LONG).show();


        plus.setOnClickListener(new OnClickListener()   {
            @Override
            public void onClick(View v) { int cu = player.getCurrentPosition();  player.seekTo(cu-5000); }});
        minus.setOnClickListener(new OnClickListener()  {
            @Override
            public void onClick(View v) {int cu = player.getCurrentPosition();  player.seekTo(cu+5000);}});


    }


    @Override
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.imageButton1) {
        if(player.isPlaying())  {
        player.pause();

        Toast.makeText(this, notPlaying, Toast.LENGTH_LONG).show();
        ImageButton img1=(ImageButton) this.findViewById(R.id.imageButton1);

        img1.setImageResource(R.drawable.play);

         }
        else
        {
            player.start();
            Toast.makeText(this, isPlaying, Toast.LENGTH_LONG).show();

            ImageButton img1=(ImageButton) this.findViewById(R.id.imageButton1);
            img1.setImageResource(R.drawable.pause);
        }   
        }
      }

    @Override
    public void run() {
        int currentPosition= 0; String s;
        int total = player.getDuration();
        while (player!=null && currentPosition<total) {
            try {
                Thread.sleep(1000);
                currentPosition= player.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }            

            seek.setProgress(currentPosition);


        }


    }
}
	
	
	
	
	
	
	