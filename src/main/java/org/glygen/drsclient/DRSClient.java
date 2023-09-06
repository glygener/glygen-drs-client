package org.glygen.drsclient;


public interface DRSClient {
    /**
     * 
     * @param drsURI drs URI for the requested file
     * @return DrsObject if successful or DrsError in case of an exception
     */
    Object getDrsObject (String drsURI);
    
    /**
     * 
     * @param drsURI drs URI for the requested file
     * @return AccessURL if successful or DrsError in case of an exception
     */
    Object getAccessURL (String drsURI);
}
