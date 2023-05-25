package jp.jaxa.iss.kibo.rpc.defaultapk;


import java.util.List;
import org.opencv.core.Mat;

import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import com.google.zxing.qrcode.decoder.Decoder;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1(){

        api.startMission();

        api.saveBitmapImage(api.getBitmapNavCam(), "out.jpg");

        moveTo(15d, 1d, 5d, 0f, 0f, 0f, 1f);

        api.laserControl(true);

        int target_id = 1;
        api.takeTargetSnapshot(target_id);

        api.flashlightControlFront(0.05f);

        String mQrContent = readQR();

        api.flashlightControlFront(0.00f);

        api.notifyGoingToGoal();
        api.reportMissionCompletion(mQrContent);

    }

    @Override
    protected void runPlan2(){
        // write your plan 2 here
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
    }

    private String readQR(){
        return "your method";
    }

    private void moveTo(double pos_x, double pos_y, double pos_z,
                               double qua_x, double qua_y, double qua_z,
                               double qua_w){

        final int LOOP_MAX = 3;
        final Point point = new Point(pos_x, pos_y, pos_z);
        final Quaternion quaternion = new Quaternion((float)qua_x, (float)qua_y,
                (float)qua_z, (float)qua_w);

        int loopCounter = 0;
        while(loopCounter < LOOP_MAX){
            api.moveTo(point, quaternion, true);
            ++loopCounter;
        }
    }

}

