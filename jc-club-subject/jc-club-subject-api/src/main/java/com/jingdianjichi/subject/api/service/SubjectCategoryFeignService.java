package com.jingdianjichi.subject.api.service;

import com.jingdianjichi.subject.api.req.SubjectCategoryDTO;
import com.jingdianjichi.subject.api.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/9 下午1:02
 */
@Service
@FeignClient(value = "jc-club-subject-dev", contextId = "subjectCategoryFeignService")
public interface SubjectCategoryFeignService {

    /**
     * 查询岗位信息，即顶层题目分类( parentId 为 0 的分类 )
     * <p>
     * 通过调用主题分类领域服务查询所有岗位信息，并将查询结果转换为DTO形式返回。
     * 此接口用于前端获取岗位信息，以展示在相应的界面中。
     *
     * @return Result<List < SubjectCategoryDTO>> 返回查询结果，包含岗位信息的列表。
     */
    @RequestMapping("/subject/category/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory();

    /**
     * 查询大类下的分类
     * @param dto categoryType 分类类型
     *            parentId 父级分类id
     * @return
     */
    @PostMapping("/subject/category/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO dto);

    /**
     * 根据传入的 id 查询当前分类下的子分类集合，并且每个子分类需装载其关联的标签集合
     * @param categoryDTO id 待查询的岗位 id
     * @return 子分类列表及与子分类关联的标签列表
     */
    @PostMapping("/subject/category/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO categoryDTO);

    /**
     * 根据标签id批量查询分类
     * @param dto labelIdList
     * @return
     */
    @PostMapping("/subject/category/queryByLabelIds")
    public Result<List<SubjectCategoryDTO>> queryByLabelIds(@RequestBody SubjectCategoryDTO dto);

    /**
     * 根据传入的次级分类名 id 查询此次级分类所属的岗位
     * @param dto id
     * @return
     */
    @PostMapping("/subject/category/queryPrimaryCategoryByCategoryId")
    public Result<SubjectCategoryDTO> queryPrimaryCategoryByCategoryId(@RequestBody SubjectCategoryDTO dto);

    /**
     * 根据分类id批量查询分类
     * @param dto idList
     * @return
     */
    @PostMapping("/subject/category/queryByIdList")
    public Result<List<SubjectCategoryDTO>> queryByIdList(@RequestBody SubjectCategoryDTO dto);
}
