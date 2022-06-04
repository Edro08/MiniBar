package com.purosurf.minibar.Printer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.purosurf.minibar.R;

public class PrintPDF {

    Bitmap bmp, scaledbmp;
    private static final int PERMISSION_REQUEST_CODE = 200;

    public PrintPDF(Bitmap bmp, Bitmap scaledbmp) {
        this.bmp = bmp;
        this.scaledbmp = scaledbmp;
    }

    public void generatePDF(Context context, int pagewidth, int pageHeight) {

        PdfDocument pdfDocument = new PdfDocument();

        Paint paint = new Paint();
        Paint title = new Paint();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas = myPage.getCanvas();

        canvas.drawBitmap(scaledbmp, 50, 50, paint);
        title.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        title.setTextSize(25);
        title.setTextAlign(Paint.Align.CENTER);
        title.setColor(ContextCompat.getColor(context, R.color.black));
        canvas.drawText("Puro Surf S.A DE C.V", 450, 60, title);
        canvas.drawText("Reporte de Consumo por Habitacion.", 450, 90, title);
        canvas.drawText("Habitacion: " + "", 450,120,title);
        canvas.drawText("Usuario Genero: ",450,150, title);
        canvas.drawText("Rango de Fechas: ",450,180,title);

        title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        title.setColor(ContextCompat.getColor(context, R.color.black));
        title.setTextSize(20);
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Productos",80,240,title);
        canvas.drawText("Cantidad", 520,240, title);
        canvas.drawText("Precio", 690,240, title);

        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(context, R.color.black));
        title.setTextSize(19);
        //recorrido de productos
        int y = 280;
        for (int i = 0; i < 1; i++){
            canvas.drawText("producto " + i ,80, y, title);
            canvas.drawText("cantidad " +i ,520, y, title);
            canvas.drawText("Precio "+i, 690, y, title);
            y += 30;
        }

        pdfDocument.finishPage(myPage);
        File file = new File(context.getFilesDir(), "rptConsumos.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }
}
