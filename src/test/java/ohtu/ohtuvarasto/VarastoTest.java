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

        // saldon pit채isi olla sama kun lis채tty m채채r채
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pit채isi viel채 olla tilavuus-lis채tt채v채 m채채r채 eli 2
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
    public void eiVoiOttaaEnemp狎KuinOn() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(9);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(8, saatuMaara, vertailuTarkkuus);//???
    }

    @Test
    public void ottaminenLis채채Tilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pit채isi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void oikeaString() {
        varasto.lisaaVarastoon(8);
        String varastoString = varasto.toString();
    //"saldo = " + saldo + ", viel채 tilaa " + paljonkoMahtuu()
        assertEquals("saldo = 8.0, viel채 tilaa 2.0", varastoString);
    }

}