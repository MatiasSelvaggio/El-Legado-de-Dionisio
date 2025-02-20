package com.api.util;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QrCodeGenerator {

    public static BufferedImage createQRwithText(String data, String topText, String bottomText) throws WriterException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix matrix = barcodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
        return modifiedQRCode(matrix, topText, bottomText);
    }


    public static BufferedImage modifiedQRCode(BitMatrix matrix, String topText, String bottomText) {
        int matrixWidth = matrix.getWidth();
        int matrixHeight = matrix.getHeight();

        BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixHeight);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int topTextWidth = fontMetrics.stringWidth(topText);
        int bottomTextWidth = fontMetrics.stringWidth(bottomText);
        int finalWidth = Math.max(matrixWidth, Math.max(topTextWidth, bottomTextWidth)) + 1;
        int finalHeight = matrixHeight + fontMetrics.getHeight() + fontMetrics.getAscent() + 1;

        BufferedImage finalImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D finalGraphics = finalImage.createGraphics();
        finalGraphics.setColor(Color.WHITE);
        finalGraphics.fillRect(0, 0, finalWidth, finalHeight);
        finalGraphics.setColor(Color.BLACK);

        finalGraphics.drawImage(image, (finalWidth - matrixWidth) / 2, fontMetrics.getAscent() + 2, null);
        finalGraphics.drawString(topText, (finalWidth - topTextWidth) / 2, fontMetrics.getAscent() + 2);
        finalGraphics.drawString(bottomText, (finalWidth - bottomTextWidth) / 2, finalHeight - fontMetrics.getDescent() - 5);

        return finalImage;
    }
}
