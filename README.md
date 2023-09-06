# glygen-drs-client
DRS java client to test DRS server implementation

Add the following dependency to your project to use the client

        <dependency>
                <groupId>org.glygen</groupId>
                <artifactId>drsclient</artifactId>
                <version>1.0.0</version>
        </dependency>

The jar file is hosted in our Nexus repository:

        <repository>
                <id>nexus</id>
                <url>https://glygen.ccrc.uga.edu/repository/content/repositories/thirdparty/</url>
        </repository>

You can use the client as follows:

        DRSClient client = new DRSClientImpl();
        Object drsObject = client.getDrsObject("drs://glygen.ccrc.uga.edu/1671659988718");
        if (drsObject instanceof DrsObject) {
            DrsObject drs = (DrsObject) drsObject;
        } else {
            System.out.println(((DrsError)drsObject).getMsg());
        }
        
        Object accessURL = client.getAccessURL("drs://glygen.ccrc.uga.edu/1671659988718");
        if (accessURL instanceof AccessURL) {
            System.out.println ("Access URL: " + ((AccessURL)accessURL).getUrl());
        } else {
            System.out.println(((DrsError)accessURL).getMsg());
        }
