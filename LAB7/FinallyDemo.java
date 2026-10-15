public class FinallyDemo {

    @SuppressWarnings("finally")
    static int tricky() {
        try {
            System.out.println("in try, about to return 1");
            return 1;
        } finally {
            System.out.println("in finally (runs BEFORE the method actually returns)");
        }
    }

    // A value returned from try is saved first, so changing the variable in finally does not change it
    static int valueSaved() {
        int x = 10;
        try {
            return x;
        } finally {
            x = 99;
            System.out.println("finally set x = " + x);
        }
    }

    public static void main(String[] args) {
        System.out.println("tricky() returned " + tricky());
        System.out.println("valueSaved() returned " + valueSaved());
    }
}

/*
PREDICTION for tricky():
   in try, about to return 1
   in finally (runs BEFORE the method actually returns)
   tricky() returned 1
WHY: 'return' does not leave the method immediately. The JVM evaluates the return value, then
runs the finally block (finally runs whether the try ends by return, exception or normal
completion), and only then returns. Only System.exit(), a JVM crash or an infinite loop stops it.
valueSaved() returns 10, not 99, because the return value (10) was already copied before finally ran.
*/
