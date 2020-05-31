package ru.itis.marshrutssite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.marshrutssite.services.FileStorageService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StorageController {

    @Autowired
    private FileStorageService service;

    // страница, которая позволяет загружать файлы
    @GetMapping("/storage")
    public String getStoragePage() {
        return "file_upload";
    }

    // принимает файлы
    // MultipartFile - файл, который вы принимаете
    // ResponseEntity - класс, который позволяет отправить в ответе
    // не только тело, но и статус и заголовки ответа
    @PostMapping("/files")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   Authentication authentication) {
        // сохраняем файл на диск
        String filePath = service.saveFile(file,authentication);
        // отправляем пользователю полный путь к этому файлу
        return ResponseEntity
                .ok()
                .body(filePath);
    }

    // URL-для получения файла
    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        // запись файла в ответ
        service.writeFileToResponse(fileName, response);
    }

}
