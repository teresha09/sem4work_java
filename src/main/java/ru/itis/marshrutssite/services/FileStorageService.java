package ru.itis.marshrutssite.services;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.marshrutssite.models.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileStorageService {
    // сохраняет файл на сервере
    String saveFile(MultipartFile file, Authentication authentication);

    // отправляет файл по запросу
    void writeFileToResponse(String fileName, HttpServletResponse response);

    List<FileInfo> getFilesByUserId(Long userId);

    void convert();
    void init();

}
