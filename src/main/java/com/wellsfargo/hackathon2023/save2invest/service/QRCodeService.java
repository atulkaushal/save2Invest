package com.wellsfargo.hackathon2023.save2invest.service;

import java.io.File;

/**
 * The interface Qr code service.
 */
public interface QRCodeService {
    /**
     * Read qr code string.
     *
     * @param qrFile the qr file
     * @return the string
     */
    String readQRCode(File qrFile);
}
