package life.zm.damdemo.damdemo.Service;


import life.zm.damdemo.damdemo.model.File;

import java.util.List;

public interface NewFileService {
    List<File> getAllFile(int folderId, int projectId);
    void createFolder(String fileName, int folderId, int projectId);
    void updateFolderName(String fileName, int fileId);
}
