package FirstTask;

public class FTStateMethod {
    public static void run(String message) {
        char[] chars = message.toCharArray();
        int currentState = 1;
        for (char c : chars) {
            currentState = checkState(currentState, c);
            if (currentState == 6) break;
        }
        if (currentState == 1 || currentState == 2 || currentState == 6) System.out.println("No");
        else System.out.println("Yes");
    }

    private static int checkState(int state, char c) {
        switch (state) {
            case 1:
            case 5:
                if (Character.isLowerCase(c)) return 6;
                else return 2;
            case 2:
                if (Character.isLowerCase(c)) return 3;
                else return 6;
            case 3:
                if (Character.isLowerCase(c)) return 4;
                else return 2;
            case 4:
                if (Character.isLowerCase(c)) return 5;
                else return 2;
        }
        return -1;
    }
}
