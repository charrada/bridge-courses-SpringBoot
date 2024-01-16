package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.entities.ImageCourse;
import project.repositories.ImageCourseRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageCourseController {

    @Autowired
    ImageCourseRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<ImageCourse> uploadPDP(@RequestParam("imageFile") MultipartFile file, @RequestParam("idCourse") int idCourse) {
        try {
            System.out.println("Original Image Byte Size - " + file.getBytes().length);
            ImageCourse img = new ImageCourse();
            img.setName(file.getOriginalFilename());
            img.setType(file.getContentType());
            img.setPicByte(compressBytes(file.getBytes()));
            img.setIdCourse(idCourse); // Set the idOperation field
            imageRepository.save(img);
            return ResponseEntity.ok(img);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    

    @GetMapping(path = { "/get/{idCourse}" })
    public ResponseEntity<ImageCourse> getPDPByIdCourse(@PathVariable("idCourse") int idCourse) {
        final Optional<ImageCourse> retrievedImage = imageRepository.findByIdCourse(idCourse);
        if (retrievedImage.isPresent()) {
            ImageCourse img = retrievedImage.get();
            img.setPicByte(decompressBytes(img.getPicByte())); // Decompress the image bytes
            return ResponseEntity.ok(img);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
