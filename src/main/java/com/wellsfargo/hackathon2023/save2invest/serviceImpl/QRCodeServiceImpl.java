package com.wellsfargo.hackathon2023.save2invest.serviceImpl;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.wellsfargo.hackathon2023.save2invest.service.QRCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * The type Qr code service.
 */
@Slf4j
@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Override
    public String readQRCode(byte[] qrCodeFile) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(qrCodeFile);
            BufferedImage bufferedImage = ImageIO.read(bais);
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
            MultiFormatReader multiFormatReader = new MultiFormatReader();

            Result result = multiFormatReader.decode(binaryBitmap);
            String qrCodeText = result.getText();
            return qrCodeText;
        } catch (IOException | NotFoundException ex) {
            log.error("Error during reading QR code image", ex);
        }
        return null;
    }
}
