package com.sdwfqin.mvpseed.base;

import java.io.File;

public class Constants {

    public static final boolean LOG_TYPE = true;

    /**
     * 路径：/data/data/com.sdwfqin.drrmvp/cache/data
     */
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    /**
     * 路径：/data/data/com.sdwfqin.drrmvp/cache/data/NetCache
     */
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
