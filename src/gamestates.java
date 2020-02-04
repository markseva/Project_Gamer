public class gamestates {

    private static boolean MENU = true;
    private static boolean PAUSE = false;
    private static boolean PLAY = false;

    public static boolean isMENU() {
        return MENU;
    }

    public static void setMENU(boolean MENU) {
        gamestates.MENU = MENU;
    }

    public static boolean isPAUSE() {
        return PAUSE;
    }

    public static void setPAUSE(boolean PAUSE) {
        gamestates.PAUSE = PAUSE;
    }

    public static boolean isPLAY() {
        return PLAY;
    }

    public static void setPLAY(boolean PLAY) {
        gamestates.PLAY = PLAY;
    }
}