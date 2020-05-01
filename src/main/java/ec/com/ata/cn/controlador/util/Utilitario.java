/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author xnio
 */
public class Utilitario {

    //Convert Date to Calendar
    public static Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

}
