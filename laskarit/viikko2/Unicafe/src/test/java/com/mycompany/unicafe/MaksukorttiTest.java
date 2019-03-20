package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void luodunKortinSaldoOikeinAlussa() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLatausKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1);
        assertEquals(11, kortti.saldo());
    }
    
    @Test
    public void rahanOttoVahentaaSaldoaOikein() {
        kortti.otaRahaa(1);
        assertEquals(9, kortti.saldo());
    }
    
    @Test
    public void rahanOttoSaldonYliEiMuutaSaldoa() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosSaldoaTarpeeksi() {
        assertEquals(true, kortti.otaRahaa(1));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosSaldoaEiTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void toStringNayttaaOikeanMaaran() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
