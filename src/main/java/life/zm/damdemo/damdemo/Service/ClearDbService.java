package life.zm.damdemo.damdemo.Service;

public interface ClearDbService {
    /**
     * 清理integrate数据，并初始化
     *
     * @return
     */
    void clearIntegrate();

    /**
     * 清理file数据，并初始化
     */
    void clearFile();

    /**
     * 清理integrateFile数据，并初始化
     */
    void clearIntegrateFile();
}
