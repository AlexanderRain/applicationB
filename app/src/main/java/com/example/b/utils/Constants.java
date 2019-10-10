package com.example.b.utils;

public class Constants {

    public static final String RECEIVER = "com.example.a.receiver.IntentReceiver";
    public static final String HANDLER_PACKAGE = "com.example.a";
    public static final String IMAGE_ACTION = "image_action";

    public static final String IMAGE_URL = "image_url";
    public static final String IMAGE_STATUS = "image_status";
    public static final String IMAGE_ID = "image_id";
    public static final long DEFAULT_ID = -1;
    public static final String IMAGE_DATE = "image_date";

    //DEFAULT status generated when app receive link from test tab
    public static final int DEFAULT = 0;
    public static final int INSERTED = 1;
    public static final int ERROR = 2;
    public static final int UNDEFINED = 3;

    //Intent actions for tab history
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static final String SERVICE_NAME = "ImageDownloadService";
    public static final String PATH = "/BIGDIG/test/B";
    public static final String JPG = ".jpg";

    //Permissions
    public static final int WRITE_EXTERNAL_PERMISSION = 10;
}
