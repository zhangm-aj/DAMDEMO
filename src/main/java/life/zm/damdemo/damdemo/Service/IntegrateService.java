package life.zm.damdemo.damdemo.Service;

import com.bimface.api.bean.request.integrate.FileIntegrateRequest;
import com.bimface.api.bean.response.databagDerivative.DatabagDerivativeBean;
import com.bimface.exception.BimfaceException;
import life.zm.damdemo.damdemo.model.ExampleQuickIntegrate;
import life.zm.damdemo.damdemo.model.ExampleQuickIntegrateFile;

import java.text.ParseException;
import java.util.List;

public interface IntegrateService {
    /**
     * 查询分页列表
     *
     * @return
     */
    List<ExampleQuickIntegrate> getAll();

    /**
     * 获取集成的文件列表
     *
     * @param integrateId
     * @return
     */
    List<ExampleQuickIntegrateFile> getIntegrateFiles(Long integrateId);

    /**
     * 发起集成模型
     *
     * @param integrateRequest
     * @return
     * @throws BimfaceException
     * @throws ParseException
     */
    ExampleQuickIntegrate integrate(FileIntegrateRequest integrateRequest) throws BimfaceException, ParseException;

    /**
     * 生成离线包
     *
     * @param integrateId
     * @return
     */
    DatabagDerivativeBean databag(Long integrateId) throws BimfaceException;

    /**
     * 删除模型
     *
     * @param integrateId
     */
    void delete(Long integrateId);
}
