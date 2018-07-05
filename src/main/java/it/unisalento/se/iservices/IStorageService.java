package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.StorageException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface IStorageService {

    String store(MultipartFile file) throws StorageException;

    Resource loadAsResource(String filename) throws StorageException, FileNotFoundException;

    void init() throws StorageException;

    Boolean removeFile(String link);

}
