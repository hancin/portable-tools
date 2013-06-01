package com.solutionspratte.InventoryManagement.configuration;

import com.solutionspratte.InventoryManagement.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * ConfigurationSettings
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationSettings {

    /*
     * Version check related settings
     */
    public static boolean DISPLAY_VERSION_RESULT;
    public static final String DISPLAY_VERSION_RESULT_CONFIGNAME = "version_check.display_results";
    public static final boolean DISPLAY_VERSION_RESULT_DEFAULT = true;

    public static String LAST_DISCOVERED_VERSION;
    public static final String LAST_DISCOVERED_VERSION_CONFIGNAME = "version_check.last_discovered_version";
    public static final String LAST_DISCOVERED_VERSION_DEFAULT = "";

    public static String LAST_DISCOVERED_VERSION_TYPE;
    public static final String LAST_DISCOVERED_VERSION_TYPE_CONFIGNAME = "version_check.last_discovered_version_type";
    public static final String LAST_DISCOVERED_VERSION_TYPE_DEFAULT = "";

    /*
     * Item related config settings
     */
    // The maximum durability for the Minium Stone 
    public static int HEART_GOLD_MAX_DURABILITY;
    public static final String HEART_GOLD_MAX_DURABILITY_CONFIGNAME = Strings.HEART_GOLD_NAME;
    public static final int HEART_GOLD_MAX_DURABILITY_DEFAULT = 1000;


}
