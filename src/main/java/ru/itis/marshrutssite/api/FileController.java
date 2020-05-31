package ru.itis.marshrutssite.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.marshrutssite.models.FileInfo;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.services.FileStorageService;
import ru.itis.marshrutssite.services.UsersService;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @GetMapping("/api/files/{userId:.+}")
    public ResponseEntity<List<FileInfo>> getUsers(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(storageService.getFilesByUserId(userId));
    }

    @GetMapping("/api/file/init")
    public ResponseEntity<?> init() {
        storageService.init();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/file/convert")
    public ResponseEntity<?> convert() {
        storageService.convert();
        return ResponseEntity.ok().build();
    }
}
