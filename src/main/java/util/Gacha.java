package util;

public class Gacha {
    /**
     * This function is to get the random number between 0 and 1
     * @param min 0
     * @param max 1
     */
    public static Double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
