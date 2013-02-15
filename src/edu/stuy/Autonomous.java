/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy;

import edu.stuy.subsystems.Acquirer;
import edu.stuy.subsystems.Conveyor;
import edu.stuy.subsystems.Drivetrain;
import edu.stuy.subsystems.Shooter;

/**
 * This is the location of the autonomous routines.
 * We assume that we always begin touching the front edge of the pyramid.
 * This gives us two discs.
 * @author Eric
 */
public class Autonomous {
    
    public static void run(int setting) {
        switch (setting) {
            case 1: 
                auton1();
                break;
            case 2: 
                auton2();
                break;
            case 3:
                auton3();
                break;
            case 4:
                auton4();
                break;
            case 5:
                auton5();
                break;
            case 6:
                auton6();
                break;
            case 7:
                auton7();
                break;
            case 8:
                auton8();
                break;
        }
    }
    
    /*
     * Start in front of pyramid in any position. 
     * Use CV to shoot 2.
     */
    public static void auton1() {
        // add CV
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
    }
      
    /*
     * Start in front of back bar of pyramid. (Can be used in a variety of positions).
     * Use CV to shoot 3.
     */
    public static void auton2() {
        // add CV 
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
    } 
    
    /*
     * Start in front of middle pyramid.
     * Use CV to shoot 2. Go back under pyramid and pick up 2. 
     * Drive forward. Use CV to fire those two. 180 degree spin at end. 
     */
    public static void auton3() {
        // add CV
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().forwardInchesRough(-Constants.PYRAMID_BASE_LENGTH / 2.0);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().forwardInchesRough(Constants.PYRAMID_BASE_LENGTH / 2.0);
        Shooter.getInstance().runShooterOut();
        Drivetrain.getInstance().spin180();
    }
    
    /*
     * Start middle in front of pyramid.
     * Use CV to shoot 2. Back up and acquire disks under pyramid and at center of field. 
     * Don?t stop for pickup. After acquired, drive forward to in front of pyramid. 
     * Stop running acquirer on the way back. Use CV to shoot the 4 acquired disks. Spin 180 at end.
     */ 
    public static void auton4() {
        // add CV
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().forwardInchesRough(-Constants.FRONT_PYRAMID_TO_CENTER);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().forwardInchesRough(Constants.FRONT_PYRAMID_TO_CENTER);
        Shooter.getInstance().runShooterOut();
        Drivetrain.getInstance().spin180();
    }
    
    /*
     * Start at the back of the pyramid.
     * Shoot 3. Back up and acquire disks at center of field. 
     * Return and shoot 4.
     * You might have 4 depending on how many people have left there from other robots.
     */
    public static void auton5() {
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
        Drivetrain.getInstance().forwardInchesRough(-Constants.CENTER_TO_BACK_OF_PYRAMID);
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().forwardInchesRough(Constants.CENTER_TO_BACK_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Shooter.getInstance().runShooterOut();
    } 
   
    /*
     * Do nothing.
     */
    public static void auton6() {
        
    }   
    
    // DO NOT TEST THESE UNTIL COMPETITION. DRIVER PRACTICE FIRST!
    // DO NOT CROSS COMPLETELY INTO THE OTHER SIDE OF THE FIELD!
    // -------------------------------------------------------------------------
    /*
     * Same as 4 with NO 180 SPIN but back up to center at end of field 
     */
    public static void auton7() {
        // add CV
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().forwardInchesRough(-Constants.FRONT_PYRAMID_TO_CENTER);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().forwardInchesRough(Constants.FRONT_PYRAMID_TO_CENTER);
        Shooter.getInstance().runShooterOut();
        Drivetrain.getInstance().forwardInchesRough(-Constants.FRONT_PYRAMID_TO_CENTER);
    }
    
    /*
     * Same as 3 but NO 180 SPIN, backup to center of field. 
     */
    public static void auton8()  {
        // add CV
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().runShooterOut();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().forwardInchesRough(-Constants.PYRAMID_BASE_LENGTH / 2.0);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().forwardInchesRough(Constants.PYRAMID_BASE_LENGTH / 2.0);
        Shooter.getInstance().runShooterOut();
        Drivetrain.getInstance().forwardInchesRough(-Constants.FRONT_PYRAMID_TO_CENTER);
    }   
}
