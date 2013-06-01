package com.solutionspratte.InventoryManagement.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.solutionspratte.InventoryManagement.lib.Reference;


import cpw.mods.fml.common.FMLLog;

/**
 * Equivalent-Exchange-3
 * 
 * LogHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LogHelper {

    private static Logger csimLogger = Logger.getLogger(Reference.MOD_ID);

    public static void init() {

        csimLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {

        csimLogger.log(logLevel, message);
    }

}
