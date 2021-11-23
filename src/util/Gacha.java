package util;

public class Gacha {
    public static Double getRandomNumber(double min, double max) {
        return (double) ((Math.random() * (max - min)) + min);

    }
}
