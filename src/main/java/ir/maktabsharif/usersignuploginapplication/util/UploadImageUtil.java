package ir.maktabsharif.usersignuploginapplication.util;

import ir.maktabsharif.usersignuploginapplication.security.Base64PhotoEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


public class UploadImageUtil {


    public static Optional<String> convertImage(HttpServletRequest request, HttpServletResponse response, String photoName) throws ServletException, IOException {
        Part filePart = request.getPart(photoName);

        if (filePart != null && filePart.getSize() > 0) {

            if (filePart.getSize() > 1024 * 300) {
                request.setAttribute("message", "Error, you must upload an image with a file size of 10kb");
                request.getRequestDispatcher("editProfile.jsp").forward(request, response);
                return Optional.empty();
            }

            InputStream fileContent = filePart.getInputStream();

            byte[] fileBytes = readAllBytesUsingBuffer(fileContent);

            return Optional.of(Base64PhotoEncoder.base64PhotoEncode(fileBytes));

        } else {
            request.setAttribute("message", "upload your picture");
        }
        return Optional.empty();
    }


    private static byte[] readAllBytesUsingBuffer(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        return buffer.toByteArray();
    }


}
