import java.util.HashMap;

public class AttemptCounter {
    private static AttemptCounter instance;
    private int count = 0;

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public static synchronized AttemptCounter getInstance(){
        if (instance == null){
            instance = new AttemptCounter();
        }
        return instance;
    }


    public int Counter() throws TooManyLoginAttemptsException {
        count += 1;
        if (count>5){
            throw new TooManyLoginAttemptsException();
        }
        return count;
    }

}
