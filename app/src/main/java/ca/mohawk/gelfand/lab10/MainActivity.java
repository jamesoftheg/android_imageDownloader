package ca.mohawk.gelfand.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * I, James Gelfand,000275852 certify that this material is my original work.
 * No other person's work has been used without due acknowledgement.
 */

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "==MainActivity==";
    private static Activity mainActivity;
    private static ArrayList<Bitmap> picList = new ArrayList<Bitmap>();
    private int count = 0;

    /**
     * onCreate - saves a reference to the current activity     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        Log.d(TAG, "onCreate");
    }

    /**
     * @return a reference to this activity
     */
    public static Activity getMainActivity() {
        return mainActivity;
    }

    public static void addImage(Bitmap image) {
        picList.add(image);
    }

    /**
     * onClick handler for button - read the editText and select an image source * @param view - default (unused)
     */
    public void getImage(View view) {
        // Grab the URL from edit text.
        // If the supplied text is too short, or doesn't include http, download a cat
        EditText input = findViewById(R.id.editText);
        String url = input.getText().toString();
        if (url.toLowerCase().contains("dog")) {
            url = "https://images05.military.com/sites/default/files/2018-03/dog-goggles.jpg";
        } else if (url.length() < 3 || url.toLowerCase().contains("cat") || !url.toLowerCase().contains("http")) {
            url = "http://i.ytimg.com/vi/Uk1RPEQI8mI/maxresdefault.jpg";
        } else if (url.toLowerCase().contains("bird")) {
            url = "https://upload.wikimedia.org/wikipedia/commons/4/45/Eopsaltria_australis_-_Mogo_Campground.jpg";
        }
        Log.d(TAG, "getImage = " + url);
        // We create and execute our AsyncTask
        // Pass in the URL of the image to display
        DownloadImageTask dl = new DownloadImageTask();
        dl.execute(url);
    }

    public void slideshow(View view) {
        Log.d(TAG, "nextImage()");
        TextView test = findViewById(R.id.testView);
        ImageView imageView = findViewById(R.id.imageView);
        if (picList.isEmpty()) {
            Log.d(TAG, "Image NOT found!)");
        }
        else {
            Log.d(TAG, "Image found!");
            if (picList.size() > count) {
                imageView.setImageBitmap(picList.get(count));
                if (count < picList.size()) {
                    count++;
                }
            }
            else {
                count = 0;
                imageView.setImageBitmap(picList.get(count));
                count++;
            }
        }
    }

}

