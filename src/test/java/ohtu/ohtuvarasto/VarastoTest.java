package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOnTilavuus() {
        Varasto negatiivinenVarasto = new Varasto(-2);
        assertEquals(0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuusKuormitettu() {
        Varasto kuormitettuVarasto = new Varasto(12, 2.5);
        assertEquals(12, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
        
    }
    
    @Test
    public void uudellaVarastollaOnTilavuusKuormitettu() {
        Varasto kuormitettuVarasto = new Varasto(-2, 2.5);
        assertEquals(0, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
        
    }

    @Test
    public void uudellaVarastollaEiNegatiivistaSaldoaKuormitettu() {
        Varasto kuormitettuVarasto = new Varasto(12, -2.5);
        assertEquals(0, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);  
    }
    
    @Test
    public void uudellaVarastollaOikeaSaldoKuormitettu() {
        Varasto kuormitettuVarasto = new Varasto(12, 2.5);
        assertEquals(2.5, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);  
    }
    
    @Test
    public void uudellaVarastollaSaldoaLiikaaKuormitettu() {
        Varasto kuormitettuVarasto = new Varasto(12, 13);
        assertEquals(12, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);  
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaLisaystaEiVoiTehda() {
        varasto.lisaaVarastoon(-0.5);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ylivuotaaJosEiMahdu() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test 
    public void negatiivistaMaaraaEiVoiOttaa() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-1);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, saatuMaara, vertailuTarkkuus);//???
    }
    
    @Test 
    public void eiVoiOttaaEnempaaKuinOn() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(9);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(8, saatuMaara, vertailuTarkkuus);//???
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void oikeaString() {
        varasto.lisaaVarastoon(8);
        String varastoString = varasto.toString();
    //"saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu()
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varastoString);
    }

}