package life.zm.damdemo.damdemo.Service.impl;


import life.zm.damdemo.damdemo.Service.NewFileService;
import life.zm.damdemo.damdemo.dao.mapper.FileMapper;
import life.zm.damdemo.damdemo.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFileServiceImpl implements NewFileService {
    @Autowired
    private FileMapper fileDao;

    @Override
    public List<File> getAllFile(int folderId, int projectId) {

        return fileDao.getAllFile(folderId,projectId);
    }

    @Override
    public void createFolder(String fileName, int folderId, int projectId) {
        fileDao.insertFolder(fileName,folderId,projectId);
    }

    @Override
    public void updateFolderName(String fileName, int fileId) {
        fileDao.updateFolderName(fileName,fileId);
    }
}
