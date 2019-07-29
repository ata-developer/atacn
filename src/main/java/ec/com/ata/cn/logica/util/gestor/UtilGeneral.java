/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica.util.gestor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ATA1
 */
public class UtilGeneral {

    public static byte[] ImagenAByte(UploadedFile file) throws Exception {
        InputStream inputStream = file.getInputstream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = inputStream.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
