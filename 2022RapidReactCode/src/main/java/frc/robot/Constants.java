// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
   /**
    * public static final class DriveConstants {
    *   public static final int kLeftMotor1Port = 0;
    *   public static final int kLeftMotor2Port = 1;
    *   public static final int kRightMotor1Port = 2;
    *   public static final int kRightMotor2Port = 3; 
    * }
    */ 
    public static final class ShootingConstants {
    // ***********************************************************************************************
    // TODO: Catch Errors when developers use an out of bounds index
    // ***********************************************************************************************
        public enum ShootingPosition {
            TARMAC      (0),      // Shooting from Tarmac
            LAUNCH1     (1),      // Shooting from Launch Pad 1
            LAUNCH2     (2),      // Shooting from Launch Pad 2
            TERMINAL    (3),      // Shooting from Terminal
            DISTANCE    (4),       // Shooting Based on Distance from LimeLight
            TEST1a      (5),
            TEST1b      (6),
            TEST1c      (7),
            TEST1d      (8),
            TEST1e      (9),
            TEST2a      (10),
            TEST2b      (11),
            TEST2c      (12),
            TEST2d      (13),
            TEST2e      (14),
            TEST3a      (15),
            TEST3b      (16),
            TEST3c      (17),
            TEST3d      (18),
            TEST3e      (19)

            ;

            private final int shootingPositionIndex;
    
            ShootingPosition(int shootingPositionIndex) {
                this.shootingPositionIndex = shootingPositionIndex;
            }
        
            public int getShootingPositionIndex() {
                return this.shootingPositionIndex;
            } 
        }

        public enum SubSystem{
            BELT        (0),
            FLYWHEEL    (1),
            REARSHOOTER (2);

            private final int shootingSubSystemIndex;

            SubSystem (int shootingSubSystemIndex) {
                this.shootingSubSystemIndex = shootingSubSystemIndex;
            }

            public int getShootingSubSystemIndex () {
                return this.shootingSubSystemIndex;
            }
        }

        private double[][] shootingSpeeds = {
            {0.6, 80, 980},         // TARMAC (ABOUT 60")  -- NOTE: The RearShooter was at 3325 at tournament, but the actual velocity achieved was 980
            {0.0, 0.0, 0},          // LAUNCH PAD 1 (ABOUT 145")
            {0.0, 0.0, 0},          // LAUNCH PAD 2 (ABOUT 187")
            {0.0, 0.0, 0},          // TERMINAL (ABOUT 248")
            {0.0, 0.0, 0},           // DISTANCE
            // TEST1a      (5),
            // TEST1b      (6),
            // TEST1c      (7),
            // TEST1d      (8),
            // TEST1e      (9),
            // TEST2a      (10),
            // TEST2b      (11),
            // TEST2c      (12),
            // TEST2d      (13),
            // TEST2e      (14),
            // TEST3a      (15),
            // TEST3b      (16),
            // TEST3c      (17),
            // TEST3d      (18),
            // TEST3e      (19)
        };

        public double getShootingSpeed(ShootingPosition position, SubSystem subSystem) {
            double speed = shootingSpeeds[position.getShootingPositionIndex()][subSystem.getShootingSubSystemIndex()];
            return speed;
        }

    }
}

