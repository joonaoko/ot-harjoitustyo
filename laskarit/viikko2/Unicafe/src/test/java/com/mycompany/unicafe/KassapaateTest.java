package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    Maksukortti kortti2;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        kortti2 = new Maksukortti(200);
    }
    
    @Test
    public void rahamaaraOikeinAlussa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjaEdullisiaLounaitaNollaAlussa() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjaMaukkaitaLounaitaNollaAlussa() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void lounaanHintaaSuurempiMaksuLisaaKassaanVainHinnan() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void lounaanHintaaSuurempiMaksuAntaaOikeanVaihtorahan() {
        assertEquals(60, kassa.syoEdullisesti(300));
    }
    
    @Test
    public void onnistunutOstosKasvattaaMyytyjenMaaraa() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutOstosEiLisaaKassaan() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void epaonnistunutOstosPalauttaaRahat() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void epaonnistunutOstosEiKasvataMyytyjenMaaraa() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void ostoKortillaVeloittaaOikeanMaaran() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void ostoKortillaPalauttaaTrue() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void onnistunutOstoKortillaKasvattaaMyytyjenMaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutOstoKortillaEiVeloita() {
        kassa.syoEdullisesti(kortti2);
        assertEquals(200, kortti2.saldo());
    }
    
    @Test
    public void epaonnistunutOstoKortillaEiKasvataMyytyjenMaaraa() {
        kassa.syoEdullisesti(kortti2);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutOstoKortillaPalauttaaKaikkiRahat() {
        kassa.syoEdullisesti(kortti2);
        assertEquals(200, kortti2.saldo());
    }
    
    @Test
    public void epaonnistunutEdullinenOstoKortillaPalauttaaFalse() {
        assertEquals(false, kassa.syoEdullisesti(kortti2));
    }
    
    @Test
    public void epaonnistunutMaukasOstoKortillaPalauttaaFalse() {
        assertEquals(false, kassa.syoMaukkaasti(kortti2));
    }
    
    @Test
    public void kortilleLatausKasvattaaSaldoa() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void kortilleLatausLisaaKassaanOikein() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivinenKortilleLatausEiLisaaSaldoa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void negatiivinenKortilleLatausEiLisaaKassaan() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}