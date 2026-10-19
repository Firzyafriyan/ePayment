package Main;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Tria
 */
public class Rupiah {
    public static String convertToRupiah(int totalRupiah){
            Locale myIndonesianLocale = new Locale("in", "ID");
            NumberFormat formater = NumberFormat.getCurrencyInstance(myIndonesianLocale);
            return formater.format(totalRupiah).replace(",00" ,"");
    }
}
