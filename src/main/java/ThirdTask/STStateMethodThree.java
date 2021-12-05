package ThirdTask;

public class STStateMethodThree {
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
                if (c == 'A') return 2;
            case 2:
                if (c == 'B') return 3;
            case 3:
                if (c == 'A' || c == 'C') return 4;
            case 4:
                if (c == 'B') return 5;
                else if (c == 'A' || c == 'C') return 4;
            default:
                return -1;
        }
    }
}
