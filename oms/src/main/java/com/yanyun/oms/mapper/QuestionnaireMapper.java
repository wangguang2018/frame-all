package com.yanyun.oms.mapper;
import com.yanyun.oms.dto.QuestionnaireDto;
import com.yanyun.oms.entity.Questionnaire;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * Mapper - 问卷调查
 *
 * @author zhaoziyu
 * @since 2018-02-27
 */
public interface QuestionnaireMapper {

    /**
     * 新建问卷调查
     *
     * @param questionnaire 问卷调查
     */
    void insert(Questionnaire questionnaire);

    /**
     * 更新问卷调查
     *
     * @param questionnaire 问卷调查
     */
    void update(Questionnaire questionnaire);

    /**
     * 删除问卷调查
     *
     * @param id 编号
     * @return 删除数量
     */
    int delete(Integer id);

    /**
     * 删除问卷调查
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 查询问卷调查
     *
     * @param id 编号
     * @return 问卷调查
     */
    QuestionnaireDto findById(Integer id);

    /**
     * 查询问卷调查
     *
     * @return 问卷调查集合
     */
    List<Questionnaire> findAll();

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);

}