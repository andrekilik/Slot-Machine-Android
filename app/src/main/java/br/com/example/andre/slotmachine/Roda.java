package br.com.example.andre.slotmachine;

/**
 * Created by Andre on 13/05/17.
 */

public class Roda extends Thread {
    interface RodaListener{
        void NewImage(int ResourceID);
    }

    private static int[] imageSlots = {R.drawable.astalos, R.drawable.gammoth,
            R.drawable.glavenus, R.drawable.duramboros,R.drawable.lagiacrus, R.drawable.mizutsune,
    R.drawable.malfestio};
    public int curInd;
    private RodaListener rodaListener;
    private long frameDuration, startsIn;
    private boolean hasStarted;

    public Roda(RodaListener rodaListener, long frameDuration, long startsIn){
        this.rodaListener = rodaListener;
        this.frameDuration = frameDuration;
        this.startsIn = startsIn;
        curInd = 0;
        hasStarted = true;
    }

    public void nextImage(){
        curInd++;
        if (curInd == imageSlots.length){
            curInd = 0;
        }
    }
    @Override
    public void run(){
        try {
            Thread.sleep(startsIn);
        }catch (InterruptedException e){
    }

    while(hasStarted){
        try {
            Thread.sleep(startsIn);
        }catch (InterruptedException e){
        }
        nextImage();

        if (rodaListener != null){
            rodaListener.NewImage(imageSlots[curInd]);
        }

        }
    }
    public void stopSpin(){
        hasStarted = false;
    }
}
