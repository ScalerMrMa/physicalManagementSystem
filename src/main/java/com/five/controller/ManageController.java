package com.five.controller;

import com.five.dao.DepartmentDao;
import com.five.dao.InnerUserDao;
import com.five.dao.OrdinaryUserDao;
import com.five.dao.ReserveDao;
import com.five.domain.*;
import com.five.service.*;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @Autowired
    private InnerUserService innerUserService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private NotionService notionService;

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private InnerUserDao innerUserDao;

    @Autowired
    private OrdinaryUserDao ordinaryUserDao;
    // ---------------------------------------查询专区-------------------------------------

    /**
     * @description 查询普通用户
     * @return  返回用户，此用户是普通用户
     */
    @RequestMapping("/getOrdinaryUsers")
    @ResponseBody
    public DataVo<LoginUser> getNormalUser(String loginUsername) {
        return ordinaryUserService.getNormalUsers(loginUsername);
    }

    /**
     * 查询内部员工西信息
     * @param innerUserName
     * @return
     */
    @RequestMapping("/getInnerUsers")
    @ResponseBody
    public DataVo<InnerUser> getInnerUser(String innerUserName) {

        return innerUserService.getInnerUsers(innerUserName);
    }

    /**
     * 查询科室信息
     * @return
     */
    @RequestMapping("/getDepartments")
    @ResponseBody
    public DataVo<Department> getDepartments() {

        return departmentService.getDepartments();
    }

    /**
     * 获取公告
     * @param notionTitle
     * @return
     */
    @RequestMapping("/getNotions")
    @ResponseBody
    public DataVo<Notion> getNotions(String notionTitle) {

        return notionService.getNotions(notionTitle);
    }

    /**
     * 获取物资信息
     */
    @RequestMapping("/getMaterial")
    @ResponseBody
    public DataVo<Material> getMaterials(String materialName){
        return materialService.getMaterial(materialName);
    }

    // ---------------------------------------禁用专区-------------------------------------
    /**
     * 此删除不是真正意义上的删除
     */

    /**
     * 根据用户的Id禁用用户
     * @param userId
     * @return
     */
    @RequestMapping("/forbidUser")
    @ResponseBody
    public ResultVo forbidUser(@RequestParam("loginId") String userId) {

        return ordinaryUserService.forbidNormalUser(userId);
    }

    /**
     * 根据id禁用工作人员
     * @param innerUserId
     * @return
     */
    @RequestMapping("/forbidInnerUser")
    @ResponseBody
    public ResultVo forbidInnerUser(@RequestParam("innerUserId") String innerUserId) {
        return innerUserService.forbidInnerUser(innerUserId);
    }

    /**
     * 根据id激活工作人员
     * @param innerUserId
     * @return
     */
    @RequestMapping("/activeInnerUser")
    @ResponseBody
    public ResultVo activeInnerUser(@RequestParam("innerUserId") String innerUserId) {
        return innerUserService.activeInnerUser(innerUserId);
    }

    /**
     * 激活用户
     * @param userId
     * @return
     */
    @RequestMapping("/activeUser")
    @ResponseBody
    public ResultVo activeUser(@RequestParam("loginId") String userId) {

        return ordinaryUserService.activeNormalUser(userId);
    }

    /**
     * 批量禁用工作人员
     * @param innerUserIds
     * @return
     */
    @RequestMapping("/forbidInnerUsers")
    @ResponseBody
    public ResultVo forbidInnerUsers(@RequestParam("innerUserId") List<String> innerUserIds) {

        return innerUserService.forbidInnerUsers(innerUserIds);
    }

    /**
     * 禁用科室
     * @param forbidDepartmentId
     * @return
     */
    @RequestMapping("/forbidDepartment")
    @ResponseBody
    public ResultVo forbidDepartmentId(@RequestParam("departmentId") String forbidDepartmentId) {
        return departmentService.forbidDepartment(forbidDepartmentId);
    }

    @RequestMapping("/expireNotion")
    @ResponseBody
    public ResultVo expireNotion(@RequestParam("notionId") String notionId) {
        return notionService.expireNotion(notionId);
    }

    /**
     * 批量设置公告为失效
     * @param notionIds
     * @return
     */
    @RequestMapping("/expireNotions")
    @ResponseBody
    public ResultVo expireNotions(@RequestParam("notionIds") List<String> notionIds) {

        return notionService.expireNotions(notionIds);
    }

    /**
     * 禁用物资
     * @param materialId
     * @return
     */
    @RequestMapping("/forbidMaterial")
    @ResponseBody
    public ResultVo forbidMaterial(@RequestParam("materialId") String materialId) {
        System.out.println(materialId);
        return  materialService.forbidMaterial(materialId);
    }

    /**
     * 启用物资
     * @param materialId
     * @return
     */
    @RequestMapping("/activeMaterial")
    @ResponseBody
    public ResultVo activeMaterial(@RequestParam("materialId") String materialId) {

        return  materialService.activeMaterial(materialId);
    }
    // --------------------------------------------添加专区----------------------------------------

    /**
     * 添加员工的信息
     * @param innerUser
     * @return
     */
    @RequestMapping("/addInnerUserInfo")
    @ResponseBody
    public ResultVo addInnerUserInfo(InnerUser innerUser) {

        return innerUserService.addInnerUserInfo(innerUser);
    }

    /**
     * 增添科室信息
     * @param department
     * @return
     */
    @RequestMapping("/addDepartment")
    @ResponseBody
    public ResultVo addDepartment(Department department) {

        return departmentService.addDepartment(department);
    }

    /**
     * 添加公告信息
     * @param notion
     * @return
     */
    @RequestMapping("/addNotion")
    @ResponseBody
    public ResultVo addNotion(Notion notion) {
        return notionService.addNotion(notion);
    }

    /**
     * 添加物资信息
     * @param material
     * @return
     */
    @RequestMapping("/addMaterial")
    @ResponseBody
    public ResultVo addMaterial(Material material) {
        return materialService.addMaterial(material);
    }
    // ---------------------------------------------修改专区-------------------------------------------------

    /**
     * 修改用户信息
     * @param innerUser
     * @return
     */
    @RequestMapping("/updateInnerUserInfo")
    @ResponseBody
    public ResultVo updateInnerUserInfo(InnerUser innerUser) {
        return innerUserService.updateInnerUserInfo(innerUser);
    }

    /**
     * 修改员工密码
     * @param innerUser
     * @return
     */
    @RequestMapping("/updateInnerUserSecret")
    @ResponseBody
    public ResultVo updateInnerUserSecret(InnerUser innerUser) {
        System.out.println(innerUser);
        return innerUserService.updateInnerUserSecret(innerUser);
    }

    /**
     * 修改科室的信息
     * @param department
     * @return
     */
    @RequestMapping("/updateDepartment")
    @ResponseBody
    public ResultVo updateDepartment(Department department) {
        return departmentService.updateDepartment(department);
    }

    /**
     * 修改公告的信息
     * @param notion
     * @return
     */
    @RequestMapping("/updateNotion")
    @ResponseBody
    public ResultVo updateNotion(Notion notion) {
        return notionService.updateNotion(notion);
    }

    /**
     * 购入物资
     * @param
     * @return
     */
    @RequestMapping("/shopMaterial")
    @ResponseBody
    public ResultVo shopMaterial(Material material) {
        System.out.println(material);
        return materialService.shopMaterial(material);
    }

    // -------------------------------------------其它功能区-------------------------------------

    /**
     * 获取管理员的信息
     * @param httpSession
     * @return
     */
    @GetMapping("/showInfo")
    public ModelAndView toShowInfo(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("admin/showInfo");
        InnerUser managerInfo = innerUserService.getManagerInfo(httpSession);
        modelAndView.addObject("innerUserNo", managerInfo.getInnerUserNo());
        // 添加其他变量
        modelAndView.addObject("innerUserName", managerInfo.getInnerUserName());
        modelAndView.addObject("innerUserGender", managerInfo.getInnerUserGender());
        modelAndView.addObject("innerUserEmail", managerInfo.getInnerUserEmail());
        modelAndView.addObject("innerUserPhone", managerInfo.getInnerUserPhone());
        modelAndView.addObject("innerUserDepartment", managerInfo.getInnerUserDepartment());
        modelAndView.addObject("innerUserRole", managerInfo.getInnerUserRole());

        return modelAndView;
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("/homepage")
    public ModelAndView toHomePage() {
        ModelAndView modelAndView = new ModelAndView("admin/homePage");
        // 获取数量
        Integer normalUserCounts = ordinaryUserService.getNormalUserCounts();
        Integer innerUserCount = innerUserService.getInnerUserCount();
        Integer departmentCount = departmentService.getDepartmentCount();
        Integer reserveCount = reserveDao.selectCount(null);

        modelAndView.addObject("normalUserCount", normalUserCounts);
        modelAndView.addObject("innerUserCount", innerUserCount);
        modelAndView.addObject("departmentCount", departmentCount);
        modelAndView.addObject("reserveCount", reserveCount);
        return modelAndView;
    }


    /**
     * 修改管理员个人信息
     */
    @GetMapping("/setting")
    public ModelAndView toSetting(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("admin/setting");
        InnerUser managerInfo = innerUserService.getManagerInfo(httpSession);
        // 添加其他变量
        modelAndView.addObject("innerUserName", managerInfo.getInnerUserName());
        modelAndView.addObject("innerUserGender", managerInfo.getInnerUserGender());
        modelAndView.addObject("innerUserEmail", managerInfo.getInnerUserEmail());
        modelAndView.addObject("innerUserPhone", managerInfo.getInnerUserPhone());
        return modelAndView;
    }

    /**
     * 修改个人信息
     * @param innerUser
     * @param httpSession
     * @return
     */
    @RequestMapping("/updatePerson")
    @ResponseBody
    public ResultVo updatePerson(InnerUser innerUser, HttpSession httpSession) {
        return innerUserService.updatePerson(innerUser, httpSession);
    }

    /**
     * 修改密码
     * @param confirmPwd
     * @return
     */
    @RequestMapping("/confirmPwd")
    public ResultVo updatePassword(String confirmPwd, HttpSession httpSession){

        return innerUserService.confirmPwd(confirmPwd, httpSession);
    }

    /**
     * 数据展示用户订单数量
     * @return
     */
    @RequestMapping("/ECharting")
    @ResponseBody
    public Map<String, Object> viewData() {
        // 分别获取相应的数据，此处数据大部分为2023，只简单处理
        Integer ordinaryUserCounts = ordinaryUserDao.selectCount(null);   // 普通用户
        Integer innerUserCounts = innerUserDao.selectCount(null);         // 员工
        Integer departmentCounts = departmentDao.selectCount(null);       // 部门
        Integer reserveCount = reserveDao.selectCount(null);              // 订单

        // 创建存放数据的集合，构建初始数据
        String[][] source = new String[5][5];
        source[0] = new String[]{"date","2020","2021","2022","2023"};
        source[1] = new String[]{"普通用户","12","15","16", String.valueOf(ordinaryUserCounts)};
        source[2] = new String[]{"员工","5","4","7", String.valueOf(innerUserCounts)};
        source[3] = new String[]{"科室","3","2","4", String.valueOf(departmentCounts)};
        source[4] = new String[]{"订单","10","11","15", String.valueOf(reserveCount)};

        // 创建包含数据的Map对象
        Map<String, Object> response = new HashMap<>();
        response.put("dataset", source);

        return response;
    }

}
