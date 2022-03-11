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
            TEST1f      (10),

            TEST2a      (11),
            TEST2b      (12),
            TEST2c      (13),
            TEST2d      (14),
            TEST2e      (15),
            TEST2f      (16),

            TEST3a      (17),
            TEST3b      (18),
            TEST3c      (19),
            TEST3d      (20),
            TEST3e      (21),
            TEST3f      (22)

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
            {0.0, 0.0, 0},          // DISTANCE

            {0.6, 4466.32, 0},                // TEST1a      (5),
            {0.6, 4466.32, 930.48},           // TEST1b      (6),
            {0.6, 4466.32, 1395.73},          // TEST1c      (7),
            {0.6, 4466.32, 1860.97},          // TEST1d      (8),
            {0.6, 4466.32, 2326.21},          // TEST1e      (9),
            {0.6, 4466.32, 2791.45},          //TEST1F        (10),

            {0.6, 5104.37, 0},                // TEST2a      (10),
            {0.6, 5104.37, 1063.41},          // TEST2b      (11),
            {0.6, 5104.37, 1595.11},          // TEST2c      (12),
            {0.6, 5104.37, 2126.82},          // TEST2d      (13),
            {0.6, 5104.37, 2658.52},          // TEST2e      (14),
            {0.6, 5104.37, 3190.23},          // TEST2f      (15),


            {0.6, 5742.41, 0},                // TEST3a      (15),
            {0.6, 5742.41, 1196.34},          // TEST3b      (16),
            {0.6, 5742.41, 1794.5},           // TEST3c      (17),
            {0.6, 5742.41, 2392.67},          // TEST3d      (18),
            {0.6, 5742.41, 2990.84},          // TEST3e      (19),
            {0.6, 5742.41, 3589.01},          // TEST3f      (20),

        };

        public double getShootingSpeed(ShootingPosition position, SubSystem subSystem) {
            double speed = shootingSpeeds[position.getShootingPositionIndex()][subSystem.getShootingSubSystemIndex()];
            return speed;
        }

    }
}

