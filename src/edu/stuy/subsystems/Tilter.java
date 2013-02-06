/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.stuy.Constants;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Talon;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


/**
 *
 * @author kevin
 */
public class Tilter {
    
    private static Tilter instance;
    private Talon tilter;
    private ADXL345_I2C accel;
    private Vector measurements10;
    private Timer updateMeasurements10;
    private final int UPDATE_PERIOD_MS10 = 10;
    
    private Tilter() {
        tilter = new Talon(Constants.TILTER_CHANNEL);
        accel = new ADXL345_I2C(Constants.ACCELEROMETER_CHANNEL, ADXL345_I2C.DataFormat_Range.k16G);
        measurements10 = new Vector();
        measurements100 = new Vector();
        start();
    }
    
    public static Tilter getInstance() {
        if (instance == null) {
            instance = new Tilter();
        }
        return instance;
    }
    
    public void tiltUp() {
        tilter.set(1);
    }
    
    public void tiltDown() {
        tilter.set(-1);
    }
    
    public void stop() {
        tilter.set(0);
    }
    
     /**
     * Starts the update thread.
     */
    public void start() {
        accelStop();
        updateMeasurements10 = new Timer();
        updateMeasurements10.schedule(new TimerTask() {

            public void run() {
                synchronized (measurements10) {
                    measurements10.addElement(new Double(getAbsoluteAngle()));
                    if (measurements10.size() > 10) {
                        measurements10.removeElementAt(0);
                    }
                }
            }
        }, 0, UPDATE_PERIOD_MS10);
        
    }
    
    public void accelStop() {
        if (updateMeasurements10 != null) 
            updateMeasurements10.cancel();
        
    }
    
    public void reset() {
        measurements10.removeAllElements();
      
    }
    
    public double getXAcceleration() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kX);
    }
    
    public double getYAcceleration() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kY);
    }
    
    public double getZAcceleration() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kZ);
    }
    
    /* Gets the angle from the measurements of the last 10 accelerations */
    public double getAbsoluteAngle10() {
        if (measurements10.isEmpty()) {
            return 0;
        }
        double sum = 0;
        synchronized (measurements10) {
            for (int i = 0; i < measurements10.size(); i++) {
                sum += ((Double) measurements10.elementAt(i)).doubleValue();
            }
            return sum / measurements10.size();
        }
    }
    
    /* Gets instantaneous angle */
    public double getAbsoluteAngle() {
        return MathUtils.atan(getYAcceleration() / getZAcceleration()) * 180.0 / Math.PI;
    }
    
}
