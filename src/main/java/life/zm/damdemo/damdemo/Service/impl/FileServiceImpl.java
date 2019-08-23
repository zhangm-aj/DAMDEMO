package life.zm.damdemo.damdemo.Service.impl;


import com.bimface.api.bean.response.FileTranslateBean;
import com.bimface.api.bean.response.databagDerivative.DatabagDerivativeBean;
import com.bimface.exception.BimfaceException;
import com.bimface.file.bean.FileBean;
import com.bimface.sdk.BimfaceClient;
import com.bimface.sdk.bean.request.OfflineDatabagRequest;
import life.zm.damdemo.damdemo.Service.FileService;
import life.zm.damdemo.damdemo.dao.mapper.ExampleQuickFileMapper;
import life.zm.damdemo.damdemo.dao.mapper.FileMapper;
import life.zm.damdemo.damdemo.model.ExampleQuickFile;
import life.zm.damdemo.damdemo.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private BimfaceClient bimfaceClient;
    @Autowired
    private ExampleQuickFileMapper exampleQuickFileMapper;

    @Override
    public List<ExampleQuickFile> getFileList(String suffix, String translateStatus,int projectId,Long folderId) {
        Example example = new Example(ExampleQuickFile.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.hasText(suffix)){
            criteria.andLike("name", "%." + suffix);
        }
        if (StringUtils.hasText(translateStatus)){
            criteria.andEqualTo("translateStatus", translateStatus);
        }
        criteria.andEqualTo("projectId", projectId);

        criteria.andEqualTo("folderId", folderId);

        example.orderBy("createTime").desc();
         List<ExampleQuickFile> list = exampleQuickFileMapper.selectByExample(example);
         List<ExampleQuickFile> listans = new ArrayList<>();
        Iterator<ExampleQuickFile> it = list.iterator();
        while(it.hasNext()){
            ExampleQuickFile ita = it.next();
            if(ita.getIsFolder()==1){
                listans.add(ita);
            }else{
                Iterator<ExampleQuickFile> itans = listans.iterator();
                int flag = 0;
                while(itans.hasNext()){
                    ExampleQuickFile itb = itans.next();
                    if(ita.getName().equals(itb.getName())){
                        flag = 1;
                        if(ita.getVersion()>itb.getVersion()){
                            listans.remove(itb);
                            listans.add(ita);
                            break;
                        }
                    }
                }
                if(flag == 0){
                    listans.add(ita);
                }
            }
        }
         return listans;
    }

    @Override
    public ExampleQuickFile store(FileBean fileBean,int projectId,Long folderId,double version) throws ParseException {
        if (fileBean.getStatus().equals("success")) {
            ExampleQuickFile exampleQuickFile = new ExampleQuickFile();
            exampleQuickFile.setId(fileBean.getFileId());
            exampleQuickFile.setName(fileBean.getName());
            exampleQuickFile.setLength(fileBean.getLength());
            exampleQuickFile.setCreateTime(DateTimeUtils.parseBimfaceDateStr(fileBean.getCreateTime()));
            exampleQuickFile.setProjectId(projectId);
            exampleQuickFile.setFolderId(folderId);
            exampleQuickFile.setVersion(version);
            exampleQuickFileMapper.insert(exampleQuickFile);
            return exampleQuickFile;
        } else {
            throw new RuntimeException("file has not upload success");
        }
    }

    @Override
    public FileTranslateBean translate(Long fileId) throws BimfaceException {
        FileTranslateBean translateBean = bimfaceClient.translate(fileId);

        ExampleQuickFile exampleQuickFile = exampleQuickFileMapper.selectByPrimaryKey(fileId);
        if(!Objects.equals(exampleQuickFile.getTranslateStatus(),translateBean.getStatus())){
            exampleQuickFile.setTranslateStatus(translateBean.getStatus());
            exampleQuickFileMapper.updateByPrimaryKey(exampleQuickFile);
        }
        return translateBean;
    }

    @Override
    public DatabagDerivativeBean databag(Long fileId) throws BimfaceException {
        OfflineDatabagRequest request = new OfflineDatabagRequest();
        request.setFileId(fileId);
        DatabagDerivativeBean offlineDatabagBean = bimfaceClient.generateOfflineDatabag(request);
        ExampleQuickFile exampleQuickFile = exampleQuickFileMapper.selectByPrimaryKey(fileId);
        if(!Objects.equals(exampleQuickFile.getDatabagStatus(),offlineDatabagBean.getStatus())){
            exampleQuickFile.setDatabagStatus(offlineDatabagBean.getStatus());
            exampleQuickFileMapper.updateByPrimaryKey(exampleQuickFile);
        }
        return offlineDatabagBean;
    }

    @Override
    public void delete(Long fileId) {
        exampleQuickFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public void createFolder(String fileName, Long folderId, int projectId) {
        exampleQuickFileMapper.insertFolder(fileName,folderId,projectId);
    }

    @Override
    public String getFile(Long id) {
        return exampleQuickFileMapper.getFolderName(id);
    }

    @Override
    public List<ExampleQuickFile> findFileByName(String name) {
        return exampleQuickFileMapper.findFileByName(name);
    }
}
