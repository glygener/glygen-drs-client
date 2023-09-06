package org.glygen.drsclient;

import org.glygen.drsclient.model.AccessURL;
import org.glygen.drsclient.model.DrsError;
import org.glygen.drsclient.model.DrsObject;

public class DrsClientApp {

    public static void main(String args[]) {
        DRSClient client = new DRSClientImpl();
        
        Object drsObject = client.getDrsObject("drs://glygen.ccrc.uga.edu/1671659988718");
        if (drsObject instanceof DrsObject) {
            DrsObject drs = (DrsObject) drsObject;
        } else {
            System.out.println(((DrsError)drsObject).getMsg());
        }
        
        Object accessURL = client.getAccessURL("drs://glygen.ccrc.uga.edu/167165998871812");
        if (accessURL instanceof AccessURL) {
            System.out.println ("Access URL: " + ((AccessURL)accessURL).getUrl());
        } else {
            System.out.println(((DrsError)accessURL).getMsg());
        }
        
        accessURL = client.getAccessURL("drs://glygen.ccrc.uga.edu/1671659988718");
        if (accessURL instanceof AccessURL) {
            System.out.println ("Access URL: " + ((AccessURL)accessURL).getUrl());
        } else {
            System.out.println(((DrsError)accessURL).getMsg());
        }
    }
}
