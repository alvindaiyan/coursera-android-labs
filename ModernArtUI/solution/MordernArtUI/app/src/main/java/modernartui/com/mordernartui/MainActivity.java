package modernartui.com.mordernartui;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private DialogFragment dialog;
    private List<ColorView> views;

    private class ColorView
    {
        View view;
        int color;

        private ColorView(View view, int color) {
            this.view = view;
            this.color = color;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.views = new ArrayList<>();

        views.add(new ColorView(findViewById(R.id.view1), getResources().getColor(R.color.view1)));
        views.add(new ColorView(findViewById(R.id.view2), getResources().getColor(R.color.view2)));
        views.add(new ColorView(findViewById(R.id.view3), getResources().getColor(R.color.view3)));
        views.add(new ColorView(findViewById(R.id.view4), getResources().getColor(R.color.view4)));
        views.add(new ColorView(findViewById(R.id.view5), getResources().getColor(R.color.view5)));

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for(ColorView view : views) {
                    int originalColor = view.color;
                    int invertedColor = ( 0x00FFFFFF - ( originalColor | 0xFF000000 ) ) |
                            ( originalColor & 0xFF000000 );

                    if ( getResources().getColor( R.color.white ) != originalColor &&
                            getResources().getColor( R.color.lightgray ) != originalColor ) {

                        int origR = ( originalColor >> 16 ) & 0x000000FF;
                        int origG = ( originalColor >> 8 ) & 0x000000FF;
                        int origB = originalColor & 0x000000FF;

                        int invR = ( invertedColor >> 16 ) & 0x000000FF;
                        int invG = ( invertedColor >> 8 ) & 0x000000FF;
                        int invB = invertedColor & 0x000000FF;

                        view.view.setBackgroundColor(Color.rgb(
                                (int) (origR + (invR - origR) * (progress / 100f)),
                                (int) (origG + (invG - origG) * (progress / 100f)),
                                (int) (origB + (invB - origB) * (progress / 100f))));
                        view.view.invalidate();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void showDialog(){
        dialog = MoreInformationDialog.newInstance();
        dialog.show(getFragmentManager(), MainActivity.class.getName());

    }
}
