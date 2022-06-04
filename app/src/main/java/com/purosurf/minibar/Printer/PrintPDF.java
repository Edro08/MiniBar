package com.purosurf.minibar.Printer;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
    String[] Title ;
    Cursor data;
    private static final int PERMISSION_REQUEST_CODE = 200;

    public PrintPDF(Bitmap bmp, Bitmap scaledbmp, String[] datos, Cursor cursor) {
        this.bmp = bmp;
        this.scaledbmp = scaledbmp;
        this.Title = datos;
        this.data = cursor;
    }

    public void generatePDF(Context context, int pagewidth, int pageHeight) {

        PdfDocument pdfDocument = new PdfDocument();

        Paint paint = new Paint();
        Paint title = new Paint();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas = myPage.getCanvas();

        canvas.drawBitmap(scaledbmp, 45, 45, paint);
        title.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        title.setTextSize(25);
        title.setTextAlign(Paint.Align.CENTER);
        title.setColor(ContextCompat.getColor(context, R.color.azul));
        canvas.drawText("Puro Surf S.A DE C.V", 450, 45, title);
        title.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        title.setTextSize(22);
        title.setColor(ContextCompat.getColor(context, R.color.black));
        canvas.drawText(Title[0], 450, 75, title);
        canvas.drawText("Reporte: " + Title[1], 450,105,title);
        if (Title[5].equals("Inventario") || Title[5].equals("Consumo")) { canvas.drawText("Fecha Impresion: " + Title[2],450,135, title); }
        if (Title[5].equals("Compra")) { canvas.drawText("Periodo: " + Title[2], 450,135,title); }
        canvas.drawText("Usuario Genero: " + Title[3],450,165,title);

        title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        title.setColor(ContextCompat.getColor(context, R.color.black));
        title.setTextSize(20);
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Productos",220,240,title);
        canvas.drawText("Cantidad", 505,240, title);
        if (Title[5].equals("Inventario")){ canvas.drawText("Precio", 680,240, title); }
        if (Title[5].equals("Consumo")){ canvas.drawText("Sub Total" ,680,240, title); }
        if (Title[5].equals("Compra")){ canvas.drawText("Fecha" ,660, 240, title);}

        paint.setStrokeWidth(2);
        canvas.drawLine(60,255,canvas.getWidth()-60,255, paint);

        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(context, R.color.black));
        title.setTextSize(18);
        title.setTextAlign(Paint.Align.LEFT);
        //recorrido de productos
        int y = 280;
        while (data.moveToNext()){
            //inventario
            if (Title[5].equals("Inventario")) { canvas.drawText(data.getString(1),80,y,title); }
            if (Title[5].equals("Inventario")) { canvas.drawText(data.getString(3), 500, y, title); }
            if (Title[5].equals("Inventario")) { canvas.drawText("$ "+ data.getString(4),650, y, title); }
            //compras
            if (Title[5].equals("Compra")) { canvas.drawText(data.getString(3),80,y, title); }
            if (Title[5].equals("Compra")) { canvas.drawText(data.getString(5), 500, y, title); }
            if (Title[5].equals("Compra")) { canvas.drawText(data.getString(4), 625, y, title); }
            //consumo
            if (Title[5].equals("Consumo")) { canvas.drawText(data.getString(1),80, y, title); }
            if (Title[5].equals("Consumo")) { canvas.drawText(data.getString(2), 500, y, title); }
            if (Title[5].equals("Consumo")) { canvas.drawText("$" + data.getString(3), 650, y, title); }
            y += 30;
        }

        pdfDocument.finishPage(myPage);
        File file = new File(context.getFilesDir(), Title[4]);
        if(file.exists()){
            file.delete();
        }

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }
}
