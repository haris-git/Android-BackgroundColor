package YOUR_PACKAGE_DIRECTORY;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /*I implemented the SensorEventListener interface in order to use the light sensor*/
    Button button;
    Button button2;
    Button button3;
    TextView textView;
    
    private RelativeLayout mRelativeLayout;

    SensorManager sensorManager;
    
    Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button3 = (Button)findViewById(R.id.button3);
        
        /*this is the layout that I will change its color*/
        mRelativeLayout = (RelativeLayout)findViewById(R.id.mRelativeLayout);

        /*A click listener for the first button*/
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mRelativeLayout.setBackgroundColor(Color.rgb(12,83,34));
            }
        });

        /*A click listener for the second button*/
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mRelativeLayout.setBackgroundColor(Color.rgb(0,30,154));
            }
        });

        /*Here, I set the light sensor of the device, andtry to read its value*/
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        textView = (TextView)findViewById(R.id.textView);


    }
    
    /*This is a method that is call whenever the "Light Sensor" button is clicked. I didn't used onClick listener. Insted I entered it in the properties tab of button2, in onClick property*/
    public void light(View view){
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    /*This is a method for testing purposes. If you enter it in the properties tab of button2 (onClick property) it will change the color to blu, whenever the button is clicked*/
    public void onButtonClick(View view){
            mRelativeLayout.setBackgroundColor(Color.BLUE);
        }
        
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    
    /*Print the value of the light sensor on screen and change the color*/
        textView.setText(Float.toString(event.values[1]));
        if(event.values[1] > 100){
          mRelativeLayout.setBackgroundColor(Color.rgb(12,83,34));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
