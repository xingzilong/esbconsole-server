package org.talend.esbconsole.server.domain.core.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.talend.esbconsole.server.domain.api.model.APICallAnalysisTableDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTop5DataDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.api.model.TaskExecutionDTO;
import org.talend.esbconsole.server.domain.api.model.TaskTimeConsumptionDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.APICallAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.APIRunAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ServiceStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.ServiceTimedTaskParam;
import org.talend.esbconsole.server.domain.api.query.ServicePageQuery;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.StatCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.query.ServiceQueryConverter;
import org.talend.esbconsole.server.domain.core.util.datafilling.DataFillingUtil;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.AnalysisProcessor;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.StrategyFactory;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.AnalysisStrategy;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
import org.talend.esbconsole.server.domain.repository.mapper.ConsumerSystemDAO;
import org.talend.esbconsole.server.domain.repository.mapper.EventsDAO;
import org.talend.esbconsole.server.domain.repository.mapper.ServiceDAO;
import org.talend.esbconsole.server.domain.repository.mapper.StatCatcherDAO;
import org.talend.esbconsole.server.tools.base.exception.ServiceConflictException;
import org.talend.esbconsole.server.tools.base.exception.ServiceVerifyException;
import org.talend.esbconsole.server.tools.base.exception.file.FileAlreadyExistsException;
import org.talend.esbconsole.server.tools.base.exception.file.FileTypeIllegalException;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.api.service.FlowControlService;
import org.talend.esbconsole.server.domain.api.service.FrequencyControlService;
import org.talend.esbconsole.server.domain.api.service.IPControlService;
import org.talend.esbconsole.server.domain.api.service.KarService;
import org.talend.esbconsole.server.domain.api.service.ServiceService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * service相关功能服务接口 {@link ServiceService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Service
public class ServiceServiceImpl implements ServiceService {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_SSS = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${service.dir}")
    private String SERVICE_DIR;

    private volatile String seviceDirCache;

    @Value("${service.delete-file}")
    private boolean SERVICE_DELETE_FILE;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private EventsDAO eventsDAO;
    @Autowired
    private StatCatcherDAO statCatcherDAO;

    @Autowired
    private ConsumerSystemDAO consumerSystemDAO;

    @Autowired
    private BundleService bundleService;

    @Autowired
    private KarService karService;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private ServiceQueryConverter serviceQueryConverter;

    @Autowired
    private IPControlService ipControlService;

    @Autowired
    private FrequencyControlService frequencyControlService;

    @Autowired
    private FlowControlService flowControlService;

    @Autowired
    private StatCatcherConverter setStatCatcherRecord;

    @Override
    public List<ServiceDTO> getAllServices() {
        List<ServiceDO> serviceDOS = serviceDAO.listAllServices();
        List<ServiceDTO> serviceDTOS = serviceConverter.do2dto(serviceDOS);
        return serviceDTOS;
    }

    @Override
    public ServiceDTO getServiceById(String id) {
        ServiceDO serviceDO = serviceDAO.getServiceById(id);
        ServiceDTO serviceDTO = serviceConverter.do2dto(serviceDO);
        return serviceDTO;
    }

    @Override
    public PageResult<ServiceDTO> getServices(ServicePageQueryParam servicePageQueryParam) {
        ServicePageQuery ServicePageQuery = serviceQueryConverter.param2query(servicePageQueryParam);
        PageHelper.startPage(servicePageQueryParam.getPageNum(), servicePageQueryParam.getPageSize());

        List<ServiceDTO> serviceDTOS = serviceDAO.listServicesByConditions(ServicePageQuery);
        PageInfo<ServiceDTO> pageInfo = new PageInfo<ServiceDTO>(serviceDTOS);
        return PageResult.of(serviceDTOS, pageInfo.getTotal());
    }

    @Override
    public void installService(MultipartFile file, ServiceInstallParam serviceInstallParam) {
        ServiceDO serviceDO = serviceConverter.param2do(serviceInstallParam);
        // 初始化设置值
        serviceDO.setEnabledSAM("0");
        serviceDO.setJob(JSONObject.toJSONString(new ArrayList<String>()));
        serviceDO.setBundleName(JSONObject.toJSONString(new ArrayList<String>()));

        // 获取文件名
        String fileName = file.getOriginalFilename();
        serviceDO.setFileName(fileName.substring(0, fileName.length() - 4));
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 文件上传路径
        String filePath = getServicesTemp(SERVICE_DIR);
        File appFile = new File(filePath + File.separator + fileName);
        // 检测是否存在目录
        try {
            if (appFile.exists()) {
                throw new IOException("文件已存在");
            }
            file.transferTo(appFile);
        } catch (IOException e) {
            log.error("installBundle-ERROR", e);
            appFile.delete();
            throw new FileAlreadyExistsException(fileName);
        }

        String url = null;
        try {
            url = appFile.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            appFile.delete();
            log.error("installBundle-ERROR", e);
        }

        // 先进行文件分析，再部署至esb-server
        // 一、根据服务类型获取相应的分析策略
        StrategyFactory strategyFactory = new StrategyFactory();
        AnalysisStrategy analysisStrategy = strategyFactory.getAnalysisStrategy(serviceDO.getServiceType());
        // 如果analysisStrategy为null终止程序执行
        if (analysisStrategy == null) {
            throw new FileTypeIllegalException(serviceDO.getServiceType());
        }
        List<ServiceDO> serviceDOList = serviceDAO.listAllServices();
        // 二、根据分析策略，创建处理器对象
        AnalysisProcessor analysisProcessor = new AnalysisProcessor(analysisStrategy);
        analysisProcessor.setServiceDOList(serviceDOList);
        // 三、执行分析处理器
        try {
            analysisProcessor.execute(appFile, serviceDO);
        } catch (ServiceVerifyException e) {
            // 验证失败，服务bundle冲突，停止部署
            appFile.delete();
            throw new ServiceConflictException(e.getMessage());
        }

        if (StringUtil.isEmpty(serviceDO.getServiceKey())) {
            serviceDO.setServiceKey(serviceInstallParam.getProxyAddress());
        }

        // 部署至esb-server
        if ("jar".equals(suffixName)) {
            Long bundleId = bundleService.installBundle(url);
            serviceDO.setBundleId(bundleId);
            serviceDO.setFileType("jar");
        } else if ("kar".equals(suffixName)) {
            karService.installKar(url);
            serviceDO.setFileType("kar");
        }

        serviceDO.setId(UUIDUtil.getUUID());
        serviceDO.setCreateTime(LocalDateTime.now());
        serviceDO.setUpdateTime(LocalDateTime.now());

        // 将服务信息存入数据库
        serviceDAO.saveService(serviceDO);

        if (SERVICE_DELETE_FILE) {
            appFile.delete();
        }
    }

    /**
     * 获取服务临时存储文件夹
     *
     * @param serviceDir 配置文件中的路径
     * @return 真实的服务路径
     */
    private String getServicesTemp(String serviceDir) {
        // 正常情况下，此处不可能出现为null的情况。
        // 此处判断，是为了方便单元测试 xingzilong 2021-11-23
        if (serviceDir == null) {
            return null;
        }
        // 先检查缓存，有则直接返回
        if (StringUtil.isNotEmpty(seviceDirCache)) {
            return seviceDirCache;
        }
        String servicesPath = serviceDir;
        // 开发环境配置
        if (serviceDir.startsWith("classpath:")) {
            String appPath = System.getProperty("user.dir");
            Path services = Paths.get(appPath + File.separator + serviceDir.substring(10));
            // 检查是否经已存在此目录，不存在则创建
            if (!Files.exists(services)) {
                log.info("开发环境启动：services文件夹不存在");
                try {
                    Files.createDirectories(services);
                    seviceDirCache = services.toString();
                    servicesPath = services.toString();
                    log.info("开发环境启动：services文件夹创建成功（{}）", services);
                } catch (IOException e) {
                    log.error("开发环境启动：services文件夹创建失败（{}）", e.getMessage());
                }
            } else {
                seviceDirCache = services.toString();
                servicesPath = services.toString();
            }
        } else { // 非开发环境配置，只更新缓存
            seviceDirCache = serviceDir;
        }
        return servicesPath;
    }

    @Override
    public ArrayList<BundleInfo> getBundlesForService(ServiceStatusActionParam serviceStatusActionParam) {
        ArrayList<BundleInfo> listBundles = new ArrayList<>();
        List<BundleInfo> allListBundles = bundleService.getAllBundlesForKaraf();
        if ("jar".equals(serviceStatusActionParam.getFileType())) {
            Long bundleId = serviceStatusActionParam.getBundleId();
            listBundles = getBundles4JAR(allListBundles, bundleId);
        } else if ("kar".equals(serviceStatusActionParam.getFileType())) {
            List<String> bundleNames = serviceStatusActionParam.getBundleName();
            listBundles = getBundles4KAR(allListBundles, bundleNames);
        }
        return listBundles;
    }

    @Override
    public void modifyService(ServiceModifyParam serviceModifyParam) {
        ServiceDO serviceDO = serviceConverter.param2do(serviceModifyParam);
        serviceDO.setUpdateTime(LocalDateTime.now());
        serviceDAO.updateService(serviceDO);
    }

    @Override
    public List<APICallTop5DataDTO> getCallTotalTop5Chart(APICallAnalysisParam apiCallAnalysisParam) {
        String serviceKey = serviceDAO.getServiceById(apiCallAnalysisParam.getId()).getServiceKey();
        List<APICallTop5DataDTO> apiCallTop5DataDTOS = eventsDAO.getCallTotalTop5(serviceKey, apiCallAnalysisParam.getTimeInterval());
        if (apiCallTop5DataDTOS.size() < 5) {
            for (int i = apiCallTop5DataDTOS.size(); i < 5; i++) {
                apiCallTop5DataDTOS.add(new APICallTop5DataDTO());
            }
        }
        return apiCallTop5DataDTOS;
    }

    @Override
    public List<APICallTop5DataDTO> getFailureTotalTop5Chart(APICallAnalysisParam apiCallAnalysisParam) {
        String serviceKey = serviceDAO.getServiceById(apiCallAnalysisParam.getId()).getServiceKey();
        List<APICallTop5DataDTO> apiCallTop5DataDTOS = eventsDAO.getFailureTotalTop5(serviceKey, apiCallAnalysisParam.getTimeInterval());
        if (apiCallTop5DataDTOS.size() < 5) {
            for (int i = apiCallTop5DataDTOS.size(); i < 5; i++) {
                apiCallTop5DataDTOS.add(new APICallTop5DataDTO());
            }
        }
        return apiCallTop5DataDTOS;
    }

    @Override
    public PageResult<APICallAnalysisTableDTO> getAPICallAnalysis(APICallAnalysisParam apiCallAnalysisParam) {
        String consumerIP = null;
        String serviceKey = serviceDAO.getServiceById(apiCallAnalysisParam.getId()).getServiceKey();
        TimeInterval timeInterval = apiCallAnalysisParam.getTimeInterval();
        // 当consumerSystem非空时，即使用了consumerSystem为筛选条件。则根据consumerSystem去消费方字典表中查询对应的消费方IP
        if (StringUtil.isNotEmpty(apiCallAnalysisParam.getConsumerSystem())) {
            consumerIP = consumerSystemDAO.getIPBySystemName(apiCallAnalysisParam.getConsumerSystem());
            // 没有符合条件的消费方，直接进行返回
            if (consumerIP == null) {
                return PageResult.of(null, 0L);
            }
        }

        // 分页获取消费方调用总数
        PageHelper.startPage(apiCallAnalysisParam.getPageNum(), apiCallAnalysisParam.getPageSize());
        // 获取调用总数
        List<APICallAnalysisTableDTO> callTotal4CallAnalysis = eventsDAO.getCallTotal4CallAnalysis(consumerIP, serviceKey, timeInterval);
        PageInfo<APICallAnalysisTableDTO> pageInfo = new PageInfo<APICallAnalysisTableDTO>(callTotal4CallAnalysis);
        // 获取消费方IP的集合
        List<String> consumerIPList = callTotal4CallAnalysis.stream()
                .map(APICallAnalysisTableDTO::getConsumerIP)
                .collect(Collectors.toList());
        // 获取失败调用总数
        List<APICallAnalysisTableDTO> callFailureTotal4CallAnalysis = eventsDAO.getCallFailureTotal4CallAnalysis(consumerIPList, serviceKey, timeInterval);
        // 获取频次限制失败总数
        List<APICallAnalysisTableDTO> callFailureTotalFrequency4CallAnalysis = eventsDAO.getCallFailureTotalFrequency4CallAnalysis(consumerIPList, serviceKey, timeInterval);
        // 获取流量限制失败总数
        List<APICallAnalysisTableDTO> callFailureTotalFlow4CallAnalysis = eventsDAO.getCallFailureTotalFlow4CallAnalysis(consumerIPList, serviceKey, timeInterval);
        // 获取IP限制失败总数
        List<APICallAnalysisTableDTO> callFailureTotalIP4CallAnalysis = eventsDAO.getCallFailureTotalIP4CallAnalysis(consumerIPList, serviceKey, timeInterval);
        // 获取平均响应时长
        List<APICallAnalysisTableDTO> avgResponseTime4CallAnalysis = eventsDAO.getAVGResponseTime4CallAnalysis(consumerIPList, serviceKey, timeInterval);

        // 合并其他五组数据到 callTotal4CallAnalysis
        mergeData(callTotal4CallAnalysis, callFailureTotal4CallAnalysis, "callFailureTotal");
        mergeData(callTotal4CallAnalysis, callFailureTotalFrequency4CallAnalysis, "callFailureTotal4Frequency");
        mergeData(callTotal4CallAnalysis, callFailureTotalFlow4CallAnalysis, "callFailureTotal4Flow");
        mergeData(callTotal4CallAnalysis, callFailureTotalIP4CallAnalysis, "callFailureTotal4IP");
        mergeData(callTotal4CallAnalysis, avgResponseTime4CallAnalysis, "avgResponseTime");
        return PageResult.of(callTotal4CallAnalysis, pageInfo.getTotal());
    }

    @Override
    public Map<String, List<APICallTotalAnalysisDTO>> getAPICallTotaleAnalysis(APIRunAnalysisParam apiRunAnalysisParam) {
        String serviceKey = serviceDAO.getServiceById(apiRunAnalysisParam.getId()).getServiceKey();
        HashMap<String, List<APICallTotalAnalysisDTO>> stringAPICallTotalAnalysisDTOHashMap = new HashMap<>();
        // 解析时间
        LocalDate startDate = LocalDate.parse(apiRunAnalysisParam.getTimeInterval().getStartTime(), formatter);
        LocalDate endDate = LocalDate.parse(apiRunAnalysisParam.getTimeInterval().getEndTime(), formatter);
        // 获取数据
        List<APICallTotalAnalysisDTO> successTotalList = eventsDAO.getServiceSuccessTotalByDate(serviceKey, apiRunAnalysisParam.getTimeInterval());
        List<APICallTotalAnalysisDTO> failureTotalList = eventsDAO.getServiceFailureTotalByDate(serviceKey, apiRunAnalysisParam.getTimeInterval());
        // 填充数据
        DataFillingUtil.filling(successTotalList, startDate, endDate);
        DataFillingUtil.filling(failureTotalList, startDate, endDate);
        stringAPICallTotalAnalysisDTOHashMap.put("success", successTotalList);
        stringAPICallTotalAnalysisDTOHashMap.put("failure", failureTotalList);
        return stringAPICallTotalAnalysisDTOHashMap;
    }

    @Override
    public List<APICallTotalAnalysisDTO> getAPIResponseTimeAnalysis(APIRunAnalysisParam apiRunAnalysisParam) {
        String serviceKey = serviceDAO.getServiceById(apiRunAnalysisParam.getId()).getServiceKey();
        // 解析时间
        LocalDate startDate = LocalDate.parse(apiRunAnalysisParam.getTimeInterval().getStartTime(), formatter);
        LocalDate endDate = LocalDate.parse(apiRunAnalysisParam.getTimeInterval().getEndTime(), formatter);
        // 获取数据
        List<APICallTotalAnalysisDTO> serviceResponseTimeByDate = eventsDAO.getServiceResponseTimeByDate(serviceKey, apiRunAnalysisParam.getTimeInterval());
        // 填充数据
        DataFillingUtil.filling(serviceResponseTimeByDate, startDate, endDate);

        return serviceResponseTimeByDate;
    }

    @Override
    public List<APICallTotalAnalysisDTO> getAPIMessageSizeAnalysis(APIRunAnalysisParam apiRunAnalysisParam) {
        String serviceKey = serviceDAO.getServiceById(apiRunAnalysisParam.getId()).getServiceKey();
        // 解析时间
        LocalDate startDate = LocalDate.parse(apiRunAnalysisParam.getTimeInterval().getStartTime(), formatter);
        LocalDate endDate = LocalDate.parse(apiRunAnalysisParam.getTimeInterval().getEndTime(), formatter);
        // 获取数据
        List<APICallTotalAnalysisDTO> serviceResponseTimeByDate = eventsDAO.getServiceMessageSizeByDate(serviceKey, apiRunAnalysisParam.getTimeInterval());
        // 填充数据
        DataFillingUtil.filling41024(serviceResponseTimeByDate, startDate, endDate);
        return serviceResponseTimeByDate;
    }

    @Override
    public List<TaskExecutionDTO> getTaskExecution(ServiceTimedTaskParam serviceTimedTaskParam) {

        //通过serviceId取出该service中的定时路由
        String timedTask = serviceDAO.getTaskConsumptionTotal(serviceTimedTaskParam.getId());
        //将路由转换成一个字符数组
        List<String> timeTaskList = JSONObject.parseObject(timedTask, new TypeReference<ArrayList<String>>() {
        });
        //循环遍历字符数组，取出查询出来的DO封装成List<TaskExecutionDTO>
        List<TaskExecutionDTO> taskExecutionDTOS = new ArrayList<TaskExecutionDTO>();
        for (String taskJobName : timeTaskList) {
            int dataSuccess = statCatcherDAO.getTaskExecuteSuccess(taskJobName, serviceTimedTaskParam.getTaskName(), serviceTimedTaskParam.getTimeInterval().getStartTime(), serviceTimedTaskParam.getTimeInterval().getEndTime());
            int dataFailure = statCatcherDAO.getTaskExecuteFailure(taskJobName, serviceTimedTaskParam.getTaskName(), serviceTimedTaskParam.getTimeInterval().getStartTime(), serviceTimedTaskParam.getTimeInterval().getEndTime());
            taskExecutionDTOS.add(new TaskExecutionDTO(taskJobName, dataSuccess, dataFailure));
        }
        return taskExecutionDTOS;
    }

    @Override
    public List<TaskTimeConsumptionDTO> getTaskConsumption(ServiceTimedTaskParam serviceTimedTaskParam) {
        //通过serviceId取出该service中的定时路由
        String timedTask = serviceDAO.getTaskConsumptionTotal(serviceTimedTaskParam.getId());
        //将路由转换成一个字符数组
        List<String> timeTaskList = JSONObject.parseObject(timedTask, new TypeReference<ArrayList<String>>() {
        });
        //循环遍历字符数组，取出查询出来的DO封装成Map
        Map<String, List<StatCatcherDO>> taskNames = new HashMap<>();
        for (String taskJobName : timeTaskList) {
            List<StatCatcherDO> list = statCatcherDAO.getTimedTask(taskJobName, serviceTimedTaskParam.getTaskName(), serviceTimedTaskParam.getTimeInterval().getStartTime(), serviceTimedTaskParam.getTimeInterval().getEndTime());
            taskNames.put(taskJobName, list);
            //taskNames.put(taskJobName,list);
        }
        //将Map形式的DO对象转成DTO对象
        List<TaskTimeConsumptionDTO> tkc = new ArrayList<>();
        for (String key : taskNames.keySet()) {
            TaskTimeConsumptionDTO dto = new TaskTimeConsumptionDTO();
            List<String> execs = new ArrayList<>();
            List<Long> duration = new ArrayList<>();
            for (StatCatcherDO ds : taskNames.get(key)) {
                execs.add(dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_SSS.format(ds.getMoment()));
                duration.add(ds.getDuration());
            }
            dto.setServiceName(key);
            dto.setTimeConsumed(duration);
            dto.setExecutionTime(execs);
            tkc.add(dto);
        }
        return tkc;
    }

    @Override
    public PageResult<JobLogDTO> getTimedTaskTable(ServiceTimedTaskParam serviceTimedTaskParam) {
        List jobNames = getJobNames(serviceTimedTaskParam);
        if (jobNames == null || jobNames.isEmpty()) {
            return PageResult.of(null, 0L);
        }
        PageHelper.startPage(serviceTimedTaskParam.getPageNum(), serviceTimedTaskParam.getPageSize());
        List<String> uuIdList = statCatcherDAO.listUUIdByConditions4Analysis(jobNames, serviceTimedTaskParam.getTimeInterval());
        if (uuIdList == null || uuIdList.isEmpty()) {
            return PageResult.of(null, 0L);
        }
        // 获取数据总数
        PageInfo<String> uuIdListPageInfo = new PageInfo<String>(uuIdList);
        // 根据miFlowIdList查询相应的请求和响应的日志结果集
        List<StatCatcherDO> statCatcherDOList = statCatcherDAO.listStatCatchersByUUIdList4Analysis(uuIdList);
        // 将查询的结果集进行聚合转换，即将同属于一次的请求和响应日志聚合为一条记录
        List<JobLogDTO> jobLogDTOList = convertToJobLogDTOList(statCatcherDOList);
        return PageResult.of(jobLogDTOList, uuIdListPageInfo.getTotal());
    }

    @Override
    public void unInstallService(ServiceStatusActionParam serviceStatusActionParam) {
        if (serviceStatusActionParam.getFileType().equals("jar")) {
            bundleService.uninstallBundleByID(serviceStatusActionParam.getBundleId());
        } else {
            karService.uninstallKar(serviceStatusActionParam.getFileName());
        }
        // 删除服务临时存储文件夹中的服务文件
        if (!SERVICE_DELETE_FILE) {
            // 文件上传路径
            String filePath = getServicesTemp(SERVICE_DIR);
            File appTempFile = new File(filePath + File.separator + serviceStatusActionParam.getFileName() + "." + serviceStatusActionParam.getFileType());
            appTempFile.delete();
        }
        serviceDAO.removeService(serviceStatusActionParam.getId());
        // 卸载服务时把ip控制、流量控制、频次控制相关信息删掉
        if (serviceStatusActionParam.getServiceType().equals("restful_ws") || serviceStatusActionParam.getServiceType().equals("soap_ws") || serviceStatusActionParam.getServiceType().equals("proxy_route")) {
            ipControlService.removeIPControl(new AC4IPStatusActionParam(serviceStatusActionParam.getId(), serviceStatusActionParam.getServiceKey()));
            frequencyControlService.removeFrequencyControl(new AC4FrequencyStatusActionParam(serviceStatusActionParam.getId(), serviceStatusActionParam.getServiceKey()));
            flowControlService.removeFlowControl(new AC4FlowStatusActionParam(serviceStatusActionParam.getId(), serviceStatusActionParam.getServiceKey()));
        }
    }

    @Override
    public void startService(ServiceStatusActionParam serviceStatusActionParam) {
        if (serviceStatusActionParam.getFileType().equals("jar")) {
            bundleService.startBundleByID(serviceStatusActionParam.getBundleId());
        } else {
            List<String> bundleNames = serviceStatusActionParam.getBundleName();
            for (String name : bundleNames) {
                bundleService.startBundleByMVNName(name);
            }
        }
    }

    @Override
    public void stopService(ServiceStatusActionParam serviceStatusActionParam) {
        if (serviceStatusActionParam.getFileType().equals("jar")) {
            bundleService.stopBundleByID(serviceStatusActionParam.getBundleId());
        } else {
            List<String> bundleNames = serviceStatusActionParam.getBundleName();
            for (String name : bundleNames) {
                bundleService.stopBundleByMVNName(name);
            }
        }
    }

    /**
     * 合并数据
     *
     * @param targetList 目标数据集
     * @param sourceList 源数据集
     */
    private void mergeData(List<APICallAnalysisTableDTO> targetList, List<APICallAnalysisTableDTO> sourceList, String fieldName) {
        Map<String, APICallAnalysisTableDTO> targetMap = targetList.stream()
                .collect(Collectors.toMap(APICallAnalysisTableDTO::getConsumerIP, Function.identity()));

        for (APICallAnalysisTableDTO sourceData : sourceList) {
            String consumerIP = sourceData.getConsumerIP();
            if (targetMap.containsKey(consumerIP)) {
                APICallAnalysisTableDTO targetData = targetMap.get(consumerIP);
                // 进行属性合并操作
                switch (fieldName) {
                    case "callFailureTotal":
                        targetData.setCallFailureTotal(sourceData.getCallFailureTotal());
                        break;
                    case "callFailureTotal4Frequency":
                        targetData.setCallFailureTotal4Frequency(sourceData.getCallFailureTotal4Frequency());
                        break;
                    case "callFailureTotal4Flow":
                        targetData.setCallFailureTotal4Flow(sourceData.getCallFailureTotal4Flow());
                        break;
                    case "callFailureTotal4IP":
                        targetData.setCallFailureTotal4IP(sourceData.getCallFailureTotal4IP());
                        break;
                    case "avgResponseTime":
                        targetData.setAvgResponseTime(sourceData.getAvgResponseTime());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 获取任务名称的集合
     *
     * @param serviceTimedTaskParam
     * @return
     */
    private List getJobNames(ServiceTimedTaskParam serviceTimedTaskParam) {
        List<String> jobNames = new ArrayList<>();
        // 条件参数  任务名为null时，获取该定时路由的所有任务名集合
        if (StringUtil.isEmpty(serviceTimedTaskParam.getTaskName())) {
            String timedTask = serviceDAO.getTaskConsumptionTotal(serviceTimedTaskParam.getId());
            jobNames = JSONObject.parseObject(timedTask, new TypeReference<ArrayList<String>>() {
            });
        } else {
            jobNames.add(serviceTimedTaskParam.getTaskName());
        }

        return jobNames;
    }

    /**
     * 将 statCatcherDOList 转换为 jobLogDTOList
     *
     * @param statCatcherDOList 原始数据
     * @return
     */
    private List<JobLogDTO> convertToJobLogDTOList(List<StatCatcherDO> statCatcherDOList) {
        Map<String, List<StatCatcherDTO>> groupedData = new HashMap<>();

        // 按照uuid对statCatcherDOList，分组
        for (StatCatcherDO statCatcherDO : statCatcherDOList) {
            groupedData.computeIfAbsent(statCatcherDO.getUuid(), k -> new ArrayList<>()).add(setStatCatcherRecord.do2dto(statCatcherDO));
        }

        List<JobLogDTO> jobLogDTOList = new ArrayList<>();

        // 将分组数据转换为JobLogDTO
        for (Map.Entry<String, List<StatCatcherDTO>> entry : groupedData.entrySet()) {
            List<StatCatcherDTO> group = entry.getValue();
            JobLogDTO jobLogDTO = new JobLogDTO();
            jobLogDTO.setUuid(entry.getKey());

            long maxDuration = 0;
            boolean hasFailure = false;
            LocalDateTime minMoment = null;
            String job = null;

            for (StatCatcherDTO statCatcherDTO : group) {
                maxDuration = Math.max(maxDuration, statCatcherDTO.getDuration() == null ? 0 : statCatcherDTO.getDuration());
                hasFailure = setHasFailure(statCatcherDTO);
                minMoment = setMinMoment(statCatcherDTO, minMoment);
                job = setJob(statCatcherDTO, job);
            }

            jobLogDTO.setTime(maxDuration);
            jobLogDTO.setStatus(hasFailure ? Constants.FAILURE : Constants.SUCCESS);
            jobLogDTO.setExecutTime(minMoment);
            jobLogDTO.setJob(job);

            jobLogDTOList.add(jobLogDTO);
        }

        // 重新排序，在分组时会将此页内的顺序打乱，所以要重新按时间进行排序
        // 页内升序
//        Collections.sort(jobLogDTOList, Comparator.comparing(JobLogDTO::getExecutTime, (s1, s2) -> s1.compareTo(s2)));
        // 页内降序
        Collections.sort(jobLogDTOList, Comparator.comparing(JobLogDTO::getExecutTime, (s1, s2) -> s2.compareTo(s1)));
        return jobLogDTOList;
    }

    /**
     * 设置是否为失败状态
     *
     * @param statCatcherDTO
     * @return
     */
    private boolean setHasFailure(StatCatcherDTO statCatcherDTO) {
        return Constants.FAILURE.equals(statCatcherDTO.getMessage());
    }

    /**
     * 设置最小执行时间
     *
     * @param statCatcherDTO
     * @param minMoment
     * @return
     */
    private LocalDateTime setMinMoment(StatCatcherDTO statCatcherDTO, LocalDateTime minMoment) {
        return minMoment == null || statCatcherDTO.getMoment().isBefore(minMoment) ? statCatcherDTO.getMoment() : minMoment;
    }

    /**
     * 设置job
     *
     * @param statCatcherDTO
     * @param job
     * @return
     */
    private String setJob(StatCatcherDTO statCatcherDTO, String job) {
        return job == null && isSamePid(statCatcherDTO) ? statCatcherDTO.getJob() : job;
    }

    /**
     * 比较当前记录 pid fatherPid rootPid 三个值是否相等，
     * 如果相等则使用此条记录的job赋值为当前分组JobLogDTO的job
     *
     * @param statCatcherDTO 一条基本记录信息
     * @return
     */
    private boolean isSamePid(StatCatcherDTO statCatcherDTO) {
        return statCatcherDTO.getPid().equals(statCatcherDTO.getFatherPid()) && statCatcherDTO.getFatherPid().equals(statCatcherDTO.getRootPid());
    }

    /**
     * 获取JAR服务对应的bundles
     *
     * @param allListBundles
     * @param bundleId
     * @return
     */
    private ArrayList<BundleInfo> getBundles4JAR(List<BundleInfo> allListBundles, Long bundleId) {
        ArrayList<BundleInfo> listBundles = new ArrayList<>();
        for (BundleInfo bundle : allListBundles) {
            if (bundleId.equals(bundle.getIdentifier())) {
                listBundles.add(bundle);
            }
        }
        return listBundles;
    }

    /**
     * 获取KAR服务对应的bundles
     *
     * @param allListBundles
     * @param bundleNames
     * @return
     */
    private ArrayList<BundleInfo> getBundles4KAR(List<BundleInfo> allListBundles, List<String> bundleNames) {
        ArrayList<BundleInfo> listBundles = new ArrayList<>();
        for (BundleInfo bundle : allListBundles) {
            for (String name : bundleNames) {
                if (name.equals(bundle.getUpdateLocation())) {
                    listBundles.add(bundle);
                }
            }
        }
        return listBundles;
    }

}
