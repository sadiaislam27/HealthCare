package com.example.health_care;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class UnitTest {

   @Test
    public void testGetUserInfo(){
        Users users = new Users("Sadia","49","23","sadia@gmail.com","123456");
        assertEquals("Sadia",users.getName());
        assertEquals("49",users.getPhone());
        assertEquals("23",users.getAge());
        assertEquals("sadia@gmail.com",users.getEmail());
        assertEquals("123456",users.getPassword());

    }
    @Test
    public void testStoreData(){
        //String heartRate, String systolic, String diastolic, String time, String currentDate,String comment
        Store store=new Store("45","34","25","5738","65","bad");
        assertEquals("45",store.getHeartRate());
        assertEquals("34",store.getSystolic());
        assertEquals("25",store.getDiastolic());
        assertEquals("5738",store.getTime());
        assertEquals("65",store.getCurrentDate());
        assertEquals("bad",store.getComment());
    }


}
