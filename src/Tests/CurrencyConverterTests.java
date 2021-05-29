package Tests;

import CurrencyConverter.CurrencyConverter;
import CurrencyConverter.CurrencyFileReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CurrencyConverterTests {
    @Test
    public void ConverterValueTest1() {
        CurrencyConverter converter = new CurrencyConverter();
        Assert.assertEquals(7204.725, converter.convert(123, 58.575), 0.0);
    }

    @Test
    public void ConverterValueTest2() {
        CurrencyConverter converter = new CurrencyConverter();
        Assert.assertEquals(726.33, converter.convert(12.4, 58.575), 0.0);
    }

    @Test
    public void ConverterToJPYTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double rate = reader.readRate("JPY");
        CurrencyConverter converter = new CurrencyConverter();
        Assert.assertEquals(1654.408, converter.convert(12.4, rate), 0.0);
    }

    @Test
    public void ConverterToAUDTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double rate = reader.readRate("AUD");
        CurrencyConverter converter = new CurrencyConverter();
        Assert.assertEquals(19.53372, converter.convert(12.4, rate), 0.0);
    }
}
