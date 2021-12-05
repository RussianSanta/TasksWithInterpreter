package ThirdTask;

public class STStateMethodFour {
    public static void run(String message) {
        char[] chars = message.toCharArray();
        int currentState = 1;
        for (char c : chars) {
            currentState = checkState(currentState, c);
            if (currentState == -1) break;
        }
        if (currentState == 5) System.out.println("Yes");
        else System.out.println("No");
    }

    private static int checkState(int state, char c) {
        switch (state) {
            case 1:
                if (c == 'A' || c == 'B') return 2;
            case 2:
                if (c == 'C') return 3;
            case 3:
                if (c == 'A' || c == 'B' || c == 'E') return 4;
            case 4:
                if (c == 'A' || c == 'B' || c == 'E') return 4;
                else if (c == 'D') return 5;
            case 5:
                if (c == 'D') return 5;
            default:
                return -1;
        }
    }
}
