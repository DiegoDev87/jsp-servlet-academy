package dev.webacademy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    /**
     * @return data de hoje no fotmato dd/MM/yyyy
     */
    public String getDataHoje() {
        String dataHoje = "";
        Date date = null;
        SimpleDateFormat sdf = null;
        try {
            date = new Date();
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataHoje = sdf.format(date);
        } finally {
            date = null;
            sdf = null;
        }
        return dataHoje;
    }

    public static void limparSB(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
            sb = null;
        }
    }
}
