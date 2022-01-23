package ru.bulish.spring.geek_shop_boot.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

///Users/evgeniabulis/Desktop/geek_shop_boot/data
public class FileUtil {
    private static final String IMAGE_FOLDER_PATH = "/data/images";
    private static final String PRODUCT_IMAGE_FOLDER_PATH = IMAGE_FOLDER_PATH + "/product";



    public static Path saveProductsImage(MultipartFile image) {
        if (image == null) {
            throw  new IllegalArgumentException("Image can't be null");
        }
        //createDirectories(Paths.get(System.getProperty("user.dir"), PRODUCT_IMAGE_FOLDER_PATH));
        Path savedPath = Paths.get(PRODUCT_IMAGE_FOLDER_PATH, image.getOriginalFilename());
        saveFile(image,Paths.get(System.getProperty("user.dir"), savedPath.toString()));
        return savedPath;
    }

   /*private static void createDirectories(Path path) {
       try {
           Files.createDirectory(path);
        } catch (IOException e) {
           throw new RuntimeException("can't save (creation)file by this path: " + path);
       }
    }
    */
    private static void saveFile(MultipartFile file, Path path) {
        try{
            file.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException("can't save file by this path: " + path);
        }
    }
}
