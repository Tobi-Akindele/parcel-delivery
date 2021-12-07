/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tobiakindele.parceldelivery.utils;

import java.util.Objects;

/**
 *
 * @author oyindamolaakindele
 */
public class Utils {
    
    public static boolean isEmpty(Object obj){
        return Objects.isNull(obj) || obj.toString().trim().isEmpty();
    }
}
