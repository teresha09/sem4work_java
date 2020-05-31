package ru.itis.marshrutssite.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.marshrutssite.models.Document;
import ru.itis.marshrutssite.models.FileInfo;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.repositories.DocumentRepository;
import ru.itis.marshrutssite.repositories.FileInfoRepository;
import ru.itis.marshrutssite.security.details.UserDetailsImpl;
import ru.itis.marshrutssite.util.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final static String FILES_PATH =
            "/home/egor/springboot/egor/files";
    private final static String CONVERTED_FILES_PATH =
            "/home/egor/springboot/egor/converted_files";

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public String saveFile(MultipartFile file, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        // вытягиваем всю инофрмацию о файле для сохранения ее в бд
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        fileInfo.setUserId(user.getId());
        // сохраняем информацию о файле
        fileInfoRepository.save(fileInfo);
        // переносим файл на наш диск
        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
        // возвращаем имя файла - новое
        return fileInfo.getStorageFileName();
    }

    // получение файла по его урлу
    @SneakyThrows
    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        // находите информацию о файле в БД
        FileInfo file = fileInfoRepository.findOneByStorageFileName(fileName);
        // указываем Content-Type для ответа
        response.setContentType(file.getType());
        // получили инпут стрим файла на диске
        InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
        // скопировали файл в ответ
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        // пробрасываем буфер
        response.flushBuffer();
    }

    @Override
    public List<FileInfo> getFilesByUserId(Long userId) {
        return  fileInfoRepository.findAllByUserId(userId);
    }

    @Override
    public void init() {
        try (Stream<Path> filesPaths = Files.walk(Paths.get(FILES_PATH))) {
            filesPaths.filter(filePath -> filePath.toFile().isFile()).forEach(
                    filePath -> {
                        File file = filePath.toFile();
                        Document document = null;
                        try {
                            document = Document.builder()
                                    .path(filePath.toString())
                                    .size(file.length())
                                    .type(Files.probeContentType(filePath))
                                    .build();
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                        documentRepository.save(document);
                    }
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Transactional
    @Override
    public void convert() {
        List<Document> documents = (List<Document>) documentRepository.findAll();

        for (Document document : documents) {
            String newFileName = CONVERTED_FILES_PATH + "/" + document.getFileName() + ".jpg";
            if (document.getType().equals("application/pdf")) {
                convertPdfToJpg(document, newFileName);
                document.setType("image/jpeg");
            }
        }
    }

    @SneakyThrows
    private void convertPdfToJpg(Document document, String newFileName) {
        PDDocument pdf = PDDocument.load(document.getSourceFile());
        PDFRenderer renderer = new PDFRenderer(pdf);
        BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
        ImageIOUtil.writeImage(image, newFileName, 300);
        pdf.close();
        File resultFile = new File(newFileName);
        document.setSourceFile(resultFile);
    }

}
