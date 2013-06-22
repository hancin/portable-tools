package com.solutionspratte.InventoryManagement.lib;


/**
 * 
 * @author David Larochelle-Pratte
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 */
public class Reference {
    public static final String MOD_ID = "CSIM";
    public static final String MOD_NAME = "Hancin World Companion";
    public static final String VERSION_NUMBER = "@VERSION@ (build @BUILD_NUMBER@)";
    
    public static final String CHANNEL_NAME = "CSIM";
    
    
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final int VERSION_CHECK_ATTEMPTS = 3;
    
    public static final String SERVER_PROXY_CLASS = "com.solutionspratte.InventoryManagement.core.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "com.solutionspratte.InventoryManagement.core.proxy.ClientProxy";
}
