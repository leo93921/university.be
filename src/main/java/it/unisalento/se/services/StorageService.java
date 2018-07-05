package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.exceptions.StorageException;
import it.unisalento.se.iservices.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class StorageService implements IStorageService {

    private static boolean initiated = false;
    private final Path rootLocation;

    @Autowired
    public StorageService() {
        this.rootLocation = Paths.get(Constants.FINAL_SAVE_LOCATION);
    }

    @Override
    public String store(MultipartFile file) throws StorageException {
        this.init();
        // String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String[] splittedFilename = StringUtils.cleanPath(file.getOriginalFilename()).split("/");
        String originalFilename = splittedFilename[splittedFilename.length - 1];
        String filename = (new Date()).getTime() + "_" + originalFilename;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return filename;
    }

    @Override
    public Resource loadAsResource(String filename) throws FileNotFoundException {
        try {
            Path filePath = this.rootLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void init() throws StorageException {
        if (!StorageService.initiated) {
            try {
                Files.createDirectories(this.rootLocation);
                StorageService.initiated = true;
            } catch (IOException e) {
                throw new StorageException("Could not initialize storage", e);
            }
        }
    }

    @Override
    public Boolean removeFile(String fileName) {
        File file = new File(this.rootLocation.toString(), fileName);
        if (!file.exists()) {
            return true;
        }
        return file.delete();
    }
}
