package ru.itis.marshrutssite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.marshrutssite.models.FileInfo;

import java.io.File;
import java.util.List;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
    List<FileInfo> findAllByUserId(Long userId);
}
