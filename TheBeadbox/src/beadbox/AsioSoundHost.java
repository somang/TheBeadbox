/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadbox;

import com.synthbot.jasiohost.AsioChannel;
import com.synthbot.jasiohost.AsioDriver;
import com.synthbot.jasiohost.AsioDriverListener;
import com.synthbot.jasiohost.AsioDriverState;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Albert@imdc
 */
public class AsioSoundHost implements AsioDriverListener {
    
    private AsioDriver driver;
    private int bufferSize;
    private double sampleRate;
    private Set<AsioChannel> activeChannels;
    
    private static float[][] waveFile;
    private static boolean play = false;
    private int currentSample, volume;
    
    private float out[];
    private float chan[][];
    
    public AsioSoundHost ( AsioDriver driver ) {
        if ( driver != null ) {
            this.driver = driver;
            this.driver.addAsioDriverListener ( this );
            
            this.bufferSize = this.driver.getBufferPreferredSize();
            this.sampleRate = this.driver.getSampleRate();
            
            this.activeChannels = new HashSet();
            for ( int i = 0; i < 8; i++ ) {
                activeChannels.add ( this.driver.getChannelOutput ( i ) );
            }
            this.driver.createBuffers ( activeChannels );
            
            waveFile = null;
            play = false;
            currentSample = 0;
            
            chan = new float[9][];
            out = new float[this.bufferSize];
            for (int n = 0; n<8; n++){
                chan[n]=out;
            }
            
        }
    }
    
    public void bufferSwitch ( long systemTime, long samplePosition, Set<AsioChannel> channels ) {        
        if ( play ) {
            //should load the samples and play it
            int max = waveFile.length;
            for (int n = 0; n<max; n++){
                for ( int i = 0; i < this.bufferSize; i++, currentSample++ ) {
                    if ( currentSample < waveFile[n].length ) {
                        chan[n][i] = waveFile[n][currentSample]*volume/10000;
                    }
                    else {
                        chan[n][i] = 0;
                    }
                }
                for ( AsioChannel channelInfo : channels ) {
                    if ( channelInfo.getChannelIndex() == n+1 ) {
                        channelInfo.write (chan[n]);
                    }
                }
            }
        }        
    }
    
    public void setVolume(int vol){
        volume=vol;
    }
        
    public static void playWaveFile ( float[][] file ) {
        play = true;
        waveFile = file;
    }
    
    public void bufferSizeChanged(int bufferSize) {
        System.out.println("bufferSizeChanged() callback received.");
    }
    
    public void latenciesChanged(int inputLatency, int outputLatency) {
        System.out.println("latenciesChanged() callback received.");
    }
    
    public void resetRequest() {
        /*
         * This thread will attempt to shut down the ASIO driver. However, it will
         * block on the AsioDriver object at least until the current method has returned.
         */
        new Thread() {
            @Override
            public void run() {
                System.out.println("resetRequest() callback received. Returning driver to INITIALIZED state.");
                driver.returnToState ( AsioDriverState.INITIALIZED );
            }
        }.start();
    }
    
    public void resyncRequest() {
        System.out.println("resyncRequest() callback received.");
    }
    
    public void sampleRateDidChange(double sampleRate) {
        System.out.println("sampleRateDidChange() callback received.");
    }
    
    public double getSampleRate() {
        return this.sampleRate;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
   
    public void output ( int channel, float[] output ) throws InterruptedException {
        for ( AsioChannel channelInfo: this.activeChannels ) {
            if ( channelInfo.getChannelIndex() == channel ) {                 
                channelInfo.write ( output );
            }
        }
    }
}
